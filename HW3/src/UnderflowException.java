/**
 * Exception class for access in empty containers
 * such as stacks, queues, and priority queues.
 * @author Mark Allen Weiss
 */
public class UnderflowException extends RuntimeException
{
	
	public String getMessage() {
		return "The stack says: I'm empty. Time for me to gracefully die.";
	}
}
