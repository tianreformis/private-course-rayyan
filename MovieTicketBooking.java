import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovieTicketBooking {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MovieTicketBooking().createUI());
    }

    private void createUI() {
        // Create frame
        JFrame frame = new JFrame("Movie Ticket Booking");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Cinema Information
        addCinemaInfo(frame, gbc);
        
        // Movie Ticket Form
        addMovieTicketForm(frame, gbc);
        
        // Screen Layout
        addScreenLayout(frame, gbc);
        
        // Book Button
        addBookButton(frame, gbc);
        
        // Finalize and display frame
        frame.setVisible(true);
    }

    private void addCinemaInfo(JFrame frame, GridBagConstraints gbc) {
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.add(new JLabel("ISB Cinema"));
        
        infoPanel.add(new JLabel("Jl. Teknologi No. 123, Surabaya, Jawa Timur, Indonesia"));
        infoPanel.add(new JLabel("+62 812-3456-7890 (Customer Service)")); 
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        frame.add(infoPanel, gbc);
    }

    private void addMovieTicketForm(JFrame frame, GridBagConstraints gbc) {
        JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        
        formPanel.add(new JLabel("Name:"));
        JTextField nameField = new JTextField();
        formPanel.add(nameField);
        
        formPanel.add(new JLabel("Phone:"));
        JTextField phoneField = new JTextField();
        formPanel.add(phoneField);
        
        formPanel.add(new JLabel("Movie:"));
        String[] movies = {"Avatar 2", "Other Movies..."}; // Add more movies as desired
        JComboBox<String> movieList = new JComboBox<>(movies);
        formPanel.add(movieList);

        formPanel.add(new JLabel("Time:"));
        String[] times = {"10.00", "12.00", "14.10", "16.00", "18.30", "20.00"};
        JComboBox<String> timeList = new JComboBox<>(times);
        formPanel.add(timeList);
        
        formPanel.add(new JLabel("Type:"));
        String[] types = {"VIP", "Regular"};
        JComboBox<String> typeList = new JComboBox<>(types);
        formPanel.add(typeList);

        formPanel.add(new JLabel("Extras:"));
        JCheckBox popcornBox = new JCheckBox("Popcorn");
        JCheckBox drinkBox = new JCheckBox("Drink");
        JPanel extrasPanel = new JPanel();popcornBox
        extrasPanel.add(popcornBox);
        extrasPanel.add(drinkBox);
        formPanel.add(extrasPanel);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.add(formPanel, gbc);
    }

    private void addScreenLayout(JFrame frame, GridBagConstraints gbc) {
        JPanel screenPanel = new JPanel(new GridLayout(7, 15, 5, 5));
        String[] seatLabels = {
            "H1", "H2", "H3", "H4", "H5", "H6", "H7", "H8", "H9", "H10", "H11", "H12", "H13", "H14",
            "G1", "G2", "G3", "G4", "G5", "G6", "G7", "G8", "G9", "G10", "G11", "G12", "G13", "G14",
            "F1", "F2", "F3", "F4", "F5", "F6", "F7", "F8", "F9", "F10", "F11", "F12", "F13", "F14",
            "E1", "E2", "E3", "E4", "E5", "E6", "E7", "E8", "E9", "E10", "E11", "E12", "E13", "E14",
            "D1", "D2", "D3", "D4", "D5", "D6", "D7", "D8", "D9", "D10", "D11", "D12", "D13", "D14",
            "C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "C10", "C11", "C12", "C13", "C14",
            "B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8", "B9", "B10", "B11", "B12", "B13", "B14",
            "A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "A10", "A11", "A12", "A13", "A14"
        };
        for (String label : seatLabels) {
            JButton seatButton = new JButton(label);
            seatButton.addActionListener(new SeatButtonActionListener(seatButton));
            screenPanel.add(seatButton);
        }
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        frame.add(screenPanel, gbc);
    }

    private void addBookButton(JFrame frame, GridBagConstraints gbc) {
        JButton bookButton = new JButton("BOOK");
        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle booking logic here
                JOptionPane.showMessageDialog(frame, "Booking Successful!");
            }
        });
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        frame.add(bookButton, gbc);
    }

    private static class SeatButtonActionListener implements ActionListener {
        private JButton seatButton;

        public SeatButtonActionListener(JButton seatButton) {
            this.seatButton = seatButton;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Toggle seat selection logic
            if (seatButton.getBackground() == Color.RED) {
                seatButton.setBackground(null); // Deselect seat
            } else {
                seatButton.setBackground(Color.RED); // Select seat
            }
        }
    }
}