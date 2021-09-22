package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import main.Mastermind;

public class GamePanel extends JPanel
{
	private JPanel guessLog;
	private JPanel resultsLog;
	
	private JPanel[][] guesses;
	private JPanel[][] results;
	
	private JLabel[][] guessLabel;
	private JLabel[][] resultsLabel;
	
	private final int numberOfGuesses;
	
	public GamePanel(final int numberOfTurns)
	{
		numberOfGuesses = numberOfTurns;
		
		setLayout(new BorderLayout());
		
		guessLog = new JPanel();
		resultsLog = new JPanel();
		
		guessLog.setLayout(new GridLayout(numberOfGuesses, 6));
		resultsLog.setLayout(new GridLayout(numberOfGuesses, 2));
		guesses = new JPanel[numberOfGuesses][6];
		results = new JPanel[numberOfGuesses][6];
		
		guessLabel = new JLabel[numberOfGuesses][6];
		resultsLabel = new JLabel[numberOfGuesses][2];
		
		for (int row = 0; row < numberOfGuesses; row++)
		{
			for (int col = 0; col < 6; col++)
			{
				guessLabel[row][col] = new JLabel();
				guesses[row][col] = new JPanel();
				guesses[row][col].setPreferredSize(new Dimension(30, 25));
				guessLog.add(guesses[row][col], row, col);
				guesses[row][col].add(guessLabel[row][col]);
				
				if (row % 2 == 0)
				{
					guesses[row][col].setBackground(Color.LIGHT_GRAY);
				}
				else
				{
					guesses[row][col].setBackground(new Color(200,200,200));
				}
			}
			for (int col = 0; col < 2; col++)
			{
				resultsLabel[row][col] = new JLabel();
				results[row][col] = new JPanel();
				results[row][col].setPreferredSize(new Dimension(20, 15));
				resultsLog.add(results[row][col], row, col);
				results[row][col].add(resultsLabel[row][col]);
				
				if (col == 0)
				{
					results[row][col].setBackground(Color.YELLOW);
				}
				else
				{
					results[row][col].setBackground(Color.GREEN);
				}
			}
		}
		
		add(guessLog, BorderLayout.WEST);
		add(resultsLog, BorderLayout.EAST);
	}
	
	public void pushGuessesUp()
	{
		if (guessLabel[numberOfGuesses-1][0].getText().equals("") == false)
		{
			JOptionPane.showMessageDialog(this, "You lose! Code was: " + Mastermind.codeword.toString());
			System.exit(0);
		}
		for (int row = numberOfGuesses-2; row >= 0; row--)
		{
			for (int col = 0; col < 6; col++)
			{
				guessLabel[row+1][col].setText(
						guessLabel[row][col].getText());
			}
			for (int col = 0; col < 2; col++)
			{
				resultsLabel[row+1][col].setText(
						resultsLabel[row][col].getText());
			}
		}
	}
	
	public void writeGuessesAndResults(final int code[], final int results[])
	{
		for (int i = 0; i < 6; i++)
		{
			guessLabel[0][i].setText(code[i] + "");
		}
		resultsLabel[0][0].setText(results[1] + "");
		resultsLabel[0][1].setText(results[0] + "");
	}
}
