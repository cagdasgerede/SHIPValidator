/**
 *
 */
package no.uib.ii.ship.validation.annotations.cross.property;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import no.uib.ii.ship.validation.annotations.cross.CrossValidation;
import no.uib.ii.ship.validation.annotations.method.IntLowerBound;

/**
 * A cross-test checking that the sum of all properties annotated with it, are at least 1.
 * Composed from {@link IntLowerBound} using {@link CrossOperator} with argument {@link Sum}.
 *
 * @author Dag Hovland and Federico Mancini
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@CrossValidation
@CrossOperator(Sum.class)
@IntLowerBound(1)
public @interface SumMin {}
