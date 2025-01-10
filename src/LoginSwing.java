import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.border.Border;

public class LoginSwing extends JDialog implements ActionListener {
    private Connection connection;

    // Swing components
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton clearButton;
    private JButton exitButton;

    public LoginSwing(Connection connection) {
        this.connection = connection;

        // Set up the dialog
        setTitle("User Login");
        setSize(600, 400);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setModal(true); // Make the dialog modal

        // Initialize components
        initializeComponents();

        // Add components to the dialog
        addComponents();

        // Set visible
        setVisible(true);
    }

    private void initializeComponents() {
        usernameField = new JTextField();
        passwordField = new JPasswordField();

        loginButton = createButton("Login");
        clearButton = createButton("Clear");
        exitButton = createButton("Exit");

        loginButton.addActionListener(this);
        clearButton.addActionListener(this);
        exitButton.addActionListener(this);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Verdana", Font.BOLD, 16));

        Color blue = new Color(138, 43, 226); // Button color
        button.setForeground(Color.WHITE);
        button.setBackground(blue);

        Border border = BorderFactory.createLineBorder(Color.DARK_GRAY, 2);
        button.setBorder(border); // Set border

        return button;
    }

    private void addComponents() {
        Container con = getContentPane();

        JLabel titleLabel = new JLabel("User Login");
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 24));
        titleLabel.setBounds(200, 10, 200, 30);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(30, 70, 100, 30);

        usernameField.setBounds(150, 70, 200, 30);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(30, 120, 100, 30);

        passwordField.setBounds(150, 120, 200, 30);

        loginButton.setBounds(50, 180, 120, 40);
        clearButton.setBounds(180, 180, 120, 40);
        exitButton.setBounds(310, 180, 120, 40);

        con.add(titleLabel);
        con.add(usernameLabel);
        con.add(usernameField);
        con.add(passwordLabel);
        con.add(passwordField);

        con.add(loginButton);
        con.add(clearButton);
        con.add(exitButton);

        Color lightBlue = new Color(173,216,230); // Background color
        con.setBackground(lightBlue);
        con.setLayout(null);
        con.setVisible(true);
    }

        @Override
        public void actionPerformed (ActionEvent e){
            if (e.getSource() == loginButton) {
                userLogin();
            } else if (e.getSource() == clearButton) {
                clearFields();
            } else if (e.getSource() == exitButton) {
                dispose();
            }
        }


    public void userLogin() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        // Check if fields are empty
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please fill all the fields.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return; // Exit the method if any field is empty
        }

        // Perform login logic here (e.g., check against database)
        // For example:
         if (authenticateUser(username, password, connection)) {
                 JOptionPane.showMessageDialog(this,
                         "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);

                 clearFields(); // Clear fields after successful login or on error
             }else {
             JOptionPane.showMessageDialog(this,
                     "Login Failed!", "Failed", JOptionPane.INFORMATION_MESSAGE);
                clearFields(); // Clear fields after unsuccessful login
         }
         }


    private boolean authenticateUser(String username, String password, Connection connection) {
        boolean isValidUser = false;

        try {
            String query = "SELECT * FROM student_table WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            isValidUser = resultSet.next(); // If there is a result, the user exists

        } catch (Exception e) {
            e.printStackTrace();
        }


        return isValidUser;
    }

    private void clearFields() {
        usernameField.setText("");
        passwordField.setText("");
    }
}
