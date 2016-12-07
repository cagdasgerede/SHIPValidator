package no.uib.ii.ship.validation.annotations.cross;
import no.uib.ii.ship.validation.*;
import java.lang.annotation.*;
import java.util.*;


// TODO: Auto-generated Javadoc
/**
 * The Interface ExactlyNNull.
 */
@Retention(RetentionPolicy.RUNTIME)
@CrossValidation
@Inherited
@Target({ElementType.ANNOTATION_TYPE,ElementType.METHOD})
public @interface ExactlyNNull {

	/**
	 * Value.
	 *
	 * @return the int
	 */
	int value() default 1;

	/**
	 * The Class Tester.
	 */
	public static class Tester implements ICrossTester<ExactlyNNull, Object>{

		/* (non-Javadoc)
		 * @see no.uib.ii.ship.validation.ICrossTester#runTest(java.lang.annotation.Annotation, java.util.ArrayList)
		 */
		public boolean runTest(ExactlyNNull  c, List<Object> v) throws ValidationException {
			int nulls = 0;
			for (Object i:v){
				if(i==null)
					nulls ++;
			}
			return nulls==c.value();
		}
	}
}
