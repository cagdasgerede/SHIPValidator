/*
 * Copyright (c) University of Bergen
 *
 *
 * Authors: Dag Hovland and Federico Mancini, SHIP, Dept. of Informatics
 */

package no.uib.ii.ship.validation.annotations.method;
import java.lang.annotation.*;


/**
 * Denotes that returned value should be between min and max.
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Validation
@Inherited
public @interface IntRange {

	/**
	 * Min.
	 *
	 * @return the int
	 */
	int min();

	/**
	 * Max.
	 *
	 * @return the int
	 */
	int max();

	/**
	 * The Class Tester.
	 */
	public static class Tester
	implements IPropertyTester<IntRange, Integer>
	{

		/* (non-Javadoc)
		 * @see no.uib.ii.ship.validation.IPropertyTester#runTest(java.lang.annotation.Annotation, java.lang.Object)
		 */
		public boolean runTest(
				IntRange r,
				Integer v) {
			return(v >= r.min() && v <= r.max());
		}
	}
}