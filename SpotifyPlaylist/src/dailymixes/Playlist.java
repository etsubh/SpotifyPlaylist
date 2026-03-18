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
 * Play list class to make the different play lists
 * 
 * @author etsub
 * @version Nov 4, 2025
 */
public class Playlist
    implements Comparable<Playlist>
{
    private GenreSet minGenreSet;
    private GenreSet maxGenreSet;
    private Song[] songs;
    private int capacity;
    private int numberOfSongs;
    private String name;

    /**
     * Constructor for Play list class
     * 
     * @param playlistName
     *            the name of the play list
     * @param minPop
     *            minimum pop percentage required
     * @param minRock
     *            minimum rock percentage required
     * @param minCountry
     *            minimum country percentage required
     * @param maxPop
     *            maximum pop percentage allowed
     * @param maxRock
     *            maximum rock percentage allowed
     * @param maxCountry
     *            maximum country percentage allowed
     * @param playlistCap
     *            maximum capacity of the play list
     */
    public Playlist(
        String playlistName,
        int minPop,
        int minRock,
        int minCountry,
        int maxPop,
        int maxRock,
        int maxCountry,
        int playlistCap)
    {
        this.name = playlistName;
        this.minGenreSet = new GenreSet(minPop, minRock, minCountry);
        this.maxGenreSet = new GenreSet(maxPop, maxRock, maxCountry);
        this.capacity = playlistCap;
        this.numberOfSongs = 0;
        this.songs = new Song[playlistCap];
    }


    /**
     * Gets the minimum GenreSet requirements
     * 
     * @return the minimum GenreSet
     */
    public GenreSet getMinGenreSet()
    {
        return minGenreSet;
    }


    /**
     * Sets the name of the play list
     * 
     * @param name
     *            the new name
     */
    public void setName(String name)
    {
        this.name = name;
    }


    /**
     * Gets the number of spaces left in the play list
     * 
     * @return the number of available spaces
     */
    public int getSpacesLeft()
    {
        return capacity - numberOfSongs;
    }


    /**
     * Gets the maximum GenreSet requirements
     * 
     * @return the maximum GenreSet
     */
    public GenreSet getMaxGenreSet()
    {
        return maxGenreSet;
    }


    /**
     * Gets the current number of songs in the play list
     * 
     * @return the number of songs
     */
    public int getNumberOfSongs()
    {
        return numberOfSongs;
    }


    /**
     * Gets the array of songs
     * 
     * @return the Song array
     */
    public Song[] getSongs()
    {
        return songs;
    }


    /**
     * Gets the capacity of the play list
     * 
     * @return the capacity
     */
    public int getCapacity()
    {
        return capacity;
    }


    /**
     * Gets the name of the play list
     * 
     * @return the play list name
     */
    public String getName()
    {
        return name;
    }


    /**
     * Checks if the play list is full
     * 
     * @return true if the play list is at capacity, false otherwise
     */
    public boolean isFull()
    {
        return numberOfSongs >= capacity;
    }


    /**
     * Helper method to check if a song is qualified for this play list
     * 
     * @param possibleSong
     *            the song to check
     * @return true if the song meets the genre requirements, false otherwise
     */
    public boolean isQualified(Song possibleSong)
    {
        if (possibleSong == null)
        {
            return false;
        }
        return possibleSong.getGenreSet()
            .isWithinRange(minGenreSet, maxGenreSet);
    }


    /**
     * Attempts to add a song to the play list
     * 
     * @param newSong
     *            the song to add
     * @return true if the song was added successfully, false otherwise
     */
    public boolean addSong(Song newSong)
    {
        // Check if play list has room
        if (isFull())
        {
            return false;
        }

        // Check if song is qualified
        if (!isQualified(newSong))
        {
            return false;
        }

        // Add the song
        songs[numberOfSongs] = newSong;
        numberOfSongs++;
        return true;
    }


    /**
     * Returns a string representation of the play list Format: "Play list:
     * Name, # of songs: X (cap: Y), Requires: Pop:A%-B%, Rock:C%-D%,
     * Country:E%-F%"
     * 
     * @return string representation of the play list
     */
    public String toString()
    {
        StringBuilder str = new StringBuilder();

        str.append("Playlist: ");
        str.append(name);
        str.append(", # of songs: ");
        str.append(numberOfSongs);
        str.append(" (cap: ");
        str.append(capacity);
        str.append("), Requires: Pop:");
        str.append(minGenreSet.getPop());
        str.append("%-");
        str.append(maxGenreSet.getPop());
        str.append("%, Rock:");
        str.append(minGenreSet.getRock());
        str.append("%-");
        str.append(maxGenreSet.getRock());
        str.append("%, Country:");
        str.append(minGenreSet.getCountry());
        str.append("%-");
        str.append(maxGenreSet.getCountry());
        str.append("%");

        return str.toString();
    }


    /**
     * Checks if this play list is equal to another object
     * 
     * @param obj
     *            the object to compare to
     * @return true if the play lists are equal, false otherwise
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

        Playlist other = (Playlist)obj;
        if (!this.name.equals(other.name))
        {
            return false;
        }

        if (!this.minGenreSet.equals(other.minGenreSet))
        {
            return false;
        }

        if (!this.maxGenreSet.equals(other.maxGenreSet))
        {
            return false;
        }

        if (this.capacity != other.capacity)
        {
            return false;
        }

        if (this.numberOfSongs != other.numberOfSongs)
        {
            return false;
        }
        for (int i = 0; i < numberOfSongs; i++)
        {
            if (!this.songs[i].equals(other.songs[i]))
            {
                return false;
            }
        }

        return true;
    }


    /**
     * Compares this play list to another play list order
     * 
     * @param other
     *            the other play list to compare to
     * @return negative if this < other, positive if this > other, 0 if equal
     */
    public int compareTo(Playlist other)
    {
        if (this.capacity != other.capacity)
        {
            return this.capacity - other.capacity;
        }

        int thisSpaces = this.getSpacesLeft();
        int otherSpaces = other.getSpacesLeft();
        if (thisSpaces != otherSpaces)
        {
            return thisSpaces - otherSpaces;
        }

        int minComparison = this.minGenreSet.compareTo(other.minGenreSet);
        if (minComparison != 0)
        {
            return minComparison;
        }
        int maxComparison = this.maxGenreSet.compareTo(other.maxGenreSet);
        if (maxComparison != 0)
        {
            return maxComparison;
        }
        return this.name.compareTo(other.name);
    }

}
