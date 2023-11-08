package UI;

import java.awt.GridLayout;
import java.awt.event.*;
import java.util.Collection;
import java.util.List;
import java.awt.Color;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Tweet.Tweet;
import User.User;
import Visitor.VisitorAnalysis;
import Visitor.VisitorNode;

//User view panel 
public class UserView extends JFrame {
    
       private User user;
       JPanel followDisplayPanel;
       private JPanel feedPanel;
       private JTextField followText;
       private JTextField tweetText;
       private JButton followButton;
       private JButton tweetButton;

       //User constructor
       public UserView(User user) {
              this.user = user;
              user.bindUserView(this);

              //frame 
              this.setTitle(user.getID() + " User View");
              this.setSize(500, 500);
              this.setLayout(new GridLayout(0, 1));
              this.addWindowListener(windowClose);

              //follow control panel
              JPanel followControlPanel = new JPanel();
              followControlPanel.setLayout(new GridLayout(1, 2));

              //Follow text field and button
              followText = new JTextField(16);
              followText.getDocument().addDocumentListener(textChange);
              followControlPanel.add(followText);
              followButton = new JButton("Follow User");
              followButton.addActionListener(actionFollow);
              followButton.setEnabled(false);
              followControlPanel.add(followButton);
              this.add(followControlPanel);

              //followers display panel
              followDisplayPanel = new JPanel();
              followDisplayPanel.setBackground(Color.GRAY);
              followDisplayPanel.setLayout(new BoxLayout(followDisplayPanel, BoxLayout.PAGE_AXIS));
              drawFollowing(user.getFollowers());
              this.add(followDisplayPanel);

              //tweet panel
              JPanel tweetPanel = new JPanel();
              tweetPanel.setLayout(new GridLayout(1, 2));

              //tweet text field and button
              tweetText = new JTextField(16);
              tweetText.getDocument().addDocumentListener(textChange);
              tweetPanel.add(tweetText);
              tweetButton = new JButton("Post Tweet");
              tweetButton.addActionListener(actionTweet);
              tweetButton.setEnabled(false);
              tweetPanel.add(tweetButton);
              this.add(tweetPanel);

              //newsFeed panel
              feedPanel = new JPanel();
              feedPanel.setBackground(Color.GRAY);
              feedPanel.setLayout(new BoxLayout(feedPanel, BoxLayout.PAGE_AXIS));
              drawFeed(user.getOrderedNewsFeedMsgs());
              this.add(feedPanel);

              this.setVisible(true);
       }

       //textChanged method for responding to changes in text fields based on if they are empty or not
       //enables or disables followButton or TweetButton if they are emty or not
       private void textChanged() {
              if(followText.getText().isEmpty()) {
                     followButton.setEnabled(false);
              }
              else {
                     followButton.setEnabled(true);
              }

              if(tweetText.getText().isEmpty()) {
                     tweetButton.setEnabled(false);
              }
              else {
                     tweetButton.setEnabled(true);
              }
       }

       //function for drawing user's news feed using hte feed list
       public void drawFeed(Collection<Tweet> feed) {
              feedPanel.removeAll();
              JLabel feedLabel = new JLabel("News Feed:");
              feedPanel.add(feedLabel);
              for(Tweet curTweet : feed) {
                     feedPanel.add(new JLabel(curTweet.getName() + " : " + curTweet.getMessage()));
              }
              feedPanel.revalidate();
              feedPanel.repaint();
       }

       //function for drawing user's following list using followings list
       public void drawFollowing(List<User> followings) {
              followDisplayPanel.removeAll();
              JLabel followLabel = new JLabel("Current Following:");
              followDisplayPanel.add(followLabel);
              for(User following : followings) {
                     String followingId = following.getID();

                     if(!followingId.equals(user.getID())) {
                            followDisplayPanel.add(new JLabel(" - " + following.getID()));
                     }
              }
              followDisplayPanel.revalidate();
              followDisplayPanel.repaint();
       }

       //ActionListener for each button clicks and user interaction

       //follow action action listener
       private ActionListener actionFollow = new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent event) {
              User userToFollow = User.getUserByID(followText.getText());
              
              if(userToFollow != null) {
                     user.followTweeter(userToFollow);
                     followText.setText("");
              }
              else {
                     System.out.println("Invalid User ID");
              }
              }
       };

       //tweet action listener
       private ActionListener actionTweet = new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent event) {
                     Tweet tweet = new Tweet(user.getID(), tweetText.getText());
                     tweetText.setText("");
                     user.postTweet(tweet);
                     VisitorNode analysisVisitor = new VisitorAnalysis();
                     tweet.accept(analysisVisitor);
              }
       };

       //Document listener for tracking text field changes for follow and tweet 
       private DocumentListener textChange = new DocumentListener() {
              //triggered when text is inserted
              @Override
              public void insertUpdate(DocumentEvent event) {
                     textChanged();
              }
              //triggered when text is removed
              @Override
              public void removeUpdate(DocumentEvent event) {
                     textChanged();
              }
              //triggered when changes have been made
              @Override
              public void changedUpdate(DocumentEvent event) {
                     textChanged();
              }
              
       };

       //Window listener for handling actions when user view window is closed
       private WindowListener windowClose = new WindowAdapter() {
              @Override
              public void windowClosing(WindowEvent event) {
                     user.unbindUserView();
              }
       };

}
