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
public @interface StringLowerBound {

	/**
	 * Value.
	 *
	 * @return the string
	 */
	String value();

	/**
	 * The Class Tester.
	 */
	public static class Tester implements IPropertyTester<StringLowerBound, String>{

		/* (non-Javadoc)
		 * @see no.uib.ii.ship.validation.IPropertyTester#runTest(java.lang.annotation.Annotation, java.lang.Object)
		 */
		public boolean runTest(StringLowerBound r, String v) {
			return (v.compareTo(r.value()) >= 0);
		}
	}
}