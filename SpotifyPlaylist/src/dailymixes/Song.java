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
 * Song class for the different songs in a play list.
 * 
 * @author etsub
 * @version Nov 4, 2025
 */
public class Song
{
    private String name;
    private String suggestedPlaylist;
    private GenreSet genreSet;

    /**
     * Constructor for Song class
     * 
     * @param name
     *            the name of the song
     * @param pop
     *            the pop percentage (0-100)
     * @param rock
     *            the rock percentage (0-100)
     * @param country
     *            the country percentage (0-100)
     * @param suggested
     *            the suggested playlist name (can be empty string)
     */
    public Song(String name, int pop, int rock, int country, String suggested)
    {
        this.name = name;
        this.genreSet = new GenreSet(pop, rock, country);
        this.suggestedPlaylist = suggested;
    }


    /**
     * Gets the name of the song
     * 
     * @return the song name
     */
    public String getName()
    {
        return name;
    }


    /**
     * Gets the suggested play list name
     * 
     * @return the suggested play list name
     */
    public String getPlaylistName()
    {
        return suggestedPlaylist;
    }


    /**
     * Gets the GenreSet for this song
     * 
     * @return the GenreSet object
     */
    public GenreSet getGenreSet()
    {
        return genreSet;
    }


    /**
     * Returns a string representation of the song
     * 
     * @return string representation of the song
     */
    public String toString()
    {
        StringBuilder str = new StringBuilder();

        if (suggestedPlaylist.length() == 0)
        {
            str.append("No-Playlist ");
        }
        str.append(name);
        str.append(" ");
        str.append("Pop:");
        str.append(genreSet.getPop());
        str.append(" Rock:");
        str.append(genreSet.getRock());
        str.append(" Country:");
        str.append(genreSet.getCountry());

        if (suggestedPlaylist.length() > 0)
        {
            str.append(" Suggested: ");
            str.append(suggestedPlaylist);
        }

        return str.toString();
    }


    /**
     * Checks if this song is equal to another object
     * 
     * @param obj
     *            the object to compare to
     * @return true if the songs are equal, false otherwise
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

        Song other = (Song)obj;

        return this.name.equals(other.name)
            && this.genreSet.equals(other.genreSet)
            && this.suggestedPlaylist.equals(other.suggestedPlaylist);
    }
}
