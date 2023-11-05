package UI;

import javax.swing.*;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

import Tweet.Tweet;
import User.User;
import User.UserComponent;
import User.UserGroup;
import Visitor.VisitorAnalysis;
import Visitor.VisitorNode;



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
       
       private UserGroup root;

       private AdminControlPanel() {
              root = new UserGroup("ROOT");

              //frame
              this.setTitle("MiniTwitter");
              this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              this.setSize(1000, 1000);
              this.setLayout(new GridLayout(1, 2));

              //tree panel 
              treePanel = new JPanel();
              treePanel.setLayout(new BoxLayout(treePanel, BoxLayout.PAGE_AXIS));
              this.add(treePanel);
              makeTree();
              
              //panel for controls on the right
              JPanel controlPanel = new JPanel();
              controlPanel.setLayout(new GridLayout(0, 1, 0, 4)); 
              
              //add view panel (add user and add group)
              JPanel addPanel = new JPanel();
              addPanel.setLayout(new GridLayout(2, 2, 4, 4));
              userText = new JTextField(16);
              addPanel.add(userText);
              addUserButton = new JButton("Add User");
             addUserButton.addActionListener(actionAddUser);
              addPanel.add(addUserButton);
              groupText = new JTextField(16);
              addPanel.add(groupText);
              addGroupButton = new JButton("Add Group");
              addGroupButton.addActionListener(actionAddGroup);
              addPanel.add(addGroupButton);
              controlPanel.add(addPanel);

              //userView panel
              JPanel userViewPanel = new JPanel();
              userViewPanel.setLayout(new GridLayout(3, 1));
              userViewButton = new JButton("Open User View");
              userViewButton.addActionListener(actionOpenUserView);
              userViewPanel.add(userViewButton);
              controlPanel.add(userViewPanel);
              disableUserView();

              //stats panel (user total, group total, message total, positive percentage)
              JPanel statsPanel = new JPanel();
              statsPanel.setLayout(new GridLayout(2, 2, 4, 4));
              showTotalUsersButton = new JButton("Show User Total");
              showTotalUsersButton.addActionListener(actionShowTotalUsers);
              statsPanel.add(showTotalUsersButton);
              showTotalGroupsButton = new JButton("Show Group Total");
              showTotalGroupsButton.addActionListener(actionShowTotalGroups);
              statsPanel.add(showTotalGroupsButton);
              showTotalMessagesButton = new JButton("Show Messages Total");
              showTotalMessagesButton.addActionListener(actionShowTotalMessages);
              statsPanel.add(showTotalMessagesButton);
              showPositivePercentButton = new JButton("Show Positive Percentage");
              showPositivePercentButton.addActionListener(actionShowPositivePercent);
              statsPanel.add(showPositivePercentButton);
              controlPanel.add(statsPanel);

              this.add(controlPanel);
              this.setVisible(true);
       }

       public static AdminControlPanel getInstance() {
              if(instance == null) {
                  instance = new AdminControlPanel();
              }
      
              return instance;
       }

       public void enableUserView() {
              userViewButton.setEnabled(true);
       }
      
       public void disableUserView() {
              userViewButton.setEnabled(false);
       }

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
       
       public void makeTree() {
              treePanel.removeAll();
              JLabel treeLabel = new JLabel("Tree View");
              treePanel.add(treeLabel);
              root.display(treePanel);
              treePanel.revalidate();
              treePanel.repaint();
       }

       //ActionListeners for buttons
       private ActionListener actionAddUser = new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent event) {
                     addToGroup(new User(userText.getText()));
                     userText.setText("");
                     makeTree();
              }
       };

       private ActionListener actionAddGroup = new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent event) {
                     addToGroup(new UserGroup(groupText.getText()));
                     groupText.setText("");
                     makeTree();
              }
       };

       private ActionListener actionOpenUserView = new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent event) {
                     if(selectedUser instanceof User) {
                            new UserView((User) selectedUser);
                     }
              }
       };

       private ActionListener actionShowTotalUsers = new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent event) {
                     new StatisticWindow("User Total: " + User.getUserTotal());
              }
       };

       private ActionListener actionShowTotalGroups = new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent event) {
                     new StatisticWindow("Group Total: " + UserGroup.getTotalGroups());
              }
       };

       private ActionListener actionShowTotalMessages = new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent event) {
                     new StatisticWindow("Messages Total: " + Tweet.getTotalTweets());
              }
       };

       private ActionListener actionShowPositivePercent = new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent event) {
              new StatisticWindow("Positive Percentage: " + Tweet.getPositivePercent() + "%");
              }
       };
}
