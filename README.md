# CS3560Assignment2
**Overview**<br>
Java program that simulates Twitter with admin controls for analysis and statistics.


**Dependencies**<br>
This project uses the following libraries:
 <br>
-java.util<br>
-javax.swing<br>
-javax.swing.event<br>
-java.awt.event<br>
-java.awt.event<br>

**Results**<br>
When Driver.java is run, Creates the admin control panel which allows users and groups to be created and allows statistics to be calculated and displayed such as total number of messages. Created users can also be viewed where the user can make them follow other users and post tweets.

**Note**<br>
-Code and run tests were done on Visual Studio Code.

**Usage**      
-VSCode: Top right button to compile run Driver.java file in Visual Studio Code.<br>
-Command line: "javac Driver.java" compiles Driver.java and all other files needed to run and create Driver.class file. "java Driver" runs the Driver code.

**Java Design Patterns Used** <br>
-Singleton Pattern: (AdminControlPanel.java) Singleton pattern is used in AdminControlPanel.java with the getInstance() method, to ensure there is only one instance fo the AdminContorlPanel active. <br>
-Composite Pattern: (UserComponent.java, User.java, UserGroup.java) Composite pattern is used in UserComponent.java, User.java, and UserGroup.java for the root hierarchies and tree view for organinzing groups and their users. <br>
-Observer Pattern: (PostedTweets.java, User.java) Observer Pattern is used in PostedTweets.java and User.java, where a User object acts as an observer and PostedTweets acts as a subject to be observed. PostedTweets notifies the subject User object when new tweets are posted.<br>
-Visitor Pattern: (VisitorAnalysis.java, Tweet.java, AdminControlPanel.java, UserView.java, User.java, UserComponent.java, UserGroup.java) Visitor Pattern is used where the VisitorAnalysis class acts a a visitor to the User.java, Tweet.java, 
and UserGroup.java in order to find analytic data such as number of tweets in Tweet.java without altering the classes VisitorAnalysis visits.
