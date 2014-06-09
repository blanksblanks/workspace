public class Binary { 
  public static void binSearch(int item, int[] numbers) { 
    int lo = 0; 
    int hi = numbers.length - 1; 
    int mid = 0; 
  
    while (lo <= hi) { 
      mid = (lo + hi) / 2; // mid point 
  
      // If we find the item, then obviously it is in the array 
      if (item == numbers[mid]) { 
        break; 
      } 
  
      if (item < numbers[mid]) { 
        // Item is in the left half 
        hi = mid-1; 
      } else { 
        // Item is in the right half 
        lo = mid+1; 
      } 
    } 
  
    if (lo <= hi) { 
      // We left the loop from breaking it 
      System.out.println("Found at numbers[" + mid + "]!"); 
    } else { 
      // Otherwise we left the loop because lo > hi 
      System.out.println("Not found!"); 
    } 
  } 
  
  public static void main(String[] args) { 
    int[] ints = new int[args.length-1]; 
    for (int i = 0; i < ints.length; ++i) { 
      ints[i] = Integer.parseInt(args[i]); 
    } 
    int search = Integer.parseInt(args[args.length - 1]); 
    binSearch(search, ints); 
  } 
}
}