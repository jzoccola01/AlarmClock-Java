import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AlarmClock extends JFrame implements ActionListener {
    private final JButton setButton;
    private final JButton resetButton;
    private final JButton snoozeButton;
    private final JLabel timeLabel;

    public AlarmClock() {
        super("My Alarm Clock");

        // Create the GUI Buttons
        setButton = new JButton("Set Alarm");
        resetButton = new JButton("Reset Alarm");
        snoozeButton = new JButton("SNOOZE");

        Font font = new Font("Helvetica Neue", Font.BOLD, 20);
        Font timeLabelFont = new Font("Helvetica Neue", Font.BOLD, 100);

        // change the font of the JButtons
        setButton.setFont(font);
        resetButton.setFont(font);
        snoozeButton.setFont(font);

        // Display the timeLabel in the center of the JFrame
        timeLabel = new JLabel("00:00:00", SwingConstants.CENTER);
        timeLabel.setFont(timeLabelFont);

        // Set the layout manager and add the components to the JFrame
        setLayout(new BorderLayout());
        //add(setButton, BorderLayout.SOUTH);
        //add(resetButton, BorderLayout.SOUTH);

        add(snoozeButton, BorderLayout.SOUTH);
        add(timeLabel, BorderLayout.CENTER);

        // Add action listeners to the buttons
        setButton.addActionListener(this);
        resetButton.addActionListener(this);
        snoozeButton.addActionListener(this);

        // Set JFrame properties
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        // Handle button clicks
        if (e.getSource() == setButton) {
            // Handle Set button click
            // Show a dialog box to allow the user to set the alarm time
            String time = JOptionPane.showInputDialog("Enter alarm time (hh:mm:ss)");
            timeLabel.setText(time);

        } else if (e.getSource() == resetButton) {
            // Handle Reset button click
            // Reset the alarm time to 00:00:00
            timeLabel.setText("00:00:00");
        } else if (e.getSource() == snoozeButton) {
            // Handle Snooze button click
            // Play a snooze sound and delay the alarm for 5 minutes
            SoundPlayer mySoundPlayer = new SoundPlayer();
            mySoundPlayer.playSound("snooze.wav");
            // Add 5 minutes to the current time
            String currentTime = timeLabel.getText();
            String[] timeParts = currentTime.split(":");
            int hours = Integer.parseInt(timeParts[0]);
            int minutes = Integer.parseInt(timeParts[1]);
            int seconds = Integer.parseInt(timeParts[2]);
            minutes += 5;

            if (minutes >= 60) {
                hours++;
                minutes -= 60;
            }

            if (hours >= 24) {
                hours -= 24;
            }

            // Update the time label
            timeLabel.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));

        }
    }

    public static void main(String[] args) {
        // Create a new instance of the AlarmClock class
        new AlarmClock();
    }
}
