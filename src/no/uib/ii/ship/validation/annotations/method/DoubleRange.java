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
 * Denotes that returned value should be between min and max.
 *
  */
@Retention(RetentionPolicy.RUNTIME)
@Validation
@Inherited
public @interface DoubleRange {

    /**
     * Min.
     *
     * @return the double
     */
    double min();

    /**
     * Max.
     *
     * @return the double
     */
    double max();

    /**
     * The Class Tester.
     */
    public static class Tester implements IPropertyTester<DoubleRange, Double>{

	    /* (non-Javadoc)
	     * @see no.uib.ii.ship.validation.IPropertyTester#runTest(java.lang.annotation.Annotation, java.lang.Object)
	     */
	    public boolean runTest(DoubleRange r, Double v) {
    		return v >= r.min() && v <= r.max();
    	}
    }
}