import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Book {
  private int id;
  private String book_title;

  public Book(String title) {
    this.book_title = title;
  }

  public int getId() {
    return id;
  }

  public String getBookTitle() {
    return book_title;
  }

  public static List<Book> all() {
    String sql = "SELECT * FROM books";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Book.class);
    }
  }

}
