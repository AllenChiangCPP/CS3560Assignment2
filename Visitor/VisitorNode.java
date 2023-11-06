package Visitor;

import Tweet.Tweet;
import User.User;
import User.UserGroup;

//visitorNode interface, declares methods inteded for visitors in the visitor design pattern
public interface VisitorNode {
       //visitation methods for users, userGroups, tweets, and messages
       public void visit(User user);
       public void visit(UserGroup userGroup);
       public void visit(Tweet tweet);
       public void visit(String message);
}
