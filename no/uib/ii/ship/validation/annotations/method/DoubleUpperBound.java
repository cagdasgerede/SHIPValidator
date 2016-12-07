/*
 * Copyright (c) University of Bergen
 *
 *
 * Authors: Dag Hovland and Federico Mancini, SHIP, Dept. of Informatics
 */

package no.uib.ii.ship.validation.annotations.method;
import java.lang.annotation.*;


// TODO: Auto-generated Javadoc
/**
 * Denotes that returned value should be less than or equal value.
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Validation
@Inherited
public @interface DoubleUpperBound {

	/**
	 * Value.
	 *
	 * @return the double
	 */
	double value();

	/**
	 * The Class Tester.
	 */
	public static class Tester implements IPropertyTester<DoubleUpperBound, Double>{

		/* (non-Javadoc)
		 * @see no.uib.ii.ship.validation.IPropertyTester#runTest(java.lang.annotation.Annotation, java.lang.Object)
		 */
		public boolean runTest(DoubleUpperBound r, Double v) {
			return (v <= r.value());
		}
	}
}