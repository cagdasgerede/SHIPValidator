package no.uib.ii.ship.validation.annotations.method;
import no.uib.ii.ship.validation.*;

import java.lang.annotation.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.XMLConstants;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

// TODO: Auto-generated Javadoc
/**
 * Denoted that returned value should be valid xml according to a given schema.
 *
 * @author mpkgc
 */
@Retention(RetentionPolicy.RUNTIME)
@Validation
@Inherited
public @interface SchemaNode {

	/**
	 * File.
	 *
	 * @return the string
	 */
	String file() default "";

	/**
	 * Url.
	 *
	 * @return the path of schema to validate against
	 */
	String url() default "";

	/**
	 * The Class Tester.
	 */
	public static class Tester implements IPropertyTester<SchemaNode, org.w3c.dom.Node>{

		/* (non-Javadoc)
		 * @see no.uib.ii.ship.validation.IPropertyTester#runTest(java.lang.annotation.Annotation, java.lang.Object)
		 */
		public boolean runTest(SchemaNode r, org.w3c.dom.Node xml) throws ValidationException {
			javax.xml.validation.Schema schema;
			SchemaFactory sf = SchemaFactory.newInstance(
					XMLConstants.W3C_XML_SCHEMA_NS_URI);
			try{
				if(! r.file().equals(""))
					schema = sf.newSchema(new File(r.file()));
				else if(! r.url().equals(""))
					schema = sf.newSchema(new URL(r.url()));
				else
					throw new ValidationException("Invalid use of Schema: " + r + ": Either url or file of annotation Schema must be specified");
			} catch(SAXException e){
				throw new ValidationException("Error in Schema.runTest: " + r.file() + " is not a XML Schema.", e);
			} catch(MalformedURLException e){
				throw new ValidationException("Error in Schema.runTest:  " + r.url() + " is not the URL of a XML Schema", e);
			}
			javax.xml.validation.Validator xml_val = schema.newValidator();
			boolean validation_success = true;
			try{
				xml_val.validate(new DOMSource(xml));
			} catch (SAXException e){
				validation_success = false;
			} catch(IOException e){
				throw new ValidationException("Error while processing xml string", e);
			}
			return validation_success;
		}
	}
}