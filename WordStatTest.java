import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class WordStatTest {

    @Test
    public void testWordStatsText() {
        WordStat macbeth = new WordStat("C:\\Users\\clin1\\Documents\\CSDS_233_Data_Structures\\P4\\Macbeth.txt");
        // testing word count
        Assert.assertEquals(3, macbeth.wordCount("antique")); // inserted this word into Macbeth for testing
        Assert.assertEquals(21, macbeth.wordCount("cawdor")); // existing word, got word count using find word feature
        Assert.assertEquals(0, macbeth.wordCount("ohio"));    // nonexistant word returns 0

        // testing word pair count
        Assert.assertEquals(0, macbeth.wordPairCount("charlie", "lin"));
        Assert.assertEquals(3, macbeth.wordPairCount("had", "he"));
        Assert.assertEquals(73, macbeth.wordPairCount("lady", "macbeth"));

        // testing word rank
        Assert.assertEquals(0, macbeth.wordRank("charlie"));
        Assert.assertEquals(1, macbeth.wordRank("the"));
        Assert.assertEquals(2, macbeth.wordRank("and"));
        Assert.assertEquals(8, macbeth.wordRank("macbeth"));
        Assert.assertEquals(true, macbeth.wordRank("antique") == macbeth.wordRank("armoire")); // inserted these words, words with same count return same rank

        // testing word pair rank
        Assert.assertEquals(0, macbeth.wordPairRank("charlie", "lin"));
        Assert.assertEquals(3, macbeth.wordPairRank("lady", "macbeth"));
        Assert.assertEquals(15, macbeth.wordPairRank("lady", "macduff"));
        Assert.assertEquals(1, macbeth.wordPairRank("word", "pair"));
        Assert.assertEquals(2, macbeth.wordPairRank("pair", "word"));

        // testing most common words
        Assert.assertEquals("[]", Arrays.toString(macbeth.mostCommonWords(0)));
        Assert.assertEquals("[]", Arrays.toString(macbeth.mostCommonWords(-5)));
        Assert.assertEquals("[the, and, of, to, i]", Arrays.toString(macbeth.mostCommonWords(5)));

        // testing least common words
        Assert.assertEquals("[]", Arrays.toString(macbeth.leastCommonWords(0)));
        Assert.assertEquals("[]", Arrays.toString(macbeth.leastCommonWords(-5)));
        /* words of same rank are alphabetically sorted */
        Assert.assertEquals("[zebra, youths, youth, yould, youbut]", Arrays.toString(macbeth.leastCommonWords(5)));

        // testing most common word pairs
        Assert.assertEquals("[]", Arrays.toString(macbeth.mostCommonWordPairs(0)));
        Assert.assertEquals("[]", Arrays.toString(macbeth.mostCommonWordPairs(-5)));
        Assert.assertEquals("[word pair, pair word, lady macbeth, of the, in the]", Arrays.toString(macbeth.mostCommonWordPairs(5)));

        // testing least common word pairs
        Assert.assertEquals("[]", Arrays.toString(macbeth.leastCommonWordPairs(0)));
        Assert.assertEquals("[]", Arrays.toString(macbeth.leastCommonWordPairs(-5)));
        Assert.assertEquals("[zebra banquo, youths that, youth pray, yourselves exeunt, yourselves apart]", Arrays.toString(macbeth.leastCommonWordPairs(5)));

        // testing most common collocations
        Assert.assertEquals("[pair]", Arrays.toString(macbeth.mostCommonCollocs(1, "word", 1)));
        Assert.assertEquals("[pair, i, macduff]", Arrays.toString(macbeth.mostCommonCollocs(3, "word", 1)));
        Assert.assertEquals("[pair]", Arrays.toString(macbeth.mostCommonCollocs(1, "word", -1)));
        Assert.assertEquals("[pair, a, any]", Arrays.toString(macbeth.mostCommonCollocs(3, "word", -1)));
        /* invalid inputs for i and k (edge cases) */
        Assert.assertEquals("[]", Arrays.toString(macbeth.mostCommonCollocs(0, "macbeth", 1)));
        Assert.assertEquals("[]", Arrays.toString(macbeth.mostCommonCollocs(3, "macbeth", 5)));
        /* k > number of collocs (edge case) */
        Assert.assertEquals("[macbeth, macduff, lady, malcolm, tis]", Arrays.toString(macbeth.mostCommonCollocs(50, "lady", 1)));

        // testing most common collocations with exclusions
        String[] exc = {"macduff", "macbeth"};
        Assert.assertEquals("[lady, malcolm, tis]", Arrays.toString(macbeth.mostCommonCollocsExc(5, "lady", 1, exc)));
        
        // testing generate word string
        Assert.assertEquals("macbeth i have done the project gutenbergtm electronic works in", macbeth.generateWordString(10, "lady"));
        Assert.assertEquals("i have done the project gutenbergtm electronic works in the", macbeth.generateWordString(10, "macbeth"));
        Assert.assertEquals("", macbeth.generateWordString(0, "macbeth"));
    }

    @Test
    public void testWordStatArray() {
        String[] words = {"This", "is", "an", "array", "of", "Strings.", "Duplicate", "duplicate", "dupLicAte", "Aardvark", "Aardvark", "Zebra", "Zebra", "Zebra"};
        WordStat stats = new WordStat(words);

        // testing word count
        Assert.assertEquals(3, stats.wordCount("duplicate"));
        Assert.assertEquals(2, stats.wordCount("aardvark"));
        Assert.assertEquals(0, stats.wordCount("null"));

        // testing word pair count
        Assert.assertEquals(1, stats.wordPairCount("this", "is"));
        Assert.assertEquals(2, stats.wordPairCount("duplicate", "duplicate"));
        Assert.assertEquals(0, stats.wordPairCount("charlie", "lin"));

        // testing word rank
        Assert.assertEquals(1, stats.wordRank("duplicate"));
        Assert.assertEquals(1, stats.wordRank("zebra"));       // duplicate ranks accounted for
        Assert.assertEquals(3, stats.wordRank("aardvark"));
        Assert.assertEquals(0, stats.wordRank("null"));

        // testing word pair rank
        Assert.assertEquals(1, stats.wordPairRank("duplicate", "duplicate"));
        Assert.assertEquals(1, stats.wordPairRank("zebra", "zebra"));       // duplicate ranks accounted for
        Assert.assertEquals(3, stats.wordPairRank("aardvark", "aardvark"));
        Assert.assertEquals(0, stats.wordPairRank("charlie", "lin"));

        // testing most common words
        Assert.assertEquals("[duplicate, zebra, aardvark, an]", Arrays.toString(stats.mostCommonWords(4)));
        Assert.assertEquals("[]", Arrays.toString(stats.mostCommonWords(0)));
        Assert.assertEquals("[duplicate, zebra, aardvark, an, array, is, of, strings, this]", Arrays.toString(stats.mostCommonWords(30)));

        // testing least common words
        Assert.assertEquals("[this, strings, of, is]", Arrays.toString(stats.leastCommonWords(4)));
        Assert.assertEquals("[]", Arrays.toString(stats.leastCommonWords(0)));
        Assert.assertEquals("[this, strings, of, is, array, an, aardvark, zebra, duplicate]", Arrays.toString(stats.leastCommonWords(30)));

        // testing most common word pairs
        Assert.assertEquals("[duplicate duplicate, zebra zebra, aardvark aardvark]", Arrays.toString(stats.mostCommonWordPairs(3)));
        Assert.assertEquals("[]", Arrays.toString(stats.mostCommonWordPairs(0)));
        Assert.assertEquals("[duplicate duplicate, zebra zebra, aardvark aardvark, aardvark zebra, an array, array of, duplicate aardvark, is an, of strings, strings duplicate, this is]", Arrays.toString(stats.mostCommonWordPairs(50)));
        
        // testing least common word pairs
        Assert.assertEquals("[this is, strings duplicate, of strings]", Arrays.toString(stats.leastCommonWordPairs(3)));
        Assert.assertEquals("[this is]", Arrays.toString(stats.leastCommonWordPairs(1)));
        /* k > number of word pairs or k < 1 (edge cases) */
        Assert.assertEquals("[]", Arrays.toString(stats.leastCommonWordPairs(0)));
        Assert.assertEquals("[this is, strings duplicate, of strings, is an, duplicate aardvark, array of, an array, aardvark zebra, aardvark aardvark, zebra zebra, duplicate duplicate]", Arrays.toString(stats.leastCommonWordPairs(50)));
        
        // testing most common collocations
        Assert.assertEquals("[this]", Arrays.toString(stats.mostCommonCollocs(1, "is", -1)));
        /* k > number of collocs (edge case) */
        Assert.assertEquals("[duplicate, aardvark]", Arrays.toString(stats.mostCommonCollocs(5, "duplicate", 1)));
        /* invalid inputs for k and i (edge cases) */
        Assert.assertEquals("[]", Arrays.toString(stats.mostCommonCollocs(-3, "duplicate", 1)));
        Assert.assertEquals("[]", Arrays.toString(stats.mostCommonCollocs(2, "duplicate", 0)));
    
        // testing most common collocations with exclusions
        String[] exc = {"this"};
        Assert.assertEquals("[]", Arrays.toString(stats.mostCommonCollocsExc(1, "is", -1, exc)));

        // testing generate word String
        Assert.assertEquals("an array of", stats.generateWordString(3, "is"));
        /* k = 0 (edge case) */
        Assert.assertEquals("", stats.generateWordString(0, "duplicate"));
    }
    
}
