// * Copyright (c) University of Bergen
package no.uib.ii.ship.validation.constraints;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import no.uib.ii.ship.validation.IValidator;
import no.uib.ii.ship.validation.IValidatorFactory;
import no.uib.ii.ship.validation.ValidationException;
import no.uib.ii.ship.validation.ValidatorFactory;
import no.uib.ii.ship.validation.annotations.BoolTest;
import no.uib.ii.ship.validation.annotations.BoolType;
import no.uib.ii.ship.validation.annotations.cross.ICrossTester;
import no.uib.ii.ship.validation.annotations.method.IPropertyTester;
import no.uib.ii.ship.validation.annotations.method.ValErr;
import no.uib.ii.ship.validation.annotations.method.Valid;
import no.uib.ii.ship.validation.summary.ISummary;
import no.uib.ii.ship.validation.summary.AnnotationSummary;
import no.uib.ii.ship.validation.summary.MethodSummary;
import no.uib.ii.ship.validation.summary.LeafSummary;

/**
 * This abstract class is extended by all "annotation objects". Each of these,
 * except the root which represents a whole method, is a box for a validation annotation,
 * and provides methods to run the tests represented by the annotation they contain.
 * When an AnnotationObject is created, also an AnnotationObject for each  meta-annotation is created
 * in a recursive fashion, and stored as children.
 * This tree structure is used both to run the tests and create the final summary.
 *
 * @param <T> The interface for the inner class containing basic tests, e.g.,  {@link IPropertyTester} or {@link ICrossTester}
 * @param <U> The class of the objects to be tested by this annotation, e.g. Object for PropertyAnnotation, or List<Object> for CrossAnnotation and CrossPropertyAnnotation.
 * Namely the type of the second parameter of the method {@link IPropertyTester#runTest(Annotation, Object)} and {@link ICrossTester#runTest(Annotation, List)}.
 * @param <V> The class of the objects to be tested by the children of this annotation. This is different from U only for CrossPropertyAnnotation
 *
 * @see #runRecTest(Object)
 */
public abstract class  AnnotationObject<T,U, V> {



	/** The annotation that this object represents. */
	private Annotation annotation;

	/** The name of the method where {@link AnnotationObject#annotation} was retrieved. */
	private String methodName;

	/** The possible custom error message to be used if the test corresponding to {@link AnnotationObject#annotation} fails. */
	private ValErr message;

	/** The list of AnnotationObjects containing the meta-annotations of {@link AnnotationObject#annotation}. */
	private List<AnnotationObject<?,V,?>> childrenList;






	/**
	 * The Constructor. Creates an AnnotationObject for each meta-annotation by calling {@link AnnotationObject#createChildren(List)}.
	 * {@link AnnotationObject#createChildren(List)} calls the abstract method {@link AnnotationObject#createChild(MetaConstraints, Annotation)}
	 *  for creating the AnnotationObject of the correct subclass.
	 *
	 * @param childConstrs : The set of validation annotations to be tested on the return value of the method(s)
	 * @param methodName : The method that the value is taken from and was annotated by {@link AnnotationObject#annotation}. Used to give error messages
	 * @param annotation : The annotation represented by this AnnotationObject. In the case of method, this is null,
	 */
	public AnnotationObject(List<Annotation> childConstrs, String methodName, Annotation annotation){
		super();
		this.methodName = methodName;
		this.childrenList = new ArrayList<AnnotationObject<?,V,?>>();

		this.annotation = annotation;
		if(childConstrs != null)
			this.createChildren(childConstrs);
	}


	/**
	 * Creates the children. For each Annotation in childAnnots (which are marked with @Validation (for PropertyAnnotation and CrossPropertyAnnotation)
	 * or @CrossValidation (for CrossAnnotation)), the meta-annotations are extracted by using MetaConstraints, and createChild is called with this
	 * as argument. createChild is implemented in the subclasses,
	 *
	 * @see #createChild(MetaConstraints, Annotation)
	 * @see MetaConstraints
	 *
	 * @param childAnnots the meta-annotations marked with @Validation (for PropertyAnnotation and CrossPropertyAnnotation)
	 * or @CrossValidation (for CrossAnnotation)
	 */
	public void createChildren(List<Annotation> childAnnots){
		for(Annotation ann : childAnnots){
			if(ann.annotationType() == ValErr.class)
				this.message=((ValErr) ann);
			else {
				MetaConstraints childConstrs = new MetaConstraints(ann.annotationType());
				AnnotationObject<?,V,?> vannChild = (AnnotationObject<?, V, ?>) createChild(childConstrs, ann);
				this.childrenList.add(vannChild);
			}
		}
	}

