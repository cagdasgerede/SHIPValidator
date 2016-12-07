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
public @interface IntLowerBound {

	/**
	 * Value.
	 *
	 * @return the int
	 */
	int value();

	/**
	 * The Class Tester.
	 */
	public static class Tester implements IPropertyTester<IntLowerBound, Integer>{

		/* (non-Javadoc)
		 * @see no.uib.ii.ship.validation.IPropertyTester#runTest(java.lang.annotation.Annotation, java.lang.Object)
		 */
		public boolean runTest(IntLowerBound r, Integer v) {
			return (v >= r.value());
		}
	}
}