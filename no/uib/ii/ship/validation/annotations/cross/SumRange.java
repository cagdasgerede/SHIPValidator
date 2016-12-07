package no.uib.ii.ship.validation.annotations.cross;
import java.lang.annotation.*;
import java.util.*;

import no.uib.ii.ship.validation.*;
import no.uib.ii.ship.validation.annotations.cross.CrossValidation;

// TODO: Auto-generated Javadoc
/**
 * Tests th.
 *
 * @author fma042
 */
@Retention(RetentionPolicy.RUNTIME)
@CrossValidation
@Inherited
@Target(ElementType.ANNOTATION_TYPE)
public @interface SumRange {

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
	public static class Tester implements ICrossTester<SumRange,Integer>{

		/* (non-Javadoc)
		 * @see no.uib.ii.ship.validation.ICrossTester#runTest(java.lang.annotation.Annotation, java.util.ArrayList)
		 */
		public boolean runTest(SumRange c, List<Integer> v) throws ValidationException {
			int sum=0;
			for (int i:v)
				sum+=i;
			return sum >= c.min() && sum <= c.max();
		}
	}
}
