import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

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


  @Test
  public void addBook_addsBookToAuthor() {
    Author myAuthor = new Author("JK Rowling");
    myAuthor.save();
    Book myBook = new Book("Clean the litter box");
    myBook.save();
    myAuthor.addBook(myBook);
    Book savedBook = myAuthor.getBooks(myAuthor.getId()).get(0);
    assertTrue(myBook.equals(savedBook));
  }

  @Test
  public void getBooks_getsBooksFromAuthor() {
    Author myAuthor = new Author("JK Rowling");
    myAuthor.save();
    Author myAuthorTwo = new Author("JM Rowling");
    myAuthorTwo.save();
    Book myBook = new Book("Clean the litter box");
    myBook.save();
    Book myBookTwo = new Book("Clean the litter box");
    myBookTwo.save();
    myAuthor.addBook(myBook);
    myAuthor.addBook(myBookTwo);
    List savedBooks = myAuthor.getBooks(myAuthor.getId());
    assertEquals(2, savedBooks.size());
  }


    @Test
    public void deleteBooks_DeletesAllBooksFromAuthor() {
      Author myAuthor = new Author("JK Rowling");
      myAuthor.save();
      Book myBook = new Book("Clean the litter box");
      myBook.save();
      myAuthor.addBook(myBook);
      myAuthor.deleteBooks();
      assertEquals(0, myBook.getAuthors().size());
    }
}
