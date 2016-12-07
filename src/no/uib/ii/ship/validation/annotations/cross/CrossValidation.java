package no.uib.ii.ship.validation.annotations.cross;
import java.lang.annotation.*;

/**
 * Annotation used to mark validation annotations
 * that test the return values of more methods at the same time.
 * 
 * @author fma042
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
@Inherited
public @interface CrossValidation {}
