/*
 * Copyright (c) University of Bergen
 *
 *
 * Authors: Dag Hovland and Federico Mancini, SHIP, Dept. of Informatics
 */
package no.uib.ii.ship.validation.annotations.method;
import java.lang.annotation.*;

import no.uib.ii.ship.validation.ValidationException;

/**
 * The Interface IPropertyTester.
 * Each basic property-test must have an inner class implementing this interface
 * @param <A>
 * @param <I>
 */
public interface IPropertyTester <A extends Annotation, I>{

	/**
	 * Run test.
	 *
	 * @param an the an
	 * @param o the o
	 *
	 * @return true, if successful
	 *
	 * @throws ValidationException the validation exception
	 */
	public boolean runTest(A an, I o) throws ValidationException;
}