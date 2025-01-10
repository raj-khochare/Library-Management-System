import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    // Database URL
    protected static final String db_url = "jdbc:mysql://localhost:3306/librarymanagementsystem";
    // Database credentials
    protected static final String db_username = "root";
    protected static final String db_password = "Admin@1234";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::createAndShowGUI);
    }

    static void createAndShowGUI() {
        JFrame frame = new JFrame("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLayout(null); // Use absolute positioning for custom layout
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        // Set background color
        Color lightBlue = new Color(173, 216, 230);
        frame.getContentPane().setBackground(lightBlue);

        // Create buttons
        JButton registerButton = createButton("Register", 200, 70, 200, 50);
        JButton studentLoginButton = createButton("Student Login", 200, 140, 200, 50);
        JButton adminLoginButton = createButton("Admin Login", 200, 210, 200, 50);
        JButton exitButton = createButton("Exit", 200, 280, 200, 50);

        // Add buttons to the frame
        frame.add(registerButton);
        frame.add(studentLoginButton);
        frame.add(adminLoginButton);
        frame.add(exitButton);

        // Add action listeners
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openRegisterDialog();
            }
        });
        studentLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openLoginDialog();
            }
        });
        adminLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAdminLoginDialog();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Set title label
        JLabel titleLabel = new JLabel("Library Management System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        titleLabel.setBounds(170, 10, 300, 30);

        // Add title label to the frame
        frame.add(titleLabel);

        // Make the frame visible
        frame.setVisible(true);
    }

    private static void openLoginDialog() {
        try (Connection connection = DriverManager.getConnection(db_url, db_username, db_password)) {
            new LoginSwing(connection); // Open login dialog
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database connection error: " + e.getMessage());
        }
    }
    private static void openAdminLoginDialog() {
        try (Connection connection = DriverManager.getConnection(db_url, db_username, db_password)) {
            new AdminLoginSwing(connection); // Open login dialog
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database connection error: " + e.getMessage());
        }
    }

    private static JButton createButton(String text, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setFont(new Font("Verdana", Font.BOLD, 16));

        Color blue = new Color(138, 43, 226); // Button color
        button.setForeground(Color.WHITE);
        button.setBackground(blue);

        button.setBounds(x, y, width, height); // Set position and size

        Border border = BorderFactory.createLineBorder(Color.DARK_GRAY, 2);
        button.setBorder(border); // Set border

        return button;
    }

    private static void openRegisterDialog() {
        try (Connection connection = DriverManager.getConnection(db_url, db_username, db_password)) {
            new RegisterSwing(connection); // Open registration dialog
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database connection error: " + e.getMessage());
        }
    }
}