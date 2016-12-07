package no.uib.ii.ship.validation.test;
import java.lang.annotation.*;
import java.util.*;


import no.uib.ii.ship.validation.*;
import no.uib.ii.ship.validation.annotations.cross.CrossValidation;
import no.uib.ii.ship.validation.annotations.cross.ICrossTester;

// TODO: Auto-generated Javadoc
/**
 * The Interface CompoundTest.
 */
@Retention(RetentionPolicy.RUNTIME)
@CrossValidation
@Inherited
public @interface CompoundTest {

	/**
	 * The Class Tester.
	 */
	public static class Tester implements ICrossTester<CompoundTest,Integer>{

		/* (non-Javadoc)
		 * @see no.uib.ii.ship.validation.ICrossTester#runTest(java.lang.annotation.Annotation, java.util.ArrayList)
		 */
		public boolean runTest(CompoundTest c, List<Integer> v) throws ValidationException {
			try {
				int sum=0;
				for (int i:v){
					sum+=i;
				}
			return sum>1;

			} catch (Exception e) {
				throw new ValidationException("Object" + v + " is not an Integer!",e);

			}
		}
	}
}
