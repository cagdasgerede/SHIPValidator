package no.uib.ii.ship.validation.summary;

import java.util.List;

/**
 * The validation summary is returned as a tree of nodes implementing this
 * interface. Resembles the "Composite" pattern
 *
 * @author Dag Hovland and Federico Mancini
 *
 */
public interface ISummary{

	/**
	 * @return Success
	 */
	public boolean getTestResult();

	/**
	 * @return the list of children
	 */
	public List<ISummary> getChildren();

	/**
	 * @param i
	 * @return String description up to level i
	 */
	public String toString(int i);

	/**
	 * @param newVar
	 */
	//void setBool_op ( BoolType newVar );

	/**
	 * @param vs
	 */
	//public void addChild( ISummary vs );


}