package no.uib.ii.ship.validation.annotations.method;
import java.lang.annotation.*;

import no.uib.ii.ship.validation.annotations.cross.CrossValidation;

/**
 * Meta-annotation used for
 * (optional) error message given when validation fails.
 *
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE,ElementType.METHOD})
@Validation
@CrossValidation
public @interface ValErr{

    /**
     * Message.
     *
     * @return the text in message
     */
    String message() default "";

    /**
     * Log.
     *
     * @return the string
     */
    String log() default "";

    /**
     * User.
     *
     * @return the string
     */
    String user() default "";

    /**
     * System.
     *
     * @return the string
     */
    String system() default "";
}