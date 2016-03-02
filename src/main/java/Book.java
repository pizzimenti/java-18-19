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

  public void save() {
    String sql = "INSERT INTO books (book_title, id) VALUES (:book_title, :id)";
    try(Connection con = DB.sql2o.open()) {
      this.id = (int) con.createQuery(sql, true)
        .addParameter("book_title", book_title)
        .addParameter("id", id)
        .executeUpdate()
        .getKey();
    }
  }

  @Override
  public boolean equals(Object otherBook) {
    if (!(otherBook instanceof Book)) {
      return false;
    } else {
      Book newBook = (Book) otherBook;
      return this.getBookTitle().equals(newBook.getBookTitle()) &&
             this.getId() == newBook.getId();
    }
  }

}
