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
    // this.checkout_date = new Date();
    // this.due_date = new Date();
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

  public void save() {
    String sql = "INSERT INTO copies (id, checkout_date, due_date, id_books, id_patrons, date_received) VALUES (:id, :checkout_date, :due_date, :id_books, :id_patrons, :date_received)";
    try(Connection con = DB.sql2o.open()) {
      this.id = (int) con.createQuery(sql, true)
        .addParameter("id", id)
        .addParameter("checkout_date", checkout_date)
        .addParameter("due_date", due_date)
        .addParameter("id_books", id_books)
        .addParameter("id_patrons", id_patrons)
        .addParameter("date_received", date_received)
        .executeUpdate()
        .getKey();
    }
  }

  public static Copy find(int id) {
    String sql = "SELECT * FROM copies WHERE id=:id";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
        .addParameter("id",id)
        .executeAndFetchFirst(Copy.class);
    }
  }


  @Override
  public boolean equals(Object otherCopy) {
    if (!(otherCopy instanceof Copy)) {
      return false;
    } else {
      Copy newCopy = (Copy) otherCopy;
      return this.getId() == newCopy.getId(); 
            //  this.getDateReceived().equals(newCopy.getDateReceived());
          //  this.getCheckoutDate().equals(newCopy.getCheckoutDate()) &&
          //  this.getDueDate().equals(newCopy.getDueDate());

    }
  }
}
