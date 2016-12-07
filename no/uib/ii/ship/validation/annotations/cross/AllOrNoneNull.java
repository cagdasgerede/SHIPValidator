/**
 *
 */
package no.uib.ii.ship.validation.annotations.cross;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

import no.uib.ii.ship.validation.ValidationException;
import no.uib.ii.ship.validation.annotations.method.ValErr;


/**
 * @author Dag Hovland and Federico Mancini
 *
 */

@Retention(RetentionPolicy.RUNTIME)
@ValErr(message="AllOrNone Error message")
@CrossValidation
@Inherited
public @interface AllOrNoneNull {
	/**
	 * @author Dag Hovland and Federico Mancini
	 *
	 */
	public static class Tester implements ICrossTester<AllOrNoneNull,String>{

		public boolean runTest(AllOrNoneNull c, List<String> v) throws ValidationException {
			boolean allNull = true;
			boolean noneNull = true;
			for(String s : v){
				if(s == null)
					noneNull = false;
				else
					allNull = false;
			}
			return allNull || noneNull;
		}
	}
}
