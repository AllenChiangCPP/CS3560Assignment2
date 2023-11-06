package UI;

import javax.swing.*;

//StatisticWindow object, frame and window for 
//show user total, show grtoup total, show messages total, and show positive percentage total popup windows
public class StatisticWindow extends JFrame{
       public StatisticWindow(String message) {
              // Set up frame for the statistic window
              this.setTitle("Statistic");
              this.setSize(400, 250);
              JLabel messageLabel = new JLabel(message);
              messageLabel.setHorizontalTextPosition(JLabel.CENTER);
              messageLabel.setHorizontalAlignment(JLabel.CENTER);
              this.add(messageLabel);
              this.setVisible(true);
          }

}
