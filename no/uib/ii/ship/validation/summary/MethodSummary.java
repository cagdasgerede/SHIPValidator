/**
 *
 */
package no.uib.ii.ship.validation.summary;

import java.util.List;

import no.uib.ii.ship.validation.annotations.method.ValErr;

/**
 * Represents all property tests on a single property/method.
 * It is a child of {@link ValidationSummary}.
 * The children are {@link AnnotationSummary}s.
 *
 *
 *
 * @author Dag Hovland and Federico Mancini
 *
 */
public class MethodSummary extends AnnotationSummary implements ISummary {
	/** The name. */
	private final String name;

	/** The value. */
	private final String value;



	/**
	 * @param name The name of the method that is tested
	 * @param value The value that is tested
	 * @param children The list of child summmaries
	 * @param errorMessage An optional error message
	 */
	public MethodSummary ( String name, Object value, List<ISummary> children, ValErr errorMessage) {
		super("bogus annotation for root summary" , errorMessage, children);
		this.name = name;
		if(value != null){
			String values = value.toString();
			this.value= (values.length() > 30) ? (values.substring(0, 30) + "...") : values;
		} else
			this.value = "null";
	}



	/**
	 * To be overridden by other summary types
	 *
	 * @return The top line returned by toString.
	 */
	public String headerLine(){
		return "The value \""+value+"\" returned by method \""+ name +"\" has not passed the following property-test(s): \n";
	}

	/* (non-Javadoc)
	 * @see no.uib.ii.ship.validation.summary.ISummary#toString(int)
	 */
	@Override
	public String toString(int i) {
		String out = this.headerLine();
		if (i!=1){
			out += and("",i).substring(1);
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
	@Override
	public String treePrinting(String out2){
		String out = "";
		if(out2 != null)
			out += out2 ;
		out+="\n";
		return out;
	}

	/**
	 * Prints the last child.
	 *
	 * @param out2 the out2
	 *
	 * @return the string
	 */
	@Override
	public String printLastChild(String out2){
		if(out2 != null)
			return out2.replaceAll("\\n",  "\n");
		else
			return "";
	}

}
