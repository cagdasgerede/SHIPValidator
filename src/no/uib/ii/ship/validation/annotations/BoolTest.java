/*
 * Copyright (c) University of Bergen
 *
 *
 * Authors: Dag Hovland and Federico Mancini, SHIP, Dept. of Informatics
 */

package no.uib.ii.ship.validation.annotations;
import no.uib.ii.ship.validation.annotations.cross.*;
import no.uib.ii.ship.validation.annotations.method.Validation;

import java.lang.annotation.*;


/**
 * The boolean connectives.
 */
@Retention(RetentionPolicy.RUNTIME)
    @Validation
    @CrossValidation
    @Inherited
    @Target(ElementType.ANNOTATION_TYPE)
    public @interface BoolTest{

	/**
	 * Value.
	 *
	 * @return the bool type
	 */
	BoolType value();
    }