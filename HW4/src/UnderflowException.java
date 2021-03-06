/**
 * Exception class for access in empty containers
 * such as stacks, queues, and priority queues.
 * @author nb2406
 * 
 * Based on Weiss code
 */

@SuppressWarnings("serial")
public class UnderflowException extends RuntimeException
{
	
	public String getMessage() {
		return "The container says: 'I'm empty. Time for me to gracefully die.'";
	}
}
