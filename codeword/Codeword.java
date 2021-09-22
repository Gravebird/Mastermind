package codeword;

import java.awt.Color;
import java.util.Random;

/**
 * Represents the codeword that the player is trying to guess.
 * 
 * The code consists of 6 digits between 1 and 6 inclusive.
 * Duplicates are allowed in the codeword. You can even have a codeword
 * like this: "333333".
 * 
 * The game will rate the guessed codeword against the actual codeword
 * to determine how many digits are correct, then once it knows this number
 * it will find how many of the digits are in the wrong position, displaying
 * no notification for a number that registered as correct.
 * 
 * For example: the codeword is 123444
 * 
 * If the user guesses 312456
 * 		There is 1 correct digit
 * 		There are 3 digits in the wrong place (1, 2, and 3, but not 4 since 4 was found correct)
 * 
 * @author Bryan Rainbow
 *
 */
public class Codeword 
{
	private Random rng = new Random();
	
	/**
	 * The codeword that the player is trying to guess.
	 */
	private int code[] = new int[6];
	
	/**
	 * Construct a codeword using the specified code.
	 * 
	 * @param givenCode the code that is meant to be used
	 * @throws InvalidCodewordException if the codeword is too large or small (must be 6 in length)
	 */
	public Codeword(final int givenCode[])
		throws InvalidCodewordException
	{
		if (givenCode.length == 6)
		{
			for (int i = 0; i < 6; i++)
			{
				if (givenCode[i] < 1 || givenCode[i] > 6)
				{
					// Construct an exception for this occurance
					System.out.println("Error: Bryan forgot to create an exception to handle codes with" +
							" numbers\nbelow 1 or above 6!\nIn constructor for Codeword");
					System.exit(1);
				}
			}
			code = givenCode;
		}
		else if (givenCode.length < 6)
		{
			throw new TooFewDigitsInCodewordException(givenCode.length);
		}
		else if (givenCode.length > 6)
		{
			throw new TooManyDigitsInCodewordException(givenCode.length);
		}
	}
	public Codeword()
	{
		for (int i = 0; i < 6; i++)
		{
			code[i] = rng.nextInt(6) + 1;
		}
	}
	
	public String toString()
	{
		String output = "";
		
		for (int i = 0; i < 6; i++)
		{
			output += code[i] + " ";
		}
		
		return output;
	}
	
	public static boolean isValidCodeword(final String tempCode)
	{
		
		return false;
	}
	
	public int[] compare(final int givenCode[])
		throws InvalidCodewordException
	{
		int results[] = new int[2];
		
		if (givenCode.length == 6)
		{
			for (int i = 0; i < 6; i++)
			{
				if (givenCode[i] < 1 || givenCode[i] > 6)
				{
					// Construct an exception for this occurance
					System.out.println("Error: Bryan forgot to create an exception to handle codes with" +
							" numbers\nbelow 1 or above 6!\nIn method compare in class Codeword");
					System.exit(1);
				}
			}
			int found[] = new int[6];
			
			for (int i = 0; i < 6; i++)
			{
				if (code[i] == givenCode[i])
				{
					results[0]++;
					found[code[i]-1]++;
				}
			}
			
			for (int i = 0; i < 6; i++)
			{
				if (found[givenCode[i]-1] < 1)
				{
					if (contains(givenCode[i]))
					{
						results[1]++;
						found[givenCode[i]-1]++;
					}
				}
			}
		}
		else if (givenCode.length < 6)
		{
			throw new TooFewDigitsInCodewordException(givenCode.length);
		}
		else if (givenCode.length > 6)
		{
			throw new TooManyDigitsInCodewordException(givenCode.length);
		}
		
		return results;
	}
	
	public static int[] parseCode(final String strCode)
	{
		int code[] = new int[6];
		boolean error = false;
		
		if (strCode.length() != 6)
		{
			error = true;
		}
		
		for (int i = 0; i < strCode.length() && !error; i++)
		{
			code[i] = strCode.charAt(i);
			code[i] -= 48;
			
			if (code[i] < 1 || code[i] > 6)
			{
				error = true;
			}
		}
		
		if (error)
		{
			return null;
		}
		
		return code;
	}
	
	/**
	 * Returns true if the code contains the given digit, false otherwise.
	 * 
	 * @param digit the digit to compare with the code
	 * @return true if the digit is found, false otherwise
	 */
	private boolean contains(final int digit)
	{
		for (int i = 0; i < 6; i++)
		{
			if (code[i] == digit)
			{
				return true;
			}
		}
		return false;
	}
}
