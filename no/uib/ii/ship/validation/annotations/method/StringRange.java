package no.uib.ii.ship.validation.annotations.method;
import java.lang.annotation.*;


// TODO: Auto-generated Javadoc
/**
 * Denotes that returned value should be between min and max.

 */
@Retention(RetentionPolicy.RUNTIME)
@Validation
@Inherited
public @interface StringRange {

    /**
     * Min.
     *
     * @return the string
     */
    String min();

    /**
     * Max.
     *
     * @return the string
     */
    String max();

    /**
     * The Class Tester.
     */
    public static class Tester implements IPropertyTester<StringRange, String>{

	    /* (non-Javadoc)
	     * @see no.uib.ii.ship.validation.IPropertyTester#runTest(java.lang.annotation.Annotation, java.lang.Object)
	     */
	    public boolean runTest(StringRange r, String v)  {
    		return v.compareTo(r.min()) >= 0 && v.compareTo(r.max()) <= 0;
    	}
    }
}