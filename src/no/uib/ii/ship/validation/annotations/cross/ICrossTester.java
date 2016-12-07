/*
 * Copyright (c) University of Bergen
 *
 *
 * Authors: Dag Hovland and Federico Mancini, SHIP, Dept. of Informatics
 */

package no.uib.ii.ship.validation.annotations.cross;
import java.lang.annotation.*;
import java.util.*;

import no.uib.ii.ship.validation.ValidationException;

/**
 * The Interface ICrossTester. The classes implementing this interface
 * should be inner classes in cross-annotations.
 *
 * @param <A> The annotation representing the test
 * @param <V> The type of the object property to be tested
 */
public interface ICrossTester<A extends Annotation, V>{

	/**
	 * Run test.
	 *
	 * @param a the a
	 * @param v the v
	 *
	 * @return true, if successful
	 *
	 * @throws ValidationException the validation exception
	 */
	public boolean runTest(A a, List<V> v) throws ValidationException;
}