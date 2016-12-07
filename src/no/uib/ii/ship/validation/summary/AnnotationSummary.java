/*
 * Copyright (c) University of Bergen
 *
 *
 * Authors: Dag Hovland and Federico Mancini, SHIP, Dept. of Informatics
 */

package no.uib.ii.ship.validation.summary;


import java.util.List;

import no.uib.ii.ship.validation.ValidationException;
import no.uib.ii.ship.validation.annotations.BoolType;
import no.uib.ii.ship.validation.annotations.cross.CrossValidation;
import no.uib.ii.ship.validation.annotations.method.ValErr;
import no.uib.ii.ship.validation.annotations.method.Validation;
import no.uib.ii.ship.validation.constraints.AnnotationObject;



/**
 * Class AnnotationSummary. Represent results from a property-test (marked with {@link Validation})
 * or a cross-test (marked with {@link CrossValidation}) test.
 * The list of children is always non-empty. The children of this are either zero or more
 * {@link AnnotationSummary}s and possibly a {@link LeafSummary}),
 * or a single {@link ValidationSummary}. (See {@link AnnotationObject}).
 * The parent is {@link MethodSummary}, {@link AnnotationSummary}, or {@link CrossSummary}
 */
public class AnnotationSummary implements ISummary{

	/** The bool_op. */
	private final BoolType bool_op;


	/** The name of the annotation object */
	private final String annotationName;



	/** The children. */
	private final List<ISummary> children;


	/** The all true. */
	private final boolean testResult;


	/** The all true. */
	private final boolean allTrue;

	/** The one true. */
	private final boolean oneTrue;

	/** The all false. */
	private final boolean allFalse;

	private ValErr errorMesage;

	/**
	 * Instantiates a new summary node.
	 * @param annotationName
	 * @param errorMessage
	 * @param children
	 * @param bool_op
	 */
	public AnnotationSummary (String annotationName, ValErr errorMessage, List<ISummary> children, BoolType bool_op) {
		this.children = children;
		this.bool_op = bool_op;
		this.annotationName = annotationName;
		this.errorMesage = errorMessage;

		boolean all = true;
		boolean one = false;
		boolean none = true;

		for(ISummary cs : children){
			if(cs.getTestResult()){
				one = true;
				none = false;
			}
			else
				all = false;
		}

		this.allFalse = none;
		this.allTrue = all;
		this.oneTrue = one;

		this.testResult = this.getSuccess();

	}


	private boolean getSuccess(){
		switch(this.bool_op){
		case ALLFALSE :
			return  this.allFalse;
		case AND :
			return  this.allTrue;
		case OR :
			return  this.oneTrue;
		default:
			assert(false);
			return false;
		}
	}

	/**
	 * Instantiates a new summary node with conjunction (the default boolean operator)
	 * Used by e.g. MethodSummary
	 * @param annotationName
	 * @param errorMessage
	 * @param children
	 */
	public AnnotationSummary (String annotationName, ValErr errorMessage, List<ISummary> children) {
		this(annotationName, errorMessage, children, BoolType.AND);
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public ValErr getErrorMessage( ) {
		return this.errorMesage;
	}




	/**
	 * @return True if all the tests represented by the meta-annotations are true
	 */
	public boolean getAllTrue(){ return allTrue; }

	/**
	 * @return True if all the tests represented by the meta-annotations are false
	 */
	public boolean getAllFalse(){ return this.allFalse; }


	/**
	 * Get the value of success.
	 *
	 * @return the value of success
	 */
	@Override
	public boolean getTestResult ( ) {
		return this.testResult;
	}

	/**
	 * Set the value of bool_op.
	 *
	 * @param newVar the new value of bool_op
	 */
	//public void setBool_op ( BoolType newVar ) {
	//	bool_op = newVar;
	//}

	/**
	 * Get the value of bool_op.
	 *
	 * @return the value of bool_op
	 */
	public BoolType getBool_op ( ) {
		return bool_op;
	}

	/**

	/**
	 * Gets the children.
	 *
	 * @return       List<InternalNode>
	 */
	public List<ISummary> getChildren(  )
	{
		return this.children;
	}


	/**
	 * @return The name of the annotation that represents the test
	 */
	public String getAnnotationName(){
		return this.annotationName;
	}


	/**
	 * Checks whether this test is an annotation with no meta-annotation tests.
	 * In that case, it must have an inner class implementing
	 * ICrossTester or IPropertyTester, and this is the single child
	 *
	 * @return true if the only child is representing an inner class base case test
	 */
	public boolean isBaseCase(){
		List<ISummary> cr = this.getChildren();
		if(cr.size() < 1)
			throw new ValidationException(
					this.annotationName
					+ " has neither inner test nor meta-annotations.");
		ISummary child = cr.get(0);
		if(cr.size()>1)
			return false;
		return child.getChildren().size() == 0;
	}

	/**
	 * To string.
	 *
	 * @param i the i
	 *
	 * @return       String
	 */
	public String toString(int i){
		String out = "";
		ValErr err=this.getErrorMessage();
		if (err!=null)
			out += "+Test: " + this.getAnnotationName() + " (Error Message: " + err.message() +")";
		else
			out += "+Test: " + this.getAnnotationName();

		if (i!=1){
			if(!this.isBaseCase()){
				String out2 = "";
				switch(this.getBool_op()){
				case ALLFALSE:
					out2= (" because the following test(s) should have failed: " + allFalse(out2,i));
					break;
				case OR:
					out2 = (" because none of the following test(s) succeeded: " + or(out2,i));
					break;
				case AND:
					out2 = (" because the following test(s) did not succeed: " + and(out2,i));
					break;
				}
				out += out2;
			}
		}
		return out;
	}

	/**
	 * Tree printing.
	 *
	 * @param out2 the out2
	 *
	 * @return the string
	 */
	public String treePrinting(String out2){
		String out = "";
		if(out2 != null)
			out += out2.replaceAll("\\n",  "\n | ");
		out+="\n |-";
		return out;
	}

	/**
	 * Prints the last child.
	 *
	 * @param out2 the out2
	 *
	 * @return the string
	 */
	public String printLastChild(String out2){
		if(out2 != null)
			return out2.replaceAll("\\n",  "\n  ");
		else
			return "";
	}






	/**
	 * All false.
	 *
	 * @param out the out
	 * @param i the i
	 *
	 * @return the string
	 */
	public String allFalse(String out, int i){
		String out2 = null;
		List<ISummary> cr = this.getChildren();
		for(ISummary n : cr){
			if (n.getTestResult()){
				out += treePrinting(out2);
				out2 = n.toString(i-1);
			}
		}
		out += printLastChild(out2);
		return out;
	}

	/**
	 * Or.
	 *
	 * @param out the out
	 * @param i the i
	 *
	 * @return the string
	 */
	public String or(String out, int i){
		String out2 = null;
		List<ISummary> cr = this.getChildren();
		for(ISummary n : cr){
			if (!n.getTestResult()){
				out += treePrinting(out2);
				out2 = n.toString(i-1);
			}
		}
		out += printLastChild(out2);
		return out;
	}

	/**
	 * And.
	 *
	 * @param out the out
	 * @param i the i
	 *
	 * @return the string
	 */
	public String and(String out, int i){
		String out2 = null;
		List<ISummary> cr = this.getChildren();
		for(ISummary n : cr){
			if (!n.getTestResult()){
				out += treePrinting(out2);
				out2=n.toString(i-1);
			}
		}
		out += printLastChild(out2);
		return out;
	}
}
