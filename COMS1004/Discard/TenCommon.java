import java.util.Scanner;
import java.io.*;

public class TenCommon 
{

public TenCommon() {
final int LENGTH = 10;
String[] bigram_array = new String[LENGTH]; //the official sorted bigram array
int[] count_array = new int[LENGTH]; //the official sorted count array

String bigram = "";
int parsed_count = 0;
File inputFile = new File("output3.txt");

try{

Scanner in = new Scanner(inputFile);

while (in.hasNext()) //iterate through every single line of the output text file
{
	parsed_count = Integer.parseInt(in.next()); //convert "20" into 20
	bigram = in.next(); //store the bigram
	
	System.out.println(parsed_count + " " + bigram);

	
	int mover = 0;
	String temp_bigram = "";
	int temp_count = 0;
	for (mover = 0; mover < LENGTH; mover++) //determine where the replacement begins, e.g. @ the 5
	{
		if (parsed_count >= count_array[mover])
		{
			temp_count = count_array[mover]; //save it so we can kick it down
			temp_bigram = bigram_array[mover]; //save it so we can kick it down
			count_array[mover] = parsed_count; //REPLACE!
			bigram_array[mover] = bigram; //REPLACE!
			break;
		}
	}

	mover++; //increment to next index
	
	String save_bigram = "";
	int save_count = 0;

	for (; mover < LENGTH; mover++) //kick everything down 1 spot
	{
		save_count = count_array[mover]; //SAVE BEFORE OVERWRITE
		save_bigram = bigram_array[mover]; //SAVE BEFORE OVERWRITE
		count_array[mover] = temp_count; //OVERWRITE!
		bigram_array[mover] = temp_bigram; //OVERWRITE!
		temp_count = save_count; //place saved value into temp
		temp_bigram = save_bigram; //place saved value into temp
	}
	
	for (int i = 0; i < LENGTH; i++)
		System.out.print(count_array[i] + " ");
	System.out.println();
	for (int i = 0; i < LENGTH; i++)
		System.out.print(bigram_array[i] + " ");
	System.out.println();

}
}
catch (IOException e)
{
	System.out.println("shit" + e);
}

}

}
