package User;

import java.util.*;

import javax.swing.JPanel;
import Visitor.VisitorNode;

public class UserGroup extends UserComponent{
       private static int totalGroups = 1;
       private List<UserComponent> userComponentList;

       public UserGroup(String ID) {
              super(ID);
              userComponentList = new ArrayList<>();
       }
       
       public static int getTotalGroups() {
              return totalGroups;
       }

       public void add(UserComponent userComponent) {
              userComponentList.add(userComponent);
       }

       public void display(JPanel displayPanel) {
              displayPanel.add(getLabel("\uD83D\uDCC1 " + getID()));
              spacing += 2;

              for(UserComponent userComponent : userComponentList) {
                     userComponent.display(displayPanel);
              }

              spacing -= 2;
       }

       public void incementGroupTotal() {
              totalGroups++;
       }

       @Override
       public void accept(VisitorNode visitor) {
              visitor.visit(this);
       }

}
