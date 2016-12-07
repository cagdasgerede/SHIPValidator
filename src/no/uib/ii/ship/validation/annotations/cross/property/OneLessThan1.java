/**
 *
 */
package no.uib.ii.ship.validation.annotations.cross.property;

import no.uib.ii.ship.validation.annotations.cross.CrossValidation;
import no.uib.ii.ship.validation.annotations.method.IntUpperBound;

/**
 * @author Dag Hovland and Federico Mancini
 *
 */
@CrossValidation
@CrossProperty(operator=PropertyOperator.EXACTLY,n=1)
@IntUpperBound(0)
public @interface OneLessThan1 {}
