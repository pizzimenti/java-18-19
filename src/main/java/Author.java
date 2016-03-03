import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Author {
  private int id;
  private String author_name;

  public Author(String author_name) {
    this.author_name = author_name;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return author_name;
  }

  public static List<Author> all() {
    String sql = "SELECT * FROM authors";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Author.class);
    }
  }

  public void save() {
    String sql = "INSERT INTO authors (author_name) VALUES (:author_name)";
    try(Connection con = DB.sql2o.open()) {
      this.id = (int) con.createQuery(sql, true)
        .addParameter("author_name", author_name)
        .executeUpdate()
        .getKey();
    }
  }

  public static Author find(int id) {
    String sql = "SELECT * FROM authors WHERE id=:id";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
        .addParameter("id",id)
        .executeAndFetchFirst(Author.class);
    }
  }

  public void addBook(Book book) {
    String sql = "INSERT INTO books_authors (id_books, id_authors) VALUES (:id_books, :id_authors);";
    try (Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
      .addParameter("id_books", book.getId())
      .addParameter("id_authors", this.getId())
      .executeUpdate();
    }
  }

  public static List<Book> getBooks(int id) {
    String sql = "SELECT books.* FROM authors " +
                "JOIN books_authors ON (authors.id = books_authors.id_authors) " +
                "JOIN books ON (books_authors.id_books = books.id) " +
                "WHERE authors.id =:id_authors";
    try (Connection con = DB.sql2o.open()) {
    List<Book> bookList = con.createQuery(sql)
        .addParameter("id_authors", id)
        .executeAndFetch(Book.class);
      return bookList;

    }
  }

  public void deleteBooks() {
    String sql = "DELETE FROM authors WHERE id=:id;";
    try (Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();

    String sqlJoin = "DELETE FROM books_authors WHERE id_authors=:id_authors;";
      con.createQuery(sqlJoin)
      .addParameter("id_authors", this.getId())
      .executeUpdate();
    }
  }

  @Override
  public boolean equals(Object otherAuthor) {
    if (!(otherAuthor instanceof Author)) {
      return false;
    } else {
      Author newAuthor = (Author) otherAuthor;
      return this.getName().equals(newAuthor.getName()) &&
             this.getId() == newAuthor.getId();
    }
  }
}
