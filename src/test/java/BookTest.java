import org.junit.*;
import static org.junit.Assert.*;

public class BookTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(0, Book.all().size());
  }

  @Test
  public void equals_returnsTrueIfNamesAretheSame() {
    Book newBook = new Book("Harry Potter");
    Book anotherBook = new Book("Harry Potter");
    assertTrue(newBook.equals(anotherBook));
  }

}
