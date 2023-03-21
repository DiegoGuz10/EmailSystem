

package main_files;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MainFrame extends JFrame implements ActionListener, ListSelectionListener{
	
	JMenuBar theMainBar = new JMenuBar();
	JMenu fileMenu = new JMenu("File");
	JMenu usersMenu= new JMenu("Users");
	JMenuItem exitItem = new JMenuItem("Exit");
	JMenuItem addRecipientItem = new JMenuItem("Add New Recipient");
	JMenuItem switchUserItem = new JMenuItem("Switch User");
	String currentUser = "Current User: " ;
	String currentUserNonFormat;
	JScrollPane inboxofMessages = new JScrollPane();
	JTextArea fieldofMessage = new JTextArea();
	JScrollPane spfieldofMessage = new JScrollPane(fieldofMessage);
	JLabel toLabel = new JLabel("To:");
	JLabel priorityLabel = new JLabel("Priority:");
	JRadioButton highCheckBox = new JRadioButton("High");
	JRadioButton medCheckBox = new JRadioButton("Medium");
	JRadioButton lowCheckBox = new JRadioButton("Low");
	ButtonGroup priorityButtonsGroup = new ButtonGroup(); 
	JLabel subjectLabel = new JLabel("Subject:");
	JLabel inboxLabel = new JLabel("Inbox:");
	JLabel contentOfSelectedEmailLabel = new JLabel("Content of Selected Email:");
	JLabel enterMessageLabel = new JLabel("Enter Message Below:");
	JTextField subjectofMessage = new JTextField();
	JButton submitButton = new JButton("Submit");
	JButton clearButton = new JButton("Clear");
	JTextArea contentofMessage = new JTextArea();
	JScrollPane spcontentofMessage = new JScrollPane(contentofMessage);
	JComboBox dropDownofUsers = new JComboBox();
	JList<String> messageList = new JList<>();
	JLabel currentLabel = new JLabel();
	ArrayList<ArrayList<String>> tempallMessagesContent = new ArrayList<ArrayList<String>>();
	ArrayList<String> tempallMessagesContentTime = new ArrayList<String>();
	DefaultListModel<String> model = new DefaultListModel<>();
	DefaultComboBoxModel modelbox = new DefaultComboBoxModel();
	static ArrayList<ArrayList<String>> allMessagesContent = new ArrayList<ArrayList<String>>();
	static ArrayList<String> tempupdatedListofUsers = new ArrayList<String>();
	static ArrayList<String> allListofUsers = new ArrayList<String>();
	headerPanel theHeaderPanel = new headerPanel();
	InboxandMessagePanel theMainPanel = new InboxandMessagePanel();
	JScrollPane scroollPane = new JScrollPane();
	otherPanel leftSideBar = new otherPanel();
	otherPanel rightSideBar = new otherPanel();
	otherPanel bottomSideBar = new otherPanel();
	
	public MainFrame(String UserName, ArrayList updatedListofUsers){	
		
		this.updateListofUsersswitchUser(UserName, updatedListofUsers);
		
		highCheckBox.addActionListener(this);
		medCheckBox.addActionListener(this);
		lowCheckBox.addActionListener(this);
		highCheckBox.setActionCommand("High");
		medCheckBox.setActionCommand("Medium");
		lowCheckBox.setActionCommand("Low");
		
		
		
		submitButton.addActionListener(this);
		clearButton.addActionListener(this);
		
		this.setTitle("Email System");
		this.setLayout(new BorderLayout(15, 15));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(650,650);
		this.getContentPane().setBackground(Color.LIGHT_GRAY);	
		this.add(theHeaderPanel, BorderLayout.NORTH);
		this.add(theMainPanel, BorderLayout.CENTER);
		this.add(leftSideBar, BorderLayout.WEST);
		this.add(rightSideBar, BorderLayout.EAST);
		this.add(bottomSideBar, BorderLayout.SOUTH);
		theHeaderPanel.add(currentLabel);
		theMainBar.setBackground(Color.LIGHT_GRAY);
		theMainBar.add(fileMenu);
		theMainBar.add(usersMenu);
		fileMenu.add(exitItem);
		usersMenu.add(switchUserItem);
		usersMenu.add(addRecipientItem);
		exitItem.addActionListener(this);
		switchUserItem.addActionListener(this);	
		addRecipientItem.addActionListener(this);	
		this.setJMenuBar(theMainBar);
		messageList.setModel(model);
		scroollPane.add(messageList);
		this.updateListofMessages();
	
		
		messageList.addListSelectionListener(this);
		scroollPane.setBounds(0, 20, 200, 160);
		spfieldofMessage.setBounds(250, 20, 300, 160);	
		dropDownofUsers.setBounds(200, 213, 175, 45);
		toLabel.setBounds(170, 213, 45, 45);	
		priorityLabel.setBounds(153, 255, 65, 45);
		lowCheckBox.setBounds(200, 255, 60, 45);
		medCheckBox.setBounds(255, 255, 90, 45);
		highCheckBox.setBounds(338, 255, 65, 45);	
		priorityButtonsGroup.add(lowCheckBox);
		priorityButtonsGroup.add(medCheckBox);
		priorityButtonsGroup.add(highCheckBox);
		highCheckBox.setSelected(true);
		subjectLabel.setBounds(150, 297, 65, 25);
		subjectofMessage.setBounds(210, 297, 175, 25);	
		contentofMessage.setBounds(55, 350, 450, 175);
		spcontentofMessage.setBounds(55, 350, 450, 175);	
		inboxLabel.setBounds(1, 0, 50, 25);
		theMainPanel.add(inboxLabel);
		contentOfSelectedEmailLabel.setBounds(252, 0, 250, 25);
		theMainPanel.add(contentOfSelectedEmailLabel);	
		enterMessageLabel.setBounds(210, 325, 150, 25);
		theMainPanel.add(enterMessageLabel);	
		theMainPanel.add(priorityLabel);
		theMainPanel.add(lowCheckBox);
		theMainPanel.add(medCheckBox);
		theMainPanel.add(highCheckBox);
		theMainPanel.add(toLabel);
		theMainPanel.add(dropDownofUsers);
		theMainPanel.add(scroollPane);
		scroollPane.setViewportView(messageList);
		theMainPanel.add(spfieldofMessage);
		theMainPanel.add(subjectLabel);
		theMainPanel.add(subjectofMessage);
		theMainPanel.add(spcontentofMessage);
		bottomSideBar.add(submitButton);
		bottomSideBar.add(clearButton);
		
		this.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == exitItem) {
			this.dispose();
		}
		else if (e.getSource() == addRecipientItem) {	
			String newRecipient = JOptionPane.showInputDialog("Please Enter the Name of the New Recipient:");
			MainFrame.tempupdatedListofUsers.add(newRecipient);
			this.updateListofUsersaddRecipient(newRecipient, tempupdatedListofUsers);		
		}
		else if (e.getSource() == switchUserItem) {	
			String newUser = JOptionPane.showInputDialog("Please Enter the Name of User:");
			MainFrame.tempupdatedListofUsers.add(newUser);
			this.updateListofUsersswitchUser(newUser, tempupdatedListofUsers);
			this.updateListofMessages();
			
		}
		else if(e.getSource() == submitButton) {
			ArrayList<String> tempArrayList = new ArrayList<String>();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			
			
			tempArrayList.add(this.currentUserNonFormat);
			tempArrayList.add(dropDownofUsers.getSelectedItem().toString());
			tempArrayList.add(priorityButtonsGroup.getSelection().getActionCommand());
			tempArrayList.add(subjectofMessage.getText());
			tempArrayList.add(dtf.format(now));
			tempArrayList.add(contentofMessage.getText());		
			allMessagesContent.add(tempArrayList);
			dropDownofUsers.setSelectedItem(tempupdatedListofUsers.get(0));
			highCheckBox.setSelected(true);
			subjectofMessage.setText("");
			contentofMessage.setText("");	
			JOptionPane.showMessageDialog(null, "Message was sent!", "Successful!", JOptionPane.INFORMATION_MESSAGE);
			this.updateListofMessages();		
		}
		else if(e.getSource() == clearButton) {
			dropDownofUsers.setSelectedItem(tempupdatedListofUsers.get(0));
			highCheckBox.setSelected(true);
			subjectofMessage.setText("");
			contentofMessage.setText("");
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(e.getValueIsAdjusting()) {
			if(e.getSource() == messageList) {
				int count = 0;
				String tempStr = messageList.getSelectedValue();
				String mainContent = null;
				String toContent = null;
				String allContent = null;
				String[] items = tempStr.split("\\s*,\\s*");
				ArrayList<String[]> items2 = new ArrayList<String[]>();
				ArrayList<String> checkerList = new ArrayList<String>();
				
				for (String i:items){
					items2.add(i.split(": "));
				}
				for (String[] i2:items2){
					for (String j: i2) {
						 
						if(count % 2 != 0) {
							checkerList.add(j);
						}
						count += 1;
					}
				}
				
				for (ArrayList<String> list : allMessagesContent) {
					if((checkerList.get(0).equals(list.get(0))) && (checkerList.get(1).equals(list.get(3))) && (checkerList.get(2).equals(list.get(2)))  && (checkerList.get(3).equals(list.get(4)))) {
						mainContent = list.get(5);
						toContent = list.get(1);
					}
				}
				
				allContent = "From: " + checkerList.get(0) + System.lineSeparator();
				allContent = allContent + "To: " + toContent + System.lineSeparator();
				allContent = allContent + "Priority: " + checkerList.get(2) + System.lineSeparator();
				allContent = allContent + "Subject: " + checkerList.get(1) + System.lineSeparator();
				allContent = allContent + "Time: " + checkerList.get(3) + System.lineSeparator();
				allContent = allContent + System.lineSeparator() + mainContent;
				fieldofMessage.setText(allContent);
			}
		}
	}
	
	public void updateListofUsersswitchUser(String UserName, ArrayList updatedListofUsers) {
		tempupdatedListofUsers.addAll(updatedListofUsers); 
	    Set<String> set = new LinkedHashSet<>();
	    
        set.addAll(tempupdatedListofUsers);
        tempupdatedListofUsers.clear();
        tempupdatedListofUsers.addAll(set);
        
        Collections.sort(tempupdatedListofUsers);
        
        dropDownofUsers.removeAllItems();
        
        for (String i : tempupdatedListofUsers){
			dropDownofUsers.addItem(i);;
		}
        
		this.currentUser = "Current User: " + UserName;
		this.currentUserNonFormat = UserName;
		this.currentLabel.setText(this.currentUser);
		dropDownofUsers.setSelectedItem(tempupdatedListofUsers.get(0));
		
	}
	
	
	public void updateListofUsersaddRecipient(String UserName, ArrayList updatedListofUsers) {
		tempupdatedListofUsers.addAll(updatedListofUsers); 
	    Set<String> set = new LinkedHashSet<>();
	    
        set.addAll(tempupdatedListofUsers);
        tempupdatedListofUsers.clear();
        tempupdatedListofUsers.addAll(set);
        
        Collections.sort(tempupdatedListofUsers);
        dropDownofUsers.removeAllItems();
        
		for (String i : tempupdatedListofUsers){
			dropDownofUsers.addItem(i);;
		}
		
		
		dropDownofUsers.setSelectedItem(tempupdatedListofUsers.get(0));
		
	}
	
	public void updateListofMessages() {
		fieldofMessage.setText(null);
		messageList.clearSelection();
		tempallMessagesContentTime.clear();
		
		for (ArrayList<String> list : allMessagesContent){
			if(this.currentUserNonFormat.equals(list.get(1))) {
				if(!tempallMessagesContent.contains(list)) {
					tempallMessagesContent.add(list);
				}
				
				tempallMessagesContentTime.add(list.get(4));
			}
		}
		
		tempallMessagesContentTime.sort(Comparator.reverseOrder());
		String[] tempallMessagesContentPri = {"High", "Medium", "Low"};
		model.removeAllElements();
		
		for (String pri: tempallMessagesContentPri){
			for (String time: tempallMessagesContentTime){
				for (ArrayList<String> list : tempallMessagesContent) {
					if((pri == list.get(2)) && (time == list.get(4))) {
						model.addElement("From: " + list.get(0) + ", Subject: " + list.get(3) + ", Priority: " + list.get(2) + ", Time: " + list.get(4));
						messageList.setModel(model);
					}
				}
			}
		}
	}
}



