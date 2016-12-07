/**
 *
 */
package no.uib.ii.ship.validation.summary;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Dag Hovland and Federico Mancini
 *
 */
public class TestSummaryNode {

	private AnnotationSummary snull, sn1;

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
		this.snull = new AnnotationSummary("", null, new ArrayList<ISummary>());
		List<ISummary> cr = new ArrayList<ISummary>();
		cr.add(this.snull);
		this.sn1 = new AnnotationSummary("AnnotationName", null, cr);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 */
	@Test
	public void testSummaryNode() {
		assertTrue(this.sn1.getErrorMessage() == null);
		assertTrue(this.sn1.getAnnotationName() == "AnnotationName");
	}

	/**
	 * Test method for {@link no.uib.ii.ship.validation.summary.AnnotationSummary#getAllTrue()}.
	 */
	@Test
	public void testGetAllTrue() {

	}

	/**
	 * Test method for {@link no.uib.ii.ship.validation.summary.AnnotationSummary#getTestResult()}.
	 */
	@Test
	public void testGetSuccess() {
		assertTrue(this.sn1.getTestResult());
	}


	/**
	 */
	@Test
	public void testGetMessage() {
	}

	/**
	 * Test method for {@link no.uib.ii.ship.validation.summary.AnnotationSummary#getChildren()}.
	 */
	@Test
	public void testGetChildren() {
		List<ISummary> cl = this.sn1.getChildren();
		assertTrue(cl.size() == 1);
		assertTrue(cl.get(0) == this.snull);
	}

	/**
	 * Test method for {@link no.uib.ii.ship.validation.summary.AnnotationSummary#toString(int)}.
	 */
	@Test
	public void testToStringInt() {

	}

	/**
	 * Test method for {@link java.lang.Object#toString()}.
	 */
	@Test
	public void testToString() {

	}

}
