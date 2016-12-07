/**
 *
 */
package no.uib.ii.ship.validation.annotations.cross.property;

import java.lang.annotation.*;

/**
 * @author Dag Hovland and Federico Mancini
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target({ElementType.ANNOTATION_TYPE})
public @interface AllProperty {
	/**
	 * Specifies whether all values should be true, or all should be
	 * false
	 */
	boolean value() default true;
}
