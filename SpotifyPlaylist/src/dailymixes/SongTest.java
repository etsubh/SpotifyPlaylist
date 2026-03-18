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

// ------------------------------------------------------------------------
/**
 * The test case to ensure the song class works correctly
 * 
 * @author etsub
 * @version Nov 4, 2025
 */
public class SongTest
    extends TestCase
{

    private Song song1;
    private Song song2;
    private Song song3;
    private Song noPlaylistSong;

    /**
     * Test set up
     */
    public void setUp()
    {
        song1 = new Song("The Final Countdown", 30, 45, 3, "p1");
        song2 = new Song("The Final Countdown", 30, 45, 3, "p1");
        song3 = new Song("Our Song", 24, 14, 50, "p2");
        noPlaylistSong = new Song("Party Rock Anthem", 2, 5, 4, "");
    }


    /**
     * Tests the constructor and getter methods
     */
    public void testConstructorAndGetters()
    {
        assertEquals("The Final Countdown", song1.getName());
        assertEquals("p1", song1.getPlaylistName());
        assertNotNull(song1.getGenreSet());
        assertEquals(30, song1.getGenreSet().getPop());
        assertEquals(45, song1.getGenreSet().getRock());
        assertEquals(3, song1.getGenreSet().getCountry());
    }


    /**
     * Tests the getName method
     */
    public void testGetName()
    {
        assertEquals("The Final Countdown", song1.getName());
        assertEquals("Our Song", song3.getName());
        assertEquals("Party Rock Anthem", noPlaylistSong.getName());
    }


    /**
     * Tests the getPlaylistName method
     */
    public void testGetPlaylistName()
    {
        assertEquals("p1", song1.getPlaylistName());
        assertEquals("p2", song3.getPlaylistName());
        assertEquals("", noPlaylistSong.getPlaylistName());
    }


    /**
     * Tests the getGenreSet method
     */
    public void testGetGenreSet()
    {
        GenreSet genreSet1 = song1.getGenreSet();
        assertNotNull(genreSet1);
        assertEquals(30, genreSet1.getPop());
        assertEquals(45, genreSet1.getRock());
        assertEquals(3, genreSet1.getCountry());

        GenreSet genreSet2 = noPlaylistSong.getGenreSet();
        assertEquals(2, genreSet2.getPop());
        assertEquals(5, genreSet2.getRock());
        assertEquals(4, genreSet2.getCountry());
    }


    /**
     * Tests toString with a suggested play list
     */
    public void testToStringWithPlaylist()
    {
        String expected =
            "The Final Countdown Pop:30 Rock:45 Country:3 Suggested: p1";
        assertEquals(expected, song1.toString());

        String expected2 = "Our Song Pop:24 Rock:14 Country:50 Suggested: p2";
        assertEquals(expected2, song3.toString());
    }


    /**
     * Tests toString without a suggested play list
     */
    public void testToStringNoPlaylist()
    {
        String expected =
            "No-Playlist Party Rock Anthem Pop:2 Rock:5 Country:4";
        assertEquals(expected, noPlaylistSong.toString());
    }


    /**
     * Tests toString with various edge cases
     */
    public void testToStringEdgeCases()
    {
        Song zeroGenres = new Song("Zero Song", 0, 0, 0, "playlist1");
        assertEquals(
            "Zero Song Pop:0 Rock:0 Country:0 Suggested: playlist1",
            zeroGenres.toString());

        Song maxGenres = new Song("Max Song", 100, 100, 100, "");
        assertEquals(
            "No-Playlist Max Song Pop:100 Rock:100 Country:100",
            maxGenres.toString());
    }


    /**
     * Tests equals with identical songs
     */
    public void testEqualsIdentical()
    {
        assertTrue(song1.equals(song2));
        assertTrue(song2.equals(song1));
    }


    /**
     * Tests equals with the same object
     */
    public void testEqualsSameObject()
    {
        assertTrue(song1.equals(song1));
    }


    /**
     * Tests equals with different songs
     */
    public void testEqualsDifferent()
    {
        assertFalse(song1.equals(song3));
        assertFalse(song3.equals(song1));
    }


    /**
     * Tests equals with different names
     */
    public void testEqualsDifferentName()
    {
        Song differentName = new Song("Different Name", 30, 45, 3, "p1");
        assertFalse(song1.equals(differentName));
    }


    /**
     * Tests equals with different genre sets
     */
    public void testEqualsDifferentGenreSet()
    {
        Song differentGenre = new Song("The Final Countdown", 31, 45, 3, "p1");
        assertFalse(song1.equals(differentGenre));

        Song differentGenre2 = new Song("The Final Countdown", 30, 46, 3, "p1");
        assertFalse(song1.equals(differentGenre2));

        Song differentGenre3 = new Song("The Final Countdown", 30, 45, 4, "p1");
        assertFalse(song1.equals(differentGenre3));
    }


    /**
     * Tests equals with different suggested play lists
     */
    public void testEqualsDifferentPlaylist()
    {
        Song differentPlaylist =
            new Song("The Final Countdown", 30, 45, 3, "p2");
        assertFalse(song1.equals(differentPlaylist));

        Song emptyPlaylist = new Song("The Final Countdown", 30, 45, 3, "");
        assertFalse(song1.equals(emptyPlaylist));
    }


    /**
     * Tests equals with null
     */
    public void testEqualsNull()
    {
        assertFalse(song1.equals(null));
    }


    /**
     * Tests equals with different class
     */
    public void testEqualsDifferentClass()
    {
        String notASong = "Not a song";
        assertFalse(song1.equals(notASong));
    }


    /**
     * Tests equals with songs that have no play list
     */
    public void testEqualsNoPlaylist()
    {
        Song noPlaylist1 = new Song("Party Rock Anthem", 2, 5, 4, "");
        Song noPlaylist2 = new Song("Party Rock Anthem", 2, 5, 4, "");

        assertTrue(noPlaylist1.equals(noPlaylist2));
        assertTrue(noPlaylistSong.equals(noPlaylist1));
    }


    /**
     * Tests that songs with empty vs not empty play lists are not equal
     */
    public void testEqualsEmptyVsNonEmpty()
    {
        Song withPlaylist = new Song("Same Song", 10, 20, 30, "playlist");
        Song withoutPlaylist = new Song("Same Song", 10, 20, 30, "");

        assertFalse(withPlaylist.equals(withoutPlaylist));
        assertFalse(withoutPlaylist.equals(withPlaylist));
    }

}
