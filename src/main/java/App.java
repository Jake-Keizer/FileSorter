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
        Set<Enrollee> enrolleeSet = new TreeSet<>();
        try {
            Scanner inputStream = new Scanner(file);
            while (inputStream.hasNext()) {
                String currentLine = inputStream.nextLine();
                String[] line = currentLine.split(",");
                Enrollee enrollee = new Enrollee(line[0], line[1], line[2], Integer.parseInt(line[3]), line[4]);
                enrolleeSet.add(enrollee);
            }
            inputStream.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        }
        List<Enrollee> enrolleeList = removeDuplicatesFromSet(enrolleeSet);
        for (Enrollee enrollee : enrolleeList) {
            String destinationPath = file.getAbsoluteFile().getParent();
            File outputFile = new File(destinationPath + enrollee.getInsuranceCompany() + ".csv");
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

    public static List<Enrollee> removeDuplicatesFromSet(Set<Enrollee> set) {
        List<Enrollee> enrolleeList = new ArrayList<>(set);
        for (int i = 0; i < enrolleeList.size(); i++) {
            for (int j = i + 1; j < enrolleeList.size(); j++) {
                if (enrolleeList.get(i).getUserId().equals(enrolleeList.get(j).getUserId())) {
                    if (enrolleeList.get(i).getVersion() > enrolleeList.get(j).getVersion()) {
                        enrolleeList.remove(j);
                    } else {
                        enrolleeList.remove(i);
                    }
                }
            }
        }
        return enrolleeList;
    }
}
