package QuizSystem;
import javax.swing.*;
import java.awt.*;

public class RoleSelection extends JPanel {
    public RoleSelection(MainApp app) {
        setLayout(new GridBagLayout());
        setBackground(new Color(30, 30, 40)); 
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        JLabel title = new JLabel("QUIZ SYSTEM PORTAL");
        title.setFont(new Font("Monospaced", Font.BOLD, 48));
        title.setForeground(new Color(0, 255, 150));
        JButton teacherBtn = new JButton("Teacher Portal");
        JButton studentBtn = new JButton("Student Portal");
        
        Font btnFont = new Font("Monospaced", Font.BOLD, 28);
        Color btnBg = new Color(50, 50, 70);

        teacherBtn.setFont(btnFont);
        teacherBtn.setBackground(btnBg);
        teacherBtn.setForeground(Color.WHITE);
        teacherBtn.setFocusPainted(false);
        teacherBtn.setPreferredSize(new Dimension(300, 80));
        studentBtn.setFont(btnFont);
        studentBtn.setBackground(btnBg);
        studentBtn.setForeground(Color.WHITE);
        studentBtn.setFocusPainted(false);
        studentBtn.setPreferredSize(new Dimension(300, 80));
        teacherBtn.addActionListener(e -> app.showScreen("Teacher"));
        studentBtn.addActionListener(e -> app.showScreen("Student"));

        gbc.gridx = 0; gbc.gridy = 0;
        add(title, gbc);
        gbc.gridy = 1;
        add(teacherBtn, gbc);
        gbc.gridy = 2;
        add(studentBtn, gbc);
    }
}