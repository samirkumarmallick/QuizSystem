package QuizSystem;
import javax.swing.*;
import java.awt.*;

public class MainApp extends JFrame {
    CardLayout cardLayout;
    JPanel mainPanel;

    QuizPanel quiz;
    public MainApp() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("Advanced Quiz System");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.setBackground(new Color(30, 30, 40));

        RoleSelection role = new RoleSelection(this);
        TeacherPanel teacher = new TeacherPanel(this);
        StudentPanel student = new StudentPanel(this);
        quiz = new QuizPanel(this);  

        ResultPanel result = new ResultPanel(this);
        mainPanel.add(role, "Role");
        mainPanel.add(teacher, "Teacher");
        mainPanel.add(student, "Student");
        mainPanel.add(quiz, "Quiz");
        mainPanel.add(result, "Result");

        add(mainPanel);
        setVisible(true);
    }

    public void showScreen(String name) {
        cardLayout.show(mainPanel, name);
    }

    public QuizPanel getQuizPanel() {
        return quiz;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainApp::new);
    }
}