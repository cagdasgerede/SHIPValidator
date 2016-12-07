/**
 *
 */
package no.uib.ii.ship.validation.annotations.cross.property;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import no.uib.ii.ship.validation.annotations.cross.*;
import no.uib.ii.ship.validation.annotations.*;

/**
 * @author Dag Hovland and Federico Mancini
 *
 */
@BoolTest(BoolType.OR)
@AllNull
@AllRequired
@Retention(RetentionPolicy.RUNTIME)
@CrossValidation
public @interface AllOrNoneNull {

}
