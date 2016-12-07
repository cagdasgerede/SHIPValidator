/**
 *
 */
package no.uib.ii.ship.validation.annotations.cross.property;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import no.uib.ii.ship.validation.annotations.cross.CrossValidation;
import no.uib.ii.ship.validation.annotations.method.IsNull;

/**
 * Uses {@link CrossProperty} to create a cross-test from the property-test {@link IsNull}.
 *
 *
 * @author Dag Hovland and Federico Mancini
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@CrossValidation
@CrossProperty(operator=PropertyOperator.EXACTLY, n=1)
@IsNull
public @interface ExactlyOneNull {}
