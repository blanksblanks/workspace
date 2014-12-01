Name: Nina Baculinao
Uni: nb2406

Homework 5 Readme

NOTE: All java files tested in vagrant environment without compilation issues

***

HUFFMAN CODE:

HOW TO RUN
  1. Compile the files using the following commands in Terminal (or just use javac *.java)
    $ javac Dijkstra.java
    $ javac Vertex.java
    $ javac Edge.java
    $ javac Graph.java
    $ javac BinaryHeap.java
    $ javac UnderflowException.java
  2. Run "Dijkstra" with 2 text file inputs (IN THIS ORDER: 1st arg, citypairs, 2nd arg, cityxy)
    $ java Dijkstra "citypairs.dat" and "cityxy.txt"

NOTES
 - Uses Weiss' code for BinaryHeap and Dijkstra based on his pseudocode
 - GUI accepts user input with spaces, but city names must be properly capitalized
 - GUI text fields for user input automatically clear on click so it is easy for users to enter new input
 - Set most instance variables in Edge and Vertex classes to public to avoid all the getters and setters from last assignment
***

CLASSES & METHODS
 
    Dijkstra
        main: parses data from inputs into Linked Lists and uses them in Graph constructor, initializes GUI
    class MouseListener
        clears the start and end textfield when clicked (but not the results field)
    class ButtonListener        
        ButtonListener: constructor
        actionPerformed: checks whether button has been pressed and calls appropriate method, answer is output in the results field
    Vertex
    Edge
    Graph
    BinaryHeap
    UnderflowException


***

EXAMPLE OUTPUT:

[Screenshot of GUI: XXXXXXXXXXXXXXXXXXXXXXXXXXXXX]

***

EXAMPLE INPUT: 1ST ARG: citypairs.dat

Peoria Chicago 117.45637488020819
St.Louis Peoria 148.2767682410161
NewYork Boston 183.43936327844142
Washington Pittsburgh 185.34832073693033
Washington NewYork 197.0913493789111
LosAngeles LasVegas 224.50835173774718
Memphis St.Louis 245.7173172570464
Tulsa St.Louis 269.04646438858845
Memphis Tulsa 287.1671986839723
Dallas Tulsa 301.7614952242913
Pittsburgh NewYork 315.91296269700615
Phoenix Albuquerque 316.7412192942371
Atlanta Memphis 317.3168763239674
Peoria Omaha 327.2002444986862
LosAngeles SanFrancisco 331.1268639056638
Phoenix LosAngeles 350.3826479721848
Denver SaltLake 359.56779611083084
LasVegas SaltLake 359.6804137008297
Pittsburgh Chicago 384.7037821493311
LasVegas SanFrancisco 392.23079940259663
NewOrleans Atlanta 402.3443798538759
Dallas Memphis 403.97648446413314
Omaha Chicago 410.1219330881976
NewOrleans Dallas 431.01972112653965
Denver Omaha 452.49198887936126
Albuquerque LasVegas 466.43863476345956
Atlanta Washington 529.9226358630098
Tulsa Denver 551.6420941153784
Dallas Albuquerque 562.8578861488928
SanFrancisco SaltLake 570.6356105256664
LasVegas Denver 589.688901031722
Miami Atlanta 604.2151934534583
Albuquerque Tulsa 636.2554518430471
Miami NewOrleans 648.2969998388085
St.Louis Washington 676.009615316232
Dallas Atlanta 685.5603547463928
Chicago Boston 806.7961328613319

2ND ARG: cityxy.txt

Boston 2542  1230
Chicago 1756  1048
Omaha  1350  990
SaltLake  565  1025
Peoria  1676  962
NewYork 2435  1081
Pittsburgh  2135  982
Denver  905  908
Washington 2312  927
St.Louis  1645  817
SanFrancisco  0  945
Tulsa  1410  686
LasVegas  338  746
Albuquerque 780  597
Memphis  1674  573
LosAngeles  138  644
Seattle 100 1400
Atlanta  1985  510
Phoenix  470  532
Dallas  1308  402
NewOrleans  1701  225
Miami  2309  0
Boise 400 1225
Raleigh 2200 678
Indianapois 1806 980
Louisville 1837 798
Cleveland 2050 1025
Reno 120 990
Minneapolis 1500 1200