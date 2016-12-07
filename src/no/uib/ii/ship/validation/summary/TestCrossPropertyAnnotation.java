/**
 *
 */
package no.uib.ii.ship.validation.summary;

import static org.junit.Assert.*;

import java.lang.annotation.Annotation;

import no.uib.ii.ship.validation.annotations.cross.property.ExactlyOneNull;
import no.uib.ii.ship.validation.annotations.cross.property.ICrossOperator;
import no.uib.ii.ship.validation.constraints.CrossAnnotation;
import no.uib.ii.ship.validation.constraints.CrossPropertyAnnotation;
import no.uib.ii.ship.validation.constraints.MetaConstraints;
import no.uib.ii.ship.validation.constraints.PropertyAnnotation;
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
public class TestCrossPropertyAnnotation {

	private CrossPropertyAnnotation ca;

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
		Annotation ann = Webform.class.getDeclaredMethod("getIBAN").getAnnotation(ExactlyOneNull.class);
		this.ca = new CrossPropertyAnnotation(CrossAnnotation.getMetaAnnotations(new MetaConstraints(ann.annotationType()), ann),ann);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link no.uib.ii.ship.validation.constraints.CrossPropertyAnnotation#createChild(no.uib.ii.ship.validation.constraints.MetaConstraints, java.lang.annotation.Annotation)}.
	 */
	@Test
	public void testCreateChild() {
		PropertyAnnotation pa = new PropertyAnnotation(null, null, null);
		this.ca.addChild(pa);
	}

	/**
	 *
	 */
	@Test
	public void testRunRecTest() {
		assertTrue(this.ca.annotationType() == ExactlyOneNull.class);
	}



	/**
	 * Test method for {@link no.uib.ii.ship.validation.constraints.CrossAnnotation#getTestInterface()}.
	 */
	@Test
	public void testGetTestInterface() {
		assertTrue(this.ca.getTestInterface() == ICrossOperator.class);
	}

}
