import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AppTest {
    private Set<Enrollee> enrolleeSet;

    @Before
    public void reSet(){
        enrolleeSet = new TreeSet<>();
        enrolleeSet.add(new Enrollee("001", "mary", "poppins", 3, "highmark"));
        enrolleeSet.add(new Enrollee("001", "mary", "poppins", 2, "highmark"));
        enrolleeSet.add(new Enrollee("002", "john", "conner", 1, "highmark"));
    }

    @After
    public void emptySet(){
        enrolleeSet.clear();
    }


    @Test
    public void remove_Duplicates_Removes_Duplicates(){
        long sizeOfList = App.removeDuplicatesFromSet(enrolleeSet).size();
        Assert.assertEquals(2, sizeOfList);

    }
    @Test
    public void remove_Duplicates_Removes_Correct_Entry(){
        List<Enrollee> list = App.removeDuplicatesFromSet(enrolleeSet);
        long version = list.get(1).getVersion();
        Assert.assertEquals(3, version);
    }
}
