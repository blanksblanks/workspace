Name: Nina Baculinao
Uni: nb2406

Homework 3 Readme

NOTE: All java files tested in vagrant environment without compilation issues

***

WORD INDEXER AND AVL WORD TREE:

HOW TO RUN
  1. Compile the files using the following commands in Terminal
    $ javac AvlWordTree.java
    $ javac WordIndexer.java
    $ javac UnderflowException.java
  2. Run "WordIndexer" with a text file input (any file name is okay), e.g.
    $ java WordIndexer input.txt

NOTES
 - Implements a word indexer by using an AVL tree with tree nodes that point at a linked list of line numbers where the words appear
 - Uses Weiss' code as a jumping point for building the AvlWordTree class
 - Added LinkedList of integers to private AvlWordNode class and constructors as another instance variable
 - Modified insert() and printTree() methods so that words and their line numbers can be inserted
 
 Regarding punctuation:
 - This program and the spell checker both strip words of all punctuation except for single quotes and punctuation embedded between alphabet letters (like hyphens)
 - Strings that do not contain letters (like numbers) are not inserted into the tree
 - Originally, assignment only required removal of ". " and ", " so I changed my regular expressions to a simple replace method to handle those cases, but later reverted back to more comprehensive punctuation cleanup after in-class clarifications and due to personal design preferences

***

SPELL CHECKER and HASH TABLE

HOW TO RUN
  1. Compile the file in Terminal
    $ javac MyHashTable.java
    $ javac SpellChecker.java
  2. Run the "SpellChecker" with three arguments: two dictionaries, and a text file to spell check, e.g.
    $ java SpellChecker words.txt personal.txt article.txt
    
    * personal.txt is a personal dictionary file I included that contains commonly typed slang like omg 

NOTES
 - Implements a spelling checker by using a hash table for variable-length strings
 - Takes as input from the command-line:
        - two dictionary files, which are hashed into one hash table
        - and a third text file, which is to be spell-checked
 - Catches errors caused by entering an insufficient number of command-line arguments or calling files that don't exist in the same directory
 - Outputs a list of misspelled words, their line numbers, and suggestions for corrections based on any of the following rules:
        - Adding one character, removing character, or swapping adjacent characters
        - Note: not implemented as it wasn't required, but I would've liked to also suggest corrections based on deleting and replacing a letter so that words like "recognise" (not in the American dictionary) could be corrected to "recognize"
 - Hash table is based on Weiss' code and book, and uses a quadratic probing implementation of handling collisions, as well as Horning's formula to obtain a hash value of every other letter in an input string
 - Uses similar process to Word Indexer of passing each line of input file into a Linked List (of lines), then using an iterator to increment the line numbers for each line and a String tokenizer to parse each individual word from the lines, removing unwanted punctuation and verifying that the input contained alphabetical letters before inputting any words into the hash table or checking if the hash table contained the word
 - No long running time issues, but input files were relatively small
