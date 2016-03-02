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
