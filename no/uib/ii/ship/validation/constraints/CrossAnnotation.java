/*
 * Copyright (c) University of Bergen
 *
 *
 * Authors: Dag Hovland and Federico Mancini, SHIP, Dept. of Informatics
 */
package no.uib.ii.ship.validation.constraints;

import java.lang.annotation.Annotation;
import java.util.List;

import no.uib.ii.ship.validation.annotations.cross.CrossValidation;
import no.uib.ii.ship.validation.annotations.cross.ICrossTester;
import no.uib.ii.ship.validation.annotations.cross.property.AllProperty;
import no.uib.ii.ship.validation.annotations.cross.property.CrossOperator;
import no.uib.ii.ship.validation.annotations.cross.property.CrossProperty;

/**
 * The Class CrossAnnotation.
 * Represents a Cross-Annotation, and the cross-test represented by it.
 * These are stored in the List in a CrossTest object, and
 * contain the runTest method, called from "runCrossTest" in CrossTest.
 *
 * @author Dag Hovland and Federico Mancini
 */
@SuppressWarnings("unchecked")
public class CrossAnnotation extends AnnotationObject<ICrossTester,List<Object>,List<Object>>{

	/**
	 * The Constructor. Private because createCrossAnnotation should be used in stead
	 *
	 * @param childConstrs the list of annotations on this annotation
	 * @param parent The annotation which is the parent of the annotations in theThis is null in the root case
	 */
	private CrossAnnotation(List<Annotation> childConstrs, Annotation parent) {
		super(childConstrs, "cross constraint", parent);
	}



	/* This will be set to Validation.class or CrossValidation.class */
	/* (non-Javadoc)
	 * @see no.uib.ii.ship.validation.PropertyAnnotation#getConstraintType()
	 */
	@Override
	Class<? extends Annotation> getConstraintType(){
		return CrossValidation.class;
	}

	/* This is set to IPropertyTester.class or ICrossTester.class in constructor
	 * and used by the method hasTesterInterface

	 * @see no.uib.ii.ship.validation.PropertyAnnotation#getTestInterface()
	 */
	@Override
	public Class<ICrossTester> getTestInterface(){
		return ICrossTester.class;
	}

	/* (non-Javadoc)
	 * @see no.uib.ii.ship.validation.PropertyAnnotation#createChild(no.uib.ii.ship.validation.MetaConstraints, java.lang.annotation.Annotation)
	 */
	@Override
	public AnnotationObject<?,List<Object>,?> createChild(MetaConstraints childConstrs, Annotation ann){
		return CrossAnnotation.createCrossAnnotation(childConstrs, ann);
	}

	/**
	 * @param childConstrs
	 * @param ann
	 * @return CrossAnnotation or CrossPropertyAnnotation
	 */
	public static AnnotationObject<?,List<Object>,?> createCrossAnnotation(MetaConstraints childConstrs, Annotation ann){
		List<Annotation> grandChildConstrs = CrossAnnotation.getMetaAnnotations(childConstrs, ann);
		if(CrossAnnotation.isSpecialCrossAnnotation(ann))
			return new CrossPropertyAnnotation(grandChildConstrs, ann);
		else
			return new CrossAnnotation(grandChildConstrs, ann);
	}


	/**
	 * @param ann
	 * @return true if ann is CrossProperty, CrossOperator or AllProperty
	 */
	public static boolean isSpecialCrossAnnotation(Annotation ann){
		return ( ann.annotationType().isAnnotationPresent(CrossProperty.class) ||
				ann.annotationType().isAnnotationPresent(CrossOperator.class) ||
				ann.annotationType().isAnnotationPresent(AllProperty.class));
	}


	/**
	 * @see no.uib.ii.ship.validation.constraints.PropertyAnnotation#createChild(no.uib.ii.ship.validation.constraints.MetaConstraints, java.lang.annotation.Annotation)
	 * @param childConstrs
	 * @param ann
	 * @return The property-annotations on the annotation ann if it is a "special" cross annotation, otherwise the cross-annotations
	 */
	public static List<Annotation> getMetaAnnotations(MetaConstraints childConstrs, Annotation ann){
		if(CrossAnnotation.isSpecialCrossAnnotation(ann)){
			return childConstrs.getMethodConstraints();
		} else {
			return childConstrs.getCrossConstraints();
		}
	}




	/* (non-Javadoc)
	 * @see no.uib.ii.ship.validation.AnnotationObject#getInnerTesterName()
	 */
	@Override
	public String getInnerTesterName() {
		return "runTest";
	}
}

