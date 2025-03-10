import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Main {
    private JTextField nameField;
    private JTextField phoneField;
    private JComboBox<String> movieDropdown;
    private JButton[] timeButtons;
    private JRadioButton vipButton;
    private JRadioButton regularButton;
    private JCheckBox popcornCheck;
    private JCheckBox drinkCheck;
    private List<JButton> selectedSeats = new ArrayList<>();
    private JButton selectedTimeButton = null;
    
    // Pricing (in IDR)
    private static final int PRICE_VIP = 100000;
    private static final int PRICE_REGULAR = 50000;
    private static final int PRICE_POPCORN = 50000;
    private static final int PRICE_DRINK = 30000;
    
    // Locale for Indonesian Rupiah
    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main().createAndShowGUI();
        });
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Movie Ticket Booking");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 600);
        frame.setLayout(new BorderLayout(10, 10));

        JLabel titleLabel = new JLabel("Movie Ticket Booking", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // Use a placeholder icon since we don't have the actual image file
        ImageIcon originalIcon = new ImageIcon("./src/logo-cinema.png"); // Path gambar
        Image scaledImage = originalIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);
        JLabel logoLabel = new JLabel(resizedIcon);
        logoLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 20));

        JPanel headerPanel = new JPanel(new BorderLayout());

        JPanel infoPanel = new JPanel(new GridLayout(3, 1));
        infoPanel.add(new JLabel("ISB Cinema", SwingConstants.LEFT));
        infoPanel.add(new JLabel("Jl. Teknologi No. 123, Surabaya, Jawa Timur, Indonesia"));
        infoPanel.add(new JLabel("+62 812-3456-7890 (Customer Service)"));

        headerPanel.add(logoLabel, BorderLayout.WEST);
        headerPanel.add(infoPanel, BorderLayout.CENTER);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setPreferredSize(new Dimension(300, 300));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        nameField = new JTextField(12);
        formPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Phone:"), gbc);
        gbc.gridx = 1;
        phoneField = new JTextField(12);
        formPanel.add(phoneField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Movie:"), gbc);
        gbc.gridx = 1;
        // Dropdown with movies
        String[] movies = {
                "Avatar 2",
                "Oppenheimer",
                "Interstellar",
                "Spider-Man: No Way Home",
                "The Batman",
                "Dune: Part Two",
                "Parasite"
        };
        movieDropdown = new JComboBox<>(movies);
        formPanel.add(movieDropdown, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Time:"), gbc);
        gbc.gridx = 1;
        JPanel timePanel = new JPanel(new GridLayout(2, 3, 5, 5));
        String[] times = {"10.00", "12.00", "14.10", "16.00", "18.30", "20.00"};
        timeButtons = new JButton[times.length];
        
        for (int i = 0; i < times.length; i++) {
            final JButton timeButton = new JButton(times[i]);
            timeButtons[i] = timeButton;
            timeButton.addActionListener(e -> handleTimeSelection(timeButton));
            timePanel.add(timeButton);
        }
        formPanel.add(timePanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(new JLabel("Type:"), gbc);
        gbc.gridx = 1;
        JPanel typePanel = new JPanel();
        vipButton = new JRadioButton("VIP", true);
        regularButton = new JRadioButton("Regular");
        ButtonGroup typeGroup = new ButtonGroup();
        typeGroup.add(vipButton);
        typeGroup.add(regularButton);
        typePanel.add(vipButton);
        typePanel.add(regularButton);
        formPanel.add(typePanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(new JLabel("Extras:"), gbc);
        gbc.gridx = 1;
        JPanel extraPanel = new JPanel();
        popcornCheck = new JCheckBox("Popcorn");
        drinkCheck = new JCheckBox("Drink");
        extraPanel.add(popcornCheck);
        extraPanel.add(drinkCheck);
        formPanel.add(extraPanel, gbc);

        JPanel seatPanel = new JPanel(new BorderLayout());
        seatPanel.setPreferredSize(new Dimension(800, 500));

        JLabel screenLabel = new JLabel("SCREEN", SwingConstants.CENTER);
        screenLabel.setFont(new Font("Arial", Font.BOLD, 14));
        screenLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        screenLabel.setOpaque(true);
        seatPanel.add(screenLabel, BorderLayout.NORTH);

        JPanel seatsGrid = new JPanel(new GridLayout(8, 14, 2, 2));

        Dimension seatButtonSize = new Dimension(35, 30);

        for (char row = 'H'; row >= 'A'; row--) {
            for (int col = 1; col <= 14; col++) {
                final JButton seatButton = new JButton(row + String.valueOf(col));
                seatButton.setPreferredSize(seatButtonSize);
                seatButton.setFont(new Font("Arial", Font.PLAIN, 10));
                seatButton.addActionListener(e -> handleSeatSelection(seatButton));
                seatsGrid.add(seatButton);
            }
        }
        seatPanel.add(seatsGrid, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton bookButton = new JButton("BOOK");
        bookButton.addActionListener(e -> processBooking());
        buttonPanel.add(bookButton);
        seatPanel.add(buttonPanel, BorderLayout.SOUTH);

        JPanel contentPanel = new JPanel(new BorderLayout());

        JLabel contentTitle = new JLabel("Movie Ticket Booking", SwingConstants.CENTER);
        contentTitle.setFont(new Font("Arial", Font.BOLD, 18));
        contentTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(formPanel, BorderLayout.NORTH);

        contentPanel.add(contentTitle, BorderLayout.NORTH);
        contentPanel.add(leftPanel, BorderLayout.WEST);
        contentPanel.add(seatPanel, BorderLayout.CENTER);

        frame.add(headerPanel, BorderLayout.NORTH);
        frame.add(contentPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }
    
    // Handle time selection - only one time can be selected
    private void handleTimeSelection(JButton timeButton) {
        // If the clicked button is already selected, do nothing
        if (timeButton == selectedTimeButton) {
            return;
        }
        
        // Reset the previously selected button, if any
        if (selectedTimeButton != null) {
            selectedTimeButton.setBackground(UIManager.getColor("Button.background"));
        }
        
        // Set the clicked button as selected
        selectedTimeButton = timeButton;
        timeButton.setBackground(Color.GREEN);
    }
    
    // Handle seat selection - multiple seats allowed
    private void handleSeatSelection(JButton seatButton) {
        if (selectedSeats.contains(seatButton)) {
            // Unselect the seat
            selectedSeats.remove(seatButton);
            seatButton.setBackground(UIManager.getColor("Button.background"));
        } else {
            // Select the seat
            selectedSeats.add(seatButton);
            seatButton.setBackground(Color.GREEN);
        }
    }
    
    // Process the booking with validation
    private void processBooking() {
        // Validate all fields are filled
        if (nameField.getText().trim().isEmpty()) {
            showError("Please input all fields.");
            return;
        }
        
        // Validate phone number
        String phone = phoneField.getText().trim();
        if (phone.isEmpty()) {
            showError("Please input all fields.");
            return;
        }
        
        if (!isValidPhoneNumber(phone)) {
            showError("Invalid phone number! Please enter numbers only (7-15 digits).");
            return;
        }
        
        // Validate time selection
        if (selectedTimeButton == null) {
            showError("Please select a time.");
            return;
        }
        
        // Validate seat selection
        if (selectedSeats.isEmpty()) {
            showError("Please select a seat.");
            return;
        }
        
        // Calculate total price
        int totalPrice = calculateTotalPrice();
        
        // Prepare seat list
        StringBuilder seatList = new StringBuilder();
        for (JButton seat : selectedSeats) {
            if (seatList.length() > 0) {
                seatList.append(" ");
            }
            seatList.append(seat.getText());
        }
        
        // Prepare extras text
        String extras = "";
        if (popcornCheck.isSelected() && drinkCheck.isSelected()) {
            extras = "Popcorn Drink";
        } else if (popcornCheck.isSelected()) {
            extras = "Popcorn";
        } else if (drinkCheck.isSelected()) {
            extras = "Drink";
        } else {
            extras = "-";
        }
        
        // Show order summary
        JDialog dialog = new JDialog();
        dialog.setTitle("Order placed successfully!");
        dialog.setSize(350, 250);
        dialog.setLocationRelativeTo(null);
        dialog.setModal(true);
        dialog.setLayout(new BorderLayout());
        
        JPanel summaryPanel = new JPanel(new GridLayout(8, 1, 5, 5));
        summaryPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        summaryPanel.add(new JLabel("Name: " + nameField.getText()));
        summaryPanel.add(new JLabel("HP: " + phone));
        summaryPanel.add(new JLabel("Movie: " + movieDropdown.getSelectedItem()));
        summaryPanel.add(new JLabel("Time: " + selectedTimeButton.getText()));
        summaryPanel.add(new JLabel("Type: " + (vipButton.isSelected() ? "VIP" : "Regular")));
        summaryPanel.add(new JLabel("Extras: " + extras));
        summaryPanel.add(new JLabel("Seats: " + seatList.toString()));
        summaryPanel.add(new JLabel("Total Price: " + currencyFormat.format(totalPrice)));
        
        JPanel buttonPane = new JPanel();
        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> dialog.dispose());
        buttonPane.add(okButton);
        
        dialog.add(summaryPanel, BorderLayout.CENTER);
        dialog.add(buttonPane, BorderLayout.SOUTH);
        
        dialog.setVisible(true);
    }
    
    // Validate phone number - must be numbers only, 7-15 digits
    private boolean isValidPhoneNumber(String phone) {
        return phone.matches("\\d{7,15}");
    }
    
    // Calculate total price based on selections
    private int calculateTotalPrice() {
        int total = 0;
        
        // Add ticket price
        total += vipButton.isSelected() ? PRICE_VIP : PRICE_REGULAR;
        
        // Multiply by number of seats
        total *= selectedSeats.size();
        
        // Add extras
        if (popcornCheck.isSelected()) {
            total += PRICE_POPCORN;
        }
        
        if (drinkCheck.isSelected()) {
            total += PRICE_DRINK;
        }
        
        return total;
    }
    
    // Show error dialog
    private void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Input Error", JOptionPane.ERROR_MESSAGE);
    }
}