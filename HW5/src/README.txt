Name: Nina Baculinao
Uni: nb2406

Homework 5 Readme

NOTE: All java files tested in vagrant environment without compilation issues

***

DIJSTRA AND KRUSKAL (TWO DIFFERENT EXECUTABLE MAIN’S):

HOW TO RUN
  1. Compile the files using the following commands in Terminal (or just use javac *.java)
    $ javac Dijkstra.java
    $ javac Vertex.java
    $ javac Edge.java
    $ javac Graph.java
    $ javac BinaryHeap.java
    $ javac UnderflowException.java
    $ javac Kruskal.java
    $ javac DisjSets.java
  2. Run "Dijkstra" with 2 text file inputs (IN THIS ORDER: 1st arg, citypairs, 2nd arg, cityxy)
    $ java Dijkstra "citypairs.dat" and "cityxy.txt"
  3. Run “Kruskal” with 1 text file input (containing list of cities and xy coordinates)
    $ java Kruskal “cityxy.txt”

NOTES
 - Graph, Edge, BinaryHeap and UnderflowException class were written to be modular and generally applicable, shared by the two different mains in Dijkstra and Kruskal classes
 - Set most instance variables in Edge and Vertex classes to public to avoid all the getters and setters from the last assignment
 - Used Linked Lists to save space wasted by Array Lists, though constantly having to initialize new iterators seemed a little inefficient
 - Uses Weiss' code for BinaryHeap and DisjointSets
 - Dijkstra and Kruskal methods both based on Weiss pseudocode
 - Dijkstra GUI accepts user input with spaces, but city names must be properly capitalized
 - Dijkstra GUI text fields for user input automatically clear on click so it is easy for users to enter new input
 - Biggest challenge was using the find method of the DisjSets for kruskal method:
    a. First unintentionally entered x1 (the coordinate) as parameter for find method instead of v1 (the first of the city pair that make an edge) and got array out of bound errors
    b. Next realized that find method only takes int values, so find(v1) where v1 was a Vertex object raised compiler errors
    c. Attempted to alter find method itself in DisjSets to accept AnyType and Objects, results were inconsistent
    d. Then tried to implement a hash function to transform the String name of the city into a unique integer; hash function consisted of hashing only the odd characters in the String into a polynomial function of 37 by Horner’s rule and finding the mod of that value by the next largest prime after the number of total edges in the list of edges, something like this:
		String key = edge.v1.name;
		for (int i = 0; i < key.length(); i += 2)
			hashVal = 37 * hashVal + key.charAt(i);
		hashVal %= nextPrime(edges.size);
Three new methods and probably around a hundred lines of code later, I had a minimum spanning tree + the disconnected city node of Los Angeles. The hash function could not even guarantee zero collisions for only 29 cities! The more I tweaked with the hash function, the more unstable results I got, sometimes only 1 edge would be missing, sometimes as much as 10 would fail to appear.
    e. Finally, realized that I could just add a unique id property to every node and that could be an incrementing integer every time I initialized a new node based on the city list input, like so:
		while (citiesIterator.hasNext()) {
			String cityName = citiesIterator.next();
			Vertex city = new Vertex(cityName, xyIterator.next(),
					xyIterator.next(), i);
			i++; // where the Vertex class’ id field is set to the counter i
		}
Apologies for the lengthiness, I just found it noteworthy that brunt of my challenge was precisely due to two such seemingly innocuous lines of code:
			int uset = ds.find(e.v1.id);
			int vset = ds.find(e.v2.id);

***

CLASSES & METHODS
 
    Dijkstra
        main: parses data from inputs into Linked Lists and uses them in Graph constructor, initializes GUI
    class MouseListener
        clears the start and end textfield when clicked (but not the results field)
    class ButtonListener        
        ButtonListener: constructor
        actionPerformed: calls dijkstra method using text input as parameters if button is pressed
    Vertex
    Edge
    Graph
    BinaryHeap
    UnderflowException
    Kruskal
        main: parses data from inputs into Linked Lists and uses them in Graph constructor, calls kruskal method to find minimum spanning tree, initializes GUI, and prints out the edges that make the minimum spanning tree to the console

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