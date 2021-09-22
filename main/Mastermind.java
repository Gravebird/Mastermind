package main;

import gui.GamePanel;
import gui.InputPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import codeword.Codeword;
import codeword.InvalidCodewordException;

public class Mastermind 
{
	public static GamePanel gamePanel = new GamePanel(10);
	public static InputPanel inputPanel = new InputPanel();
	public static Codeword codeword = new Codeword();
	public static int gamemode;
	
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Mastermind");
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		String options[] = new String[2];
		options[0] = "One Player";
		options[1] = "Two Players";
		
		gamemode = JOptionPane.showOptionDialog(frame,
				"Select a gamemode",
				"Gamemode",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				options,
				options[0]);
		
		options = new String[3];
		options[0] = "Easy";
		options[1] = "Medium";
		options[2] = "Hard";
		
		int difficulty = JOptionPane.showOptionDialog(frame,
				"Select a difficulty",
				"Difficult",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				options,
				options[0]);
		
		if (difficulty == 0)
		{
			gamePanel = new GamePanel(12);
		}
		else if (difficulty == 1)
		{
			gamePanel = new GamePanel(10);
		}
		else
		{
			gamePanel = new GamePanel(8);
		}
		
		frame.add(gamePanel, BorderLayout.CENTER);
		frame.add(inputPanel, BorderLayout.SOUTH);
		frame.pack();
		frame.setVisible(true);
		
		if (gamemode == 1)
		{
			String tempCode;
			int rawCode[];
			
			do
			{
				tempCode = JOptionPane.showInputDialog("Enter the codeword");
				
				rawCode = Codeword.parseCode(tempCode);
				
				try
				{
					if (rawCode != null)
					{
						codeword = new Codeword(rawCode);
					}
				}
				catch (InvalidCodewordException e)
				{
					JOptionPane.showConfirmDialog(frame, "Invalid Codeword: Must be 6 digits in length, from numbers 1 through 6.");
					rawCode = null;
				}
			}
			while (rawCode == null);
		}
	}
}
