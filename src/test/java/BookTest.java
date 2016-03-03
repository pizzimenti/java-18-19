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

  @Test
  public void save_returnTrueIfNamesAreSame() {
    Book newBook = new Book("Harry Potter");
    newBook.save();
    Book savedBook = Book.all().get(0);
    assertEquals(newBook.getId(), savedBook.getId());
  }

  @Test
  public void find_findsObjectInDatabaseById() {
    Book newBook = new Book("Harry Potter");
    newBook.save();
    Book savedBook = Book.find(newBook.getId());
    assertTrue(newBook.equals(savedBook));
  }
}
