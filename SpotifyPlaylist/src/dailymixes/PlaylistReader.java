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

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;

// -------------------------------------------------------------------------
/**
 * Reads play list and song data from input files
 * 
 * @author etsub
 * @version Nov 11, 2025
 */
public class PlaylistReader
{
    private ArrayQueue<Song> queue;
    private Playlist[] playlists;

    /**
     * Expected number of tokens in a play list line
     */
    public static final int PLAYLIST_TOKENS = 8;
    /**
     * Expected number of tokens in a song line
     */
    public static final int SONG_TOKENS = 5;

    // ----------------------------------------------------------
    /**
     * Create a new PlaylistReader object.
     * 
     * @param songsFileName
     *            name of the songs file
     * @param playlistsFileName
     *            name of the play lists file
     * @throws ParseException
     *             if file format is wrong
     * @throws DailyMixDataException
     *             if data is invalid
     * @throws FileNotFoundException
     *             if file doesn't exist
     */
    public PlaylistReader(String songsFileName, String playlistsFileName)
        throws ParseException,
        DailyMixDataException,
        FileNotFoundException
    {
        queue = readQueueFile(songsFileName);
        playlists = readPlaylistFile(playlistsFileName);

        PlaylistCalculator calculator =
            new PlaylistCalculator(queue, playlists);
        new PlaylistWindow(calculator);
    }


    /**
     * Creates a new PlaylistReader and starts the program.
     */
    private Playlist[] readPlaylistFile(String playlistFileName)
        throws ParseException,
        DailyMixDataException,
        FileNotFoundException
    {
        Playlist[] parsedPlaylists =
            new Playlist[PlaylistCalculator.NUM_PLAYLISTS];

        File newFile = new File(playlistFileName);
        Scanner file = new Scanner(newFile);

        int playlistCounter = 0;

        while (file.hasNextLine()
            && playlistCounter < PlaylistCalculator.NUM_PLAYLISTS)
        {
            String read = file.nextLine();
            String[] tokens = read.split(",\\s*");

            if (tokens.length < 8)
            {
                throw new ParseException("Not enough tokens on line", 0);
            }
            else if (tokens.length > 8)
            {
                throw new ParseException("Too many tokens on line", 0);
            }
            else
            {
                String playlistName = tokens[0];
                int minPop = Integer.valueOf(tokens[1]);
                int minRock = Integer.valueOf(tokens[2]);
                int minCountry = Integer.valueOf(tokens[3]);
                int maxPop = Integer.valueOf(tokens[4]);
                int maxRock = Integer.valueOf(tokens[5]);
                int maxCountry = Integer.valueOf(tokens[6]);
                int maxSongsPerPlaylist = Integer.valueOf(tokens[7]);

                if (!isInValidPercentRange(minPop, minRock, minCountry))
                {
                    throw new DailyMixDataException(
                        "Invalid min genre percentages");
                }

                if (!isInValidPercentRange(maxPop, maxRock, maxCountry))
                {
                    throw new DailyMixDataException(
                        "Invalid max genre percentages");
                }

                Playlist newPlaylist = new Playlist(
                    playlistName,
                    minPop,
                    minRock,
                    minCountry,
                    maxPop,
                    maxRock,
                    maxCountry,
                    maxSongsPerPlaylist);

                parsedPlaylists[playlistCounter] = newPlaylist;
                playlistCounter++;
            }
        }

        if (playlistCounter < PlaylistCalculator.NUM_PLAYLISTS)
        {
            throw new DailyMixDataException("Not enough playlists");
        }

        file.close();
        return parsedPlaylists;
    }


    /**
     * Reads the songs file and creates a queue of Song objects.
     */
    private ArrayQueue<Song> readQueueFile(String songFileName)
        throws ParseException,
        DailyMixDataException,
        FileNotFoundException
    {
        ArrayQueue<Song> parsedSongs =
            new ArrayQueue<Song>(ArrayQueue.DEFAULT_CAPACITY);

        File newFile = new File(songFileName);
        Scanner file = new Scanner(newFile);

        while (file.hasNextLine())
        {
            String read = file.nextLine();
            String[] tokens = read.split(",\\s*");

            if (tokens.length != SONG_TOKENS
                && tokens.length != SONG_TOKENS - 1)
            {
                throw new ParseException("Invalid number of tokens", 0);
            }

            String playlist = "";

            String songName = tokens[0];
            int pop = Integer.valueOf(tokens[1]);
            int rock = Integer.valueOf(tokens[2]);
            int country = Integer.valueOf(tokens[3]);

            if (tokens.length == SONG_TOKENS)
            {
                playlist = tokens[4];
            }

            if (!isInValidPercentRange(pop, rock, country))
            {
                throw new DailyMixDataException("Invalid genre percentages");
            }

            Song newSong = new Song(songName, pop, rock, country, playlist);
            parsedSongs.enqueue(newSong);
        }

        file.close();
        return parsedSongs;
    }


    /**
     * Checks if three numbers are all between 0 and 100.
     */
    private boolean isInValidPercentRange(int num1, int num2, int num3)
    {
        boolean valid = num1 >= PlaylistCalculator.MIN_PERCENT
            && num1 <= PlaylistCalculator.MAX_PERCENT
            && num2 >= PlaylistCalculator.MIN_PERCENT
            && num2 <= PlaylistCalculator.MAX_PERCENT
            && num3 >= PlaylistCalculator.MIN_PERCENT
            && num3 <= PlaylistCalculator.MAX_PERCENT;

        return valid;
    }
}
