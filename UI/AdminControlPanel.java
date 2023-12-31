package UI;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.Color;

import Tweet.Tweet;
import User.User;
import User.UserComponent;
import User.UserGroup;
import Visitor.VisitorAnalysis;
import Visitor.VisitorNode;


//AdminControlPanel. draws UI using java Swing and contains functionalities
public class AdminControlPanel extends JFrame {
       
       private static AdminControlPanel instance;

       public static UserComponent selectedUser;

       private JPanel treePanel;
       private JTextField userText;
       private JTextField groupText;
       private JButton addUserButton;
       private JButton addGroupButton;
       private JButton userViewButton;
       private JButton showTotalUsersButton;
       private JButton showTotalGroupsButton;
       private JButton showTotalMessagesButton;
       private JButton showPositivePercentButton;
       private JButton IDVerificationButton;
       private JButton lastUpdatedUserButton;
       
       private UserGroup root;

       private AdminControlPanel() {
              //default root UserGroup for treePanel
              root = new UserGroup("ROOT");

              //Main frame for Admin Control Panel
              this.setTitle("MiniTwitter");
              this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
              this.setLayout(new GridLayout(1, 2));

              //tree panel for users and groups 
              treePanel = new JPanel();
              treePanel.setLayout(new BoxLayout(treePanel, BoxLayout.PAGE_AXIS));
              treePanel.setBackground(Color.GRAY);
              this.add(treePanel);
              makeTree();
              
              //panel for controls on the right
              JPanel controlPanel = new JPanel();
              controlPanel.setLayout(new GridLayout(0, 1, 0, 4)); 
              
              //add view panel (add user and add group)
              JPanel addPanel = new JPanel();
              addPanel.setLayout(new GridLayout(2, 2, 4, 4));

              //add user text field and button
              userText = new JTextField(16);
              addPanel.add(userText);
              addUserButton = new JButton("Add User");
              addUserButton.addActionListener(actionAddUser);
              addPanel.add(addUserButton);

              //add group text field and button
              groupText = new JTextField(16);
              addPanel.add(groupText);
              addGroupButton = new JButton("Add Group");
              addGroupButton.addActionListener(actionAddGroup);
              addPanel.add(addGroupButton);
              controlPanel.add(addPanel);

              //userView, validate ID, and show last updated user panel and button
              JPanel userViewPanel = new JPanel();
              userViewPanel.setLayout(new GridLayout(3, 1));

              //open user view button
              userViewButton = new JButton("Open User View");
              userViewButton.addActionListener(actionOpenUserView);
              userViewPanel.add(userViewButton);

              //validate ID button
              IDVerificationButton = new JButton("Verify IDs");
              IDVerificationButton.addActionListener(actionVerifyIDs);
              userViewPanel.add(IDVerificationButton);

              //find last updated user button
              lastUpdatedUserButton = new JButton("Find Last Updated User");
              lastUpdatedUserButton.addActionListener(actionFindLastUser);
              userViewPanel.add(lastUpdatedUserButton);

              controlPanel.add(userViewPanel);
              disableUserView();

              
              

              //stats panel (user total, group total, message total, positive percentage)
              JPanel statsPanel = new JPanel();
              statsPanel.setLayout(new GridLayout(2, 2, 4, 4));

              //show total users button
              showTotalUsersButton = new JButton("Show User Total");
              showTotalUsersButton.addActionListener(actionShowTotalUsers);
              statsPanel.add(showTotalUsersButton);

              //show total groups button
              showTotalGroupsButton = new JButton("Show Group Total");
              showTotalGroupsButton.addActionListener(actionShowTotalGroups);
              statsPanel.add(showTotalGroupsButton);

              //show total messages button
              showTotalMessagesButton = new JButton("Show Messages Total");
              showTotalMessagesButton.addActionListener(actionShowTotalMessages);
              statsPanel.add(showTotalMessagesButton);

              //show positive percentage button
              showPositivePercentButton = new JButton("Show Positive Percentage");
              showPositivePercentButton.addActionListener(actionShowPositivePercent);
              statsPanel.add(showPositivePercentButton);
              controlPanel.add(statsPanel);



              this.add(controlPanel);
              this.setVisible(true);
       }

       //getInstance method for implementing Singlton pattern, ensures one instance of AdminControlPanel is created
       public static AdminControlPanel getInstance() {
              if(instance == null) {
                  instance = new AdminControlPanel();
              }
              return instance;
       }

       //functions for enabling or disabling userview for userview button
       public void enableUserView() {
              userViewButton.setEnabled(true);
       }
      
       public void disableUserView() {
              userViewButton.setEnabled(false);
       }

       //addToGroup function for adding user to a created group or default root group
       //adds user to group if a group is selected, defaults to root otherwise
       private void addToGroup(UserComponent componentToAdd) {
              if(selectedUser instanceof UserGroup) {
                  ((UserGroup) selectedUser).add(componentToAdd);
              }
              else {
                  root.add(componentToAdd);
              }
              VisitorNode analysisVisitor = new VisitorAnalysis();
              componentToAdd.accept(analysisVisitor);
          }
       
       //constructs and refreshes tree display
       public void makeTree() {
              treePanel.removeAll();
              JLabel treeLabel = new JLabel("Tree View");
              treePanel.add(treeLabel);
              root.display(treePanel);
              treePanel.revalidate();
              treePanel.repaint();
       }

       //ActionListeners for button clicks and user interaction

       //Add User actionListener 
       private ActionListener actionAddUser = new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent event) {
                     addToGroup(new User(userText.getText()));
                     userText.setText("");
                     makeTree();
              }
       };

       //Add Group actionListener 
       private ActionListener actionAddGroup = new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent event) {
                     addToGroup(new UserGroup(groupText.getText()));
                     groupText.setText("");
                     makeTree();
              }
       };

       //Open user view actionListener
       private ActionListener actionOpenUserView = new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent event) {
                     if(selectedUser instanceof User) {
                            new UserView((User) selectedUser);
                     }
              }
       };

       //Show total users actionListener
       private ActionListener actionShowTotalUsers = new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent event) {
                     new StatisticWindow("User Total: " + User.getUserTotal());
              }
       };

       //show total groups actionListener
       private ActionListener actionShowTotalGroups = new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent event) {
                     new StatisticWindow("Group Total: " + UserGroup.getTotalGroups());
              }
       };

       //show total messages actionListener
       private ActionListener actionShowTotalMessages = new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent event) {
                     new StatisticWindow("Messages Total: " + Tweet.getTotalTweets());
              }
       };

       //show positive percentage action listener
       private ActionListener actionShowPositivePercent = new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent event) {
                     new StatisticWindow("Positive Percentage: " + Tweet.getPositivePercent() + "%");
              }
       };

       private ActionListener actionVerifyIDs = new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent event) {
                     String verificationMsg;
                     boolean isValid = UserComponent.validateIDs();
                     if(isValid) {
                            verificationMsg = "All IDs are valid.";
                     }
                     else {
                            verificationMsg = "WARNING. There are invalid IDs.";
                     }
                     JOptionPane.showMessageDialog(AdminControlPanel.this, verificationMsg);
              }
       };

       private ActionListener actionFindLastUser = new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent event) {
                     String foundUserMessage;
                     User lastUpdatedUser = User.getLastUpdatedUser();
                     if(lastUpdatedUser != null) {
                            foundUserMessage = "Last Updated User: " + User.getLastUpdatedUser().getID();
                     }
                     else {
                            foundUserMessage = "No users found."
;                     }
                     JOptionPane.showMessageDialog(AdminControlPanel.this, foundUserMessage);
              }
       };
}
