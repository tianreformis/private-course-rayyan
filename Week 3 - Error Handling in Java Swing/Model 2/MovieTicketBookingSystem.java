import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MovieTicketBookingSystem extends JFrame {
    // Form components
    private JTextField nameField, phoneField;
    private JComboBox<String> movieDropdown;
    private JRadioButton vipButton, regularButton;
    private JCheckBox popcornCheckbox, drinksCheckbox;
    private JButton bookButton;
    private JPanel seatPanel;
    private ArrayList<JToggleButton> seatButtons = new ArrayList<>();
    private JButton[] timeSlotButtons;
    
    // Constants
    private static final int ROWS = 5;
    private static final int COLS = 8;
    private static final Color SELECTED_COLOR = new Color(152, 251, 152); // Light green
    private static final Color BACKGROUND_COLOR = Color.WHITE;
    private static final Color TEXT_COLOR = Color.WHITE;
    private static final String[] MOVIES = {"Avengers: Endgame", "The Batman", "Dune", "Spider-Man: No Way Home", "Top Gun: Maverick"};
    private static final String[] TIME_SLOTS = {"10:00 AM", "1:00 PM", "4:00 PM", "7:00 PM", "10:00 PM"};
    
    public MovieTicketBookingSystem() {
        // Set up the JFrame
        setTitle("ISB Cinema - Movie Ticket Booking System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        
        // Set background color for the frame
        getContentPane().setBackground(BACKGROUND_COLOR);
        
        // Create the main panel with a border layout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(BACKGROUND_COLOR);
        
        // Add header panel
        mainPanel.add(createHeaderPanel(), BorderLayout.NORTH);
        
        // Create the content panel with two sections
        JPanel contentPanel = new JPanel(new GridLayout(1, 2, 15, 0));
        contentPanel.setBackground(BACKGROUND_COLOR);
        
        // Add form panel to the left side of content
        contentPanel.add(createFormPanel());
        
        // Add seat selection panel to the right side of content
        contentPanel.add(createSeatSelectionPanel());
        
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        
        // Add the main panel to the frame
        add(mainPanel);
        
        // Apply white text to all components
        applyWhiteTextToAllComponents(mainPanel);
    }
    
    // Apply white text recursively to all components
    private void applyWhiteTextToAllComponents(Container container) {
        for (Component comp : container.getComponents()) {
            if (comp instanceof JLabel) {
                ((JLabel) comp).setForeground(TEXT_COLOR);
            } else if (comp instanceof JButton) {
                ((JButton) comp).setForeground(TEXT_COLOR);
            } else if (comp instanceof JToggleButton) {
                ((JToggleButton) comp).setForeground(TEXT_COLOR);
            } else if (comp instanceof JCheckBox) {
                ((JCheckBox) comp).setForeground(TEXT_COLOR);
            } else if (comp instanceof JRadioButton) {
                ((JRadioButton) comp).setForeground(TEXT_COLOR);
            } else if (comp instanceof JTextField) {
                ((JTextField) comp).setForeground(TEXT_COLOR);
                ((JTextField) comp).setCaretColor(Color.BLACK); // Keep caret visible
            } else if (comp instanceof JComboBox) {
                ((JComboBox<?>) comp).setForeground(TEXT_COLOR);
            }
            
            if (comp instanceof Container) {
                comp.setBackground(BACKGROUND_COLOR);
                applyWhiteTextToAllComponents((Container) comp);
            }
        }
    }
    
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(BACKGROUND_COLOR);
        
        // Logo and cinema details
        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        logoPanel.setBackground(BACKGROUND_COLOR);
        
        // Use ImageIcon for the logo
        ImageIcon logoIcon = new ImageIcon("cinema_logo.png");
        Image scaledImage = logoIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(scaledImage));
        logoPanel.add(logoLabel);
        
        JPanel cinemaDetailsPanel = new JPanel(new GridLayout(3, 1));
        cinemaDetailsPanel.setBackground(BACKGROUND_COLOR);
        
        JLabel cinemaNameLabel = new JLabel("ISB Cinema");
        cinemaNameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        cinemaNameLabel.setForeground(TEXT_COLOR);
        
        JLabel addressLabel = new JLabel("123 Movie Street, Entertainment District");
        addressLabel.setForeground(TEXT_COLOR);
        
        JLabel contactLabel = new JLabel("Phone: +1-234-567-8910 | Email: info@isbcinema.com");
        contactLabel.setForeground(TEXT_COLOR);
        
        cinemaDetailsPanel.add(cinemaNameLabel);
        cinemaDetailsPanel.add(addressLabel);
        cinemaDetailsPanel.add(contactLabel);
        
        logoPanel.add(cinemaDetailsPanel);
        headerPanel.add(logoPanel, BorderLayout.WEST);
        
        headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        
        return headerPanel;
    }
    
    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Booking Information"));
        formPanel.setBackground(BACKGROUND_COLOR);
        
        // Name and Phone fields
        JPanel personalInfoPanel = new JPanel(new GridLayout(2, 2, 5, 10));
        personalInfoPanel.setBackground(BACKGROUND_COLOR);
        
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setForeground(TEXT_COLOR);
        personalInfoPanel.add(nameLabel);
        
        nameField = new JTextField(20);
        nameField.setBackground(BACKGROUND_COLOR);
        nameField.setForeground(TEXT_COLOR);
        nameField.setCaretColor(Color.BLACK); // Keep caret visible
        personalInfoPanel.add(nameField);
        
        JLabel phoneLabel = new JLabel("Phone Number:");
        phoneLabel.setForeground(TEXT_COLOR);
        personalInfoPanel.add(phoneLabel);
        
        phoneField = new JTextField(20);
        phoneField.setBackground(BACKGROUND_COLOR);
        phoneField.setForeground(TEXT_COLOR);
        phoneField.setCaretColor(Color.BLACK); // Keep caret visible
        personalInfoPanel.add(phoneField);
        
        // Movie selection
        JPanel moviePanel = new JPanel(new GridLayout(1, 2, 5, 0));
        moviePanel.setBackground(BACKGROUND_COLOR);
        
        JLabel movieLabel = new JLabel("Select Movie:");
        movieLabel.setForeground(TEXT_COLOR);
        moviePanel.add(movieLabel);
        
        movieDropdown = new JComboBox<>(MOVIES);
        movieDropdown.setBackground(BACKGROUND_COLOR);
        movieDropdown.setForeground(TEXT_COLOR);
        moviePanel.add(movieDropdown);
        
        // Time slot selection
        JPanel timeSlotPanel = new JPanel(new GridLayout(2, 1));
        timeSlotPanel.setBackground(BACKGROUND_COLOR);
        
        JLabel timeSlotLabel = new JLabel("Select Time Slot:");
        timeSlotLabel.setForeground(TEXT_COLOR);
        timeSlotPanel.add(timeSlotLabel);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBackground(BACKGROUND_COLOR);
        
        timeSlotButtons = new JButton[TIME_SLOTS.length];
        ButtonGroup timeSlotGroup = new ButtonGroup();
        
        for (int i = 0; i < TIME_SLOTS.length; i++) {
            final int index = i;
            timeSlotButtons[i] = new JButton(TIME_SLOTS[i]);
            timeSlotButtons[i].setPreferredSize(new Dimension(100, 30));
            timeSlotButtons[i].setBackground(BACKGROUND_COLOR);
            timeSlotButtons[i].setForeground(TEXT_COLOR);
            buttonPanel.add(timeSlotButtons[i]);
            
            // Add action listener to handle selection
            timeSlotButtons[i].addActionListener(e -> {
                // Deselect all buttons
                for (JButton button : timeSlotButtons) {
                    button.setBackground(BACKGROUND_COLOR);
                }
                // Select the clicked button
                timeSlotButtons[index].setBackground(SELECTED_COLOR);
                timeSlotButtons[index].setForeground(Color.BLACK); // Make text visible on selected background
            });
        }
        timeSlotPanel.add(buttonPanel);
        
        // VIP or Regular selection
        JPanel seatTypePanel = new JPanel(new GridLayout(1, 3));
        seatTypePanel.setBackground(BACKGROUND_COLOR);
        
        JLabel seatTypeLabel = new JLabel("Seat Type:");
        seatTypeLabel.setForeground(TEXT_COLOR);
        seatTypePanel.add(seatTypeLabel);
        
        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        radioPanel.setBackground(BACKGROUND_COLOR);
        
        vipButton = new JRadioButton("VIP");
        vipButton.setBackground(BACKGROUND_COLOR);
        vipButton.setForeground(TEXT_COLOR);
        
        regularButton = new JRadioButton("Regular");
        regularButton.setBackground(BACKGROUND_COLOR);
        regularButton.setForeground(TEXT_COLOR);
        regularButton.setSelected(true);
        
        ButtonGroup seatTypeGroup = new ButtonGroup();
        seatTypeGroup.add(vipButton);
        seatTypeGroup.add(regularButton);
        
        radioPanel.add(vipButton);
        radioPanel.add(regularButton);
        seatTypePanel.add(radioPanel);
        
        // Extras selection
        JPanel extrasPanel = new JPanel(new GridLayout(1, 3));
        extrasPanel.setBackground(BACKGROUND_COLOR);
        
        JLabel extrasLabel = new JLabel("Extras:");
        extrasLabel.setForeground(TEXT_COLOR);
        extrasPanel.add(extrasLabel);
        
        JPanel checkboxPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        checkboxPanel.setBackground(BACKGROUND_COLOR);
        
        popcornCheckbox = new JCheckBox("Popcorn");
        popcornCheckbox.setBackground(BACKGROUND_COLOR);
        popcornCheckbox.setForeground(TEXT_COLOR);
        
        drinksCheckbox = new JCheckBox("Drinks");
        drinksCheckbox.setBackground(BACKGROUND_COLOR);
        drinksCheckbox.setForeground(TEXT_COLOR);
        
        checkboxPanel.add(popcornCheckbox);
        checkboxPanel.add(drinksCheckbox);
        extrasPanel.add(checkboxPanel);
        
        // Book button
        JPanel bookButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bookButtonPanel.setBackground(BACKGROUND_COLOR);
        
        bookButton = new JButton("Book Tickets");
        bookButton.setPreferredSize(new Dimension(150, 40));
        bookButton.setBackground(new Color(65, 105, 225)); // Royal Blue
        bookButton.setForeground(Color.WHITE);
        bookButton.setFont(new Font("Arial", Font.BOLD, 14));
        
        bookButton.addActionListener(e -> {
            // Check if all required fields are filled
            if (nameField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your name.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (phoneField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your phone number.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Check if at least one seat is selected
            boolean seatSelected = false;
            for (JToggleButton seat : seatButtons) {
                if (seat.isSelected()) {
                    seatSelected = true;
                    break;
                }
            }
            
            if (!seatSelected) {
                JOptionPane.showMessageDialog(this, "Please select at least one seat.", "Selection Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Get selected time slot
            String selectedTimeSlot = null;
            for (int i = 0; i < timeSlotButtons.length; i++) {
                if (timeSlotButtons[i].getBackground() == SELECTED_COLOR) {
                    selectedTimeSlot = TIME_SLOTS[i];
                    break;
                }
            }
            
            if (selectedTimeSlot == null) {
                JOptionPane.showMessageDialog(this, "Please select a time slot.", "Selection Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Show booking confirmation
            StringBuilder message = new StringBuilder();
            message.append("Booking Confirmed!\n\n");
            message.append("Name: ").append(nameField.getText().trim()).append("\n");
            message.append("Phone: ").append(phoneField.getText().trim()).append("\n");
            message.append("Movie: ").append(movieDropdown.getSelectedItem()).append("\n");
            message.append("Time: ").append(selectedTimeSlot).append("\n");
            message.append("Seat Type: ").append(vipButton.isSelected() ? "VIP" : "Regular").append("\n");
            
            message.append("Selected Seats: ");
            for (int i = 0; i < seatButtons.size(); i++) {
                if (seatButtons.get(i).isSelected()) {
                    String seatLabel = seatButtons.get(i).getText();
                    message.append(seatLabel).append(" ");
                }
            }
            message.append("\n");
            
            if (popcornCheckbox.isSelected() || drinksCheckbox.isSelected()) {
                message.append("Extras: ");
                if (popcornCheckbox.isSelected()) message.append("Popcorn ");
                if (drinksCheckbox.isSelected()) message.append("Drinks");
            }
            
            JOptionPane.showMessageDialog(this, message.toString(), "Booking Confirmation", JOptionPane.INFORMATION_MESSAGE);
        });
        
        bookButtonPanel.add(bookButton);
        
        // Add components to the form panel with some spacing
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(personalInfoPanel);
        formPanel.add(Box.createVerticalStrut(15));
        formPanel.add(moviePanel);
        formPanel.add(Box.createVerticalStrut(15));
        formPanel.add(timeSlotPanel);
        formPanel.add(Box.createVerticalStrut(15));
        formPanel.add(seatTypePanel);
        formPanel.add(Box.createVerticalStrut(15));
        formPanel.add(extrasPanel);
        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(bookButtonPanel);
        
        return formPanel;
    }
    
    private JPanel createSeatSelectionPanel() {
        JPanel selectionPanel = new JPanel(new BorderLayout(0, 10));
        selectionPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Seat Selection"));
        selectionPanel.setBackground(BACKGROUND_COLOR);
        
        // Screen panel
        JPanel screenPanel = new JPanel(new BorderLayout());
        screenPanel.setBackground(BACKGROUND_COLOR);
        
        JLabel screenLabel = new JLabel("SCREEN", SwingConstants.CENTER);
        screenLabel.setOpaque(true);
        screenLabel.setBackground(Color.LIGHT_GRAY);
        screenLabel.setForeground(Color.BLACK); // Keep screen text visible
        screenLabel.setFont(new Font("Arial", Font.BOLD, 16));
        screenLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        screenLabel.setPreferredSize(new Dimension(0, 30));
        screenPanel.add(screenLabel, BorderLayout.NORTH);
        
        // Seat layout panel
        seatPanel = new JPanel(new GridLayout(ROWS, COLS, 10, 10));
        seatPanel.setBorder(BorderFactory.createEmptyBorder(30, 10, 10, 10));
        seatPanel.setBackground(BACKGROUND_COLOR);
        
        // Create seats
        char rowChar = 'A';
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                String seatLabel = "" + rowChar + (j + 1);
                JToggleButton seatButton = new JToggleButton(seatLabel);
                seatButton.setPreferredSize(new Dimension(50, 40));
                seatButton.setBackground(BACKGROUND_COLOR);
                seatButton.setForeground(TEXT_COLOR);
                
                // Set appearance for the seat buttons
                seatButton.addActionListener(e -> {
                    JToggleButton button = (JToggleButton) e.getSource();
                    if (button.isSelected()) {
                        button.setBackground(SELECTED_COLOR);
                        button.setForeground(Color.BLACK); // Make text visible on selected background
                    } else {
                        button.setBackground(BACKGROUND_COLOR);
                        button.setForeground(TEXT_COLOR);
                    }
                });
                
                seatPanel.add(seatButton);
                seatButtons.add(seatButton);
            }
            rowChar++;
        }
        
        // Legend panel
        JPanel legendPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        legendPanel.setBackground(BACKGROUND_COLOR);
        
        JPanel availablePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        availablePanel.setBackground(BACKGROUND_COLOR);
        
        JButton availableSample = new JButton();
        availableSample.setPreferredSize(new Dimension(20, 20));
        availableSample.setBackground(BACKGROUND_COLOR);
        availableSample.setEnabled(true);
        availablePanel.add(availableSample);
        
        JLabel availableLabel = new JLabel("Available");
        availableLabel.setForeground(TEXT_COLOR);
        availablePanel.add(availableLabel);
        
        JPanel selectedPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        selectedPanel.setBackground(BACKGROUND_COLOR);
        
        JButton selectedSample = new JButton();
        selectedSample.setPreferredSize(new Dimension(20, 20));
        selectedSample.setBackground(SELECTED_COLOR);
        selectedSample.setEnabled(true);
        selectedPanel.add(selectedSample);
        
        JLabel selectedLabel = new JLabel("Selected");
        selectedLabel.setForeground(TEXT_COLOR);
        selectedPanel.add(selectedLabel);
        
        legendPanel.add(availablePanel);
        legendPanel.add(Box.createHorizontalStrut(20));
        legendPanel.add(selectedPanel);
        
        // Add components to the selection panel
        selectionPanel.add(screenPanel, BorderLayout.NORTH);
        selectionPanel.add(seatPanel, BorderLayout.CENTER);
        selectionPanel.add(legendPanel, BorderLayout.SOUTH);
        
        return selectionPanel;
    }
    
    public static void main(String[] args) {
        // Set look and feel to system default
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Create and display the application
        SwingUtilities.invokeLater(() -> {
            MovieTicketBookingSystem app = new MovieTicketBookingSystem();
            app.setVisible(true);
        });
    }
}