	/**
	 * Creates the child AnnotationObject representing one meta-annotation.
	 * Called from createChildren for each meta-annotation of correct type on the current annotation.
	 *
	 * @see #createChildren(List)
	 * @see CrossAnnotation#createChild(MetaConstraints, Annotation)
	 * @see PropertyAnnotation#createChild(MetaConstraints, Annotation)
	 * @see CrossPropertyAnnotation#createChild(MetaConstraints, Annotation)
	 *
	 * @param childConstrs the child constrs
	 * @param ann the ann
	 *
	 * @return the property annotation
	 */
	public abstract AnnotationObject<?,V,?> createChild(MetaConstraints childConstrs, Annotation ann);

	/**
	 * Annotation type of the current annotation
	 *
	 * @return the class<? extends annotation>
	 */
	public Class<? extends Annotation> annotationType(){
		if(this.annotation == null)
			return null;
		else
			return annotation.annotationType();
	}

	/**
	 * Returns false if the annotation a is not present, or if the annotation is null
	 * @param a The annotation that is searched for
	 * @return false if the annotation a is not present, or if the annotation is null
	 */
	public boolean isAnnotationPresent(Class<? extends Annotation> a){
		if(this.annotation == null)
			return false;
		else
			return this.annotation.annotationType().isAnnotationPresent(a);
	}

	/**
	 * Recursive method for running tests.
	 * Extracts meta-annotations of all annotations.
	 * If the annotation is a "base case", not put together of others, the corresponding test is run.
	 * Otherwise, the method is called recursively on the validation-meta-annotations on that annotation
	 *
	 * @param value the value
	 *
	 * @return the summary node
	 */

	@SuppressWarnings("unchecked")
	ISummary runRecTest(U value){

		if (this.annotationType() == Valid.class){
			IValidatorFactory vf = new ValidatorFactory();
			IValidator val = vf.getValidator();
			ISummary vs = val.validate(value);
			List<ISummary> cr = new ArrayList<ISummary>();
			cr.add(vs);
			ISummary propertySummary = new AnnotationSummary(this.getSimpleName(), this.getMessage(), cr);
			return propertySummary;
		}
		return testRunner((V) value);
	}


	//public  abstract TestRunner(U1 value);

	/**
	 * @param value The concrete value of the property to be tested
	 * @return A Summary Node of type MethodSummary or AnnotationSummary with the test results
	 */
	@SuppressWarnings("unchecked")
	public ISummary testRunner(V value){
		List<ISummary> cr = new ArrayList<ISummary>();
		BoolType bt = BoolType.AND;
		for(AnnotationObject<?, V,?> ann : this.childrenList){
			Class<? extends Annotation> acls = ann.annotationType();
			if (acls == BoolTest.class){
				assert(this.annotation != null);
				bt = ((BoolTest) ann.getAnnotation()).value();
				continue;
			}
			cr.add(ann.runRecTest((V) value));
		}
		if (this.getInnerTester() != null){
			cr.add(this.runBasicTest((U) value));
		} else {
			if (this.childrenList.isEmpty())
				throw new ValidationException(this.getClass() + ": Annotation " + this.annotation + " on method " + this.methodName
						+ " has neither \n an inner class " + this.getTestInterface() + ", or any meta-annotation representing tests.");
		}
		if(this.annotation==null){
			assert(bt == BoolType.AND);
			return new MethodSummary(
					this.methodName,
					value,
					cr,
					this.getMessage());
		} else
			return new AnnotationSummary(
					this.getSimpleName(),
					this.getMessage(),
					cr,
					bt);
	}

