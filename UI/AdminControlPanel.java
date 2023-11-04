package UI;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

public class AdminControlPanel extends JFrame {
       private static AdminControlPanel instance;

       private JPanel treePanel;
       private JTextField userText;
       private JTextField groupText;
       private JButton addUserButton;
       private JButton addGroupButton;
       private JButton userViewButton;
       private JButton validateIDButton;
       private JButton lastUpdatedUserButton;
       private JButton showTotalUsersButton;
       private JButton showTotalGroupsButton;
       private JButton showTotalMessagesButton;
       private JButton showPositivePercentButton;

       private AdminControlPanel() {

              //frame
              this.setTitle("MiniTwitter");
              this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              this.setSize(1000, 1000);
              this.setLayout(new GridLayout(1, 2));

              //tree panel 
              treePanel = new JPanel();
              treePanel.setLayout(new BoxLayout(treePanel, BoxLayout.PAGE_AXIS));
              this.add(treePanel);
              
              //panel for controls on the right
              JPanel controlPanel = new JPanel();
              controlPanel.setLayout(new GridLayout(0, 1, 0, 4)); 
              
              //add view panel (add user and add group)
              JPanel addPanel = new JPanel();
              addPanel.setLayout(new GridLayout(2, 2, 4, 4));
              userText = new JTextField(16);
              addPanel.add(userText);
              addUserButton = new JButton("Add User");
              addPanel.add(addUserButton);
              groupText = new JTextField(16);
              addPanel.add(groupText);
              addGroupButton = new JButton("Add Group");
              addPanel.add(addGroupButton);
              controlPanel.add(addPanel);

              //userView panel
              JPanel userViewPanel = new JPanel();
              userViewPanel.setLayout(new GridLayout(3, 1));
              userViewButton = new JButton("Open User View");
              userViewPanel.add(userViewButton);
              controlPanel.add(userViewPanel);

              //stats panel (user total, group total, message total, positive percentage)
              JPanel statsPanel = new JPanel();
              statsPanel.setLayout(new GridLayout(2, 2, 4, 4));
              showTotalUsersButton = new JButton("Show User Total");
              statsPanel.add(showTotalUsersButton);
              showTotalGroupsButton = new JButton("Show Group Total");
              statsPanel.add(showTotalGroupsButton);
              showTotalMessagesButton = new JButton("Show Messages Total");
              statsPanel.add(showTotalMessagesButton);
              showPositivePercentButton = new JButton("Show Positive Percentage");
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
}
