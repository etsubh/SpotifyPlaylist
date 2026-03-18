// Virginia Tech Honor Code Pledge:

//

// As a Hokie, I will conduct myself with honor and integrity at all times.

// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.

// -- Etsub Habtewold (etsubh)
// LLM Statement:

//

// LLM Statement:

// I have not used any assistance for the assignment beyond course resources and
// staff.
package dailymixes;

// -------------------------------------------------------------------------
/**
 * Genre Set class that checks all the different Genres
 * 
 * @author etsub
 * @version Nov 4, 2025
 */
public class GenreSet
    implements Comparable<GenreSet>
{
    private int pop;
    private int rock;
    private int country;

    /**
     * Constructor for GenreSet
     * 
     * @param pop
     *            the pop percentage
     * @param rock
     *            the rock percentage
     * @param country
     *            the country percentage
     */
    public GenreSet(int pop, int rock, int country)
    {
        this.pop = pop;
        this.rock = rock;
        this.country = country;
    }


    /**
     * Gets the pop percentage
     * 
     * @return the pop percentage
     */
    public int getPop()
    {
        return pop;
    }


    /**
     * Gets the rock percentage
     * 
     * @return the rock percentage
     */
    public int getRock()
    {
        return rock;
    }


    /**
     * Gets the country percentage
     * 
     * @return the country percentage
     */
    public int getCountry()
    {
        return country;
    }


    /**
     * Checks if this GenreSet is less than or equal to another GenreSet and
     * returns true only if all three attributes are less than or equal to the
     * one in the other GenreSet
     * 
     * @param other
     *            the GenreSet to compare to
     * @return true if all attributes are false otherwise
     */
    public boolean isLessThanOrEqualTo(GenreSet other)
    {
        return this.pop <= other.pop && this.rock <= other.rock
            && this.country <= other.country;
    }


    /**
     * Checks if this GenreSet is within the range defined by a minimum and
     * maximum GenreSet
     * 
     * @param minGenreSet
     *            the minimum GenreSet
     * @param maxGenreSet
     *            the maximum GenreSet
     * @return true if within range, false otherwise
     */
    public boolean isWithinRange(GenreSet minGenreSet, GenreSet maxGenreSet)
    {
        return this.pop >= minGenreSet.pop && this.pop <= maxGenreSet.pop
            && this.rock >= minGenreSet.rock && this.rock <= maxGenreSet.rock
            && this.country >= minGenreSet.country
            && this.country <= maxGenreSet.country;
    }


    /**
     * Returns a string representation of the GenreSet
     * 
     * @return string representation
     */
    public String toString()
    {
        StringBuilder str = new StringBuilder();
        str.append("Pop:");
        str.append(pop);
        str.append(" Rock:");
        str.append(rock);
        str.append(" Country:");
        str.append(country);
        return str.toString();
    }


    /**
     * Checks if this GenreSet is equal to another object Two GenreSets are
     * equal if all three fields are equal
     * 
     * @param obj
     *            the object to compare to
     * @return true if equal, false otherwise
     */
    public boolean equals(Object obj)
    {
        if (obj == this)
        {
            return true;
        }

        if (obj == null)
        {
            return false;
        }

        if (obj.getClass() != this.getClass())
        {
            return false;
        }

        GenreSet other = (GenreSet)obj;

        return this.pop == other.pop && this.rock == other.rock
            && this.country == other.country;
    }


    /**
     * Compares this GenreSet to another GenreSet based on the sum of their
     * genre percentages
     * 
     * @param other
     *            the GenreSet to compare to
     * @return negative if this < other, positive if this > other, 0 if equal
     */
    public int compareTo(GenreSet other)
    {
        int thisSum = this.pop + this.rock + this.country;
        int otherSum = other.pop + other.rock + other.country;
        return thisSum - otherSum;
    }
}
