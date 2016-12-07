/*
 * Copyright (c) University of Bergen
 *
 *
 * Authors: Dag Hovland and Federico Mancini, SHIP, Dept. of Informatics
 */
package no.uib.ii.ship.validation;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating Validator objects.
 */
public class ValidatorFactory implements IValidatorFactory{

	/**
	 * Gets the validator.
	 *
	 * @return the validator
	 */
	@Override
	public IValidator getValidator(){
		return new Validator();
	}
}