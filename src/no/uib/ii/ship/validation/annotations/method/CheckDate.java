package no.uib.ii.ship.validation.annotations.method;
import java.lang.annotation.*;
import java.util.*;
/*
 * Copyright (c) University of Bergen
 *
 *
 * Authors: Dag Hovland and Federico Mancini, SHIP, Dept. of Informatics
 */


import no.uib.ii.ship.validation.*;
import no.uib.ii.ship.validation.test.Date;


// TODO: Auto-generated Javadoc
/**
 * The Interface CheckDate.
 */
@Retention(RetentionPolicy.RUNTIME)
@Validation
@Inherited
public @interface CheckDate {

	/**
	 * The Class Tester.
	 */
	public static class Tester implements IPropertyTester<CheckDate,Date>{

		/* (non-Javadoc)
		 * @see no.uib.ii.ship.validation.IPropertyTester#runTest(java.lang.annotation.Annotation, java.lang.Object)
		 */
		public boolean runTest(CheckDate r, Date v) throws ValidationException {
			Calendar today= Calendar.getInstance();
			int day=Calendar.DAY_OF_MONTH;
			int month=Calendar.MONTH;
			int year=Calendar.YEAR;
			day=today.get(day);
			month=today.get(month);
			year=today.get(year);
			Date d = v;
			return d.getDay()>=day&&d.getMonth()>=month+1&&d.getYear()>=year;
		}
	}
}
