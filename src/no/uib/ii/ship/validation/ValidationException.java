/*
 * Copyright (c) 2006 NoWires Research Group
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to
 * deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or
 * sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 */
package no.uib.ii.ship.validation;

/**
 * This entity represents exceptions that occur during validation of an object.
 *
 * @author Yngve Espelid, yngvee@ii.uib.no
 */
public class ValidationException  extends RuntimeException{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * A simple constructor calling the super class constructor with the given
	 * parameters.
	 *
	 * @param s a string describing the exception
	 * @param t the root exception
	 */
	public ValidationException(String s, Throwable t){
		super(s,t);
	}

	/**
	 * A simple constructor calling the super class constructor.
	 *
	 * @param s a string describing the exception.
	 */
	public ValidationException(String s){
		super(s,null);
	}
}
