/**
 *
 */
package no.uib.ii.ship.validation.annotations.cross.property;


/**
 *
 *
 * Used by {@link SumMin}.
 *
 *
 * @author Dag Hovland and Federico Mancini
 *
 */
public class Sum implements ICrossOperator<Integer>{
	/**
	 * Returns the arithmetic sum of the two arguments
	 *
	 * @param l
	 * @param r
	 * @return l+r
	 *
	 * @see ICrossOperator#op(Object, Object)
	 */
	public Integer op(Integer l, Integer r){ return l+r; }
}
/**
 * @author Dag Hovland and Federico Mancini
 *
 */
