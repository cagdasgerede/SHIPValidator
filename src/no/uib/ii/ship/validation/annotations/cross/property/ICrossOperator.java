/**
 *
 */
package no.uib.ii.ship.validation.annotations.cross.property;

/**
 * A class implementing this interface must be given as argument to a {@link CrossOperator} annotation.
 * An example is seen in {@link Sum}
 *
 * @author Dag Hovland and Federico Mancini
 * @param <A>
 *
 */
public interface ICrossOperator <A>{
	/**
	 * @param r
	 * @param l
	 * @return the result of applying an associative operator to r and l
	 */
	public A op(A r, A l);
}
