package Tweet;

import java.util.UUID;

import Visitor.Node;
import Visitor.VisitorNode; 
//Tweet object, tweet message and functions for tracking Node of Visitor
public class Tweet implements Node {
       private static int nextCreated = 1;
       private static int totalTweets = 0;
       private static int positiveCount = 0;
       private static int positivePercent = 0;

       private int orderCreated;
       private String ID;
       private String name;
       private String message;
       
       //Tweet constructor
       public Tweet(String name, String message) {
              orderCreated = nextCreated++;
              ID = UUID.randomUUID().toString();
              this.name = name;
              this.message = message;
       }

       //getter and setters
       public static int getTotalTweets() {
              return totalTweets;
       }

       public static int getPositivePercent() {
              return positivePercent;
       }

       public void incrementTotalTweets() {
              totalTweets++;
       }

       public int getOrderCreated() {
              return orderCreated;
       }

       public String getID() {
              return ID;
       }

       public String getName() {
              return name;
       }

       public String getMessage() {
              return message;
       }

       //calculates and updates positive percentage if positive message found
       public static void updatePositivePercent(boolean positive) {
              if(positive) {
                     positiveCount++;
              }
              positivePercent = (int)((double) positiveCount / totalTweets * 100);
       }

       //Visitor patter, allows visitor to access tweet object and its message
       public void accept(VisitorNode visitor) {
              visitor.visit(this);
              visitor.visit(message);
       }

}
