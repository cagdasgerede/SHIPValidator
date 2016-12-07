/*
 * Copyright (c) University of Bergen
 *
 *
 * Authors: Dag Hovland and Federico Mancini, SHIP, Dept. of Informatics
 */
package no.uib.ii.ship.validation.constraints;

import java.lang.annotation.Annotation;
import java.util.List;

import no.uib.ii.ship.validation.annotations.method.IPropertyTester;
import no.uib.ii.ship.validation.annotations.method.Validation;

/**
 * The Class PropertyAnnotation.
 */
@SuppressWarnings("unchecked")
public class PropertyAnnotation extends AnnotationObject<IPropertyTester,Object,Object> {

	/**
	 * The Constructor.
	 *
	 * @param childConstrs : The set of validation annotations to be tested on the value
	 * @param methodName : The method that the value is taken from and was annotated. Used to give error messages
	 * @param annotation : The parent annotation. In the case of method, this is null,
	 */
	public PropertyAnnotation(List<Annotation> childConstrs, String methodName, Annotation annotation){
		super(childConstrs, methodName, annotation);
	}



	/**
	 * Creates the child.
	 *
	 * @param childConstrs the child constrs
	 * @param ann the ann
	 *
	 * @return the property annotation
	 */
	@Override
	public PropertyAnnotation createChild(MetaConstraints childConstrs, Annotation ann){
		List<Annotation> grandChildConstrs = childConstrs.getMethodConstraints();
		return new PropertyAnnotation(grandChildConstrs, this.getMethodName(), ann);
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
    @Override
	public String toString(){
		if(this.annotationType() != null)
			return this.annotationType().toString();
		else
			return "root PropertyAnnotation on " + this.getMethodName();
	}

	/* This will be set to Validation.class or CrossValidation.class */
	/**
	 * Gets the constraint type.
	 *
	 * @return the constraint type
	 */
	Class<? extends Annotation> getConstraintType(){
		return Validation.class;
	}




	/* This is set to IPropertyTester.class or ICrossTester.class in constructor
	 * and used by the method hasTesterInterface

	 * @return the test interface
	 */
	public Class<IPropertyTester> getTestInterface(){
		return IPropertyTester.class;
	}



	/* (non-Javadoc)
	 * @see no.uib.ii.ship.validation.AnnotationObject#getInnerTesterName()
	 */
	@Override
	public String getInnerTesterName() {
		return"runTest";
	}
}
