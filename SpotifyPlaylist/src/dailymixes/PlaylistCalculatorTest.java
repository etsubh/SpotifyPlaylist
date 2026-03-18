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

import student.TestCase;
import list.AList;

// -------------------------------------------------------------------------
/**
 * The test case to ensure the Play list calculator class works correctly
 * 
 * @author etsub
 * @version Nov 11, 2025
 */
public class PlaylistCalculatorTest
    extends TestCase
{
    private PlaylistCalculator calculator;
    private ArrayQueue<Song> queue;
    private Playlist[] playlists;
    private Song song1;
    private Song song2;
    private Song song3;

    /**
     * sets up the test cases
     */
    public void setUp()
    {
        queue = new ArrayQueue<Song>();
        playlists = new Playlist[3];

        playlists[0] = new Playlist("Daily Mix 1", 0, 25, 70, 10, 35, 90, 5);
        playlists[1] = new Playlist("Daily Mix 2", 20, 40, 30, 50, 60, 50, 8);
        playlists[2] = new Playlist("Daily Mix 3", 60, 20, 10, 80, 40, 30, 10);

        song1 = new Song("Song1", 5, 30, 80, "Daily Mix 1");
        song2 = new Song("Song2", 35, 50, 40, "Daily Mix 2");
        song3 = new Song("Song3", 70, 25, 15, "");

        queue.enqueue(song1);
        queue.enqueue(song2);
        queue.enqueue(song3);

        calculator = new PlaylistCalculator(queue, playlists);
    }


    // ----------------------------------------------------------
    /**
     * Tests the constructor works correctly.
     */
    public void testConstructor()
    {
        assertNotNull(calculator);
        assertEquals(3, calculator.getQueue().getSize());
        assertEquals(3, calculator.getPlaylists().length);
    }


    // ----------------------------------------------------------
    /**
     * Tests constructor throws exception when queue is null.
     */
    public void testConstructorNullQueue()
    {
        Exception thrown = null;
        try
        {
            new PlaylistCalculator(null, playlists);
        }
        catch (Exception e)
        {
            thrown = e;
        }
        assertTrue(thrown instanceof IllegalArgumentException);
    }


    // ----------------------------------------------------------
    /**
     * Tests getQueue returns the correct queue.
     */
    public void testGetQueue()
    {
        assertEquals(queue, calculator.getQueue());
    }


    // ----------------------------------------------------------
    /**
     * Tests getPlaylists returns the correct array.
     */
    public void testGetPlaylists()
    {
        assertEquals(playlists, calculator.getPlaylists());
    }


    // ----------------------------------------------------------
    /**
     * Tests getRejectedTracks returns an empty list initially.
     */
    public void testGetRejectedTracks()
    {
        AList<Song> rejected = calculator.getRejectedTracks();
        assertNotNull(rejected);
        assertEquals(0, rejected.getLength());
    }


    // ----------------------------------------------------------
    /**
     * Tests getPlaylistIndex finds playlists correctly.
     */
    public void testGetPlaylistIndex()
    {
        assertEquals(0, calculator.getPlaylistIndex("Daily Mix 1"));
        assertEquals(1, calculator.getPlaylistIndex("Daily Mix 2"));
        assertEquals(2, calculator.getPlaylistIndex("Daily Mix 3"));
        assertEquals(-1, calculator.getPlaylistIndex("Nonexistent"));
    }


    // ----------------------------------------------------------
    /**
     * Tests getPlaylistForSong with null song returns null.
     */
    public void testGetPlaylistForSongNull()
    {
        assertNull(calculator.getPlaylistForSong(null));
    }


    // ----------------------------------------------------------
    /**
     * Tests song with valid suggestion goes to correct playlist.
     */
    public void testGetPlaylistForSongWithValidSuggestion()
    {
        Playlist result = calculator.getPlaylistForSong(song1);
        assertNotNull(result);
        assertEquals("Daily Mix 1", result.getName());
    }


    // ----------------------------------------------------------
    /**
     * Tests song with invalid suggestion gets placed elsewhere.
     */
    public void testGetPlaylistForSongWithInvalidSuggestion()
    {
        Song invalidSong = new Song("Invalid", 5, 30, 80, "Fake Playlist");
        Playlist result = calculator.getPlaylistForSong(invalidSong);
        assertNotNull(result);
    }


    // ----------------------------------------------------------
    /**
     * Tests song with no suggestion gets placed in best playlist.
     */
    public void testGetPlaylistForSongNoSuggestion()
    {
        Playlist result = calculator.getPlaylistForSong(song3);
        assertNotNull(result);
        assertEquals("Daily Mix 3", result.getName());
    }


    // ----------------------------------------------------------
    /**
     * Tests unqualified song returns null.
     */
    public void testGetPlaylistForSongUnqualified()
    {
        Song unqualified = new Song("Bad", 0, 0, 0, "Daily Mix 1");
        Playlist result = calculator.getPlaylistForSong(unqualified);
        assertNull(result);
    }


    // ----------------------------------------------------------
    /**
     * Tests adding a song to play list works.
     */
    public void testAddSongToPlaylist()
    {
        assertTrue(calculator.addSongToPlaylist());
        assertEquals(2, calculator.getQueue().getSize());
        assertEquals(1, playlists[0].getNumberOfSongs());
    }


    // ----------------------------------------------------------
    /**
     * Tests adding song from empty queue returns false.
     */
    public void testAddSongToPlaylistEmptyQueue()
    {
        ArrayQueue<Song> emptyQueue = new ArrayQueue<Song>();
        PlaylistCalculator calc = new PlaylistCalculator(emptyQueue, playlists);
        assertFalse(calc.addSongToPlaylist());
    }


    // ----------------------------------------------------------
    /**
     * Tests adding unqualified song returns false.
     */
    public void testAddSongToPlaylistUnqualified()
    {
        ArrayQueue<Song> testQueue = new ArrayQueue<Song>();
        Song bad = new Song("Bad", 0, 0, 0, "Daily Mix 1");
        testQueue.enqueue(bad);
        PlaylistCalculator calc = new PlaylistCalculator(testQueue, playlists);
        assertFalse(calc.addSongToPlaylist());
        assertEquals(1, testQueue.getSize());
    }


    // ----------------------------------------------------------
    /**
     * Tests rejecting a song moves it to rejected list.
     */
    public void testReject()
    {
        calculator.reject();
        assertEquals(2, calculator.getQueue().getSize());
        assertEquals(1, calculator.getRejectedTracks().getLength());
        assertEquals(song1, calculator.getRejectedTracks().getEntry(0));
    }


    // ----------------------------------------------------------
    /**
     * Tests rejecting from empty queue does nothing.
     */
    public void testRejectEmptyQueue()
    {
        ArrayQueue<Song> emptyQueue = new ArrayQueue<Song>();
        PlaylistCalculator calc = new PlaylistCalculator(emptyQueue, playlists);
        calc.reject();
        assertEquals(0, calc.getRejectedTracks().getLength());
    }


    // ----------------------------------------------------------
    /**
     * Tests rejecting multiple songs.
     */
    public void testMultipleRejects()
    {
        calculator.reject();
        calculator.reject();
        assertEquals(2, calculator.getRejectedTracks().getLength());
        assertEquals(song1, calculator.getRejectedTracks().getEntry(0));
        assertEquals(song2, calculator.getRejectedTracks().getEntry(1));
    }


    // ----------------------------------------------------------
    /**
     * Tests class constants have correct values.
     */
    public void testConstants()
    {
        assertEquals(3, PlaylistCalculator.NUM_PLAYLISTS);
        assertEquals(0, PlaylistCalculator.MIN_PERCENT);
        assertEquals(100, PlaylistCalculator.MAX_PERCENT);
    }


    // ----------------------------------------------------------
    /**
     * Tests song for full playlist returns null.
     */
    public void testGetPlaylistForSongFullPlaylist()
    {
        for (int i = 0; i < 5; i++)
        {
            playlists[0].addSong(new Song("Filler" + i, 5, 30, 80, ""));
        }

        Song testSong = new Song("Test", 5, 30, 80, "Daily Mix 1");
        Playlist result = calculator.getPlaylistForSong(testSong);
        assertNull(result);
    }


    // ----------------------------------------------------------
    /**
     * Tests adding multiple songs in sequence.
     */
    public void testAddMultipleSongs()
    {
        assertTrue(calculator.addSongToPlaylist());
        assertTrue(calculator.addSongToPlaylist());
        assertTrue(calculator.addSongToPlaylist());

        assertEquals(0, calculator.getQueue().getSize());
        assertTrue(calculator.getRejectedTracks().isEmpty());
    }


    // ----------------------------------------------------------
    /**
     * Tests song goes to playlist with most capacity.
     */
    public void testGetPlaylistWithMaximumCapacity()
    {
        Song noSuggestion = new Song("NoSuggestion", 70, 25, 15, "");
        Playlist result = calculator.getPlaylistForSong(noSuggestion);
        assertNotNull(result);
        assertEquals("Daily Mix 3", result.getName());
    }


    // ----------------------------------------------------------
    /**
     * Tests song with empty suggestion string.
     */
    public void testGetPlaylistForSongEmptySuggestion()
    {
        Song emptySuggestion = new Song("Empty", 70, 25, 15, "");
        Playlist result = calculator.getPlaylistForSong(emptySuggestion);
        assertNotNull(result);
    }


    // ----------------------------------------------------------
    /**
     * Tests getPlaylistIndex with edge cases.
     */
    public void testGetPlaylistIndexEdgeCases()
    {
        assertEquals(-1, calculator.getPlaylistIndex(""));
        assertEquals(-1, calculator.getPlaylistIndex(null));
    }


    // ----------------------------------------------------------
    /**
     * Tests adding song to full play list fails.
     */
    public void testAddSongToFullPlaylist()
    {
        for (int i = 0; i < 5; i++)
        {
            playlists[0].addSong(new Song("Fill" + i, 5, 30, 80, ""));
        }

        ArrayQueue<Song> testQueue = new ArrayQueue<Song>();
        Song testSong = new Song("Test", 5, 30, 80, "Daily Mix 1");
        testQueue.enqueue(testSong);

        PlaylistCalculator calc = new PlaylistCalculator(testQueue, playlists);
        assertFalse(calc.addSongToPlaylist());
    }


    // ----------------------------------------------------------
    /**
     * Tests rejecting then adding songs.
     */
    public void testRejectThenAdd()
    {
        calculator.reject();
        assertEquals(1, calculator.getRejectedTracks().getLength());

        assertTrue(calculator.addSongToPlaylist());
        assertEquals(1, calculator.getQueue().getSize());
    }


    // ----------------------------------------------------------
    /**
     * Tests song that fits no play list returns null.
     */
    public void testGetPlaylistForSongNoValidPlaylist()
    {
        Song impossible = new Song("Impossible", 99, 99, 99, "");
        Playlist result = calculator.getPlaylistForSong(impossible);
        assertNull(result);
    }


    // ----------------------------------------------------------
    /**
     * Tests play list priority based on capacity.
     */
    public void testPlaylistPriority()
    {
        Song fits23 = new Song("Fits2and3", 65, 25, 15, "");
        Playlist result = calculator.getPlaylistForSong(fits23);
        assertNotNull(result);
        assertEquals("Daily Mix 3", result.getName());
    }


    // ----------------------------------------------------------
    /**
     * Tests suggested play list that doesn't qualify returns null.
     */
    public void testGetPlaylistForSongWithSuggestionButUnqualified()
    {
        Song suggested = new Song("Suggested", 99, 99, 99, "Daily Mix 1");
        Playlist result = calculator.getPlaylistForSong(suggested);
        assertNull(result);
    }


    // ----------------------------------------------------------
    /**
     * Tests canAccept with null play list
     */
    public void testCanAcceptWithNullPlaylist()
    {
        ArrayQueue<Song> testQueue = new ArrayQueue<Song>();
        Song testSong = new Song("Test", 99, 99, 99, "");
        testQueue.enqueue(testSong);

        PlaylistCalculator calc = new PlaylistCalculator(testQueue, playlists);
        Playlist result = calc.getPlaylistForSong(testSong);
        assertNull(result);
    }


    // ----------------------------------------------------------
    /**
     * Tests canAccept with null song via getPlaylistForSong
     */
    public void testCanAcceptWithNullSong()
    {
        Playlist result = calculator.getPlaylistForSong(null);
        assertNull(result);
    }


    // ----------------------------------------------------------
    /**
     * Tests getPlaylistWithMaximumCapacity with null song
     */
    public void testGetPlaylistWithMaximumCapacityNull()
    {
        Playlist result = calculator.getPlaylistForSong(null);
        assertNull(result);
    }


    // ----------------------------------------------------------
    /**
     * Tests getPlaylistForSong with null suggested play list name.
     */
    public void testGetPlaylistForSongNullSuggestion()
    {
        Song song = new Song("Test", 70, 25, 15, null);
        Playlist result = calculator.getPlaylistForSong(song);
        assertNotNull(result);
    }


    // ----------------------------------------------------------
    /**
     * Tests canAccept handles null play list and valid song.
     */
    public void testCanAcceptNullPlaylist()
    {
        Song validSong = new Song("ValidSong", 50, 40, 30, "Daily Mix 1");
        PlaylistCalculator calc = new PlaylistCalculator(queue, playlists);

        Playlist result = calc.getPlaylistForSong(validSong);

        assertNull(calc.getPlaylistForSong(null));
        assertNull(
            calc.getPlaylistForSong(
                new Song("NoList", 50, 40, 30, "Unknown Mix")));
    }


    /**
     * Tests canAccept when song doesn't qualify for play list.
     */
    public void testCanAcceptUnqualifiedSong()
    {
        Song badSong = new Song("Unqualified", 0, 0, 0, "Daily Mix 1");

        Playlist result = calculator.getPlaylistForSong(badSong);
        assertNull(result);
    }

}
