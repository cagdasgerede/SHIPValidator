/**
 *
 */
package no.uib.ii.ship.validation.summary;

import java.util.ArrayList;
import java.util.List;

/**
 * The result of the test specified by an inner class implementing IPropertyTester or
 * ICrossTester.
 *
 * @author Dag Hovland and Federico Mancini
 *
 */
public class LeafSummary implements ISummary {

	private final boolean success;


	/**
	 * @param success
	 */
	public LeafSummary(boolean success) {
		this.success = success;
	}





	/* (non-Javadoc)
	 * @see no.uib.ii.ship.validation.summary.ISummary#getChildren()
	 */
	@Override
	public List<ISummary> getChildren() {
		return new ArrayList<ISummary>();
	}


	/* (non-Javadoc)
	 * @see no.uib.ii.ship.validation.summary.ISummary#getTestResult()
	 */
	@Override
	public boolean getTestResult() {
		return this.success;
	}


	/* (non-Javadoc)
	 * @see no.uib.ii.ship.validation.summary.ISummary#setBool_op(no.uib.ii.ship.validation.annotations.BoolType)
	 */
	/*@Override
	public void setBool_op(BoolType newVar) {
		assert(false);
	}*/


	/* (non-Javadoc)
	 * @see no.uib.ii.ship.validation.summary.ISummary#toString(int)
	 */
	@Override
	public String toString(int i) {
		return "";
	}



}
