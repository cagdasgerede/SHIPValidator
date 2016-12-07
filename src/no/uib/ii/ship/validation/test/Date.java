package no.uib.ii.ship.validation.test;

// TODO: Auto-generated Javadoc
import no.uib.ii.ship.validation.annotations.method.IntRange;

/**
 * The Class Date.
 */
public class Date {

	/** The day. */
	private int day;

	/** The month. */
	private int month;

	/** The year. */
	private int year;

	/**
	 * Instantiates a new date.
	 *
	 * @param d the d
	 * @param m the m
	 * @param y the y
	 */
	public Date(int d, int m, int y){
		this.day=d;
		this.month=m;
		this.year=y;
	}

	/**
	 * Gets the day.
	 *
	 * @return the day
	 */
	@IntRange(min=1, max=30)
	public int getDay(){
		return this.day;
	}

	/**
	 * Gets the month.
	 *
	 * @return the month
	 */
	@IntRange(min=1, max=12)
	public int getMonth(){
		return this.month;
	}

	/**
	 * Gets the year.
	 *
	 * @return the year
	 */
	@IntRange(min=2000, max=2030)
	public int getYear(){
		return this.year;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		String s=this.day+"/"+month+"/"+year;
		return s;
	}

	/**
	 * Prints the date.
	 */
	public void printDate(){
		System.out.println(this.toString());
	}

}
