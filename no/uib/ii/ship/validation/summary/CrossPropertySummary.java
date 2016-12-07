/**
 *
 */
package no.uib.ii.ship.validation.summary;

import java.util.List;

import no.uib.ii.ship.validation.annotations.BoolType;
import no.uib.ii.ship.validation.annotations.cross.property.CrossProperty;
import no.uib.ii.ship.validation.annotations.cross.property.PropertyOperator;
import no.uib.ii.ship.validation.annotations.method.ValErr;

/**
 * Represents the result of a {@link CrossProperty} test.
 * The children of this are {@link AnnotationSummary}. The parent is {@link MethodSummary} or {@link CrossSummary}
 *
 * @author Dag Hovland and Federico Mancini
 *
 */
public class CrossPropertySummary extends AnnotationSummary {

	private final int successes;
	private final PropertyOperator po;
	private final int n;
	/**
	 * @param annotationName
	 * @param errorMessage
	 * @param children
	 * @param cp
	 */
	public CrossPropertySummary(String annotationName, ValErr errorMessage, List<ISummary> children, CrossProperty cp) {
		super(annotationName, errorMessage, children, BoolType.AND);
		this.po = cp.operator();
		this.n = cp.n();

		int succ = 0;
		for(ISummary cr : children){
			if(cr.getTestResult())
				succ++;
		}

		this.successes = succ;
	}

	/**
	 *
	 * @return True if none of the property tests passed
	 */
	public boolean getNoneTrue(){
		assert(this.po == null);
		return (this.successes == 0);
	}

	@Override public boolean getTestResult(){
		assert(this.po != null);
		switch(this.po){
		case ATLEAST:
			return this.successes >= this.n;
		case ATMOST:
			return this.successes <= this.n;
		case EXACTLY:
			return this.successes == this.n;
		default:
			assert(false);
		}
		assert(false);
		return false;
	}


}