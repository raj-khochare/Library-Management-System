//
//import java.awt.Color;
//import java.awt.Container;
//import java.awt.Font;
//import java.awt.event.ActionListener;
//import java.sql.Connection;
//import javax.swing.BorderFactory;
//import javax.swing.JButton;
//import java.sql.DriverManager;
//import javax.swing.JDialog;
//import javax.swing.JLabel;
//import javax.swing.JPasswordField;
//import javax.swing.JTextField;
//import javax.swing.border.Border;
//
//public class studentDashboard extends JDialog implements ActionListener {
//
//    private Connection connection;
//
//    // Swing components
//    private JTextField usernameField;
//    private JPasswordField passwordField;
//    private JButton loginButton;
//    private JButton clearButton;
//    private JButton exitButton;
//
//    public studentDashboard(Connection connection) {
//        this.connection = connection;
//
//        // Set up the dialog
//        setTitle("Student Dashboard");
//        setSize(600, 400);
//        setLayout(null);
//        setResizable(false);
//        setLocationRelativeTo(null);
//        setModal(true); // Make the dialog modal
//
//        // Initialize components
//        initializeComponents();
//
//        // Add components to the dialog
//        addComponents();
//
//        // Set visible
//        setVisible(true);
//    }
//
//    private void initializeComponents() {
//        usernameField = new JTextField();
//        passwordField = new JPasswordField();
//
//        loginButton = createButton("Login");
//        clearButton = createButton("Clear");
//        exitButton = createButton("Exit");
//
//        loginButton.addActionListener(this);
//        clearButton.addActionListener(this);
//        exitButton.addActionListener(this);
//    }
//
//    private JButton createButton(String text) {
//        JButton button = new JButton(text);
//        button.setFont(new Font("Verdana", Font.BOLD, 16));
//
//        Color blue = new Color(138, 43, 226); // Button color
//        button.setForeground(Color.WHITE);
//        button.setBackground(blue);
//
//        Border border = BorderFactory.createLineBorder(Color.DARK_GRAY, 2);
//        button.setBorder(border); // Set border
//
//        return button;
//    }
//
//    private void addComponents() {
//        Container con = getContentPane();
//
//        JLabel titleLabel = new JLabel("User Login");
//        titleLabel.setFont(new Font("Verdana", Font.BOLD, 24));
//        titleLabel.setBounds(200, 10, 200, 30);
//
//        JLabel usernameLabel = new JLabel("Username:");
//        usernameLabel.setBounds(30, 70, 100, 30);
//
//        usernameField.setBounds(150, 70, 200, 30);
//
//        JLabel passwordLabel = new JLabel("Password:");
//        passwordLabel.setBounds(30, 120, 100, 30);
//
//        passwordField.setBounds(150, 120, 200, 30);
//
//        loginButton.setBounds(50, 180, 120, 40);
//        clearButton.setBounds(180, 180, 120, 40);
//        exitButton.setBounds(310, 180, 120, 40);
//
//        con.add(titleLabel);
//        con.add(usernameLabel);
//        con.add(usernameField);
//        con.add(passwordLabel);
//        con.add(passwordField);
//
//        con.add(loginButton);
//        con.add(clearButton);
//        con.add(exitButton);
//
//        Color lightBlue = new Color(173,216,230); // Background color
//        con.setBackground(lightBlue);
//        con.setLayout(null);
//        con.setVisible(true);
//    }
//}
