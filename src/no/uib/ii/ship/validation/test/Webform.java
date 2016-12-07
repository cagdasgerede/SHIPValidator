package no.uib.ii.ship.validation.test;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import no.uib.ii.ship.validation.IValidator;
import no.uib.ii.ship.validation.IValidatorFactory;
import no.uib.ii.ship.validation.ValidatorFactory;
import no.uib.ii.ship.validation.annotations.BoolTest;
import no.uib.ii.ship.validation.annotations.BoolType;
import no.uib.ii.ship.validation.annotations.cross.AllLessThan;
import no.uib.ii.ship.validation.annotations.cross.CrossValidation;
import no.uib.ii.ship.validation.annotations.cross.OneLessThan;
import no.uib.ii.ship.validation.annotations.cross.property.AllOrNoneNull;
import no.uib.ii.ship.validation.annotations.cross.property.AllProperty;
import no.uib.ii.ship.validation.annotations.cross.property.ExactlyOneNull;
import no.uib.ii.ship.validation.annotations.cross.property.SumMin;
import no.uib.ii.ship.validation.annotations.method.IntRange;
import no.uib.ii.ship.validation.annotations.method.IntUpperBound;
import no.uib.ii.ship.validation.annotations.method.NotRequired;
import no.uib.ii.ship.validation.annotations.method.PatMatch;
import no.uib.ii.ship.validation.annotations.method.ValErr;
import no.uib.ii.ship.validation.annotations.method.Validation;
import no.uib.ii.ship.validation.summary.ValidationSummary;



/**
 * The Class Webform.
 *
 * @author mpkgc
 */
public class Webform {

	/** The IBAN. */
	private String IBAN;

	/** The BIC. */
	private String BIC;


	/** The account. */
	private String account;

	/** The clearingcode. */
	private String clearingcode;

	/** The amount euro. */
	private Integer amountEuro;

	/** The amount cents. */
	private Integer amountCents;
	//private Date start;

	/**
	 * The Constructor.
	 *
	 * @param iban the iban
	 * @param bic the bic
	 * @param account the account
	 * @param clearingcode the clearingcode
	 * @param amountEuro the amount euro
	 * @param amountCents the amount cents
	 */
	public Webform(String iban, String bic, String account,
			String clearingcode, Integer amountEuro, Integer amountCents) {
		super();
		IBAN = iban;
		BIC = bic;
		this.account = account;
		this.clearingcode = clearingcode;
		this.amountEuro = amountEuro;
		this.amountCents = amountCents;
	}

        /**
         * Gets the iBAN.
         *
         * @return the iBAN
         */
       @ExactlyOneNull
        @NotRequired
        @ValidateIBAN
	public String getIBAN() {
		return IBAN;
	}

        /**
         * Gets the bIC.
         *
         * @return the bIC
         */
        @ValidateBIC
	//@Required
	public String getBIC() {
		return BIC;
	}

	/**
	 * Gets the account.
	 *
	 * @return the account
	 */
	@AllOrNoneNull
	@DatabaseString
        @NotRequired
	@ExactlyOneNull
	public String getAccount() {
		return account;
	}

	/**
	 * Gets the clearingcode.
	 *
	 * @return the clearingcode
	 */
	@AllOrNoneNull
	@DatabaseString
        @NotRequired
	public String getClearingcode() {
		return clearingcode;
	}

	/**
	 * Gets the amount euro.
	 *
	 * @return the amount euro
	 */
	@AmountCheck
	@IntRange(min=0,max=10000)
	public Integer getAmountEuro() {
		return amountEuro;
	}

	/**
	 * Gets the amount cents.
	 *
	 * @return the amount cents
	 */
	@AmountCheck
	@IntRange(min=0,max=99)
	public Integer getAmountCents() {
		return amountCents;
	}


	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args){
		Webform w = new Webform(null,"BICCODES","01","AB1232342", 10000, 0);
		IValidatorFactory vf = new ValidatorFactory();
		IValidator val = vf.getValidator();
		ValidationSummary vs = val.validate(w);
		System.out.println(vs.toString());

	}


}

@Retention(RetentionPolicy.RUNTIME)
@CrossValidation
@Inherited
@BoolTest(BoolType.OR)
@OneLessThan(1)
@AllLessThan(10000)
@interface MaxAmount {}

@Retention(RetentionPolicy.RUNTIME)
@CrossValidation
@Inherited
@BoolTest(BoolType.AND)
@SumMin
@MaxAmount
@interface AmountCheck {}

@CrossValidation
@Retention(RetentionPolicy.RUNTIME)
@AllProperty
@IntUpperBound(9999)
@interface AllLessThan10000{}


@Retention(RetentionPolicy.RUNTIME)
@Validation
@Inherited
@DatabaseString
@BoolTest(BoolType.AND)
@PatMatch("[A-Z]{2}[0-9]{2}\\w{1,30}")
@ValErr(message="not valid IBAN")
@interface ValidateIBAN {

}

@Retention(RetentionPolicy.RUNTIME)
@Validation
@Inherited
@ValErr(message="Invalid BIC")
@BoolTest(BoolType.AND)
@PatMatch("\\w{8}|\\w{11}")
@DatabaseString
@interface ValidateBIC{}



