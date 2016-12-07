package no.uib.ii.ship.validation.annotations.method;
import java.lang.annotation.*;

import no.uib.ii.ship.validation.constraints.MetaConstraints;
import no.uib.ii.ship.validation.constraints.PropertyTest;

/**
 * Denoted that returned value should not be null.
 *
 * @see  PropertyTest#getValueConstraints(MetaConstraints) nullTest
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Inherited
@NullTest(true)
public @interface NotRequired {}