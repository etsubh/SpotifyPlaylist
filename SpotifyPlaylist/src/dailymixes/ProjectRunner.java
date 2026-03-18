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

import java.io.FileNotFoundException;
import java.text.ParseException;

// -------------------------------------------------------------------------
/**
 * The main entry point for the Daily Mix project and runs the program by
 * creating a PlaylistReader object.
 * 
 * @author etsub
 * @version Nov 11, 2025
 */
public class ProjectRunner
{
    // ----------------------------------------------------------
    /**
     * It creates a PlaylistReader using either command line arguments or
     * default file name
     * 
     * @param args
     * @throws FileNotFoundException
     * @throws ParseException
     * @throws DailyMixDataException
     */
    public static void main(String[] args)
        throws FileNotFoundException,
        ParseException,
        DailyMixDataException
    {
        if (args.length == 2)
        {
            new PlaylistReader(args[0], args[1]);
        }
        else
        {
            new PlaylistReader("input.txt", "playlists.txt");
        }
    }
}
