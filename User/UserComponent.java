package User;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

import javax.swing.*;

import UI.AdminControlPanel;
import Visitor.Node;

public abstract class UserComponent implements Node{
       protected static int spacing = 0;

       private static final Color TRANSPARENT = new Color(0, 0, 0);
       private static final Color SELECTED = new Color(255, 255, 0);

       private String ID;
       private JLabel label;

       public UserComponent(String ID) {
              this.ID = ID;
       }

       public void setID(String ID) {
              this.ID = ID;
       }

       public String getID() {
              return ID;
       }

       protected JLabel getLabel(String labelText){
              if(label == null){
                     label = new JLabel();
                     labelText = String.format("%" + (spacing + labelText.length()) + "s", labelText);
                     label.setText(labelText);
                     label.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                   UserComponent selectedUser = AdminControlPanel.selectedUser;
                                   if(selectedUser != null) {
                                          JLabel curSelectedLabel = selectedUser.getLabel(null);
                                          curSelectedLabel.setOpaque(false);
                                          curSelectedLabel.setBackground(TRANSPARENT);

                                   }

                                   label.setOpaque(true);
                                   label.setBackground(SELECTED);
                                   AdminControlPanel.selectedUser = UserComponent.this;
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
       public abstract void display(JPanel displayPanel);
}
