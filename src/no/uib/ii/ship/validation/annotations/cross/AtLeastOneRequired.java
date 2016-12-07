package no.uib.ii.ship.validation.annotations.cross;

import java.util.*;

import no.uib.ii.ship.validation.*;

import java.lang.annotation.*;


// TODO: Auto-generated Javadoc
/**
 * The Interface AtLeastOneRequired.
 */
@Retention(RetentionPolicy.RUNTIME)
@CrossValidation
@Inherited
@Target({ElementType.ANNOTATION_TYPE,ElementType.METHOD})
public @interface AtLeastOneRequired {

	/**
	 * The Class Tester.
	 */
	public static class Tester implements ICrossTester<AtLeastOneRequired, Object>{

		/* (non-Javadoc)
		 * @see no.uib.ii.ship.validation.ICrossTester#runTest(java.lang.annotation.Annotation, java.util.ArrayList)
		 */
		public boolean runTest(AtLeastOneRequired  c, List<Object> v) throws ValidationException {
			boolean allNull = true;
			for (Object i:v){
				if(i!=null)
					allNull = false;
			}
			return !allNull;
		}
	}
}
