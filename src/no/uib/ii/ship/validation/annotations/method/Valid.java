/**
 *
 */
package no.uib.ii.ship.validation.annotations.method;
import java.lang.annotation.*;

/**
 * @author Dag Hovland and Federico Mancini
 * Used to call the validator recursively on the return value of a method
 */
 @Retention(RetentionPolicy.RUNTIME)
 @Target(ElementType.METHOD)
 @Inherited
 @Validation
public @interface Valid {}
