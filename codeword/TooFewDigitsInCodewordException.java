package codeword;

/**
 * Thrown when there are not enough digits in a codeword (less than 6)
 * 
 * @author Bryan Rainbow
 *
 */
public class TooFewDigitsInCodewordException extends InvalidCodewordException
{
	/**
	 * The amount of digits found in the codeword that caused the error.
	 */
	private final int numberOfDigitsFound;
	
	/**
	 * Constructs a TooFewDigitsInCodewordException with the specified data
	 * 
	 * @param amount the amount of digits in the codeword that caused the error
	 */
	public TooFewDigitsInCodewordException(final int amount)
	{
		super("Given codeword has too few digits: required 6 but found " + amount);
		numberOfDigitsFound = amount;
	}
}
