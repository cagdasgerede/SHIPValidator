package no.uib.ii.ship.validation.annotations.cross;
import java.lang.annotation.*;
import java.util.*;

import no.uib.ii.ship.validation.*;
import no.uib.ii.ship.validation.annotations.cross.CrossValidation;


/**
 * Tests th.
 *
 * @author fma042
 */
@Retention(RetentionPolicy.RUNTIME)
@CrossValidation
@Inherited
@Target(ElementType.ANNOTATION_TYPE)
public @interface SumMin {

	/**
	 * Value.
	 *
	 * @return the int
	 */
	int value();

	/**
	 * The Class Tester.
	 */
	public static class Tester implements ICrossTester<SumMin,Integer>{

		/* (non-Javadoc)
		 * @see no.uib.ii.ship.validation.ICrossTester#runTest(java.lang.annotation.Annotation, java.util.ArrayList)
		 */
		public boolean runTest(SumMin c, List<Integer> v) throws ValidationException {
			int sum=0;
			for (int i:v)
				sum+=i;
			return sum>=c.value();
		}
	}
}
