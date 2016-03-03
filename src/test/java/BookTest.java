import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

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

  @Test
  public void addAuthor_addsAuthorToBook() {
    Author myAuthor = new Author("JK Rowling");
    myAuthor.save();
    Book myBook = new Book("Clean the litter box");
    myBook.save();
    myBook.addAuthor(myAuthor);
    Author savedAuthor = myBook.getAuthors().get(0);
    assertTrue(myAuthor.equals(savedAuthor));
  }

  @Test
  public void getAuthors_getsAuthorsFromBook() {
    Author myAuthor = new Author("JK Rowling");
    myAuthor.save();
    Author myAuthorTwo = new Author("JM Rowling");
    myAuthorTwo.save();
    Book myBook = new Book("Clean the litter box");
    myBook.save();
    Book myBookTwo = new Book("Clean the litter box");
    myBookTwo.save();
    myBook.addAuthor(myAuthor);
    myBook.addAuthor(myAuthor);
    List savedAuthors = myBook.getAuthors();
    assertEquals(savedAuthors.size(), 2);
  }

  @Test
  public void deleteAuthors_DeleteAllAuthorsFromBook() {
    Author myAuthor = new Author("JK Rowling");
    myAuthor.save();
    Book myBook = new Book("Clean the litter box");
    myBook.save();
    myBook.addAuthor(myAuthor);
    myBook.deleteAuthors();
    assertEquals(0, myAuthor.getBooks().size());
  }
}
