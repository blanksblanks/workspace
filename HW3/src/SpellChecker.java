/**
 * 
 * Exercise 5.21
 * 
 * @author nb2406
 * 
 *         Weiss, Exercise 5.21: Implement a spelling checker by using a hash
 *         table. Assume that the dictionary comes from two sources: an existing
 *         large dictionary and a second file containing a personal dictionary.
 *         Output all misspelled words and the line numbers in which they occur.
 *         Also, for each misspelled word, list any words in the dictionary that
 *         are obtainable by applying any of the following rules: a. Add one
 *         character. b. Remove one character. c. Exchange adjacent characters.
 * 
 *         This is a command line application. The dictionary files should be
 *         provided as command line arguments to the programming. Here is a
 *         sample dictionary file. You do not have to submit the big dictionary
 *         file with your program, but you should submit a sample small
 *         dictionary file. Write your own hash function. In addition, the file
 *         being spellchecked should be provided as a command line argument.
 * 
 *         Pseudo code: hash "apple" and point to tree of possible misspellings
 *         add one char:
 *         string s = apple;
 *         while (apple has next){
 *         string t = s(remove i)
 *         if (s = hash)
 *          	insert t;
 *         i++;
 *         }
 *         
 *         exchange chars:
 *         temp = i;
 *         i = i.next;
 *         i.next = temp;
 * 
 */

public class SpellChecker {

}
