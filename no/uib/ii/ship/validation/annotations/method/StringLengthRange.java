package no.uib.ii.ship.validation.annotations.method;
import java.lang.annotation.*;


// TODO: Auto-generated Javadoc
/**
 * Denotes that returned value should be between min and max.
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Validation
@Inherited
public @interface StringLengthRange {

    /**
     * Min.
     *
     * @return the int
     */
    int min();

    /**
     * Max.
     *
     * @return the int
     */
    int max();

    /**
     * The Class Tester.
     */
    public static class Tester implements IPropertyTester<StringLengthRange, String>{

	    /* (non-Javadoc)
	     * @see no.uib.ii.ship.validation.IPropertyTester#runTest(java.lang.annotation.Annotation, java.lang.Object)
	     */
	    public boolean runTest(StringLengthRange r, String v)  {
    		return v.length() >= r.min() && v.length() <= r.max();
    	}
    }
}