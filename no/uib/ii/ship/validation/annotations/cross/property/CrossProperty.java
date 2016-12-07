/**
 *
 */
package no.uib.ii.ship.validation.annotations.cross.property;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;



/**
 * @author Dag Hovland and Federico Mancini
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target({ElementType.ANNOTATION_TYPE})
public @interface CrossProperty {
	/**
	 * This enum defines how the property-test is extended or mapped to
	 * be a cross-test.
	 */
	PropertyOperator operator();

	/**
	 * Depending on the enum operator(), this integer must also be filled in.
	 * Only for ALL and NONE can it be left undefined.
	 */
	int n();
}
