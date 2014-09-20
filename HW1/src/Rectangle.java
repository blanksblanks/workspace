public class Rectangle {

   private int length, width;
   //   area, perim;

   // constructor
   public Rectangle() {
      length = 0;
      width = 0;
   }

   // constructor
   public Rectangle(int len, int wid) {
      length = len;
      width = wid;
   }

   // accessors
   public int getLength() { 
	   return length; 
   }

   public int getWidth() { 
	   return width;
   }
   
//   public int getArea(){ 
//	   area = length * width;
//	   return area;
//   }
//   
//   public int getPerim() { 
//	   perim = 2*length + 2*width;
//	   return perim;
//   }
//
//   public String toString() {
//      return "Rectangle with width: " +  width +
//             ", length: " + length +
//             ", area: " + getArea() +
//             ",  and perimeter:  " + getPerim();
//   }
}