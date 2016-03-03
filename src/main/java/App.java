import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.List;

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
      model.put("books", Book.all());
      model.put("authors", Author.all());

      int selectedAuthor = Integer.parseInt(request.queryParams("author"));
      List<Book> booksByAuthor = Author.getBooks(selectedAuthor);
      String authorName = Author.find(selectedAuthor).getName();

      model.put("listAuthorName", authorName);
      model.put("books", booksByAuthor);
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());

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
      String authorNumber = request.queryParams("authorNumber");
      Author newAuthor = new Author(authorName);
      newAuthor.save();

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
} //end of app
