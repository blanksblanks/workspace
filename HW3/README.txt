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
 - Strings are evaluated as lowercase
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
        - Adding one character (from a-z + apostrophe), removing character, or swapping adjacent characters
        - Note: not implemented as it wasn't required, but I would've liked to also suggest corrections based on a) adding a space, and b) deleting and replacing a letter so that words like "recognise" (not in the American dictionary) could be corrected to "recognize"
 - Hash table is based on Weiss' code and book, and uses a quadratic probing implementation of handling collisions, as well as Horning's formula to obtain a hash value of every other letter in an input string
 - Uses similar process to Word Indexer of passing each line of input file into a Linked List (of lines), then using an iterator to increment the line numbers for each line and a String tokenizer to parse each individual word from the lines, removing unwanted punctuation and verifying that the input contained alphabetical letters before inputting any words into the hash table or checking if the hash table contained the word
 - No long running time issues, but input files were relatively small
 
***

TEST CASE: WORD INDEXER

➜ java WordIndexer hippo.txt
a: 9, 28, 39
all: 42
although: 3
among: 38
and: 4, 6, 6, 18, 22, 29, 33, 37
angels: 33
arms: 37
as: 41, 41
ascending: 32
at: 21, 24, 27, 29
based: 9
be: 38, 41
being: 24
belly: 2
below: 43
betrays: 22
blood: 4, 6, 36
broad-backed: 1
but: 18, 23
by: 42
can: 8, 16, 29
church: 8, 13, 19, 24, 29, 43
clean: 36
compassing: 12
damp: 32
day: 26
dividends: 14
ends: 12
enfold: 37
err: 11
every: 23
fail: 8
feeble: 11
feed: 29
firm: 3
flesh: 4, 6
for: 9
frail: 6
from: 19, 32
fruits: 18
gather: 14
god: 24, 28, 34
gold: 39
harp: 39
he: 3, 4, 27, 38, 41
hear: 23
heavenly: 37
him: 33, 36, 37
hippopotamus: 1
hippopotamus’s: 26
hippo’s: 11, 21
his: 2
hoarse: 22
hosannas: 34
hunts: 27
i: 31
in: 2, 12, 14, 27, 28, 34, 44
inflexions: 22
is: 4, 6, 9, 27
it: 9
its: 14
kist: 42
lamb: 36
loud: 34
mango: 17
mango-tree: 17
martyr’d: 42
material: 12
mating: 21
may: 11
merely: 4
miasmal: 44
mist: 44
mud: 2
mysterious: 28
need: 13
nervous: 7
never: 8, 13, 16
night: 27
odd: 22
of: 18, 34, 36, 39
old: 44
on: 2, 17, 39
once: 29
one: 24
over: 19
passed: 27
peach: 18
performing: 39
pomegranate: 18
potamus: 16, 31
praise: 34
quiring: 33
reach: 16
refresh: 19
rejoice: 23
remains: 43
rests: 2
rock: 9
round: 33
saints: 38
savannas: 32
saw: 31
sea: 19
seems: 3
seen: 38
shall: 36, 37, 38, 41
shock: 7
sing: 33
sleep: 27, 29
snow: 41
so: 3
steps: 11
stir: 13
susceptible: 7
take: 31
the: 1, 2, 8, 11, 13, 16, 17, 17, 19, 21, 24, 26, 29, 31, 32, 34, 36, 38, 42, 43, 44
time: 21
to: 3, 7, 14
true: 8, 13, 43
upon: 9
us: 3
virgins: 42
voice: 21
wash: 36
washed: 41
way: 28
we: 23
weak: 6
week: 23
while: 8, 13, 43
white: 41
wing: 31
with: 24
works: 28
wrapt: 44

TEST CASE: SPELL CHECKER

➜ java SpellChecker words.txt personal.txt readme.txt

nina, line 1, suggestions: ninja
baculinao, line 1, no suggestions
uni, line 2, suggestions: unit
nb, line 2, suggestions: nab, nib, nub, b, n
readme, line 4, no suggestions
java, line 6, no suggestions
indexer, line 10, no suggestions
avl, line 10, no suggestions
javac, line 14, no suggestions
avlwordtree.java, line 14, no suggestions
javac, line 15, no suggestions
wordindexer.java, line 15, no suggestions
javac, line 16, no suggestions
underflowexception.java, line 16, no suggestions
wordindexer, line 17, no suggestions
e.g, line 17, no suggestions
java, line 18, no suggestions
wordindexer, line 18, no suggestions
input.txt, line 18, no suggestions
indexer, line 21, no suggestions
avl, line 21, no suggestions
weiss', line 22, no suggestions
avlwordtree, line 22, no suggestions
linkedlist, line 23, no suggestions
avlwordnode, line 23, no suggestions
printtree, line 24, no suggestions
in-class, line 29, no suggestions
javac, line 37, no suggestions
myhashtable.java, line 37, no suggestions
javac, line 38, no suggestions
spellchecker.java, line 38, no suggestions
spellchecker, line 39, no suggestions
e.g, line 39, no suggestions
java, line 40, no suggestions
spellchecker, line 40, no suggestions
words.txt, line 40, no suggestions
personal.txt, line 40, no suggestions
article.txt, line 40, no suggestions
personal.txt, line 42, no suggestions
variable-length, line 45, no suggestions
command-line, line 46, no suggestions
spell-checked, line 48, no suggestions
command-line, line 49, no suggestions
recognise, line 52, no suggestions
american, line 52, no suggestions
weiss', line 53, no suggestions
horning's, line 53, no suggestions
indexer, line 54, no suggestions
tokenizer, line 54, no suggestions