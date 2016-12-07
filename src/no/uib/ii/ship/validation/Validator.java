/*
 * Copyright (c) University of Bergen
 *
 *
 * Authors: Dag Hovland and Federico Mancini, SHIP, Dept. of Informatics
 */
package no.uib.ii.ship.validation;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import no.uib.ii.ship.validation.constraints.CrossConstraints;
import no.uib.ii.ship.validation.constraints.MetaConstraints;
import no.uib.ii.ship.validation.constraints.PropertyTest;
import no.uib.ii.ship.validation.summary.ISummary;
import no.uib.ii.ship.validation.summary.ValidationSummary;

/**
 * The Class Validator. Should be instantiated by ValidatorFactory
 * Part of a "Abstract Factory" design pattern. Note that this validator is state-less,
 * that is, it has no fields. So only one Validator is needed.
 *
 * @see ValidatorFactory
 * @author Federico Mancini and Dag Hovland
 */
public class Validator implements IValidator{
	/**
	 * Creates the validation summary.
	 * @param methodSummaries
	 *
	 * @return the validation summary
	 */
	private ValidationSummary createValidationSummary(List<ISummary> methodSummaries){
		return new ValidationSummary(methodSummaries);
	}

	/**
	 * Creates the ValidationSummary. Loops on all methods: all that have validation or cross-validation annotations are run, and
	 * the return value is 1) checked against the method annotations, and 2) added to the list of values
	 * @see IValidator#validate(Object)
	 *
	 * @param obj The object that is to be validated / tested
	 *
	 * @return the ValidationSummary has getSuccess to indicate failure and toString to print error messages
	 */
	public ValidationSummary validate(Object obj) {
		List<ISummary> methodSummaries = new ArrayList<ISummary>();
		CrossConstraints cross = new CrossConstraints();
		for (Method met : obj.getClass().getMethods()){
			MetaConstraints constr = new MetaConstraints(met);
			if(constr.hasConstraints()){
				PropertyTest method = new PropertyTest(met, obj, constr);
				Object returnValue = method.getReturnValue();
				cross.addConstraints(constr.getCrossConstraints(), returnValue);
				ISummary ms = method.runTest();
				methodSummaries.add(ms);
			}
		}
		if(!cross.isEmpty())
			methodSummaries.add(cross.runCrossTests());
		return this.createValidationSummary(methodSummaries);
	}


}
