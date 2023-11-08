package User;

import java.util.*;

import javax.swing.JPanel;
import Visitor.VisitorNode;

//userGroup class extends UserComponent
public class UserGroup extends UserComponent{
       //default count is 1 due to root user gorup
       private static int totalGroups = 1; 

       //holds user groups or users
       private List<UserComponent> userComponentList;

       //user group constructor, invokes UserComponent constructor for ID
       public UserGroup(String ID) {
              super(ID);
              userComponentList = new ArrayList<>();
       }
       
       //method for getting number of groups 
       public static int getTotalGroups() {
              return totalGroups;
       }

       //method for adding userComponent to group
       public void add(UserComponent userComponent) {
              userComponentList.add(userComponent);
       }

       //method for displaying user group in a panel
       public void display(JPanel displayPanel) {
              displayPanel.add(getLabel("\uD83D\uDCC1 " + getID()));
              spacing += 2;

              for(UserComponent userComponent : userComponentList) {
                     userComponent.display(displayPanel);
              }

              spacing -= 2;
       }

       //method for incrementing total # of groups
       public void incementGroupTotal() {
              totalGroups++;
       }

       //override method from vistiorNode interface
       @Override
       public void accept(VisitorNode visitor) {
              visitor.visit(this);
       }

}
