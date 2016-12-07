/*
 * Copyright (c) University of Bergen
 *
 *
 * Authors: Dag Hovland and Federico Mancini, SHIP, Dept. of Informatics
 */

package no.uib.ii.ship.validation.annotations.method;
import java.lang.annotation.*;

import no.uib.ii.ship.validation.annotations.BoolTest;
import no.uib.ii.ship.validation.annotations.BoolType;


/**
 * Meta-annotation used on basic validation annotations.
 */
@Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.ANNOTATION_TYPE)
public @interface Validation {
}


/**
 * Denotes that returned value should be wellformed XML
 * @parameter val : specifies that this is a validation annotation, and can add general info
 * @author mpkgc
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Validation
@Inherited
@interface WellFormedXML {
}



/**
   @parameter Class: The class that this test can be done on
   @parameter getter The name of a method without parameters returning the value to be tested
**/
@Retention(RetentionPolicy.RUNTIME)
@Validation
@Inherited
@interface MethodValidation {
    Class<?> Class();
    String getter();
}





/**
    Example of combination of validation annotations into a new rule
    This would be the replacement for the collection of rules in a separate XML file

    @Validation and @Retention(RetentionPolicy.RUNTIME) are compulsory

    @Inherited is optional, but probably what you usually want
 **/

@BoolTest(BoolType.ALLFALSE)
@Validation
    @interface NotPayRange{}





@IntRange(min=0,max=10000)
@interface Positive {
}




