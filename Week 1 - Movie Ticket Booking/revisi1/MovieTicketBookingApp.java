import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovieTicketBookingApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        // Create the frame
        JFrame frame = new JFrame("Movie Ticket Booking");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        
        // Create panels
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel formPanel = new JPanel(new GridLayout(5, 2));
        JPanel screenPanel = new JPanel(new GridLayout(8, 15));
        JPanel bookingPanel = new JPanel();
        
        // Header Label
        JLabel headerLabel = new JLabel("ISB Cinema", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        
        // Movie Booking Form
        formPanel.add(new JLabel("Name:"));
        JTextField nameField = new JTextField();
        formPanel.add(nameField);
        
        formPanel.add(new JLabel("Phone:"));
        JTextField phoneField = new JTextField();
        formPanel.add(phoneField);
        
        formPanel.add(new JLabel("Movie:"));
        String[] movies = {"Avatar 2", "Spider-Man", "Titanic"};
        JComboBox<String> movieDropdown = new JComboBox<>(movies);
        formPanel.add(movieDropdown);
        
        formPanel.add(new JLabel("Time:"));
        String[] times = {"10:00", "12:00", "14:10", "16:00", "18:30", "20:00"};
        JComboBox<String> timeDropdown = new JComboBox<>(times);
        formPanel.add(timeDropdown);
        
        formPanel.add(new JLabel("Type:"));
        String[] types = {"VIP", "Regular"};
        JComboBox<String> typeDropdown = new JComboBox<>(types);
        formPanel.add(typeDropdown);
        
        // Extras
        formPanel.add(new JLabel("Extras:"));
        JCheckBox popcornBox = new JCheckBox("Popcorn");
        JCheckBox drinkBox = new JCheckBox("Drink");
        JPanel extrasPanel = new JPanel();
        extrasPanel.add(popcornBox);
        extrasPanel.add(drinkBox);
        formPanel.add(extrasPanel);
        
        // Create Screen Seating
        for (char row = 'A'; row <= 'F'; row++) {
            for (int col = 1; col <= 14; col++) {
                String seatLabel = "" + row + col;
                JButton seatButton = new JButton(seatLabel);
                seatButton.setActionCommand(seatLabel);
                screenPanel.add(seatButton);
                
                // Add an action listener for the seat button
                seatButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Handle seat selection
                        seatButton.setEnabled(false); // Disable the button once selected
                        seatButton.setBackground(Color.RED); // Change color to indicate selection
                    }
                });
            }
        }
        
        // Booking Button
        JButton bookButton = new JButton("BOOK");
        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle booking action
                String name = nameField.getText();
                String phone = phoneField.getText();MovieTicketBookingApp
                String movie = (String) movieDropdown.getSelectedItem();
                String time = (String) timeDropdown.getSelectedItem();
                String type = (String) typeDropdown.getSelectedItem();
                
                String extras = "";
                if (popcornBox.isSelected()) extras += "Popcorn ";
                if (drinkBox.isSelected()) extras += "Drink";
                
                // Display booking confirmation
                String message = "Booking Details:\n" +
                        "Name: " + name + "\n" +
                        "Phone: " + phone + "\n" +
                        "Movie: " + movie + "\n" +
                        "Time: " + time + "\n" +
                        "Type: " + type + "\n" +
                        "Extras: " + extras;
                JOptionPane.showMessageDialog(frame, message);
            }
        });
        
        bookingPanel.add(bookButton);
        
        // Adding components to the main panel
        mainPanel.add(headerLabel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.WEST);
        mainPanel.add(screenPanel, BorderLayout.CENTER);
        mainPanel.add(bookingPanel, BorderLayout.SOUTH);
        
        // Set up frame content
        frame.setContentPane(mainPanel);
        frame.setVisible(true);
    }
}