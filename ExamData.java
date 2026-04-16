package QuizSystem;
import java.util.*;

public class ExamData {
    public static List<String> questions = new ArrayList<>();
    public static List<String[]> options = new ArrayList<>();
    public static List<Integer> answers = new ArrayList<>();

    public static String examCode = "";
    public static int examTime = 3600;
    public static List<Integer> userAnswers = new ArrayList<>();
    public static List<Boolean> answered = new ArrayList<>();

    public static void initializeStudentData() {
        userAnswers.clear();
        answered.clear();
        for(int i = 0; i < questions.size(); i++) {
            userAnswers.add(-1);
            answered.add(false);
        }
    }

    public static void clear() {
        questions.clear();
        options.clear();
        answers.clear();
        userAnswers.clear();
        answered.clear();
    }
}