import org.junit.*;
import static org.junit.Assert.*;

public class CopyTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(0, Copy.all().size());
  }

  @Test
  public void equals_returnsTrueIfNamesAretheSame() {
    Copy newCopy = new Copy();
    Copy anotherCopy = new Copy();
    assertTrue(newCopy.equals(anotherCopy));
  }

  @Test
  public void save_returnTrueIfNamesAreSame() {
    Copy newCopy = new Copy();
    newCopy.save();
    Copy savedCopy = Copy.all().get(0);
    assertEquals(newCopy.getId(), savedCopy.getId());
  }

}
