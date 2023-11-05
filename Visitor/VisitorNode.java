package Visitor;

import Tweet.Tweet;
import User.User;
import User.UserGroup;

public interface VisitorNode {
       public void visit(User user);
       public void visit(UserGroup userGroup);
       public void visit(Tweet tweet);
       public void visit(String message);
}
