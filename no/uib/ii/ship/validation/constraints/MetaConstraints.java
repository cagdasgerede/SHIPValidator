/*
 * Copyright (c) University of Bergen
 *
 *
 * Authors: Dag Hovland and Federico Mancini, SHIP, Dept. of Informatics
 */
package no.uib.ii.ship.validation.constraints;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.ArrayList;
import java.util.List;

import no.uib.ii.ship.validation.annotations.cross.CrossValidation;
import no.uib.ii.ship.validation.annotations.method.Validation;

// TODO: Auto-generated Javadoc
/**
 * The Class MetaConstraints.
 *
 * @author Dag Hovland and Federico Mancini
 */
public class MetaConstraints {

	/** The constraints. */
	private List<Annotation> constraints;

	/** The cross_constraints. */
	private List<Annotation> cross_constraints;

	/** The has constraints. */
	private boolean hasConstraints;

	/**
	 * Instantiates a new meta constraints.
	 *
	 * @param met the met
	 */
	public MetaConstraints(AnnotatedElement met) {
		super();
		constraints = new ArrayList<Annotation>();
		cross_constraints = new ArrayList<Annotation>();

		for (Annotation ann : met.getAnnotations()){
			if(ann.annotationType().isAnnotationPresent(Validation.class))
				constraints.add(ann);
			if(ann.annotationType().isAnnotationPresent(CrossValidation.class)){
				cross_constraints.add(ann);
			}
		}
		this.hasConstraints = false;
		if(! (constraints.isEmpty() && cross_constraints.isEmpty()) )
			this.hasConstraints = true;

	}

	/**
	 * Checks for constraints.
	 *
	 * @return true, if successful
	 */
	public boolean hasConstraints(){
		return hasConstraints;
	}

	/**
	 * Gets the method constraints.
	 *
	 * @return the method constraints
	 */
	public List<Annotation> getMethodConstraints(){
		return constraints;
	}

	/**
	 * Gets the cross constraints.
	 *
	 * @return the cross constraints
	 */
	public List<Annotation> getCrossConstraints(){
		return cross_constraints;
	}


}


