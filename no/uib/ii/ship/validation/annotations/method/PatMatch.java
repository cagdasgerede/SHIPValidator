package no.uib.ii.ship.validation.annotations.method;
import java.lang.annotation.*;
import java.util.regex.Pattern;


// TODO: Auto-generated Javadoc
/**
 * Denoted that returned value should match the regular expression in the string.
 *
 * @author mpkgc
 */
@Retention(RetentionPolicy.RUNTIME)
@Validation
public @interface PatMatch {

	/**
	 * Value.
	 *
	 * @return the string containing regular expression
	 */
	String value();

	/**
	 * The Class Tester.
	 */
	public static class Tester implements IPropertyTester<PatMatch, String>{

		/* (non-Javadoc)
		 * @see no.uib.ii.ship.validation.IPropertyTester#runTest(java.lang.annotation.Annotation, java.lang.Object)
		 */
		public boolean runTest(PatMatch p, String o){
			return Pattern.matches(p.value(),o);
		}
	}
}