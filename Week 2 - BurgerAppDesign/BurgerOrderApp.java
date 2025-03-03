import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BurgerOrderApp extends JFrame implements ActionListener, KeyListener {

    // UI Components
    private JLabel titleLabel, descriptionLabel, imageLabel, quantityLabel, priceLabel;
    private JButton smallButton, mediumButton, largeButton;
    private JTextField quantityField;
    private JButton addButton, subtractButton, addToCartButton;
    private ButtonGroup sizeGroup;
    private JPanel mainPanel, orderPanel, selectionPanel, quantityPanel;

    // Data
    private int quantity = 0;
    private int burgerSize = 0; // 0: Small, 1: Medium, 2: Large
    private int price = 0;
    private final int SMALL_PRICE = 20000;
    private final int MEDIUM_PRICE = 30000;
    private final int LARGE_PRICE = 40000;

    // Image Icons (assuming you have these in the same directory)
    private ImageIcon smallImage = new ImageIcon("small_burger.png");
    private ImageIcon mediumImage = new ImageIcon("medium_burger.png");
    private ImageIcon largeImage = new ImageIcon("large_burger.png");

    public BurgerOrderApp() {
        setTitle("Burger House Ordering App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 600);
        setLocationRelativeTo(null); // Center the window

        // Main Panel
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Order Panel (Center)
        orderPanel = new JPanel();
        orderPanel.setLayout(new GridBagLayout()); // Using GridBagLayout for better control
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Add some padding

        // Initialize UI components
        titleLabel = new JLabel("Cheese Burger");
        titleLabel.setFont(new Font("Poppins", Font.BOLD, 20)); // Make title bigger
           descriptionLabel = new JLabel("<html><body style='width: 250px; text-align: center; '>Super tasty cheese burger with thick beef patty, fresh veggies served between slices of bread!</body></html>");
        descriptionLabel.setFont(new Font("Poppins", Font.PLAIN, 14));
        

        // Scale Images
        smallImage = scaleImage(smallImage, 200, 150); //scale to a width of 200 and height of 150
        mediumImage = scaleImage(mediumImage, 200, 150);
        largeImage = scaleImage(largeImage, 200, 150);

        imageLabel = new JLabel(smallImage); // Initial image

        smallButton = new JButton("S");
        smallButton.setFont(new Font("Poppins", Font.BOLD, 14));
        mediumButton = new JButton("M");
        mediumButton.setFont(new Font("Poppins", Font.BOLD, 14));
        largeButton = new JButton("L");
        largeButton.setFont(new Font("Poppins", Font.BOLD, 14));

        quantityField = new JTextField("0", 5);

        addButton = new JButton("+");
        subtractButton = new JButton("-");
        addToCartButton = new JButton("Add to Cart");
        addToCartButton.setForeground(Color.WHITE);
        addToCartButton.setBackground(Color.decode("#ffA500"));

        priceLabel = new JLabel("Rp. 0");
        priceLabel.setFont(new Font("Arial", Font.BOLD, 16));

        // Group the radio buttons
        sizeGroup = new ButtonGroup();
        sizeGroup.add(smallButton);
        sizeGroup.add(mediumButton);
        sizeGroup.add(largeButton);
        smallButton.setSelected(true); // Select Small by default

        // Selection Panel  (buttons)
        selectionPanel = new JPanel(new FlowLayout());
        selectionPanel.add(smallButton);
        selectionPanel.add(mediumButton);
        selectionPanel.add(largeButton);

        // Quantity Panel
        quantityPanel = new JPanel(new FlowLayout());
        quantityPanel.add(addButton);
        quantityPanel.add(quantityField);        
        quantityPanel.add(subtractButton);

        // Add Action Listeners
        smallButton.addActionListener(this);
        mediumButton.addActionListener(this);
        largeButton.addActionListener(this);
        addButton.addActionListener(this);
        subtractButton.addActionListener(this);
        addToCartButton.addActionListener(this);

        //Add Key Listener
        quantityField.addKeyListener(this);

        // Add components to the order panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span two columns
        orderPanel.add(titleLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        orderPanel.add(descriptionLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        orderPanel.add(imageLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        orderPanel.add(selectionPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        orderPanel.add(quantityPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        orderPanel.add(priceLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        orderPanel.add(addToCartButton, gbc);

        // Add components to the frame
        mainPanel.add(orderPanel, BorderLayout.CENTER);

        add(mainPanel);

        setVisible(true);
    }

    // Method to scale the image while maintaining aspect ratio
    private ImageIcon scaleImage(ImageIcon icon, int w, int h) {
        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    // Method to update the price label
    private void updatePrice() {
        price = quantity * getBurgerPrice();
        priceLabel.setText("Rp. " + price);
    }

    private int getBurgerPrice() {
        switch (burgerSize) {
            case 0:
                return SMALL_PRICE;
            case 1:
                return MEDIUM_PRICE;
            case 2:
                return LARGE_PRICE;
            default:
                return SMALL_PRICE; // Default to small
        }
    }

    // Method to update the image
    private void updateImage() {
        switch (burgerSize) {
            case 0:
                imageLabel.setIcon(smallImage);
                break;
            case 1:
                imageLabel.setIcon(mediumImage);
                break;
            case 2:
                imageLabel.setIcon(largeImage);
                break;
        }
        imageLabel.revalidate();
        imageLabel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == smallButton) {
            burgerSize = 0;
            updateImage();
            updatePrice();
        } else if (e.getSource() == mediumButton) {
            burgerSize = 1;
            updateImage();
            updatePrice();
        } else if (e.getSource() == largeButton) {
            burgerSize = 2;
            updateImage();
            updatePrice();
        } else if (e.getSource() == addButton) {
            quantity++;
            quantityField.setText(String.valueOf(quantity));
            updatePrice();
        } else if (e.getSource() == subtractButton) {
            if (quantity > 0) {
                quantity--;
                quantityField.setText(String.valueOf(quantity));
                updatePrice();
            }
        } else if (e.getSource() == addToCartButton) {
            if (quantity == 0) {
                JOptionPane.showMessageDialog(this, "Please add at least one burger to cart before ordering.");
            } else {
                JOptionPane.showMessageDialog(this, "Order Placed Successfully!");
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used, but required by KeyListener
    }

   @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                int newQuantity = Integer.parseInt(quantityField.getText());
                if (newQuantity < 0) {
                    JOptionPane.showMessageDialog(this, "Invalid value. Please enter a non-negative number.");
                    quantity = 0;
                    quantityField.setText("0");
                } else {
                    quantity = newQuantity;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter a number.");
                quantity = 0;
                quantityField.setText("0");
            } finally {
                updatePrice();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Not used, but required by KeyListener
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BurgerOrderApp());
    }
}
