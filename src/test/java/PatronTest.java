import org.junit.*;
import static org.junit.Assert.*;

public class PatronTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(0, Patron.all().size());
  }

  @Test
  public void equals_returnsTrueIfNamesAretheSame() {
    Patron newPatron = new Patron("Joe Patron");
    Patron anotherPatron = new Patron("Joe Patron");
    assertTrue(newPatron.equals(anotherPatron));
  }

}
