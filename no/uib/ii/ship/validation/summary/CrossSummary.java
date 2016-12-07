/*
 * Copyright (c) University of Bergen
 *
 *
 * Authors: Dag Hovland and Federico Mancini, SHIP, Dept. of Informatics
 */

package no.uib.ii.ship.validation.summary;

import java.util.List;





/**
 * Class CrossSummary. Represents all cross-tests on an object. A {@link ValidationSummary} contains either none (if there are no cross-annotations)
 * or a single CrossSummary. The children of this object are any type of {@link AnnotationSummary},
 * including {@link AllPropertySummary}, {@link CrossPropertySummary} and {@link AnnotationSummary}.
 *
 */
public class CrossSummary extends MethodSummary {

	/**
	 * Instantiates a new cross summary.
	 * @param children
	 *
	 */
	public CrossSummary(List<ISummary> children) {
		super("Cross Tests", null, children, null);
	}


	/* (non-Javadoc)
	 * @see no.uib.ii.ship.validation.MethodSummary#toString(int)
	 */
	@Override
	public String headerLine(){
		return "The following cross-tests have failed:\n";
	}



}
