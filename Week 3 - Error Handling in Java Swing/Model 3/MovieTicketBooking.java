import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MovieTicketBooking extends JFrame {
    // Components
    private JTextField nameField, phoneField;
    private JComboBox<String> movieComboBox;
    private JToggleButton[] timeButtons;
    private JToggleButton vipButton, regularButton;
    private JCheckBox popcornCheckBox, drinkCheckBox;
    private JButton bookButton;
    private JPanel seatPanel;
    private JToggleButton[][] seatButtons;
    
    // Data
    private List<String> selectedSeats;
    private double totalPrice;
    private final String[] MOVIES = {"Parasite", "Avengers", "Joker", "Interstellar"};
    private final String[] TIMES = {"10.00", "12.00", "14.10", "16.00", "18.30", "20.00"};
    private final String[][] SEAT_ROWS = {
        {"A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "A10", "A11", "A12", "A13", "A14"},
        {"B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8", "B9", "B10", "B11", "B12", "B13", "B14"},
        {"C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "C10", "C11", "C12", "C13", "C14"},
        {"D1", "D2", "D3", "D4", "D5", "D6", "D7", "D8", "D9", "D10", "D11", "D12", "D13", "D14"},
        {"E1", "E2", "E3", "E4", "E5", "E6", "E7", "E8", "E9", "E10", "E11", "E12", "E13", "E14"},
        {"F1", "F2", "F3", "F4", "F5", "F6", "F7", "F8", "F9", "F10", "F11", "F12", "F13", "F14"},
        {"G1", "G2", "G3", "G4", "G5", "G6", "G7", "G8", "G9", "G10", "G11", "G12", "G13", "G14"}
    };
    
    // Prices
    private final double VIP_PRICE = 100000.00;
    private final double REGULAR_PRICE = 50000.00;
    private final double POPCORN_PRICE = 50000.00;
    private final double DRINK_PRICE = 30000.00;
    
    public MovieTicketBooking() {
        // Initialize frame
        setTitle("Movie Ticket Booking");
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        // Initialize components
        initializeComponents();
        
        // Set up the layout
        setupLayout();
        
        // Add action listeners
        addActionListeners();
        
        // Initialize data
        selectedSeats = new ArrayList<>();
    }
    
    private void initializeComponents() {
        // Input fields
        nameField = new JTextField(20);
        phoneField = new JTextField(20);
        
        // Movie selection
        movieComboBox = new JComboBox<>(MOVIES);
        
        // Time selection
        timeButtons = new JToggleButton[TIMES.length];
        for (int i = 0; i < TIMES.length; i++) {
            timeButtons[i] = new JToggleButton(TIMES[i]);
        }
        
        // Type selection
        vipButton = new JToggleButton("VIP");
        regularButton = new JToggleButton("Regular");
        
        // Extra selection
        popcornCheckBox = new JCheckBox("Popcorn");
        drinkCheckBox = new JCheckBox("Drink");
        
        // Seat panel
        seatPanel = new JPanel(new GridLayout(SEAT_ROWS.length, SEAT_ROWS[0].length, 5, 5));
        seatButtons = new JToggleButton[SEAT_ROWS.length][SEAT_ROWS[0].length];
        
        for (int i = 0; i < SEAT_ROWS.length; i++) {
            for (int j = 0; j < SEAT_ROWS[i].length; j++) {
                seatButtons[i][j] = new JToggleButton(SEAT_ROWS[i][j]);
                seatButtons[i][j].setPreferredSize(new Dimension(40, 30));
                seatPanel.add(seatButtons[i][j]);
            }
        }
        
        // Book button
        bookButton = new JButton("BOOK");
    }
    
    private void setupLayout() {
        // Main panel with padding
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Header panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("ISB Cinema");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        
        JPanel addressPanel = new JPanel(new GridLayout(2, 1));
        addressPanel.add(new JLabel("Jl. Teknologi No. 123, Surabaya, Jawa Timur, Indonesia"));
        addressPanel.add(new JLabel("+62 812-3456-7890 (Customer Service)"));
        
        headerPanel.add(titleLabel, BorderLayout.WEST);
        headerPanel.add(addressPanel, BorderLayout.CENTER);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
        // Form panel
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        
        // Name
        formPanel.add(new JLabel("Name"));
        formPanel.add(nameField);
        
        // Phone
        formPanel.add(new JLabel("Phone"));
        formPanel.add(phoneField);
        
        // Movie
        formPanel.add(new JLabel("Movie"));
        formPanel.add(movieComboBox);
        
        // Time
        formPanel.add(new JLabel("Time"));
        JPanel timePanel = new JPanel(new GridLayout(2, 3, 5, 5));
        for (JToggleButton timeButton : timeButtons) {
            timePanel.add(timeButton);
        }
        formPanel.add(timePanel);
        
        // Type
        formPanel.add(new JLabel("Type"));
        JPanel typePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        typePanel.add(vipButton);
        typePanel.add(regularButton);
        formPanel.add(typePanel);
        
        // Extras
        JPanel extrasLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        extrasLabelPanel.add(new JLabel("Extras"));
        formPanel.add(extrasLabelPanel);
        
        JPanel extrasPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        extrasPanel.add(popcornCheckBox);
        extrasPanel.add(drinkCheckBox);
        formPanel.add(extrasPanel);
        
        // Seat section label
        JLabel screenLabel = new JLabel("Screen", JLabel.CENTER);
        screenLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        screenLabel.setPreferredSize(new Dimension(600, 30));
        
        // Combine all panels
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(headerPanel, BorderLayout.NORTH);
        topPanel.add(formPanel, BorderLayout.CENTER);
        
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.add(screenLabel, BorderLayout.NORTH);
        centerPanel.add(seatPanel, BorderLayout.CENTER);
        
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(bookButton);
        
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    private void addActionListeners() {
        // Time button exclusivity
        for (JToggleButton timeButton : timeButtons) {
            timeButton.addActionListener(e -> {
                for (JToggleButton btn : timeButtons) {
                    if (btn != e.getSource()) {
                        btn.setSelected(false);
                    }
                }
                if (timeButton.isSelected()) {
                    timeButton.setBackground(Color.GREEN);
                } else {
                    timeButton.setBackground(null);
                }
            });
        }
        
        // Type button exclusivity
        ActionListener typeListener = e -> {
            if (e.getSource() == vipButton && vipButton.isSelected()) {
                regularButton.setSelected(false);
            } else if (e.getSource() == regularButton && regularButton.isSelected()) {
                vipButton.setSelected(false);
            }
        };
        
        vipButton.addActionListener(typeListener);
        regularButton.addActionListener(typeListener);
        
        // Seat selection
        for (int i = 0; i < SEAT_ROWS.length; i++) {
            for (int j = 0; j < SEAT_ROWS[i].length; j++) {
                final int row = i;
                final int col = j;
                seatButtons[i][j].addActionListener(e -> {
                    JToggleButton seatButton = seatButtons[row][col];
                    if (seatButton.isSelected()) {
                        seatButton.setBackground(Color.GREEN);
                        selectedSeats.add(seatButton.getText());
                    } else {
                        seatButton.setBackground(null);
                        selectedSeats.remove(seatButton.getText());
                    }
                });
            }
        }
        
        // Book button
        bookButton.addActionListener(e -> validateAndBook());
    }
    
  
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MovieTicketBooking app = new MovieTicketBooking();
            app.setVisible(true);
        });
    }
}