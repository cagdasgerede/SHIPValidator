package no.uib.ii.ship.validation.test;

import no.uib.ii.ship.validation.annotations.BoolTest;
import no.uib.ii.ship.validation.annotations.BoolType;
import no.uib.ii.ship.validation.annotations.cross.*;
import no.uib.ii.ship.validation.annotations.method.*;

import java.lang.annotation.*;

// TODO: Auto-generated Javadoc
/**
 * The Class InputObj.
 */
public class InputObj {

	/** The amount. */
	private int amount;

	/** The decimal. */
	private int decimal;

	/** The account. */
	private String account;

	/** The message. */
	private String message;

	/** The date. */
	private Date date;


	/**
	 * Instantiates a new input obj.
	 *
	 * @param amount the amount
	 * @param decimal the decimal
	 * @param account the account
	 * @param message the message
	 * @param date the date
	 */
	public InputObj(int amount, int decimal, String account, String message, Date date) {
		super();
		this.amount = amount;
		this.decimal = decimal;
		this.account = account;
		this.message = message;
		this.date = date;
	}

	/**
	 * Gets the amount.
	 *
	 * @return the amount
	 */
	@CompoundTest
	@CompoundTest4
	@IntRange(min=0,max=999)
	public int getAmount() {
		return amount;
	}

	/**
	 * Second test.
	 *
	 * @return the int
	 */
	@IntRange(min=0,max=21)
	public int secondTest(){
		return 10;
	}



	/**
	 * Test string.
	 *
	 * @return the string
	 */
	@NotRequired
	public String testString(){
		return "tekst";
	}

	/**
	 * Gets the test string.
	 *
	 * @return the test string
	 */
	@StringRange(min="",max="abc")
	public String getTestString(){
		return "adc";
	}

    //@SchemaString(url="http://www.ii.uib.no/~dagh/po.xsd")
	/**
     * Gets the xML doc.
     *
     * @return the xML doc
     */
    public String getXMLDoc(){
		return  "<?xml version=\"1.0\"?><purchaseOrder xmlns=\"po\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"po po.xsd\" orderDate=\"1999-10-20\">"
		+"<shipTo country=\"US\">      <name>Alice Smith</name>      <street>123 Maple Street</street>      <city>Mill Valley</city>      <state>CA</state>      <zip>90952</zip>   </shipTo>   <billTo country=\"US\">      <name>Robert Smith</name>      <street>8 Oak Avenue</street>      <city>Old Town</city>"
		+"<state>PA</state>      <zip>95819</zip>   </billTo>   <comment>Hurry, my lawn is going wild</comment>   <items>      <item partNum=\"872-AA\">         <productName>Lawnmower</productName>"
		+"<quantity>1</quantity>         <USPrice>148.95</USPrice>         <comment>Confirm this is electric</comment>      </item>      <item partNum=\"926-AA\">         <productName>Baby Monitor</productName>"
		+"<quantity>1</quantity>         <USPrice>39.98</USPrice>         <shipDate>1999-05-21</shipDate>      </item>   </items></purchaseOrder>";
	}

	/**
	 * Gets the decimal.
	 *
	 * @return the decimal
	 */
	@CompoundTest3
	@CompoundTest
	@AtLeastOneRequired
	@IntRange(min=10,max=99)
	@Test2
	public int getDecimal() {
		return decimal;
	}

	/**
	 * Gets the account.
	 *
	 * @return the account
	 */
	@PatMatch("[0-9]{9}")
	public String getAccount() {
		return account;
	}

	//@BoolTest(BoolType.AllFalse)
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	@PatMatch(".*[[^\\d]&&[^\\w]].*")
	public String getMessage() {
		return message;
	}

	//@BoolTest(BoolType.Or)
	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	@CheckDate
	@Valid
	@Required
	public Date getDate() {
		return date;
	}


}

@Retention(RetentionPolicy.RUNTIME)
@CrossValidation
@Inherited
@SumMin(1)
@interface CompoundTest2 {
}

@Retention(RetentionPolicy.RUNTIME)
@CrossValidation
@Inherited
@CompoundTest2
@interface CompoundTest3 {
}

@Retention(RetentionPolicy.RUNTIME)
@CrossValidation
@Inherited
@CompoundTest3
@interface CompoundTest4 {
}

@Validation
@Retention(RetentionPolicy.RUNTIME)
@BoolTest(BoolType.ALLFALSE)
@IntRange(min=0,max=90)
@interface Test1{
}
@Validation
@Retention(RetentionPolicy.RUNTIME)
@Test1
@IntRange(min=2,max=12)
@interface Test3{
}

@Validation
@Retention(RetentionPolicy.RUNTIME)
@Test3
@Test1
@interface Test2{
}




