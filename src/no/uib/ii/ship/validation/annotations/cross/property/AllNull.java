/**
 *
 */
package no.uib.ii.ship.validation.annotations.cross.property;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import no.uib.ii.ship.validation.annotations.cross.CrossValidation;
import no.uib.ii.ship.validation.annotations.method.IsNull;

/**
 * @author Dag Hovland and Federico Mancini
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@CrossValidation
@AllProperty
@IsNull
public @interface AllNull {

}
