import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.*;

public class App {


    public static void main(String[] args) throws FileNotFoundException {
        Scanner keyboard = new Scanner(System.in);
        FileSorterUI ui = new FileSorterUI(keyboard);
        File file = ui.promptForFile();
        Set<Enrollee> enrolleeSet = null;
        Map<String, Enrollee> enrolleeMap = new HashMap<>();
        try {
            Scanner inputStream = new Scanner(file);
            while (inputStream.hasNext()) {
                String currentLine = inputStream.nextLine();
                String[] line = currentLine.split(",");
                Enrollee enrollee = new Enrollee(line[0], line[1], line[2], Integer.parseInt(line[3]), line[4]);
                if (enrolleeMap.containsKey(enrollee.getUserId())) {
                    if (enrolleeMap.get(enrollee.getUserId()).getVersion() < enrollee.getVersion()) {
                        enrolleeMap.put(enrollee.getUserId(), enrollee);
                    } else {
                        continue;
                    }
                }
                enrolleeMap.put(enrollee.getUserId(), enrollee);
                enrolleeSet = new TreeSet<>(enrolleeMap.values());
            }
            inputStream.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        }
        writeToFiles(enrolleeSet);

    }

    public static void writeToFiles(Set<Enrollee> enrolleeSet) {
        for (Enrollee enrollee : enrolleeSet) {
            File outputFile = new File(enrollee.getInsuranceCompany() + ".csv");
            if (!outputFile.exists()) {
                try (PrintWriter destinationWriter = new PrintWriter(outputFile)) {
                    destinationWriter.println(enrollee.toString());
                } catch (FileNotFoundException e) {
                    System.err.println("Cannot open file for writing");
                }
            } else {
                try (PrintWriter destinationWriter = new PrintWriter(new FileOutputStream(outputFile, true))) {
                    destinationWriter.println(enrollee.toString());

                } catch (FileNotFoundException e) {
                    System.err.println("Cannot open file for writing");
                }
            }
        }
    }
}
