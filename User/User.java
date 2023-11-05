package User;

import java.util.*;

import javax.swing.JPanel;

import Observable.Observer;
import Tweet.PostedTweets;
import Tweet.Tweet;
import UI.UserView;
import Visitor.VisitorNode;

public class User extends UserComponent implements Observer {
       private static List<User> allUsers = new ArrayList<>();
       private static int userTotal = 0;

       private UserView currentUser;
       private List<User> followerList;
       private List<User> followingList;

       private PostedTweets postedTweets;
       
       private Map<String, Tweet> newsFeed;

       public User(String ID) {
              super(ID);
              allUsers.add(this);
              followerList = new ArrayList<>();
              followingList = new ArrayList<>();
              postedTweets = new PostedTweets();
              newsFeed = new HashMap<>();
              followTweeter(this); // User follows itself to see own tweets
       }

       public static int getUserTotal(){
              return userTotal;
       }

       public List<User> getFollowers(){
              return followerList;
       }

       public List<User> getFollowing(){
              return followingList;
       }

       public void incrementTotalUsers() {
              userTotal++;
       }

       public Collection<Tweet> getOrderedNewsFeedMsgs() {
              List<Tweet> orderedNewsFeed = new ArrayList<>(newsFeed.values());
              orderedNewsFeed.sort(Comparator.comparing(Tweet::getOrderCreated));
              return orderedNewsFeed;
       }

       public static User getUserByID(String ID) {
              User userFound = null;

              for(User user : allUsers) {
                     if(user.getID().equals(ID) && userFound == null) {
                            userFound = user;
                     }
              }
              return userFound;
       }

       public void display(JPanel displayPanel) {
              displayPanel.add(getLabel("\uD83D\uDC64 " + getID()));
       }

       public void postTweet(Tweet tweet) {
              postedTweets.post(tweet);
       }

       //method for following another user
       //adds current user as observer to followed user's posted tweets and following list
       public void followTweeter(User user) {
              user.postedTweets.addObserver(this);
              followingList.add(user);
              user.followerList.add(this);

              if(currentUser != null) {
                     currentUser.drawFollowing(followingList);
              }
       }

       public void bindUserView(UserView feedView) {
              currentUser = feedView;
       }

       public void unbindUserView() {
              currentUser = null;
       }

       @Override
       public void update() {
              for(User followedUser : followingList) {
                     List<Tweet> tweetsByUser = followedUser.postedTweets.getTweets();
                     for(Tweet tweet : tweetsByUser) {
                            String currentTweetID = tweet.getID();

                            if(!newsFeed.containsKey(currentTweetID)) {
                                   newsFeed.put(currentTweetID, tweet);
                            }
                     }
              }

              currentUser.drawFeed(getOrderedNewsFeedMsgs());
       }

       @Override 
       public void accept(VisitorNode visitor) {
              visitor.visit(this);
       }
}
