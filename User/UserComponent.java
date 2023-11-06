package User;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

import javax.swing.*;

import UI.AdminControlPanel;
import Visitor.Node;

//Abstract class UserComponent for implementing Nodes 
public abstract class UserComponent implements Node{
       protected static int spacing = 0;

       //colors for UI, highlights yellow when group or user is selected
       private static final Color TRANSPARENT = new Color(0, 0, 0);
       private static final Color SELECTED = new Color(255, 255, 0);

       private String ID;
       private JLabel label;

       //userComponent selector
       public UserComponent(String ID) {
              this.ID = ID;
       }

       //getter and setter
       public void setID(String ID) {
              this.ID = ID;
       }

       public String getID() {
              return ID;
       }

       //method for getting JLabel from specifed label text
       protected JLabel getLabel(String labelText){
              if(label == null){
                     label = new JLabel();
                     labelText = String.format("%" + (spacing + labelText.length()) + "s", labelText);
                     label.setText(labelText);

                     //mouse listener for handling lable clicks and selections
                     label.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                   UserComponent selectedUser = AdminControlPanel.selectedUser;
                                   if(selectedUser != null) {
                                          JLabel curSelectedLabel = selectedUser.getLabel(null);
                                          curSelectedLabel.setOpaque(false);
                                          curSelectedLabel.setBackground(TRANSPARENT);

                                   }

                                   //selected user highlighted yellow
                                   label.setOpaque(true);
                                   label.setBackground(SELECTED);
                                   AdminControlPanel.selectedUser = UserComponent.this;
                                   //enable or disable user view in AdminControlPanel
                                   if(AdminControlPanel.selectedUser instanceof User) {
                                          AdminControlPanel.getInstance().enableUserView();
                                   }
                                   else {
                                          AdminControlPanel.getInstance().disableUserView();
                                   }

                            }
                     });
              }
              return label;
       }

       //abstract method for displaying user component in a panel
       public abstract void display(JPanel displayPanel);
}
