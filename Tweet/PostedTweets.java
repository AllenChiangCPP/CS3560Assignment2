package Tweet;

import java.util.ArrayList;
import java.util.List;

import Observable.Subject;

//PostedTweets class manages a colleciton of Tweet objects
//notifes observers when new tweet is posted
public class PostedTweets extends Subject{
       List<Tweet> tweets;
       
       public PostedTweets() {
              tweets = new ArrayList<>();
       }

       public void post(Tweet tweet) {
              tweets.add(tweet);
              notifyObservers();
       }
       
       public List<Tweet>  getTweets() {
              return tweets;
       }
}
