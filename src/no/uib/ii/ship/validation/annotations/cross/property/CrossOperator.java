/**
 *
 */
package no.uib.ii.ship.validation.annotations.cross.property;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import no.uib.ii.ship.validation.annotations.cross.CrossValidation;
import no.uib.ii.ship.validation.annotations.method.Validation;
import no.uib.ii.ship.validation.constraints.CrossPropertyAnnotation;
import no.uib.ii.ship.validation.summary.AnnotationSummary;


/**
 * Creates a cross-test from a property-test and a "summing" function.
 * The properties to be tested by this test are summed by the operator {@link #value()}.
 * The property tests represented by the property-annotations "together" with this annotation, are then run on this sum.
 * Example usage can be seen in {@link SumMin}. That is, the annotation having a CrossProperty as meta-annotation should also have a {@link CrossValidation} meta-annotation,
 * while the other meta-annotations should represent property-tests and be marked {@link Validation}.
 * This annotation is represented in the framework with {@link CrossPropertyAnnotation}.
 * The summary of the result of this test is returned as a {@link AnnotationSummary}.
 *
 *
 * @author Dag Hovland and Federico Mancini
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target({ElementType.ANNOTATION_TYPE})
public @interface CrossOperator{
	/**
	 * This class contains one binary operator. This operator
	 * is used to get one value ("a sum") from the whole list of values
	 */
	Class<? extends ICrossOperator<?>> value();

}
