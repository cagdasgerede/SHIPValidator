/*
 * Copyright (c) University of Bergen
 *
 *
 * Authors: Dag Hovland and Federico Mancini, SHIP, Dept. of Informatics
 */

package no.uib.ii.ship.validation.summary;

import java.util.List;

import no.uib.ii.ship.validation.Validator;



/**
 * Class ValidationSummary. This is returned by {@link Validator#validate(Object)}, and should be queried
 * by the client application by calling {@link #getTestResult()} and possibly {@link #toString()}
 *
 * The children are a {@link MethodSummary} for each annotated property in the object and possibly a single {@link CrossSummary} containing all the cross-tests
 */
public class ValidationSummary implements ISummary{


	/** The success. */
	private final boolean testResult;

	/** The methods. This list is non-empty.
	 * They are actually MethodSummary, and at most one is CrossSummary
	 */
	private final List<ISummary> methods;

	/**
	 * Instantiates a new validation summary.
	 * @param methods
	 *
	 */
	public ValidationSummary(List<ISummary> methods) {
		this.methods = methods;
		boolean succ = true;
		for(ISummary sum : methods){
			if (!sum.getTestResult())
				succ = false;
		}
		this.testResult = succ;
	};

	/**
	 * Get the value of success.
	 *
	 * @return the value of success
	 */
	@Override
	public boolean getTestResult ( ) {
		return this.testResult;
	}

	/**
	 * Get the value of methods.
	 *
	 * @return the value of methods
	 */
	public List<ISummary> getChildren ( ) {
		return methods;
	}




	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return toString(0);
	}

	/**
	 * To string.
	 *
	 * @param i the i
	 *
	 * @return the string
	 */
	public String toString(int i){
		String out="";
		if (this.getTestResult())
			out="All tests succeded!!!!!!!";
		else{

			for(ISummary m:methods){
				if(!m.getTestResult())
					out=out+m.toString(i)+"\n================================================\n";
			}
		}
		return out;
	}


}
