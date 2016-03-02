import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;
import java.util.Date;

public class Copy {
  private int id;
  private Date checkout_date;
  private Date due_date;
  private int id_books;
  private int id_patrons;

  public Copy() {

  }

  public int getId() {
    return id;
  }

  public Date getCheckoutDate() {
    return checkout_date;
  }

  public Date getDueDate() {
    return due_date;
  }

  public int getBookId() {
    return id_books;
  }

  public int getPatronId() {
    return id_patrons
  }

  public static List<Copy> all() {
    String sql = "SELECT * FROM copies";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Copy.class);
    }
  }

}
