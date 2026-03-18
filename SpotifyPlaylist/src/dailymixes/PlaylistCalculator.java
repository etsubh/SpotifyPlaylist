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

import list.AList;
import java.util.Arrays;

// -------------------------------------------------------------------------
/**
 * Handles the logic for assigning songs to play lists.
 * 
 * @author etsub
 * @version Nov 11, 2025
 */
public class PlaylistCalculator
{
    private Playlist[] playlists;
    private ArrayQueue<Song> songQueue;
    private AList<Song> rejectedTracks;

    /**
     * Number of playlists in the system
     */
    public static final int NUM_PLAYLISTS = 3;
    /**
     * Minimum valid genre percentage
     */
    public static final int MIN_PERCENT = 0;
    /**
     * Maximum valid genre percentage
     */
    public static final int MAX_PERCENT = 100;

    // ----------------------------------------------------------
    /**
     * Create a new PlaylistCalculator object.
     * 
     * @param songQueue
     *            the song queue
     * @param playlists
     *            the playlist
     */
    public PlaylistCalculator(ArrayQueue<Song> songQueue, Playlist[] playlists)
    {
        if (songQueue == null)
        {
            throw new IllegalArgumentException("Queue cannot be null");
        }

        this.songQueue = songQueue;
        this.playlists = playlists;
        this.rejectedTracks = new AList<Song>();
    }


    // ----------------------------------------------------------
    /**
     * Gets the song queue.
     * 
     * @return the queue of songs
     */
    public ArrayQueue<Song> getQueue()
    {
        return songQueue;
    }


    // ----------------------------------------------------------
    /**
     * Gets the play lists array.
     * 
     * @return the array play list
     */
    public Playlist[] getPlaylists()
    {
        return playlists;
    }


    // ----------------------------------------------------------
    /**
     * Gets the list of rejected songs.
     * 
     * @return the rejected track list
     */
    public AList<Song> getRejectedTracks()
    {
        return rejectedTracks;
    }


    // ----------------------------------------------------------
    /**
     * Finds the index of a play list by name.
     * 
     * @param playlistName
     *            name of play list
     * @return index of the play list
     */
    public int getPlaylistIndex(String playlistName)
    {
        for (int i = 0; i < NUM_PLAYLISTS; i++)
        {
            if (playlists[i].getName().equals(playlistName))
            {
                return i;
            }
        }
        return -1;
    }


    /**
     * Checks if a playlist can accept a song.
     * 
     * @param playlist
     *            the playlist to check
     * @param song
     *            the song to add
     * @return true if the playlist can accept the song
     */
    private boolean canAccept(Playlist playlist, Song song)
    {
        if (playlist == null || song == null)
        {
            return false;
        }

        return !playlist.isFull() && playlist.isQualified(song);
    }


    /**
     * Finds the play list with the most capacity that can accept the song.
     * 
     * @param aSong
     *            the song to place
     * @return the best play list, or null if none accept
     */
    private Playlist getPlaylistWithMaximumCapacity(Song aSong)
    {
        if (aSong == null)
        {
            return null;
        }

        Playlist[] sortedPlaylists = Arrays.copyOf(playlists, playlists.length);
        Arrays.sort(sortedPlaylists);

        for (int i = sortedPlaylists.length - 1; i >= 0; i--)
        {
            Playlist currentPlaylist = sortedPlaylists[i];

            if (canAccept(currentPlaylist, aSong))
            {
                return currentPlaylist;
            }
        }

        return null;
    }


    // ----------------------------------------------------------
    /**
     * Determines which play list a song should go to.
     * 
     * @param nextSong
     *            the song to place
     * @return the play list song
     */
    public Playlist getPlaylistForSong(Song nextSong)
    {
        if (nextSong == null)
        {
            return null;
        }

        String suggestedPlaylistName = nextSong.getPlaylistName();

        if (suggestedPlaylistName != null && suggestedPlaylistName.length() > 0)
        {
            int playlistIndex = getPlaylistIndex(suggestedPlaylistName);

            if (playlistIndex != -1)
            {
                Playlist suggestedPlaylist = playlists[playlistIndex];

                if (canAccept(suggestedPlaylist, nextSong))
                {
                    return suggestedPlaylist;
                }
                else
                {
                    return null;
                }
            }
        }

        return getPlaylistWithMaximumCapacity(nextSong);
    }


    // ----------------------------------------------------------
    /**
     * Tries to add the next song from the queue to a play list.
     * 
     * @return true if the song is added
     */
    public boolean addSongToPlaylist()
    {
        if (songQueue.isEmpty())
        {
            return false;
        }

        Song nextSong = songQueue.getFront();
        Playlist targetPlaylist = getPlaylistForSong(nextSong);

        if (targetPlaylist != null)
        {
            targetPlaylist.addSong(nextSong);
            songQueue.dequeue();
            return true;
        }

        return false;
    }


    // ----------------------------------------------------------
    /**
     * Rejects the next song in the queue.
     */
    public void reject()
    {
        if (!songQueue.isEmpty())
        {
            Song rejectedSong = songQueue.dequeue();
            rejectedTracks.add(rejectedSong);
        }
    }

}
