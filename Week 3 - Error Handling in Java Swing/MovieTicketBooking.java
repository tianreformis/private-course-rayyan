import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;

public class MovieTicketBooking extends JFrame {

    private JPanel contentPane;
    private JTextField nameField;
    private JTextField phoneField;
    private JComboBox<String> movieDropdown;
    private JPanel timeSlotsPanel;
    private JRadioButton vipRadio;
    private JRadioButton regularRadio;
    private JCheckBox popcornCheck;
    private JCheckBox drinksCheck;
    private JPanel seatsPanel;
    private JLabel screenLabel;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MovieTicketBooking frame = new MovieTicketBooking();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public MovieTicketBooking() {
        setTitle("ISB Cinema - Movie Ticket Booking");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        // North Panel for Logo and Cinema Details
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        contentPane.add(northPanel, BorderLayout.NORTH);

        ImageIcon logoIcon = new ImageIcon(new ImageIcon("logo.png").getImage().getScaledInstance(100, 50, Image.SCALE_SMOOTH));
        JLabel logoLabel = new JLabel(logoIcon);
        northPanel.add(logoLabel);

        JPanel cinemaDetailsPanel = new JPanel();
        cinemaDetailsPanel.setLayout(new BoxLayout(cinemaDetailsPanel, BoxLayout.Y_AXIS));
        JLabel nameLabel = new JLabel("ISB Cinema");
        JLabel addressLabel = new JLabel("123 Main Street, City");
        JLabel contactLabel = new JLabel("Contact: (123) 456-7890");
        cinemaDetailsPanel.add(nameLabel);
        cinemaDetailsPanel.add(addressLabel);
        cinemaDetailsPanel.add(contactLabel);
        northPanel.add(cinemaDetailsPanel);

        // West Panel for Form Inputs
        JPanel westPanel = new JPanel();
        westPanel.setPreferredSize(new Dimension(250, 0));
        westPanel.setLayout(new GridLayout(0, 1, 5, 5));
        contentPane.add(westPanel, BorderLayout.WEST);

        JLabel nameLabelForm = new JLabel("Name:");
        nameField = new JTextField();
        westPanel.add(nameLabelForm);
        westPanel.add(nameField);

        JLabel phoneLabelForm = new JLabel("Phone Number:");
        phoneField = new JTextField();
        westPanel.add(phoneLabelForm);
        westPanel.add(phoneField);

        JLabel movieLabelForm = new JLabel("Select Movie:");
        movieDropdown = new JComboBox<>(new String[]{"Movie 1", "Movie 2", "Movie 3"});
        westPanel.add(movieLabelForm);
        westPanel.add(movieDropdown);

        JLabel timeSlotLabelForm = new JLabel("Select Time Slot:");
        westPanel.add(timeSlotLabelForm);
        timeSlotsPanel = new JPanel();
        timeSlotsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton time1Button = new JButton("10:00");
        JButton time2Button = new JButton("13:00");
        JButton time3Button = new JButton("16:00");
        timeSlotsPanel.add(time1Button);
        timeSlotsPanel.add(time2Button);
        timeSlotsPanel.add(time3Button);
        westPanel.add(timeSlotsPanel);

        JLabel seatTypeLabelForm = new JLabel("Select Seat Type:");
        westPanel.add(seatTypeLabelForm);
        vipRadio = new JRadioButton("VIP");
        regularRadio = new JRadioButton("Regular");
        ButtonGroup seatTypeGroup = new ButtonGroup();
        seatTypeGroup.add(vipRadio);
        seatTypeGroup.add(regularRadio);
        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        radioPanel.add(vipRadio);
        radioPanel.add(regularRadio);
        westPanel.add(radioPanel);

        JLabel extrasLabelForm = new JLabel("Extras:");
        westPanel.add(extrasLabelForm);
        popcornCheck = new JCheckBox("Popcorn");
        drinksCheck = new JCheckBox("Drinks");
        JPanel checkPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        checkPanel.add(popcornCheck);
        checkPanel.add(drinksCheck);
        westPanel.add(checkPanel);

        JButton bookButton = new JButton("Book Now");
        westPanel.add(bookButton);

        // Center Panel for Seat Selection
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        contentPane.add(centerPanel, BorderLayout.CENTER);

        screenLabel = new JLabel("Screen", SwingConstants.CENTER);
        centerPanel.add(screenLabel, BorderLayout.NORTH);

        seatsPanel = new JPanel();
        seatsPanel.setLayout(new GridLayout(8, 10, 5, 5)); // 8 rows, 10 seats per row
        centerPanel.add(seatsPanel, BorderLayout.CENTER);

        // Initialize Seats
        for (int i = 1; i <= 80; i++) {
            JButton seatButton = new JButton(String.valueOf(i));
            seatsPanel.add(seatButton);
        }

        // Event Listeners (Functionality to be implemented)
        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement booking logic here
                JOptionPane.showMessageDialog(MovieTicketBooking.this, "Booking confirmed!");
            }
        });
    }
}
