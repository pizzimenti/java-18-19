import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Patron {
  private int id;
  private String patron_name;

  public Patron(String patron_name) {
    this.patron_name = patron_name;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return patron_name;
  }

  public static List<Patron> all() {
    String sql = "SELECT * FROM patrons";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Patron.class);
    }
  }

  public void save() {
    String sql = "INSERT INTO patrons (patron_name) VALUES (:patron_name)";
    try(Connection con = DB.sql2o.open()) {
      this.id = (int) con.createQuery(sql, true)
        .addParameter("patron_name", patron_name)
        .executeUpdate()
        .getKey();
    }
  }

  public static Patron find(int id) {
    String sql = "SELECT * FROM patrons WHERE id=:id";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
        .addParameter("id",id)
        .executeAndFetchFirst(Patron.class);
    }
  }

  @Override
  public boolean equals(Object otherPatron) {
    if (!(otherPatron instanceof Patron)) {
      return false;
    } else {
      Patron newPatron = (Patron) otherPatron;
      return this.getName().equals(newPatron.getName()) &&
             this.getId() == newPatron.getId();
    }
  }

}
