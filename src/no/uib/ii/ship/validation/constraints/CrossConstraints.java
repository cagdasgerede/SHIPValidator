/*
 * Copyright (c) University of Bergen
 *
 *
 * Authors: Dag Hovland and Federico Mancini, SHIP, Dept. of Informatics
 */

package no.uib.ii.ship.validation.constraints;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import no.uib.ii.ship.validation.annotations.method.ValErr;
import no.uib.ii.ship.validation.summary.CrossSummary;
import no.uib.ii.ship.validation.summary.ISummary;


/**
 * The Class CrossConstraints.
 *
 * @author Dag Hovland and Federico Mancini
 *
 * Maintains a map from cross-constraints to lists of values that must be tested
 * This object is created for each object to tested,
 * and is updated for each method in the class of the object which has CrossValidation annotations
 */
public class CrossConstraints {


	/** The cross tests. */
	private Map<Annotation, CrossTest> crossTests;

	/**
	 * Instantiates a new cross constraints.
	 */
	public CrossConstraints() {
		super();
		this.crossTests = new HashMap<Annotation, CrossTest>();
	}

	/**
	 * Adds the constraints.
	 *
	 * @param list the cross_constraints
	 * @param returnValue the return value
	 */
	public void addConstraints(List<Annotation> list, Object returnValue){
		for(Annotation a : list){
			if(a.annotationType() != ValErr.class){
				CrossTest tmp=this.crossTests.get(a);
				if(tmp==null){
					tmp = new CrossTest(a);
				}
				tmp.add(a,returnValue);
				this.crossTests.put(a, tmp);
			}
		}
	}


	/**
	 * Checks if is empty.
	 *
	 * @return true, if is empty
	 */
	public boolean isEmpty(){
		return this.crossTests.isEmpty();
	}

	/**
	 * Key set.
	 *
	 * @return the set< annotation>
	 */
	public Set<Annotation> keySet(){
		return this.crossTests.keySet();
	}

	/**
	 * Run cross tests.
	 *
	 * @return the cross summary
	 */
	public ISummary runCrossTests(){
		List<ISummary> cr = new ArrayList<ISummary>();
		for(CrossTest test : this.crossTests.values()){
			cr.add(test.runTest());
		}
		ISummary n=new CrossSummary(cr);
		return n;
	}





}


