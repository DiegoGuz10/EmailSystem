

package main_files;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;


public class Main {
	
	//String inputtedUser;
	
	public Main() {
		String firstSender = JOptionPane.showInputDialog("Please Enter the Name of the Sender:");
		String firstRecipient = JOptionPane.showInputDialog("Please Enter the Name of the Recipient:");
		ArrayList<String> firstRecipientList = new ArrayList<String>();
		firstRecipientList.add(firstRecipient);
		firstRecipientList.add(firstSender);
		
		MainFrame theFrame = new MainFrame(firstSender, firstRecipientList);
		theFrame.setVisible(true);
	}
	
	
	public static void main(String[] args) {
		Main mainSystem = new Main();
		
	}
}
