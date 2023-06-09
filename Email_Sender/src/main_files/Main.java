

package main_files;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;


public class Main {
	
	public Main() {
		String firstUser = JOptionPane.showInputDialog("Please Enter the Name of the User:");
		String firstRecipient = JOptionPane.showInputDialog("Please Enter the Name of the Recipient:");
		ArrayList<String> firstRecipientList = new ArrayList<String>();
		firstRecipientList.add(firstRecipient);
		firstRecipientList.add(firstUser);
		
		if((firstUser == null) || (firstRecipient == null) || (firstUser.isBlank()) || (firstRecipient.isBlank())) {
			JOptionPane.showMessageDialog(null, "The Names of the User and Recipient cannot be blank. Please re-run the system again.", "Error Encountered!", JOptionPane.ERROR_MESSAGE);
		}
		else {
			MainFrame theFrame = new MainFrame(firstUser, firstRecipientList);
			theFrame.setVisible(true);
		}
	}
	
	
	public static void main(String[] args) {
		Main mainSystem = new Main();
		
	}
}
