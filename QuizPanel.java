package QuizSystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QuizPanel extends JPanel {
    MainApp app;
    JLabel questionLabel, timerLabel;
    JRadioButton opt1, opt2, opt3, opt4;
    ButtonGroup bg;
    JButton nextBtn, backBtn;

    JPanel sidebar;
    JButton[] qButtons;
    int current = 0;
    int timeLeft;
    Timer timer;

    Color panelBg = new Color(30, 30, 40);
    Color textFg = Color.WHITE;
    Color accentColor = new Color(0, 255, 150);

    public QuizPanel(MainApp app) {
        this.app = app;
        setLayout(new BorderLayout());
        setBackground(panelBg);

        questionLabel = new JLabel("", JLabel.CENTER);
        questionLabel.setFont(new Font("Monospaced", Font.BOLD, 30));
        questionLabel.setForeground(textFg);
        timerLabel = new JLabel("Time: 00:00");
        timerLabel.setFont(new Font("Monospaced", Font.BOLD, 28));
        timerLabel.setForeground(new Color(255, 100, 100)); // Red warning color

        JPanel top = new JPanel(new BorderLayout());
        top.setBackground(panelBg);
        top.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        top.add(questionLabel, BorderLayout.CENTER);
        top.add(timerLabel, BorderLayout.EAST);
        add(top, BorderLayout.NORTH);

        opt1 = new JRadioButton();
        opt2 = new JRadioButton();
        opt3 = new JRadioButton();
        opt4 = new JRadioButton();

        Font optFont = new Font("Monospaced", Font.PLAIN, 24);
        JRadioButton[] options = {opt1, opt2, opt3, opt4};
        bg = new ButtonGroup();
        
        JPanel center = new JPanel(new GridLayout(4,1,15,15));
        center.setBackground(panelBg);
        center.setBorder(BorderFactory.createEmptyBorder(20, 150, 20, 150));
        for(JRadioButton opt : options) {
            opt.setFont(optFont);
            opt.setBackground(panelBg);
            opt.setForeground(textFg);
            opt.setFocusPainted(false);
            bg.add(opt);
            center.add(opt);
        }

        add(center, BorderLayout.CENTER);
        backBtn = new JButton("◄ Previous");
        nextBtn = new JButton("Next ►");

        Font btnFont = new Font("Monospaced", Font.BOLD, 24);
        backBtn.setFont(btnFont);
        nextBtn.setFont(btnFont);
        backBtn.setBackground(new Color(80, 80, 100));
        backBtn.setForeground(Color.WHITE);
        backBtn.setFocusPainted(false);
        
        nextBtn.setBackground(new Color(70, 130, 180));
        nextBtn.setForeground(Color.WHITE);
        nextBtn.setFocusPainted(false);

        JPanel bottom = new JPanel();
        bottom.setBackground(panelBg);
        bottom.setBorder(BorderFactory.createEmptyBorder(20, 20, 30, 20));
        bottom.add(backBtn);
        bottom.add(Box.createHorizontalStrut(40)); // Space between buttons
        bottom.add(nextBtn);

        add(bottom, BorderLayout.SOUTH);
        sidebar = new JPanel();
        sidebar.setLayout(new GridLayout(0, 1, 10, 10));
        sidebar.setBackground(new Color(20, 20, 30)); // Slightly darker sidebar
        sidebar.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JScrollPane scrollPane = new JScrollPane(sidebar);
        scrollPane.setBorder(null);
        add(scrollPane, BorderLayout.WEST);

        timer = new Timer(1000, e -> {
            timeLeft--;
            updateTimerDisplay();
            if(timeLeft <= 0) {
                timer.stop();
                finishExam();
            }
        });

        nextBtn.addActionListener(e -> {
            saveAnswer();
            current++;
            loadQuestion();
        });

        backBtn.addActionListener(e -> {
            if(current > 0) {
                saveAnswer();
                current--;
                loadQuestion();
            }
        });

        addComponentListener(new ComponentAdapter() {
            public void componentShown(ComponentEvent e) {
                startExam();
            }
        });
    }
    
    void updateTimerDisplay() {
        int mins = timeLeft / 60;
        int secs = timeLeft % 60;
        timerLabel.setText(String.format("Time: %02d:%02d", mins, secs));
    }

    void startExam() {
        current = 0;
        ExamData.initializeStudentData();
        timeLeft = ExamData.examTime;
        updateTimerDisplay();
        if(timer.isRunning()) timer.stop();
        timer.start();
        buildSidebar();
        loadQuestion();
    }

    void buildSidebar() {
        sidebar.removeAll();
        int n = ExamData.questions.size();
        qButtons = new JButton[n];

        for(int i=0; i<n; i++) {
            int index = i;
            qButtons[i] = new JButton("Q" + (i+1));
            qButtons[i].setFont(new Font("Monospaced", Font.BOLD, 18));
            qButtons[i].setBackground(new Color(180, 70, 70)); // Red for unanswered
            qButtons[i].setForeground(Color.WHITE);
            qButtons[i].setFocusPainted(false);
            qButtons[i].addActionListener(e -> {
                saveAnswer();
                current = index;
                loadQuestion();
            });

            sidebar.add(qButtons[i]);
        }

        sidebar.revalidate();
        sidebar.repaint();
    }

    void loadQuestion() {
        if(current >= ExamData.questions.size()) {
            finishExam();
            return;
        }

        questionLabel.setText("Q" + (current+1) + ": " + ExamData.questions.get(current));

        String[] opts = ExamData.options.get(current);
        opt1.setText(opts[0]);
        opt2.setText(opts[1]);
        opt3.setText(opts[2]);
        opt4.setText(opts[3]);

        bg.clearSelection();
        int prev = ExamData.userAnswers.get(current);
        if(prev == 0) opt1.setSelected(true);
        if(prev == 1) opt2.setSelected(true);
        if(prev == 2) opt3.setSelected(true);
        if(prev == 3) opt4.setSelected(true);

        updateSidebar();
    }

    void saveAnswer() {
        int selected = -1;

        if(opt1.isSelected()) selected = 0;
        if(opt2.isSelected()) selected = 1;
        if(opt3.isSelected()) selected = 2;
        if(opt4.isSelected()) selected = 3;

        ExamData.userAnswers.set(current, selected);
        if(selected != -1) {
            ExamData.answered.set(current, true);
        }

        updateSidebar();
    }

    void updateSidebar() {
        for(int i=0; i<qButtons.length; i++) {
            if(i == current) {
                qButtons[i].setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
            } else {
                qButtons[i].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
            }

            if(ExamData.answered.get(i)) {
                qButtons[i].setBackground(new Color(70, 180, 100)); // Green for answered
            } else {
                qButtons[i].setBackground(new Color(180, 70, 70)); // Red for unanswered
            }
        }
    }

    void finishExam() {
        timer.stop();
        int score = 0;
        for(int i=0; i<ExamData.answers.size(); i++) {
            if(ExamData.userAnswers.get(i).equals(ExamData.answers.get(i))) {
                score++;
            }
        }
        ResultPanel.score = score;
        app.showScreen("Result");
    }
}