package no.uib.ii.ship.validation.annotations.method;
import java.lang.annotation.*;

import no.uib.ii.ship.validation.constraints.MetaConstraints;


/**
 * Denoted that returned value should not be null.
 *
 * @see  no.uib.ii.ship.validation.constraints.PropertyTest#getValueConstraints(MetaConstraints) nullTest
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Inherited
@NullTest(false)
public @interface Required {}