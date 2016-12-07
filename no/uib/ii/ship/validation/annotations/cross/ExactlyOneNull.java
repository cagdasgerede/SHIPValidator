package no.uib.ii.ship.validation.annotations.cross;

import java.lang.annotation.*;



/**
 * The Interface ExactlyOneNull.
 */
@Retention(RetentionPolicy.RUNTIME)
@CrossValidation
@Inherited
@ExactlyNNull(1)
public @interface ExactlyOneNull {
}