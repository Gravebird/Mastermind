package codeword;

/**
 * Thrown when the codeword has too many digits (more than 6).
 * 
 * @author Bryan Rainbow
 *
 */
public class TooManyDigitsInCodewordException extends InvalidCodewordException
{
	/**
	 * The amount of digits found in the codeword that caused the error.
	 */
	private final int numberOfDigitsFound;
	
	/**
	 * Constructs a TooManyDigitsInCodewordException with the specified data.
	 * 
	 * @param amount the amount of digits in the codeword that caused the error
	 */
	public TooManyDigitsInCodewordException(final int amount)
	{
		super("Given codeword has too many digits: required 6 but found " + amount);
		numberOfDigitsFound = amount;
	}
}
