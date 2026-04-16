package QuizSystem;
import javax.swing.*;
import java.awt.*;

public class ResultPanel extends JPanel {
    static int score;
    JLabel resultLabel;
    public ResultPanel(MainApp app) {

        setLayout(new GridBagLayout());
        setBackground(new Color(30, 30, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        JLabel title = new JLabel("EXAM COMPLETE");
        title.setFont(new Font("Monospaced", Font.BOLD, 48));
        title.setForeground(new Color(0, 255, 150));
        resultLabel = new JLabel();
        resultLabel.setFont(new Font("Monospaced", Font.BOLD, 64));
        resultLabel.setForeground(Color.WHITE);

        JButton backBtn = new JButton("Return to Home");
        backBtn.setFont(new Font("Monospaced", Font.BOLD, 24));
        backBtn.setBackground(new Color(70, 130, 180));
        backBtn.setForeground(Color.WHITE);
        backBtn.setFocusPainted(false);
        backBtn.addActionListener(e -> app.showScreen("Role"));

        gbc.gridx = 0; gbc.gridy = 0;
        add(title, gbc);
        gbc.gridy = 1;
        add(resultLabel, gbc);
        gbc.gridy = 2;
        add(backBtn, gbc);
    }
    @Override
    public void setVisible(boolean aFlag) {
        super.setVisible(aFlag);
        int maxScore = ExamData.questions.size();
        resultLabel.setText("Score: " + score + " / " + maxScore);
    }
}