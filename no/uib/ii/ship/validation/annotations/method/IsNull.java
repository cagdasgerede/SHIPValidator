package no.uib.ii.ship.validation.annotations.method;
import java.lang.annotation.*;

import no.uib.ii.ship.validation.constraints.MetaConstraints;


/**
 * Denotes that returned value should be null.
 *
 * @see  no.uib.ii.ship.validation.constraints.PropertyTest#getValueConstraints(MetaConstraints) nullTest
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.ANNOTATION_TYPE})
@Inherited
@Validation
public @interface IsNull {
	/**
	 * The Class Tester.
	 */
	public static class Tester implements IPropertyTester<IsNull, Object>{

		/* (non-Javadoc)
		 * @see no.uib.ii.ship.validation.IPropertyTester#runTest(java.lang.annotation.Annotation, java.lang.Object)
		 */
		public boolean runTest(IsNull r, Object v){
			return v == null;
		}
	}
}