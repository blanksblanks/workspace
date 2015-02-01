
public class Modder {
	
	public static void main(String[] args){
		int[] numbers = {4371, 1323, 6173, 4199, 4344, 9679, 1989};
		int mod = 19;
				
		for (int i = 0; i < numbers.length; i++){
			System.out.println(numbers[i] + "  " + numbers[i]%mod);
		}
	}

}

