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

  @Test
  public void save_returnTrueIfNamesAreSame() {
    Patron newPatron = new Patron("Joe Patron");
    newPatron.save();
    Patron savedPatron = Patron.all().get(0);
    assertEquals(newPatron.getId(), savedPatron.getId());
  }

  @Test
  public void find_findsObjectInDatabaseById() {
    Patron newPatron = new Patron("Joe Patron");
    newPatron.save();
    Patron savedPatron = Patron.find(newPatron.getId());
    assertEquals(newPatron.getId(), savedPatron.getId());
  }
}
