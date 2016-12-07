/*
 * Copyright (c) University of Bergen
 *
 *
 * Authors: Dag Hovland and Federico Mancini, SHIP, Dept. of Informatics
 */
package no.uib.ii.ship.validation;


/**
 * A factory for creating Validator objects. Part of the "Abstract Factory" design pattern
 *
 * This is the preferred way of getting a validator
 *
 * @see ValidatorFactory
 * @see Validator
 *
 */
public interface IValidatorFactory{

	/**
	 * Gets the validator.
	 *
	 * @return the validator
	 */
	public IValidator getValidator();
}