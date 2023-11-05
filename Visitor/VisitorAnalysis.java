package Visitor;

import Tweet.Tweet;
import User.User;
import User.UserGroup;

public class VisitorAnalysis implements VisitorNode{
       @Override
       public void visit(User user) {
              user.incrementTotalUsers();
       }

       @Override
       public void visit(UserGroup userGroup) {
              userGroup.incementGroupTotal();
       }

       @Override
       public void visit(Tweet tweet) {
              tweet.incrementTotalTweets();
       }

       @Override
       public void visit(String message) {
              message = message.toLowerCase(); // Only need to search for lowercase words

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
