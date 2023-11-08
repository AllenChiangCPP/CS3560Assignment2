package UI;

import javax.swing.*;

//StatisticWindow object, popup window for showing data, takes message as input
//show user total, show group total, show messages total, and show positive percentage total popup windows
public class StatisticWindow extends JFrame{
       public StatisticWindow(String message) {
              // Set up frame for the statistic window
              this.setTitle("Statistic");
              this.setSize(300, 300);
              JLabel messageLabel = new JLabel(message);
              messageLabel.setHorizontalTextPosition(JLabel.CENTER);
              messageLabel.setHorizontalAlignment(JLabel.CENTER);
              this.add(messageLabel);
              this.setVisible(true);
          }

}
