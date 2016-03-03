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
    String sql = "INSERT INTO books (book_title) VALUES (:book_title)";
    try(Connection con = DB.sql2o.open()) {
      this.id = (int) con.createQuery(sql, true)
        .addParameter("book_title", book_title)
        .executeUpdate()
        .getKey();
    }
  }

  public static Book find(int id) {
    String sql = "SELECT * FROM books WHERE id=:id";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
        .addParameter("id",id)
        .executeAndFetchFirst(Book.class);
    }
  }

  public void addAuthor(int authorId) {
    String sql = "INSERT INTO books_authors (id_books, id_authors) VALUES (:id_books, :id_authors);";
    try (Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
      .addParameter("id_books", this.getId())
      .addParameter("id_authors", authorId)
      .executeUpdate();
    }
  }

  public List<Author> getAuthors() {
    String sql = "SELECT authors.* FROM books " +
                "JOIN books_authors ON (books.id = books_authors.id_books) " +
                "JOIN authors ON (books_authors.id_authors = authors.id) " +
                "WHERE books.id =:id_books";
    try (Connection con = DB.sql2o.open()) {
    List<Author> authorList = con.createQuery(sql)
        .addParameter("id_books", this.getId())
        .executeAndFetch(Author.class);
      return authorList;

    }
  }

  public void deleteAuthors() {
    String sql = "DELETE FROM books WHERE id=:id;" + "DELETE FROM books_authors WHERE id_authors=:id_authors;";
    try (Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
      .addParameter("id", id)
      .addParameter("id_authors", this.getId())
      .executeUpdate();
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
