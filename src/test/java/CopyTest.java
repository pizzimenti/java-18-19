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
    Copy newCopy = new Copy(1);
    Copy anotherCopy = new Copy(1);
    assertTrue(newCopy.equals(anotherCopy));
  }

  // @Test
  // public void save_returnTrueIfNamesAreSame() {
  //   Copy newCopy = new Copy();
  //   newCopy.save();
  //   Copy savedCopy = Copy.all().get(0);
  //   assertEquals(newCopy.getId(), savedCopy.getId());
  // }

  // @Test
  // public void find_findsObjectInDatabaseById() {
  //   Copy newCopy = new Copy();
  //   newCopy.save();
  //   Copy savedCopy = Copy.find(newCopy.getId());
  //   assertEquals(newCopy.getId(), savedCopy.getId());
  // }

  @Test
  public void addInventory_addsSpecifiedNumberOfCopies_indexSize() {
      Book newBook = new Book("Harry Potter");
      newBook.save();
      int bookId = newBook.getId();
      Copy newCopy = new Copy(bookId);
      int qty = 26;
      newCopy.addInventory(qty);
      assertEquals(26, Copy.all().size());
  }
}
 // DID A CHECK FOR FALSE POSITIVES -- TEST PASSES
