/**
 *
 */
package no.uib.ii.ship.validation.summary;

import java.util.List;

import no.uib.ii.ship.validation.annotations.BoolType;
import no.uib.ii.ship.validation.annotations.cross.property.AllProperty;
import no.uib.ii.ship.validation.annotations.method.ValErr;

/**
 * Represents the summary of a {@link AllProperty} test
 * The children of this are {@link AnnotationSummary}. The parent is
 *  {@link AnnotationSummary}, {@link MethodSummary}, or {@link CrossSummary}.
 *
 * @author Dag Hovland and Federico Mancini
 *
 */
public class AllPropertySummary extends AnnotationSummary{

	private final boolean allValue;

	/**
	 * @param annotationName
	 * @param errorMessage
	 * @param children
	 * @param ap
	 */
	public AllPropertySummary(String annotationName, ValErr errorMessage, List<ISummary> children, AllProperty ap) {
		super(annotationName, errorMessage, children, BoolType.AND);
		this.allValue = ap.value();
	}

	/**
	 * Uses the inherited methods from
	 * @see AnnotationSummary#getAllTrue()
	 * @see CrossPropertySummary#getNoneTrue()
	 * @return True if AllProperty(x) and all property tests are x (where x=false or x = true)
	 */
	@Override public boolean getTestResult(){
		boolean retval;
		if(this.allValue){
			retval = this.getAllTrue();
		} else {
			retval = this.getAllFalse();
		}
		return retval;
	}

}
