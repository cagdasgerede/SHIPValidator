package no.uib.ii.ship.validation.annotations.method;
import java.lang.annotation.*;

import no.uib.ii.ship.validation.constraints.MetaConstraints;


// TODO: Auto-generated Javadoc
/**
 * Denoted that returned value should not be null.
 *
 * @see  no.uib.ii.ship.validation.constraints.PropertyTest#getValueConstraints(MetaConstraints) nullTest
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.ANNOTATION_TYPE})
@Validation
@Inherited
public @interface NullTest {

	/**
	 * Value.
	 *
	 * @return true, if successful
	 */
	boolean value();

	/**
	 * The Class Tester.
	 */
	public static class Tester implements IPropertyTester<NullTest, Object>{

		/* (non-Javadoc)
		 * @see no.uib.ii.ship.validation.IPropertyTester#runTest(java.lang.annotation.Annotation, java.lang.Object)
		 */
		public boolean runTest(NullTest r, Object v){
			boolean ret = (v != null) || r.value();
			return ret;
		}
	}
}