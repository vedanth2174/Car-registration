import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class Carregistrations extends JFrame implements ActionListener {
    private JTextField T1, T2, T3, T4;
    private final String dbURL = "jdbc:mysql://localhost:3306/vehicles";
    private final String username = "root";
    private final String password = "Root@123";

    public Carregistrations() {
        this.setTitle("Auto Valut");
        this.setSize(1080, 720);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        // Set background image
        JLabel background = new JLabel(new ImageIcon("img.png"));
        background.setBounds(0, 0, 1080, 720);
        this.add(background);

        JLabel L1 = new JLabel("Auto Vault");
        L1.setBounds(430, 50, 480, 60);
        L1.setFont(new Font("Serif", Font.BOLD, 44));
        background.add(L1);

        JLabel L2 = new JLabel("Your Car's Chronicle");
        L2.setBounds(450, 120, 600, 20);
        L2.setFont(new Font("Serif", Font.PLAIN, 20));
        background.add(L2);

        JLabel Number = new JLabel("Car Number: ");
        Number.setBounds(320, 200, 150, 30);
        Number.setFont(new Font("Serif", Font.PLAIN, 20));
        background.add(Number);

        T1 = new JTextField();
        T1.setBounds(500, 200, 250, 30);
        background.add(T1);

        JLabel name = new JLabel("Car Model: ");
        name.setBounds(320, 250, 150, 30);
        name.setFont(new Font("Serif", Font.PLAIN, 20));
        background.add(name);

        T2 = new JTextField();
        T2.setBounds(500, 250, 250, 30);
        background.add(T2);

        JLabel Colour = new JLabel("Car Colour: ");
        Colour.setBounds(320, 300, 150, 30);
        Colour.setFont(new Font("Serif", Font.PLAIN, 20));
        background.add(Colour);

        T3 = new JTextField();
        T3.setBounds(500, 300, 250, 30);
        background.add(T3);

        JLabel carprice = new JLabel("Car Price: ");
        carprice.setBounds(320, 350, 150, 30);
        carprice.setFont(new Font("Serif", Font.PLAIN, 20));
        background.add(carprice);

        T4 = new JTextField();
        T4.setBounds(500, 350, 250, 30);
        background.add(T4);

        JButton newregistration = new JButton("Register");
        newregistration.setBounds(310, 450, 180, 40);
        newregistration.setFont(new Font("Serif", Font.PLAIN, 16));
        newregistration.addActionListener(this);
        background.add(newregistration);

        JButton clearButton = new JButton("Clear");
        clearButton.setBounds(530, 450, 180, 40);
        clearButton.setFont(new Font("Serif", Font.PLAIN, 16));
        clearButton.addActionListener(e -> clearFields());
        background.add(clearButton);

        JButton updateButton = new JButton("Update");
        updateButton.setBounds(310, 500, 180, 40);
        updateButton.setFont(new Font("Serif", Font.PLAIN, 16));
        updateButton.addActionListener(e -> updateRecord());
        background.add(updateButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setBounds(530, 500, 180, 40);
        deleteButton.setFont(new Font("Serif", Font.PLAIN, 16));
        deleteButton.addActionListener(e -> deleteRecord());
        background.add(deleteButton);

        JButton displayButton = new JButton("Display");
        displayButton.setBounds(430, 570, 180, 40);
        displayButton.setFont(new Font("Serif", Font.PLAIN, 16));
        displayButton.addActionListener(e -> displayRecords());
        background.add(displayButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Carregistrations().setVisible(true);
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Register":
                registerCar();
                break;
        }
    }

    private void registerCar() {
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {
            int Number = Integer.parseInt(T1.getText());
            String Name = T2.getText();
            String Colour = T3.getText();
            int Price = Integer.parseInt(T4.getText());

            String sql = "INSERT INTO v1 (Number, Name , Colour , Price) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setInt(1, Number);
                statement.setString(2, Name);
                statement.setString(3, Colour);
                statement.setInt(4, Price);

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(this, "A new car was registered successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to register c!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        T1.setText("");
        T2.setText("");
        T3.setText("");
        T4.setText("");
    }

    private void updateRecord() {
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {
            int Number = Integer.parseInt(T1.getText());
            String Name = T2.getText();
            String Colour = T3.getText();
            int Price = Integer.parseInt(T4.getText());

            String sql = "UPDATE v1 SET Name=?, Colour=?, Price=? WHERE Number=?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, Name);
                statement.setString(2, Colour);
                statement.setInt(3, Price);
                statement.setInt(4, Number);
        
                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(this, "Record updated successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to update record!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteRecord() {
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {
            int Number = Integer.parseInt(T1.getText());
            String sql = "DELETE FROM v1 WHERE Number=?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setInt(1, Number);

                int rowsDeleted = statement.executeUpdate();
                if (rowsDeleted > 0) {
                    JOptionPane.showMessageDialog(this, "Record deleted successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete record!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayRecords() {
        try (Connection conn = DriverManager.getConnection(dbURL, username, password);
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM v1")) {

            StringBuilder result = new StringBuilder();
            while (resultSet.next()) {
                result.append("Number: ").append(resultSet.getInt("Number")).append(", ")
                        .append("Name: ").append(resultSet.getString("Name")).append(", ")
                        .append("Colour: ").append(resultSet.getString("Colour")).append(", ")
                        .append("Price: ").append(resultSet.getInt("Price")).append("\n");
            }

            JOptionPane.showMessageDialog(this, result.toString(), "Records", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
