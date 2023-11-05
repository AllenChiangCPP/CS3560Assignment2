package UI;

import javax.swing.*;

public class StatisticWindow extends JFrame{

       public StatisticWindow(String message) {
              // Set up frame
              this.setTitle("Statistic");
              this.setSize(400, 250);
              JLabel messageLabel = new JLabel(message);
              messageLabel.setHorizontalTextPosition(JLabel.CENTER);
              messageLabel.setHorizontalAlignment(JLabel.CENTER);
              this.add(messageLabel);
              this.setVisible(true);
          }

}
