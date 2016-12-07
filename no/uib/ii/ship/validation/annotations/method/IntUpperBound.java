/*
 * Copyright (c) University of Bergen
 *
 *
 * Authors: Dag Hovland and Federico Mancini, SHIP, Dept. of Informatics
 */

package no.uib.ii.ship.validation.annotations.method;
import java.lang.annotation.*;


/**
 * Denotes that returned value should be less than or equal value.
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Validation
@Inherited
public @interface IntUpperBound {

	/**
	 * Value.
	 *
	 * @return the int, highest valid value
	 */
	int value();

	/**
	 * The Class Tester.
	 */
	public static class Tester implements IPropertyTester<IntUpperBound, Integer>{

		/* (non-Javadoc)
		 * @see no.uib.ii.ship.validation.IPropertyTester#runTest(java.lang.annotation.Annotation, java.lang.Object)
		 */
		public boolean runTest(IntUpperBound r, Integer v) {
			return (v <= r.value());
		}
	}
}