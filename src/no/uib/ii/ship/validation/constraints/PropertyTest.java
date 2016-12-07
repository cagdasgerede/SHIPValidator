/*
 * Copyright (c) University of Bergen
 *
 *
 * Authors: Dag Hovland and Federico Mancini, SHIP, Dept. of Informatics
 */
package no.uib.ii.ship.validation.constraints;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import no.uib.ii.ship.validation.ValidationException;
import no.uib.ii.ship.validation.annotations.method.NotRequired;
import no.uib.ii.ship.validation.annotations.method.Required;
import no.uib.ii.ship.validation.summary.ISummary;


/**
 * Represents all tests on a single property. An object of this class is created for each
 * no-arg method (in the object passed to the Validator) which is annotated
 * with at least one property-annotation (those with a "@Validation" marker)
 *
 * @author Dag Hovland and Federico Mancini
 */
public class PropertyTest {

	/** The test tree. */
	private PropertyAnnotation testTree;

	/** The return value. */
	private Object returnValue;

	/** The met. */
	private Method met;

//	/** The constr. */
//	private MetaConstraints constr;

	/**
	 * Instantiates a new property test.
	 *
	 * @param met the met
	 * @param obj the obj
	 * @param constr the constr
	 */
	public PropertyTest(Method met, Object obj, MetaConstraints constr) {
		this.met = met;
		try{
			this.returnValue = met.invoke(obj);
		} catch (IllegalAccessException e){
			throw new ValidationException("Serious error in validate. A method returned in getMethods() in an object's class could not be called. This should not be possible, only public methods should have been returned.", e);
		} catch (InvocationTargetException e){
			throw new ValidationException("Serious error in validate. A method in an objects class could not be called. This should not be possible!", e);
		} catch (IllegalArgumentException e){
			throw new ValidationException("It seems the method \"" + met + "\" with non-empty parameter list is given validation annotations. This is not allowed", e);
		}
		this.testTree = new PropertyAnnotation(getValueConstraints(constr), met.getName(), null);

	}

	/**
	 * Gets the return value.
	 *
	 * @return the return value
	 */
	public Object getReturnValue(){
		return returnValue;
	}

	/**
	 * Run test.
	 *
	 * @return the method summary
	 */
	public ISummary runTest(){
		return  this.testTree.runRecTest(returnValue);
	}

	/**
	 * Gets the method constraints according to return value. If null, only the @Required and @NoptRequired annotations are considered.
	 * @param constr All annotations which represents a validation test on the current method
	 *
	 * @return the method constraints
	 */
	public List<Annotation> getValueConstraints(MetaConstraints constr){
		if (returnValue == null){
			boolean hasRequired = met.isAnnotationPresent(Required.class);
			boolean hasNotRequired = met.isAnnotationPresent(NotRequired.class);
			if(hasRequired || hasNotRequired){
				Class<? extends Annotation> klazz = hasRequired ? Required.class : NotRequired.class;
				Annotation nullTestAnnot = (Annotation) met.getAnnotation(klazz);
				return Arrays.asList(nullTestAnnot);
			}
		}
		return constr.getMethodConstraints();
	}

}