	/**
	 * @return An instance of the class @tester
	 * @param tester The class to be instantiated. Must have a public constructor with no arguments
	 */
	@SuppressWarnings("unchecked")
	public T getTesterObject(Class<T> tester){

		Constructor<?> tester_constr;
		Object tester_object;
		try {
			tester_constr = tester.getConstructor();
		} catch (NoSuchMethodException e){
			throw new ValidationException("Error in runTest: The class " + tester + " is missing the constructor with (no arguments). This must be fixed", e);
		}
		try {
			tester_object = tester_constr.newInstance();
		} catch (InvocationTargetException e){
			throw new ValidationException("Error in Validator.runTest. ", e);
		} catch (InstantiationException e){
			throw new ValidationException("Error in runTest: The constructor " + tester_constr + " cannot be called with no arguments. This is impossible?!", e);
		} catch (IllegalAccessException e){
			throw new ValidationException("Error in runTest: " + tester + " has private empty constructor. Fix this, it must be public", e);
		}

		return (T) tester_object;
	}
	/**
	 * Calls the runTest method.
	 * @param tester_object
	 * @param methodName
	 * @return true if the test succeeds
	 */
	public Method callTest(T tester_object, String methodName){
		Method m = null;
		for(Method m1 : ((T)tester_object).getClass().getDeclaredMethods()){
			if(m1.getName().equals(methodName)){
				Class<?>[] params = m1.getParameterTypes();
				if(params.length == 2&&((methodName.equals("runTest")&& params[0] == this.annotationType())||
						(methodName.equals("op")&&
								(params[0]==params[1] && params[0].isAssignableFrom(m1.getReturnType()) &&
										params[1].isAssignableFrom(m1.getReturnType()))))){
					m = m1;
					break;
				}
			}
		}
		if(m == null)
			throw new ValidationException("Error in runTest: " + tester_object + " does not have correct method " +methodName+". This must be fixed.");
		return m;
	}

	/**
	 * @return The string name of the function in the inner tester, e.g. "op" or "runTest"
	 */
	public abstract String getInnerTesterName();

	/**
	 * This extracts the basic test class from the annotation and tries to run the test
	 * Most of the programmer errors are caught here.
	 *
	 * @param value The value to be tested
	 *
	 * @return the summary node
	 */
	LeafSummary runBasicTest(U value) {

		Class<T> tester = this.getInnerTester();

		T tester_object = this.getTesterObject(tester);
		Method tester_method = this.callTest(tester_object, this.getInnerTesterName());
		try{
			boolean success= (Boolean) tester_method.invoke(tester_object, this.getAnnotation(), value);
			return new LeafSummary(success);
		} catch (IllegalAccessException e){
			throw new ValidationException("Error in runTest: Cannot access method " + tester_method + " in class " + tester + ". Fix this, it must be accessible (public?)", e);
		} catch (InvocationTargetException e){
			if (e.getCause().getClass() == NullPointerException.class){
				throw (NullPointerException) e.getCause();
			}
			throw new ValidationException("There was an error running method:\n" + tester_method + "\nCheck causing exception. It could be that the type of the returned value (" + value + ") from the annotated method:\n" + this.methodName + "\nis different from what the test assumes", e);
		}

	}


	/**
	 * Gets the inner tester.
	 * @return the inner tester
	 */
	@SuppressWarnings("unchecked")
	public Class<T> getInnerTester(){
		if (annotation == null)
			return null;
		for(Class<?> i : annotation.annotationType().getClasses()){
			if(this.getTestInterface().isAssignableFrom(i)){
				return  (Class<T>) i;
			}
		}
		return null;
	}

	/**
	 * Gets the simple name.
	 *
	 * @return the simple name
	 */
	public String getSimpleName(){
		if(this.annotation == null)
			return "null";
		String name = this.annotationType().getSimpleName();
		String longname = this.annotation.toString();
		if (longname.indexOf('(') >= 0)
			return "@"+name+longname.substring(longname.indexOf('('));
		else
			return name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		if(this.annotationType() != null)
			return this.annotationType().toString();
		else
			return "root PropertyAnnotation on " + this.methodName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object va){
		if(!(va instanceof PropertyAnnotation))
			return false;
		return ((PropertyAnnotation) va).getAnnotation().equals(this.annotation);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode(){
		return this.annotation.hashCode();
	}



	/**
	 * Gets the annotation.
	 *
	 * @return the annotation
	 */
	public Annotation getAnnotation() {
		return annotation;
	}

	/* This will be set to Validation.class or CrossValidation.class */
	/**
	 * Gets the constraint type.
	 *
	 * @return the constraint type
	 */
	abstract Class<? extends Annotation> getConstraintType();



	/**
	 * Gets the method name.
	 *
	 * @return the method name
	 */
	protected String getMethodName() {
		return this.methodName;
	}


	/* This is set to IPropertyTester.class or ICrossTester.class in constructor
	 * and used by the method hasTesterInterface
	 */
	/**
	 * Gets the test interface, e.g. "IPropertyTester.class" or "ICrossTester.class"
	 *
	 * @return the test interface
	 */
	public abstract Class<T> getTestInterface();


	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public ValErr getMessage() {
		return message;
	}

	/**
	 * @param anobj
	 */
	public void addChild(AnnotationObject<?,V,?> anobj){
		this.childrenList.add(anobj);
	}
}
