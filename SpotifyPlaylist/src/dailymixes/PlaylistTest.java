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

// -------------------------------------------------------------------------
/**
 * The test case to ensure the Play list class works correctly
 * 
 * @author etsub
 * @version Nov 4, 2025
 */
public class PlaylistTest
    extends TestCase
{
    private Playlist playlist;
    private Song qualifiedSong;
    private Song unqualifiedSong;

    /**
     * Sets up test fixtures
     */
    public void setUp()
    {
        playlist = new Playlist("Favorites", 0, 25, 70, 10, 35, 90, 5);

        qualifiedSong = new Song("Good Song", 5, 30, 80, "Favorites");

        unqualifiedSong = new Song("Bad Song", 50, 50, 50, "");
    }


    /**
     * Tests constructor and basic getters
     */
    public void testConstructor()
    {
        assertEquals("Favorites", playlist.getName());
        assertEquals(5, playlist.getCapacity());
        assertEquals(0, playlist.getNumberOfSongs());
        assertEquals(5, playlist.getSpacesLeft());
    }


    /**
     * Tests setName
     */
    public void testSetName()
    {
        playlist.setName("NewName");
        assertEquals("NewName", playlist.getName());
    }


    /**
     * Tests getMinGenreSet
     */
    public void testGetMinGenreSet()
    {
        GenreSet min = playlist.getMinGenreSet();
        assertEquals(0, min.getPop());
        assertEquals(25, min.getRock());
        assertEquals(70, min.getCountry());
    }


    /**
     * Tests getMaxGenreSet
     */
    public void testGetMaxGenreSet()
    {
        GenreSet max = playlist.getMaxGenreSet();
        assertEquals(10, max.getPop());
        assertEquals(35, max.getRock());
        assertEquals(90, max.getCountry());
    }


    /**
     * Tests isFull
     */
    public void testIsFull()
    {
        assertFalse(playlist.isFull());
        for (int i = 0; i < 5; i++)
        {
            playlist.addSong(new Song("Song" + i, 5, 30, 80, ""));
        }

        assertTrue(playlist.isFull());
    }


    /**
     * Tests getSpacesLeft
     */
    public void testGetSpacesLeft()
    {
        assertEquals(5, playlist.getSpacesLeft());
        playlist.addSong(qualifiedSong);
        assertEquals(4, playlist.getSpacesLeft());
    }


    /**
     * Tests isQualified with valid song
     */
    public void testIsQualified()
    {
        assertTrue(playlist.isQualified(qualifiedSong));
        assertFalse(playlist.isQualified(unqualifiedSong));
        assertFalse(playlist.isQualified(null));
    }


    /**
     * Tests addSong success
     */
    public void testAddSongSuccess()
    {
        assertTrue(playlist.addSong(qualifiedSong));
        assertEquals(1, playlist.getNumberOfSongs());
    }


    /**
     * Tests addSong failure cases
     */
    public void testAddSongFailure()
    {
        assertFalse(playlist.addSong(unqualifiedSong));
        for (int i = 0; i < 5; i++)
        {
            playlist.addSong(new Song("Song" + i, 5, 30, 80, ""));
        }
        assertFalse(playlist.addSong(qualifiedSong));
    }


    /**
     * Tests toString
     */
    public void testToString()
    {
        String expected = "Playlist: Favorites, # of songs: 0 (cap: 5), "
            + "Requires: Pop:0%-10%, Rock:25%-35%, Country:70%-90%";
        assertEquals(expected, playlist.toString());
    }


    /**
     * Tests equals
     */
    public void testEquals()
    {
        Playlist same = new Playlist("Favorites", 0, 25, 70, 10, 35, 90, 5);
        Playlist different = new Playlist("Other", 0, 25, 70, 10, 35, 90, 5);

        assertTrue(playlist.equals(playlist));
        assertTrue(playlist.equals(same));
        assertFalse(playlist.equals(different));
        assertFalse(playlist.equals(null));
        assertFalse(playlist.equals("string"));
    }


    /**
     * Tests equals with different songs
     */
    public void testEqualsWithSongs()
    {
        Playlist p1 = new Playlist("Test", 0, 0, 0, 100, 100, 100, 5);
        Playlist p2 = new Playlist("Test", 0, 0, 0, 100, 100, 100, 5);

        Song s1 = new Song("A", 50, 50, 50, "");
        p1.addSong(s1);

        assertFalse(p1.equals(p2));

        p2.addSong(s1);
        assertTrue(p1.equals(p2));
    }


    /**
     * Tests compareTo with capacity
     */
    public void testCompareTo()
    {
        Playlist larger = new Playlist("P1", 0, 0, 0, 100, 100, 100, 10);
        Playlist smaller = new Playlist("P2", 0, 0, 0, 100, 100, 100, 5);

        assertTrue(larger.compareTo(smaller) > 0);
        assertTrue(smaller.compareTo(larger) < 0);
        assertEquals(0, playlist.compareTo(playlist));
    }


    /**
     * Tests compareTo with spaces left
     */
    public void testCompareToSpaces()
    {
        Playlist p1 = new Playlist("P1", 0, 0, 0, 100, 100, 100, 5);
        Playlist p2 = new Playlist("P2", 0, 0, 0, 100, 100, 100, 5);

        p2.addSong(new Song("S", 50, 50, 50, ""));
        assertTrue(p1.compareTo(p2) > 0);
    }


    /**
     * Tests compareTo with name
     */
    public void testCompareToName()
    {
        Playlist alpha = new Playlist("Alpha", 0, 0, 0, 100, 100, 100, 5);
        Playlist beta = new Playlist("Beta", 0, 0, 0, 100, 100, 100, 5);

        assertTrue(alpha.compareTo(beta) < 0);
        assertTrue(beta.compareTo(alpha) > 0);
    }


    /**
     * Tests equals with songs - tests the loop that compares each song
     */
    public void testEqualsWithMultipleSongs()
    {
        Playlist p1 = new Playlist("Test", 0, 0, 0, 100, 100, 100, 5);
        Playlist p2 = new Playlist("Test", 0, 0, 0, 100, 100, 100, 5);

        Song s1 = new Song("Song1", 50, 50, 50, "");
        Song s2 = new Song("Song2", 40, 40, 40, "");
        Song s3 = new Song("Song3", 30, 30, 30, "");

        p1.addSong(s1);
        p1.addSong(s2);
        p1.addSong(s3);

        p2.addSong(s1);
        p2.addSong(s2);
        p2.addSong(s3);

        assertTrue(p1.equals(p2));
    }


    /**
     * Tests equals where songs differ in the middle of the array
     */
    public void testEqualsSongsDifferInMiddle()
    {
        Playlist p1 = new Playlist("Test", 0, 0, 0, 100, 100, 100, 5);
        Playlist p2 = new Playlist("Test", 0, 0, 0, 100, 100, 100, 5);

        Song s1 = new Song("Song1", 50, 50, 50, "");
        Song s2 = new Song("Song2", 40, 40, 40, "");
        Song s3 = new Song("Song3", 30, 30, 30, "");
        Song different = new Song("Different", 40, 40, 40, "");

        p1.addSong(s1);
        p1.addSong(s2);
        p1.addSong(s3);

        p2.addSong(s1);
        p2.addSong(different);
        p2.addSong(s3);

        assertFalse(p1.equals(p2));
    }


    /**
     * Tests equals where first song differs
     */
    public void testEqualsFirstSongDiffers()
    {
        Playlist p1 = new Playlist("Test", 0, 0, 0, 100, 100, 100, 5);
        Playlist p2 = new Playlist("Test", 0, 0, 0, 100, 100, 100, 5);

        Song s1 = new Song("Song1", 50, 50, 50, "");
        Song s2 = new Song("Song2", 40, 40, 40, "");
        Song different = new Song("Different", 50, 50, 50, "");

        p1.addSong(s1);
        p1.addSong(s2);

        p2.addSong(different);
        p2.addSong(s2);

        assertFalse(p1.equals(p2));
    }


    /**
     * Tests equals where last song differs
     */
    public void testEqualsLastSongDiffers()
    {
        Playlist p1 = new Playlist("Test", 0, 0, 0, 100, 100, 100, 5);
        Playlist p2 = new Playlist("Test", 0, 0, 0, 100, 100, 100, 5);

        Song s1 = new Song("Song1", 50, 50, 50, "");
        Song s2 = new Song("Song2", 40, 40, 40, "");
        Song different = new Song("Different", 40, 40, 40, "");

        p1.addSong(s1);
        p1.addSong(s2);

        p2.addSong(s1);
        p2.addSong(different);

        assertFalse(p1.equals(p2));
    }


    /**
     * Tests compareTo when minGenreSet differs (returns early)
     */
    public void testCompareToMinGenreSetDiffers()
    {
        Playlist p1 = new Playlist("P1", 20, 30, 40, 50, 60, 70, 10);
        Playlist p2 = new Playlist("P2", 10, 20, 30, 50, 60, 70, 10);
        assertTrue(p1.compareTo(p2) > 0);
        assertTrue(p2.compareTo(p1) < 0);
    }


    /**
     * Tests compareTo when minGenreSet same but maxGenreSet differs
     */
    public void testCompareToMaxGenreSetDiffers()
    {
        Playlist p1 = new Playlist("P1", 10, 20, 30, 60, 70, 80, 10);
        Playlist p2 = new Playlist("P2", 10, 20, 30, 50, 60, 70, 10);
        assertTrue(p1.compareTo(p2) > 0);
        assertTrue(p2.compareTo(p1) < 0);
    }


    /**
     * Tests compareTo when everything same except name
     */
    public void testCompareToOnlyNameDiffers()
    {
        Playlist p1 = new Playlist("Alpha", 10, 20, 30, 50, 60, 70, 10);
        Playlist p2 = new Playlist("Beta", 10, 20, 30, 50, 60, 70, 10);
        assertTrue(p1.compareTo(p2) < 0);
        assertTrue(p2.compareTo(p1) > 0);
    }


    /**
     * Tests compareTo when capacity differs (returns early)
     */
    public void testCompareToCapacityDiffersEarly()
    {
        Playlist p1 = new Playlist("P1", 0, 0, 0, 100, 100, 100, 15);
        Playlist p2 = new Playlist("P2", 0, 0, 0, 100, 100, 100, 10);
        assertTrue(p1.compareTo(p2) > 0);
        assertTrue(p2.compareTo(p1) < 0);
    }


    /**
     * Tests compareTo when capacity same but spaces differ (returns early)
     */
    public void testCompareToSpacesDifferEarly()
    {
        Playlist p1 = new Playlist("P1", 0, 0, 0, 100, 100, 100, 10);
        Playlist p2 = new Playlist("P2", 0, 0, 0, 100, 100, 100, 10);

        Song s = new Song("Song", 50, 50, 50, "");
        p2.addSong(s);
        p2.addSong(new Song("Song2", 50, 50, 50, ""));
        assertTrue(p1.compareTo(p2) > 0);
        assertTrue(p2.compareTo(p1) < 0);
    }


    /**
     * Tests compareTo with all attributes identical
     */
    public void testCompareToAllIdentical()
    {
        Playlist p1 = new Playlist("Same", 10, 20, 30, 50, 60, 70, 10);
        Playlist p2 = new Playlist("Same", 10, 20, 30, 50, 60, 70, 10);

        assertEquals(0, p1.compareTo(p2));
        assertEquals(0, p2.compareTo(p1));
    }


    /**
     * Tests compareTo progression through all comparison levels
     */
    public void testCompareToProgression()
    {
        Playlist large = new Playlist("Z", 0, 0, 0, 100, 100, 100, 20);
        Playlist small = new Playlist("A", 0, 0, 0, 100, 100, 100, 10);
        assertTrue(large.compareTo(small) > 0);

        Playlist moreSpaces = new Playlist("Z", 0, 0, 0, 100, 100, 100, 10);
        Playlist lessSpaces = new Playlist("A", 0, 0, 0, 100, 100, 100, 10);
        Song s = new Song("S", 50, 50, 50, "");
        lessSpaces.addSong(s);
        assertTrue(moreSpaces.compareTo(lessSpaces) > 0);
    }


    /**
     * Tests equals with minGenreSet having different values
     */
    public void testEqualsMinGenreSetNotEqual()
    {
        Playlist p1 = new Playlist("Test", 10, 20, 30, 50, 60, 70, 10);
        Playlist p2 = new Playlist("Test", 15, 20, 30, 50, 60, 70, 10);

        assertFalse(p1.equals(p2));
    }


    /**
     * Tests equals with maxGenreSet having different values
     */
    public void testEqualsMaxGenreSetNotEqual()
    {
        Playlist p1 = new Playlist("Test", 10, 20, 30, 50, 60, 70, 10);
        Playlist p2 = new Playlist("Test", 10, 20, 30, 55, 60, 70, 10);

        assertFalse(p1.equals(p2));
    }


    /**
     * Tests equals with capacity different
     */
    public void testEqualsCapacityNotEqual()
    {
        Playlist p1 = new Playlist("Test", 10, 20, 30, 50, 60, 70, 10);
        Playlist p2 = new Playlist("Test", 10, 20, 30, 50, 60, 70, 15);

        assertFalse(p1.equals(p2));
    }
}
