/**
 *
 */
package no.uib.ii.ship.validation;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import no.uib.ii.ship.validation.test.Webform;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Dag Hovland and Federico Mancini
 *
 */
public class WebformTest {

	private IValidator val;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		IValidatorFactory vf = new ValidatorFactory();
		val = vf.getValidator();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Basic webform test
	 */
	@Test
	public void test1(){
		Webform w = new Webform(null,"BICCODES","01","AB1232342", 10, 10);
		assertTrue(val.validate(w).getTestResult());
	}

	/**
	 * Exactly 10 000 euro is allowed
	 */
	@Test
	public void test1a(){
		Webform w = new Webform(null,"BICCODES","01","AB1232342", 10000, 0);
		assertTrue(val.validate(w).getTestResult());
	}


	/**
	 * Exactly 1 cent is allowed
	 */
	@Test
	public void test1b(){
		Webform w = new Webform(null,"BICCODES","01","AB1232342", 0, 1);
		assertTrue(val.validate(w).getTestResult());
	}

	/**
	 * Amount zero not allowed
	 */
	@Test
	public void test2(){
		Webform w = new Webform(null,"BICCODES","01","AB1232342", 0, 0);
		assertFalse(val.validate(w).getTestResult());
	}

	/**
	 * Wrong BIC
	 */
	@Test
	public void test3(){
		Webform w = new Webform(null,"BICCODE","01","AB1232342", 10, 10);
		assertFalse(val.validate(w).getTestResult());
	}

	/**
	 * More than 10 000 not allowed
	 */
	@Test
	public void test4(){
		Webform w = new Webform(null,"BICCODES","01","AB1232342", 10000, 1);
		assertFalse(val.validate(w).getTestResult());
	}

}
