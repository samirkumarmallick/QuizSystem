package QuizSystem;
import java.io.*;

public class CSVLoader {
    public static void load(File file) {
        try {
            ExamData.clear();
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            br.readLine(); 

            while((line = br.readLine()) != null) {
                String[] data = line.split(",");
                ExamData.questions.add(data[0]);
                ExamData.options.add(new String[]{
                    data[1], data[2], data[3], data[4]
                });
                ExamData.answers.add(Integer.parseInt(data[5]) - 1);
            }
            
            br.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}