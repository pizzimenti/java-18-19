import org.junit.*;
import static org.junit.Assert.*;

public class AuthorTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(0, Author.all().size());
  }

  @Test
  public void equals_returnsTrueIfNamesAretheSame() {
    Author newAuthor = new Author("JK Rowling");
    Author anotherAuthor = new Author("JK Rowling");
    assertTrue(newAuthor.equals(anotherAuthor));
  }

  @Test
  public void save_returnTrueIfNamesAreSame() {
    Author newAuthor = new Author("JK Rowling");
    newAuthor.save();
    Author savedAuthor = Author.all().get(0);
    assertEquals(newAuthor.getId(), savedAuthor.getId());
  }

  @Test
  public void find_findsObjectInDatabaseById() {
    Author newAuthor = new Author("JK Rowling");
    newAuthor.save();
    Author savedAuthor = Author.find(newAuthor.getId());
    assertEquals(newAuthor.getId(), savedAuthor.getId());
  }
}
