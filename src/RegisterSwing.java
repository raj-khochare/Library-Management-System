import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.*;
import java.sql.*;

public class RegisterSwing extends JDialog implements ActionListener {
    private Connection connection;

    // Swing components
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField tufIdField;
    private JTextField emailField;
    private JButton registerButton;
    private JButton clearButton;
    private JButton exitButton;

    public RegisterSwing(Connection connection) {
        this.connection = connection;

        // Set up the dialog
        setTitle("User Registration");
        setSize(600, 500);
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
        firstNameField = new JTextField();
        lastNameField = new JTextField();
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        tufIdField = new JTextField();
        emailField = new JTextField();

        registerButton = createButton("Register");
        clearButton = createButton("Clear");
        exitButton = createButton("Exit");

        registerButton.addActionListener(this);
        clearButton.addActionListener(this);
        exitButton.addActionListener(this);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Verdana", Font.BOLD, 16));

        Color blue = new Color(138, 43, 226);
        button.setForeground(Color.WHITE);
        button.setBackground(blue);

        Border border = BorderFactory.createLineBorder(Color.DARK_GRAY, 2);
        button.setBorder(border);

        return button;
    }

    private void addComponents() {
        Container con = getContentPane();

        JLabel titleLabel = new JLabel("User Registration");
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 24));
        titleLabel.setBounds(100, 10, 300, 50);

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setBounds(30, 50, 100, 30);

        firstNameField.setBounds(150, 50, 200, 30);

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setBounds(30, 90, 100, 30);

        lastNameField.setBounds(150, 90, 200, 30);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(30, 130, 100, 30);

        usernameField.setBounds(150, 130, 200, 30);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(30, 170, 100, 30);

        passwordField.setBounds(150, 170, 200, 30);

        JLabel tufIdLabel = new JLabel("TUF_ID:");
        tufIdLabel.setBounds(30, 210, 100, 30);

        tufIdField.setBounds(150, 210, 200, 30);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(30, 250, 100, 30);

        emailField.setBounds(150, 250, 200, 30);

        registerButton.setBounds(50, 300, 120, 40);
        clearButton.setBounds(180, 300, 120, 40);
        exitButton.setBounds(310, 300, 120, 40);

        con.add(titleLabel);
        con.add(firstNameLabel);
        con.add(firstNameField);
        con.add(lastNameLabel);
        con.add(lastNameField);
        con.add(usernameLabel);
        con.add(usernameField);
        con.add(passwordLabel);
        con.add(passwordField);
        con.add(tufIdLabel);
        con.add(tufIdField);
        con.add(emailLabel);
        con.add(emailField);

        con.add(registerButton);
        con.add(clearButton);
        con.add(exitButton);

        Color lightBlue = new Color(173, 216, 230);
        con.setBackground(lightBlue);
        con.setLayout(null);
        con.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerButton) {
            userRegister();
        } else if (e.getSource() == clearButton) {
            clearFields();
        } else if (e.getSource() == exitButton) {
            dispose();
        }
    }

    public void userRegister() {
        String first = firstNameField.getText();
        String last = lastNameField.getText();
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String tuf_ID = tufIdField.getText();
        String email = emailField.getText();
        if (first.isEmpty() || last.isEmpty() || username.isEmpty() || password.isEmpty() || tuf_ID.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all the fields.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return; // Exit the method if any field is empty
        }

        if (userExists(username, tuf_ID)) {
            JOptionPane.showMessageDialog(this,
                    "User already exists with the provided Username or TUF_ID.",
                    "Registration Error", JOptionPane.ERROR_MESSAGE);
            clearFields();
            return;
        }

        try {
            String query = "INSERT INTO student_table(first,last,username,password,tuf_ID,email) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, first);
            preparedStatement.setString(2, last);
            preparedStatement.setString(3, username);
            preparedStatement.setString(4, password);
            preparedStatement.setString(5, tuf_ID);
            preparedStatement.setString(6, email);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(this,
                        "Student Registered Successfully",
                        "Registration Success", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
                dispose();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Error during registration: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private boolean userExists(String username, String tuf_ID) {
        String query = "SELECT COUNT(*) FROM student_table WHERE username=? OR tuf_ID=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, tuf_ID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0; // Return true if there is at least one existing user
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Error checking user existence: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return false; // Return false if no existing user is found
    }

    private void clearFields() {
        firstNameField.setText("");
        lastNameField.setText("");
        usernameField.setText("");
        passwordField.setText("");
        tufIdField.setText("");
        emailField.setText("");
    }
}

