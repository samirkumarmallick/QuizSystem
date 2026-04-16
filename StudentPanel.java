package QuizSystem;
import javax.swing.*;
import java.awt.*;

public class StudentPanel extends JPanel {
    JTextField codeField;
    JButton enterBtn, backBtn;
    MainApp app;

    public StudentPanel(MainApp app) {
        this.app = app;
        setLayout(new GridBagLayout());
        setBackground(new Color(30, 30, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        JLabel title = new JLabel("Student Login");
        title.setForeground(new Color(0, 255, 150));
        title.setFont(new Font("Monospaced", Font.BOLD, 36));
        JLabel label = new JLabel("Enter Access Code:");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Monospaced", Font.BOLD, 24));
        codeField = new JTextField(10);
        codeField.setFont(new Font("Monospaced", Font.BOLD, 28));
        codeField.setBackground(new Color(50, 50, 70));
        codeField.setForeground(Color.WHITE);
        codeField.setCaretColor(Color.WHITE);
        codeField.setHorizontalAlignment(JTextField.CENTER);

        enterBtn = new JButton("Start Exam");
        backBtn = new JButton("Back");
        Font btnFont = new Font("Monospaced", Font.BOLD, 22);
        
        enterBtn.setFont(btnFont);
        enterBtn.setBackground(new Color(70, 180, 100));
        enterBtn.setForeground(Color.WHITE);
        enterBtn.setFocusPainted(false);
        
        backBtn.setFont(btnFont);
        backBtn.setBackground(new Color(180, 70, 70));
        backBtn.setForeground(Color.WHITE);
        backBtn.setFocusPainted(false);

        codeField.addActionListener(e -> validateCode());
        enterBtn.addActionListener(e -> validateCode());
        backBtn.addActionListener(e -> app.showScreen("Role"));

        gbc.gridx = 0; gbc.gridy = 0;
        add(title, gbc);
        gbc.gridy = 1;
        add(label, gbc);
        gbc.gridy = 2;
        add(codeField, gbc);
        gbc.gridy = 3;
        add(enterBtn, gbc);
        gbc.gridy = 4;
        add(backBtn, gbc);

        SwingUtilities.invokeLater(() -> {
            getRootPane().setDefaultButton(enterBtn);
        });
    }

    void validateCode() {
        String entered = codeField.getText().trim();
        if(entered.equals(ExamData.examCode) && !entered.isEmpty()) {
            ExamData.initializeStudentData();
            app.showScreen("Quiz");
        } else {
            JOptionPane.showMessageDialog(this, "Invalid Exam Code");
        }
    }
}