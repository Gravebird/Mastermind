package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import codeword.InvalidCodewordException;

import main.Mastermind;

public class InputPanel extends JPanel
{
	JTextField inputField = new JTextField();
	JButton confirmButton = new JButton("Submit");
	JPanel errorPanel = new JPanel();
	JLabel errorText = new JLabel();
	
	public InputPanel()
	{
		setLayout(new BorderLayout());
		errorPanel.setPreferredSize(new Dimension(40, 25));
		errorPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		errorPanel.add(errorText);
		add(inputField, BorderLayout.CENTER);
		add(confirmButton, BorderLayout.EAST);
		add(errorPanel, BorderLayout.SOUTH);
		
		confirmButton.addActionListener(new CodeListener());
	}
	
	private int[] parseCode()
	{
		String strCode = inputField.getText();
		int code[] = new int[6];
		inputField.setText("");
		boolean error = false;
		
		if (strCode.length() != 6)
		{
			errorText.setText("Code must be exactly 6 digits in length.");
			error = true;
		}
		
		for (int i = 0; i < strCode.length(); i++)
		{
			code[i] = strCode.charAt(i);
			code[i] -= 48;
			
			if (code[i] < 1 || code[i] > 6)
			{
				errorText.setText("Digits must be between 1 and 6.");
				error = true;
			}
		}
		
		if (error)
		{
			errorPanel.setBackground(Color.RED);
			return null;
		}
		
		errorText.setText("");
		errorPanel.setBackground(Color.WHITE);
		
		return code;
	}
	
	private class CodeListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			int code[] = parseCode();
			int results[];
			
			if (code != null)
			{
				Mastermind.gamePanel.pushGuessesUp();
				
				try
				{
					results = Mastermind.codeword.compare(code);
					Mastermind.gamePanel.writeGuessesAndResults(code, results);
					
					if (results[0] == 6)
					{
						JOptionPane.showMessageDialog(Mastermind.inputPanel, "You win!");
						System.exit(0);
					}
				}
				catch (InvalidCodewordException e)
				{
					errorText.setText("Fatal Error: invalid code when comparing!");
					errorPanel.setBackground(new Color(256,128,128));
				}
			}
		}
	}
}
