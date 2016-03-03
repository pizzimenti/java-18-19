import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.List;
import org.sql2o.*;


public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("books", Book.all());
      model.put("authors", Author.all());
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());

    post("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      // model.put("books", Book.all());
      model.put("authors", Author.all());

      int selectedAuthor = Integer.parseInt(request.queryParams("author"));
      List<Book> booksByAuthor = Author.getBooks(selectedAuthor);
      String authorName = Author.find(selectedAuthor).getName();

      model.put("listAuthorName", authorName);
      model.put("books", booksByAuthor);
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());

    get("/add-book", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("books", Book.all());
      model.put("authors", Author.all());
      model.put("template", "templates/add-book.vtl");
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());

    post("/add-book", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      String bookName = request.queryParams("book");
      int authorId = Integer.parseInt(request.queryParams("author"));
      Book newBook = new Book(bookName);
      newBook.save();
      newBook.addAuthor(authorId);

      response.redirect("/add-book");
      return null;
    });

    get("/add-author", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("books", Book.all());
      model.put("authors", Author.all());
      model.put("template", "templates/add-author.vtl");
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());

    post("/add-author", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      String authorName = request.queryParams("author");
      int bookId = Integer.parseInt(request.queryParams("book"));
      Author newAuthor = new Author(authorName);
      newAuthor.save();
      newAuthor.addBook(bookId);

      response.redirect("/add-author");
      return null;
    });

    post("/add-author-to-book", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();


      int authorId = Integer.parseInt(request.queryParams("author-existing"));
      int bookId = Integer.parseInt(request.queryParams("book-existing"));
      addBookAndAuthor(bookId, authorId);


      response.redirect("/add-author");
      return null;
    });

    // get("/book/:id", (request, response) -> {
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   model.put("books", Book.all());
    //   model.put("authors", Author.all());
    //   model.put("template", "templates/book.vtl");
    //
    //   Book book = Book.find(Integer.parseInt(request.params(":id")));
    //   List<Author> authorList = book.getAuthors();
    //   model.put("book", book);
    //   model.put("authorList", authorList);
    //   return new ModelAndView (model, layout);
    // }, new VelocityTemplateEngine());
    //
  } //end of main
  public static void addBookAndAuthor(int bookId, int authorId) {
    String sql = "INSERT INTO books_authors (id_books, id_authors) VALUES (:id_books, :id_authors);";
    try (Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
      .addParameter("id_books", bookId)
      .addParameter("id_authors", authorId)
      .executeUpdate();
    }
  }
} //end of app
