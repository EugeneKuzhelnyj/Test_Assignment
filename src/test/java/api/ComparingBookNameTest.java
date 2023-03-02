package api;

import entity.Book;
import org.apache.hc.core5.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class ComparingBookNameTest {
    private static final String BOOK_STORE_ENDPOINT = "https://demoqa.com/BookStore/v1/Books";
    private final List<String> bookNamesFromSite = Arrays.asList("Git Pocket Guide","Learning JavaScript Design Patterns",
            "Designing Evolvable Web APIs with ASP.NET","Speaking JavaScript","You Don't Know JS",
            "Programming JavaScript Applications","Eloquent JavaScript, Second Edition","Understanding ECMAScript 6");
    private static final Logger LOG = LoggerFactory.getLogger(ComparingBookNameTest.class);

    @Test
    public void isTitleOfBooksFromPageMatchesWithTitleFromResponseAPI() {
        List<Book> books = given()
                .when()
                .get(BOOK_STORE_ENDPOINT)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response()
                .getBody()
                .jsonPath()
                .getList("books", Book.class);
        LOG.info("Books from API response {}",books.toString());
       List<String> title = books.stream().map(Book::getTitle).collect(Collectors.toList());
       assertThat(title).withFailMessage("This collections of names aren't equal to each other")
               .isEqualTo(bookNamesFromSite);
    }

}
