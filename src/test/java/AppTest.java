import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.assertj.core.api.Assertions.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.*;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
      goTo("http://localhost:4567/");
      assertThat(pageSource()).contains("Library Catalog");
  }

  @Test
  public void addAuthor_addsAuthorToDatabase() {
    goTo("http://localhost:4567/");
    click("#add-author");
    fill("#author-name").with("John Steinbeck");
    submit("#submit-author");
    assertThat(pageSource()).contains("John Steinbeck");
  }

} // end IntegrationTest
