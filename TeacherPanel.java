package QuizSystem;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class TeacherPanel extends JPanel {
    public TeacherPanel(MainApp app) {

        setLayout(new GridBagLayout());
        setBackground(new Color(30, 30, 40));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Teacher Dashboard", JLabel.CENTER);
        titleLabel.setFont(new Font("Monospaced", Font.BOLD, 36));
        titleLabel.setForeground(new Color(0, 255, 150));

        JLabel timeLabel = new JLabel("Exam Time (Minutes):");
        timeLabel.setFont(new Font("Monospaced", Font.BOLD, 24));
        timeLabel.setForeground(Color.WHITE);

        JTextField timeField = new JTextField("60", 5);
        timeField.setFont(new Font("Monospaced", Font.BOLD, 24));
        timeField.setBackground(new Color(50, 50, 70));
        timeField.setForeground(Color.WHITE);
        timeField.setCaretColor(Color.WHITE);

        JButton uploadBtn = new JButton("Set Time & Upload CSV");
        JButton backBtn = new JButton("Back to Home");
        Font btnFont = new Font("Monospaced", Font.BOLD, 22);
        Color btnBg = new Color(70, 130, 180);
        
        uploadBtn.setFont(btnFont);
        uploadBtn.setBackground(btnBg);
        uploadBtn.setForeground(Color.WHITE);
        uploadBtn.setFocusPainted(false);

        backBtn.setFont(btnFont);
        backBtn.setBackground(new Color(180, 70, 70));
        backBtn.setForeground(Color.WHITE);
        backBtn.setFocusPainted(false);

        uploadBtn.addActionListener(e -> {
            try {
                int minutes = Integer.parseInt(timeField.getText().trim());
                if(minutes <= 0) throw new NumberFormatException();
                ExamData.examTime = minutes * 60; 
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid time entered. Defaulting to 60 minutes.");
                ExamData.examTime = 3600;
                timeField.setText("60");
            }

            JFileChooser chooser = new JFileChooser();
            int res = chooser.showOpenDialog(null);
            if(res == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                CSVLoader.load(file);
                ExamData.examCode = String.valueOf((int)(Math.random()*9000 + 1000));
                JOptionPane.showMessageDialog(null,
                        "Exam Configured Successfully!\nTime: " + (ExamData.examTime/60) + " mins\nExam Code: " + ExamData.examCode);
            }
        });

        backBtn.addActionListener(e -> app.showScreen("Role"));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(titleLabel, gbc);
        gbc.gridy = 1; gbc.gridwidth = 1;
        add(timeLabel, gbc);
        gbc.gridx = 1;
        add(timeField, gbc);
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        add(uploadBtn, gbc);
        gbc.gridy = 3;
        add(backBtn, gbc);
    }
}