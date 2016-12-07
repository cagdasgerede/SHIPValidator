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
 * Denotes that returned value should be larger than or equal value.
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Validation
@Inherited
public @interface DoubleLowerBound {

	/**
	 * Value.
	 *
	 * @return the double
	 */
	double value();

	/**
	 * The Class Tester.
	 */
	public static class Tester implements IPropertyTester<DoubleLowerBound, Double>{

		/* (non-Javadoc)
		 * @see no.uib.ii.ship.validation.IPropertyTester#runTest(java.lang.annotation.Annotation, java.lang.Object)
		 */
		public boolean runTest(DoubleLowerBound r, Double v) {
			return (v >= r.value());
		}
	}
}