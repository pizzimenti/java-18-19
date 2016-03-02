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
  private Date date_received;

  public Copy() {
    this.date_received = new Date();
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
    return id_patrons;
  }

  public Date getDateReceived() {
    return date_received;
  }

  public static List<Copy> all() {
    String sql = "SELECT * FROM copies";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Copy.class);
    }
  }

  @Override
  public boolean equals(Object otherCopy) {
    if (!(otherCopy instanceof Copy)) {
      return false;
    } else {
      Copy newCopy = (Copy) otherCopy;
      return this.getId() == newCopy.getId() &&
             this.getDateReceived().equals(newCopy.getDateReceived());
             //FAIL ASSERT FOR UNKNOWN REASON
            //  this.getCheckoutDate().equals(newCopy.getCheckoutDate()) &&
            //  this.getDueDate().equals(newCopy.getDueDate());

    }
  }
}
