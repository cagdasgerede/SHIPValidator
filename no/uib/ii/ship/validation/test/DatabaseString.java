package no.uib.ii.ship.validation.test;
import java.lang.annotation.*;
import no.uib.ii.ship.validation.annotations.method.*;

/**
 * The Interface DatabaseString.
 */
@Retention(RetentionPolicy.RUNTIME)
@Validation
@Inherited
@StringLengthRange(min=0,max=100)
public @interface DatabaseString{}