package no.uib.ii.ship.validation.test;
import no.uib.ii.ship.validation.*;
import no.uib.ii.ship.validation.summary.ValidationSummary;

/**
 * For testing
 */
public class Inputval {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main (String[] args) {
		Date d=new Date(24, 3, 1999);
		InputObj o=new InputObj(0, 0, "1203903899" , "ciaohackeriamoilsito", d);
		IValidatorFactory vf = new ValidatorFactory();
		IValidator v = vf.getValidator();
		ValidationSummary vs=v.validate(o);
		System.out.println(vs.toString());
	}

}
