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
 * Exception thrown when there's invalid data in the play list or song input
 * files.
 * 
 * @author etsub
 * @version Nov 11, 2025
 */
public class DailyMixDataException
    extends Exception
{
    // ----------------------------------------------------------
    /**
     * Create a new DailyMixDataException object.
     * 
     * @param string
     *            the error message
     */
    public DailyMixDataException(String string)
    {
        super(string);
    }
}
