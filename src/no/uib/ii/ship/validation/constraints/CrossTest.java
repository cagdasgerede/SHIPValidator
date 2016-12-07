/*
 * Copyright (c) University of Bergen
 *
 *
 * Authors: Dag Hovland and Federico Mancini, SHIP, Dept. of Informatics
 */
package no.uib.ii.ship.validation.constraints;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import no.uib.ii.ship.validation.summary.ISummary;


/**
 * The Class CrossTest.
 * Maintains a list of all values that should be
 * tested on a given cross-test. One CrossTest is instantiated
 * for each CrossAnnotation occurring on method-level in the validated object
 *
 * @author Federico Mancini and Dag Hovland
 */
public class CrossTest {

	/** The test tree. */
	private AnnotationObject<?, List<Object>, ?> testTree;

	/** The return values. */
	private List<Object> returnValues;

	/**
	 * Instantiates a new cross test.
	 * @param ann
	 */
	public CrossTest(Annotation ann) {
		returnValues = new ArrayList<Object>();
		this.testTree = CrossAnnotation.createCrossAnnotation(new MetaConstraints(ann.annotationType()), ann);
	}


	/**
	 * Run test.
	 *
	 * @return the summary node
	 */
	ISummary runTest(){
		return this.testTree.runRecTest(returnValues);
	}


	/**
	 * Adds the.
	 *
	 * @param ann the ann
	 * @param returnValue the return value
	 */
	void add(Annotation ann,Object returnValue){
		this.returnValues.add(returnValue);
	}


}


