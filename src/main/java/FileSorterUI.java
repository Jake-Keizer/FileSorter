import java.io.File;
import java.util.Scanner;

public class FileSorterUI {
    private Scanner userInput;

    public FileSorterUI(Scanner userInput) {
        this.userInput = userInput;
    }

    public File promptForFile(){
        System.out.println("Please enter absolute path to file");
        String path = userInput.nextLine();
        return new File(path);
    }
}
