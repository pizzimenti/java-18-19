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

}
