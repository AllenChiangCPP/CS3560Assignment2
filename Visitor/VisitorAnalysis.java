package Visitor;

import Tweet.Tweet;
import User.User;
import User.UserGroup;

//visitorAnalysis class, performs analysis on total count of groups, users, and tweets, as well as positivity percentage
public class VisitorAnalysis implements VisitorNode{
       //overriden visit method, increments total amount of users (counts # of users)
       @Override
       public void visit(User user) {
              user.incrementTotalUsers();
       }

       //overriden visit method, increments total amount of groups (counts # of groups)
       @Override
       public void visit(UserGroup userGroup) {
              userGroup.incementGroupTotal();
       }

       //overriden visit method, increments total amount of tweets (counts # of tweets)
       @Override
       public void visit(Tweet tweet) {
              tweet.incrementTotalTweets();
       }

       //overriden visit method, gets and updates percentage of positive tweets
       @Override
       public void visit(String message) {
              message = message.toLowerCase(); //converts message to lowercase to account for case differences
              if(message.contains("good")
                     || message.contains("great")
                     || message.contains("excellent"))
              {
                     Tweet.updatePositivePercent(true);
              }
              else {
                     Tweet.updatePositivePercent(false);
              }
       }
}
