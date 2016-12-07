/*
 * Copyright (c) University of Bergen
 *
 *
 * Authors: Dag Hovland and Federico Mancini, SHIP, Dept. of Informatics
 */
package no.uib.ii.ship.validation;

import no.uib.ii.ship.validation.summary.ValidationSummary;

/**
 * A interface for validators. They should be constructed in ValidatorFacories. Part of the "Abstract Factory" design pattern.
 * This is the
 * main interface to the framework for client applications.
 * 
 * @see Validator
 *
 */
public interface IValidator{

	/**
	 * This is the function that client applications of the validation framework need to call.
	 * The argument is the object where no-arg functions returning properties are annotated
	 *
	 * @param o Object to be validated
	 *
	 * @return the validation summary
	 */
	public ValidationSummary validate(Object o);
}