import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Set;
import java.util.TreeSet;

public class AppTest {
    private Set<Enrollee> enrolleeSet;
    private String destinationPath = "src/main/resources";

    @Before
    public void reSet(){
        enrolleeSet = new TreeSet<>();
        enrolleeSet.add(new Enrollee("001", "mary", "poppins", 3, "bluecross"));
        enrolleeSet.add(new Enrollee("001", "mary", "poppins", 2, "bluecross"));
        enrolleeSet.add(new Enrollee("002", "john", "conner", 1, "UPMC"));
        enrolleeSet.add(new Enrollee("003", "ted", "lasso", 2, "UPMC"));
    }

    @After
    public void emptySet(){
        enrolleeSet.clear();
    }


    @Test
    public void write_To_File_Creates_Correct_Number_Of_Files(){
        App.writeToFiles(enrolleeSet);
        for (Enrollee enrollee : enrolleeSet){
            File test = new File(enrollee.getInsuranceCompany() + ".csv");
            Assert.assertTrue(test.exists());
        }

    }

}
