package no.uib.ii.ship.validation.annotations.cross;
import java.lang.annotation.*;
import java.util.*;

import no.uib.ii.ship.validation.*;
import no.uib.ii.ship.validation.annotations.cross.CrossValidation;

// TODO: Auto-generated Javadoc
/**
 * Tests that all the values is at least the given limit. Used alone the test could have been implemented with validation-annotations, but is interesting in composition.
 *
 * @author fma042
 */
@Retention(RetentionPolicy.RUNTIME)
@CrossValidation
@Inherited
@Target(ElementType.ANNOTATION_TYPE)
public @interface AllAtLeast {

	/**
	 * Value.
	 *
	 * @return the int
	 */
	int value();

	/**
	 * The Class Tester.
	 */
	public static class Tester implements ICrossTester<AllAtLeast,Integer>{

		/* (non-Javadoc)
		 * @see no.uib.ii.ship.validation.ICrossTester#runTest(java.lang.annotation.Annotation, java.util.ArrayList)
		 */
		public boolean runTest(AllAtLeast c, List<Integer> v) throws ValidationException {
			for (int i:v)
				if (i < c.value()) return false;
			return true;
		}
	}
}
