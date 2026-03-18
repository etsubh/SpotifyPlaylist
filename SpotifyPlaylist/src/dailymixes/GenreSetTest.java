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
 * Test for genre set to ensure it works properly
 * 
 * @author etsub
 * @version Nov 4, 2025
 */
public class GenreSetTest
    extends TestCase
{
    private GenreSet genreSet1;
    private GenreSet genreSet2;
    private GenreSet genreSet3;

    /**
     * Sets up test fixtures
     */
    public void setUp()
    {
        genreSet1 = new GenreSet(20, 50, 30);
        genreSet2 = new GenreSet(20, 50, 30);
        genreSet3 = new GenreSet(10, 40, 25);
    }


    /**
     * Tests constructor and getters
     */
    public void testConstructorAndGetters()
    {
        assertEquals(20, genreSet1.getPop());
        assertEquals(50, genreSet1.getRock());
        assertEquals(30, genreSet1.getCountry());
    }


    /**
     * Tests isLessThanOrEqualTo when true
     */
    public void testIsLessThanOrEqualToTrue()
    {
        // genreSet3 (10,40,25) <= genreSet1 (20,50,30)
        assertTrue(genreSet3.isLessThanOrEqualTo(genreSet1));

        // Equal case
        assertTrue(genreSet1.isLessThanOrEqualTo(genreSet2));
    }


    /**
     * Tests isLessThanOrEqualTo when false
     */
    public void testIsLessThanOrEqualToFalse()
    {
        assertFalse(genreSet1.isLessThanOrEqualTo(genreSet3));
        GenreSet mixed = new GenreSet(15, 60, 20);
        assertFalse(genreSet1.isLessThanOrEqualTo(mixed));
    }


    /**
     * Tests isWithinRange when in range
     */
    public void testIsWithinRangeTrue()
    {
        GenreSet min = new GenreSet(10, 40, 20);
        GenreSet max = new GenreSet(30, 60, 40);
        assertTrue(genreSet1.isWithinRange(min, max));
        GenreSet atMin = new GenreSet(10, 40, 20);
        assertTrue(atMin.isWithinRange(min, max));

        GenreSet atMax = new GenreSet(30, 60, 40);
        assertTrue(atMax.isWithinRange(min, max));
    }


    /**
     * Tests isWithinRange when out of range
     */
    public void testIsWithinRangeFalse()
    {
        GenreSet min = new GenreSet(10, 40, 20);
        GenreSet max = new GenreSet(30, 60, 40);

        GenreSet tooLow = new GenreSet(5, 30, 15);
        assertFalse(tooLow.isWithinRange(min, max));
        GenreSet tooHigh = new GenreSet(35, 70, 50);
        assertFalse(tooHigh.isWithinRange(min, max));

        GenreSet oneOut = new GenreSet(20, 50, 50);
        assertFalse(oneOut.isWithinRange(min, max));
    }


    /**
     * Tests toString
     */
    public void testToString()
    {
        assertEquals("Pop:20 Rock:50 Country:30", genreSet1.toString());
        assertEquals("Pop:10 Rock:40 Country:25", genreSet3.toString());
    }


    /**
     * Tests equals with same object
     */
    public void testEqualsSameObject()
    {
        assertTrue(genreSet1.equals(genreSet1));
    }


    /**
     * Tests equals with identical GenreSet
     */
    public void testEqualsIdentical()
    {
        assertTrue(genreSet1.equals(genreSet2));
        assertTrue(genreSet2.equals(genreSet1));
    }


    /**
     * Tests equals with different GenreSet
     */
    public void testEqualsDifferent()
    {
        assertFalse(genreSet1.equals(genreSet3));
        GenreSet diffPop = new GenreSet(25, 50, 30);
        assertFalse(genreSet1.equals(diffPop));

        GenreSet diffRock = new GenreSet(20, 55, 30);
        assertFalse(genreSet1.equals(diffRock));

        GenreSet diffCountry = new GenreSet(20, 50, 35);
        assertFalse(genreSet1.equals(diffCountry));
    }


    /**
     * Tests equals with null
     */
    public void testEqualsNull()
    {
        assertFalse(genreSet1.equals(null));
    }


    /**
     * Tests equals with different class
     */
    public void testEqualsDifferentClass()
    {
        assertFalse(genreSet1.equals("Pop:20 Rock:50 Country:30"));
    }


    /**
     * Tests compareTo when equal
     */
    public void testCompareToEqual()
    {
        assertEquals(0, genreSet1.compareTo(genreSet2));
    }


    /**
     * Tests compareTo when this is less
     */
    public void testCompareToLess()
    {
        assertTrue(genreSet3.compareTo(genreSet1) < 0);
    }


    /**
     * Tests compareTo when this is greater
     */
    public void testCompareToGreater()
    {
        assertTrue(genreSet1.compareTo(genreSet3) > 0);
    }


    /**
     * Tests compareTo with different distributions but same sum
     */
    public void testCompareToSameSum()
    {
        GenreSet set1 = new GenreSet(50, 25, 25);
        GenreSet set2 = new GenreSet(30, 30, 40);

        assertEquals(0, set1.compareTo(set2));
    }


    /**
     * Tests with edge case values
     */
    public void testEdgeCases()
    {
        GenreSet zero = new GenreSet(0, 0, 0);
        GenreSet max = new GenreSet(100, 100, 100);

        assertEquals("Pop:0 Rock:0 Country:0", zero.toString());
        assertEquals("Pop:100 Rock:100 Country:100", max.toString());

        assertTrue(zero.compareTo(max) < 0);
        assertTrue(max.compareTo(zero) > 0);
    }


    /**
     * Tests isWithinRange with one attribute at each boundary
     */
    public void testIsWithinRangeBoundaries()
    {
        GenreSet min = new GenreSet(10, 40, 20);
        GenreSet max = new GenreSet(30, 60, 40);
        GenreSet popMax = new GenreSet(30, 50, 30);
        assertTrue(popMax.isWithinRange(min, max));

        GenreSet rockMin = new GenreSet(20, 40, 30);
        assertTrue(rockMin.isWithinRange(min, max));
        GenreSet countryBelowMin = new GenreSet(20, 50, 19);
        assertFalse(countryBelowMin.isWithinRange(min, max));
    }


    /**
     * Tests isLessThanOrEqualTo with partially equal values
     */
    public void testIsLessThanOrEqualToPartialEqual()
    {
        GenreSet partial = new GenreSet(20, 45, 25);
        assertTrue(partial.isLessThanOrEqualTo(genreSet1));
    }


    /**
     * Tests isLessThanOrEqualTo with one attribute equal, others less
     */
    public void testIsLessThanOrEqualToOneEqual()
    {
        GenreSet popEqual = new GenreSet(20, 45, 25);
        assertTrue(popEqual.isLessThanOrEqualTo(genreSet1));
        GenreSet rockEqual = new GenreSet(15, 50, 25);
        assertTrue(rockEqual.isLessThanOrEqualTo(genreSet1));
        GenreSet countryEqual = new GenreSet(15, 45, 30);
        assertTrue(countryEqual.isLessThanOrEqualTo(genreSet1));
    }


    /**
     * Tests isLessThanOrEqualTo with two attributes equal, one less
     */
    public void testIsLessThanOrEqualToTwoEqual()
    {
        GenreSet twoEqual = new GenreSet(20, 50, 25);
        assertTrue(twoEqual.isLessThanOrEqualTo(genreSet1));
        GenreSet twoEqual2 = new GenreSet(20, 45, 30);
        assertTrue(twoEqual2.isLessThanOrEqualTo(genreSet1));
        GenreSet twoEqual3 = new GenreSet(15, 50, 30);
        assertTrue(twoEqual3.isLessThanOrEqualTo(genreSet1));
    }


    /**
     * Tests isLessThanOrEqualTo with one attribute greater (should be false)
     */
    public void testIsLessThanOrEqualToOneGreater()
    {
        GenreSet popGreater = new GenreSet(25, 40, 20);
        assertFalse(popGreater.isLessThanOrEqualTo(genreSet1));
        GenreSet rockGreater = new GenreSet(15, 55, 20);
        assertFalse(rockGreater.isLessThanOrEqualTo(genreSet1));
        GenreSet countryGreater = new GenreSet(15, 40, 35);
        assertFalse(countryGreater.isLessThanOrEqualTo(genreSet1));
    }


    /**
     * Tests isLessThanOrEqualTo with two attributes greater (should be false)
     */
    public void testIsLessThanOrEqualToTwoGreater()
    {
        GenreSet twoGreater = new GenreSet(25, 55, 20);
        assertFalse(twoGreater.isLessThanOrEqualTo(genreSet1));
        GenreSet twoGreater2 = new GenreSet(25, 40, 35);
        assertFalse(twoGreater2.isLessThanOrEqualTo(genreSet1));
        GenreSet twoGreater3 = new GenreSet(15, 55, 35);
        assertFalse(twoGreater3.isLessThanOrEqualTo(genreSet1));
    }


    /**
     * Tests isLessThanOrEqualTo with all attributes greater (should be false)
     */
    public void testIsLessThanOrEqualToAllGreater()
    {
        GenreSet allGreater = new GenreSet(25, 55, 35);
        assertFalse(allGreater.isLessThanOrEqualTo(genreSet1));
    }


    /**
     * Tests isLessThanOrEqualTo with zero values
     */
    public void testIsLessThanOrEqualToZero()
    {
        GenreSet zero = new GenreSet(0, 0, 0);
        GenreSet nonZero = new GenreSet(10, 20, 30);

        assertTrue(zero.isLessThanOrEqualTo(nonZero));
        assertTrue(zero.isLessThanOrEqualTo(zero));
        assertFalse(nonZero.isLessThanOrEqualTo(zero));
    }


    /**
     * Tests isWithinRange with pop below minimum
     */
    public void testIsWithinRangePopBelowMin()
    {
        GenreSet min = new GenreSet(10, 40, 20);
        GenreSet max = new GenreSet(30, 60, 40);

        GenreSet popBelow = new GenreSet(5, 50, 30);
        assertFalse(popBelow.isWithinRange(min, max));
    }


    /**
     * Tests isWithinRange with pop above maximum
     */
    public void testIsWithinRangePopAboveMax()
    {
        GenreSet min = new GenreSet(10, 40, 20);
        GenreSet max = new GenreSet(30, 60, 40);

        GenreSet popAbove = new GenreSet(35, 50, 30);
        assertFalse(popAbove.isWithinRange(min, max));
    }


    /**
     * Tests isWithinRange with rock below minimum
     */
    public void testIsWithinRangeRockBelowMin()
    {
        GenreSet min = new GenreSet(10, 40, 20);
        GenreSet max = new GenreSet(30, 60, 40);

        GenreSet rockBelow = new GenreSet(20, 35, 30);
        assertFalse(rockBelow.isWithinRange(min, max));
    }


    /**
     * Tests isWithinRange with rock above maximum
     */
    public void testIsWithinRangeRockAboveMax()
    {
        GenreSet min = new GenreSet(10, 40, 20);
        GenreSet max = new GenreSet(30, 60, 40);

        GenreSet rockAbove = new GenreSet(20, 65, 30);
        assertFalse(rockAbove.isWithinRange(min, max));
    }


    /**
     * Tests isWithinRange with country below minimum
     */
    public void testIsWithinRangeCountryBelowMin()
    {
        GenreSet min = new GenreSet(10, 40, 20);
        GenreSet max = new GenreSet(30, 60, 40);

        GenreSet countryBelow = new GenreSet(20, 50, 15);
        assertFalse(countryBelow.isWithinRange(min, max));
    }


    /**
     * Tests isWithinRange with country above maximum
     */
    public void testIsWithinRangeCountryAboveMax()
    {
        GenreSet min = new GenreSet(10, 40, 20);
        GenreSet max = new GenreSet(30, 60, 40);

        GenreSet countryAbove = new GenreSet(20, 50, 45);
        assertFalse(countryAbove.isWithinRange(min, max));
    }


    /**
     * Tests isWithinRange with pop at minimum boundary
     */
    public void testIsWithinRangePopAtMin()
    {
        GenreSet min = new GenreSet(10, 40, 20);
        GenreSet max = new GenreSet(30, 60, 40);

        GenreSet popAtMin = new GenreSet(10, 50, 30);
        assertTrue(popAtMin.isWithinRange(min, max));
    }


    /**
     * Tests isWithinRange with pop at maximum boundary
     */
    public void testIsWithinRangePopAtMax()
    {
        GenreSet min = new GenreSet(10, 40, 20);
        GenreSet max = new GenreSet(30, 60, 40);

        GenreSet popAtMax = new GenreSet(30, 50, 30);
        assertTrue(popAtMax.isWithinRange(min, max));
    }


    /**
     * Tests isWithinRange with rock at minimum boundary
     */
    public void testIsWithinRangeRockAtMin()
    {
        GenreSet min = new GenreSet(10, 40, 20);
        GenreSet max = new GenreSet(30, 60, 40);

        GenreSet rockAtMin = new GenreSet(20, 40, 30);
        assertTrue(rockAtMin.isWithinRange(min, max));
    }


    /**
     * Tests isWithinRange with rock at maximum boundary
     */
    public void testIsWithinRangeRockAtMax()
    {
        GenreSet min = new GenreSet(10, 40, 20);
        GenreSet max = new GenreSet(30, 60, 40);

        GenreSet rockAtMax = new GenreSet(20, 60, 30);
        assertTrue(rockAtMax.isWithinRange(min, max));
    }


    /**
     * Tests isWithinRange with country at minimum boundary
     */
    public void testIsWithinRangeCountryAtMin()
    {
        GenreSet min = new GenreSet(10, 40, 20);
        GenreSet max = new GenreSet(30, 60, 40);

        GenreSet countryAtMin = new GenreSet(20, 50, 20);
        assertTrue(countryAtMin.isWithinRange(min, max));
    }


    /**
     * Tests isWithinRange with country at maximum boundary
     */
    public void testIsWithinRangeCountryAtMax()
    {
        GenreSet min = new GenreSet(10, 40, 20);
        GenreSet max = new GenreSet(30, 60, 40);

        GenreSet countryAtMax = new GenreSet(20, 50, 40);
        assertTrue(countryAtMax.isWithinRange(min, max));
    }


    /**
     * Tests isWithinRange with all attributes at minimum
     */
    public void testIsWithinRangeAllAtMinimum()
    {
        GenreSet min = new GenreSet(10, 40, 20);
        GenreSet max = new GenreSet(30, 60, 40);

        assertTrue(min.isWithinRange(min, max));
    }


    /**
     * Tests isWithinRange with all attributes at maximum
     */
    public void testIsWithinRangeAllAtMaximum()
    {
        GenreSet min = new GenreSet(10, 40, 20);
        GenreSet max = new GenreSet(30, 60, 40);

        assertTrue(max.isWithinRange(min, max));
    }


    /**
     * Tests isWithinRange with multiple attributes out of range
     */
    public void testIsWithinRangeMultipleOutOfRange()
    {
        GenreSet min = new GenreSet(10, 40, 20);
        GenreSet max = new GenreSet(30, 60, 40);

        GenreSet twoHigh = new GenreSet(35, 65, 30);
        assertFalse(twoHigh.isWithinRange(min, max));

        GenreSet twoLow = new GenreSet(5, 50, 15);
        assertFalse(twoLow.isWithinRange(min, max));

        GenreSet allHigh = new GenreSet(35, 65, 45);
        assertFalse(allHigh.isWithinRange(min, max));

        GenreSet allLow = new GenreSet(5, 35, 15);
        assertFalse(allLow.isWithinRange(min, max));
    }


    /**
     * Tests isWithinRange with mixed boundary conditions
     */
    public void testIsWithinRangeMixedBoundaries()
    {
        GenreSet min = new GenreSet(10, 40, 20);
        GenreSet max = new GenreSet(30, 60, 40);

        GenreSet mixed1 = new GenreSet(10, 60, 30);
        assertTrue(mixed1.isWithinRange(min, max));

        GenreSet justBelowMin = new GenreSet(9, 50, 30);
        assertFalse(justBelowMin.isWithinRange(min, max));

        GenreSet justAboveMax = new GenreSet(20, 61, 30);
        assertFalse(justAboveMax.isWithinRange(min, max));
    }
}
