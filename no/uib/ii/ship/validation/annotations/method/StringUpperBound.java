package no.uib.ii.ship.validation.annotations.method;
import java.lang.annotation.*;


// TODO: Auto-generated Javadoc
/**
 * Denotes that returned value should be less than or equal value.
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Validation
@Inherited
public @interface StringUpperBound {

	/**
	 * Value.
	 *
	 * @return the string, largest valid value
	 */
	String value();

	/**
	 * The Class Tester.
	 */
	public static class Tester implements IPropertyTester<StringUpperBound, String>{

		/* (non-Javadoc)
		 * @see no.uib.ii.ship.validation.IPropertyTester#runTest(java.lang.annotation.Annotation, java.lang.Object)
		 */
		public boolean runTest(StringUpperBound r, String v) {
			return (v.compareTo(r.value()) <= 0);
		}
	}
}