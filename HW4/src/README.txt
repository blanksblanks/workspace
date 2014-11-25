Name: Nina Baculinao
Uni: nb2406

Homework 4 Readme

NOTE: All java files tested in vagrant environment without compilation issues

***

HUFFMAN CODE:

HOW TO RUN
  1. Compile the files using the following commands in Terminal (or just use javac *.java)
    $ javac HuffmanCode.java
    $ javac HuffmanNode.java
    $ javac HuffmanTree.java
    $ javac BinaryHeap.java
  2. Run "HuffmanCode" with a text file input (any file name is okay), e.g.
    $ java HuffmanCode input.txt

NOTES
 - Uses Weiss' code for BinaryHeap and deleteMin methods as a jumping point to create Huffman subtrees out of Huffman Nodes
 - Program only supports text files that solely contain the first 128 original ASCII characters and returns an error if there are non-ASCII characters in the input
 - GUI is a little cramped looking after 6 levels, the text fields besides the results field automatically clear so it easy for user to enter new input to encode/decode
 - hope you like the random color algorithm!

***

CLASSES & METHODS
 
    HuffmanCode
        FileLineParser: accepts input file from user and returns a linked list of every line in the file with appended new line to the end of each line
        buildTinyTrees: iterates through linked list of lines, returning "a forest of tiny Huffman trees" i.e. an array of isolated Huffman nodes, one to store every character from the list and its frequency
        main: initializes the above methods and the HuffmanTree constructor, as well as the GUI frame and Encode/Decode buttons, also prints all nodes and their encoding in alphabetical order
    class MouseListener
        clears the textfield and binary textfield when clicked (but not the results field)
    class ButtonListener        
        ButtonListener: constructor
        actionPerformed: checks whether Encode/Decode button has been pressed and calls appropriate method, answer is output in the results field
    
    HuffmanNode
        Instance variables:
            character
            left
            right
            frequency
            height
            code 
        HuffmanNode: 3 constructors, one that takes in a String for characters/leaf nodes, another that takes in an int, and left, right nodes for the t-numbers        
        compareTo: method used by binary heap to compare nodes to each other by frequency (the "promise" kept for implements Comparable)
        isLeaf: checks if both children are null
        getFrequency: gets private frequency variable
        setHeight: sets private height variable
        getHeight: gets private height variable
        fixHeight: fixes all the heights of the children in a node's subtree
        setBinaryCode: setter method for binary code
        getBinaryCode: getter method for binary code       
        getCharacter: getter method for character element
        toString: similar to getCharacter, but return sp and nl for printing purposes
        
    HuffmanTree
        Instance variables:
          root
          hash
          radius
          hgap
          vgap
          levels
          frameHeight
          frameWidth
        HuffmanTree: constructor method that takes in an array of Huffman Nodes, throws them in a heap, build a tree out of them, encodes the leaves and sets the frame size based on the size of the tree
        buildHuffmanTree: private method that takes in a binary heap of Huffman nodes, and creates new subtrees by deleting the minimums from the heap and throwing back in the subtree roots with the joined frequencies
        encodeLeaves: traverses the tree, encoding 0's on the left and 1's on the right, and setting binary codes for the leaves as well as putting them in a hash table
        encode: encodes an ASCII string into a binary code by traversing the tree, catches errors if character is not in the tree
        decode: decodes a binary code into an ASCII string by accessing a hash table, catches index out of bounds and nullpointer errors 
        paintComponent: recovers the Graphics2D object, sets font and calls displaytree method
        displayTree: recursive method that draws the tree in the GUI with labelled circles for nodes and lines leading to the children until it reaches a leaf and labels the leaf with its binary encoding
        printCenteredString: method written to center a string depending on the width of the string
        printVerticalString: method to add an enter space after every character in a string, used to print strings vertically
        mixRandomColorWith: method that generates a random color and blends it with the color parameter, in this instance, mint, so everything has a minty cast (for fun, wasn't necessary to the assignment)

    BinaryHeap
        insert: insert x
        deleteMin: return and remove smallest item
        findMin: return smallest item
        isEmpty: return true if empty
        makeEmpty: remove all items

***

TEST CASE:

➜  cat input2.txt
sea seat eat see ease teat tease seas

➜  java HuffmanCode input2.txt
TABLE OF ASCII CHARACTERS AND CORRESPONDING HUFFMAN CODES:
nl 1100
sp 111
a 01
e 10
s 00
t 1101

[Screenshot of GUI: http://cl.ly/YgJX]

➜  cat input.txt             
hello there, this is just a tester file for characters and stuff.
the quick brown fox jumps over the lazy dog.
or does the lazy dog jump over the quick brown fox?
needless to say, this file is (ABSOLUTELY!!!) pointless outside of testing purposes.

➜  java HuffmanCode input.txt
TABLE OF ASCII CHARACTERS AND CORRESPONDING HUFFMAN CODES:
nl 011010
sp 111
! 010101
( 01111000
) 01101101
, 0111010
. 010010
? 01111001
A 01101100
B 01110110
E 01110111
L 1000101
O 01010010
S 01010011
T 0101000
U 10001000
Y 10001001
a 01011
b 0111000
c 100011
d 01000
e 000
f 10010
g 001110
h 10011
i 11011
j 001111
k 1101000
l 01100
m 1101001
n 00110
o 1011
p 110101
q 0111101
r 0010
s 1100
t 1010
u 10000
v 0111001
w 0111111
x 0111110
y 010011
z 0110111

[Screenshot of GUI: http://cl.ly/YhDH]
