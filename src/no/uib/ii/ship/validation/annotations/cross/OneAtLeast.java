package no.uib.ii.ship.validation.annotations.cross;
import java.lang.annotation.*;
import java.util.*;

import no.uib.ii.ship.validation.*;
import no.uib.ii.ship.validation.annotations.cross.CrossValidation;

// TODO: Auto-generated Javadoc
/**
 * Tests that at least one of the values is at least the given limit.
 *
 * @author fma042
 */
@Retention(RetentionPolicy.RUNTIME)
@CrossValidation
@Inherited
@Target(ElementType.ANNOTATION_TYPE)
public @interface OneAtLeast {

	/**
	 * Value.
	 *
	 * @return the int
	 */
	int value();

	/**
	 * The Class Tester.
	 */
	public static class Tester implements ICrossTester<OneAtLeast,Integer>{

		/* (non-Javadoc)
		 * @see no.uib.ii.ship.validation.ICrossTester#runTest(java.lang.annotation.Annotation, java.util.ArrayList)
		 */
		public boolean runTest(OneAtLeast c, List<Integer> v) throws ValidationException {
			for (int i:v)
				if (i>=c.value()) return true;
			return false;
		}
	}
}
