package guru.qa.tests;

import com.codeborne.selenide.CollectionCondition;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.ParameterizedTest;

import java.awt.print.Paper;
import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class WebFormTest extends TestBase {

    PopupTest popupTest = new PopupTest();

    @ValueSource(strings = {"Художественная литература", "Образование"})
    @ParameterizedTest(name = "Панель навигации содержит {0}")
    void commonSearchTest(String testData) {
        open("https://www.chitai-gorod.ru");
        PopupTest.removePopup();
        $(".catalog__nav").$(byText("Книги")).hover();
        $(byText(testData)).click();
        PopupTest.removePopup();
        $(".navigation").shouldHave(text(testData));
    }

    @CsvSource(value = {
            "Игры, Настольные и семейные игры",
            "Игрушки, Развивающие игрушки для маленьких"
    })
    @ParameterizedTest(name = "Панель навигации для \"{0}\" содержит \"{1}\"")
    void searchGames(String testData, String expectedResult) {
        open("https://www.chitai-gorod.ru");
        PopupTest.removePopup();
        $(".catalog__nav").$(byText("Игры и игрушки")).hover();
        $(byText(testData)).click();
        PopupTest.removePopup();
        $(".navigation").shouldHave(text(expectedResult));
    }

    static Stream<Arguments> dataProviderForSelenideSiteMenuTest() {
        return Stream.of(
                Arguments.of(Stationery.Paper, List.of("Бланки, конверты, учетные книги", "Бумага и картон",
                        "Бумага офисная", "Для рисования и черчения", "Дневники", "Ежедневники, планинги, органайзеры",
                        "Тетради школьные", "Книги для записей, блокноты", "Тетради общие", "Тетради тематические")),
                Arguments.of(Stationery.Haberdashery, List.of("Визитницы, запасные блоки", "Обложки для документов",
                        "Папки кожаные и кожзам", "Портфели, папки-портфели деловые"))
        );
    }

    @MethodSource("dataProviderForSelenideSiteMenuTest")
    @ParameterizedTest(name = "Для пункта меню {0} в панели навигации дочерними элементами являются пункты {1}")
    void selenideSiteMenuTest(Stationery stationery, List<String> expectedButtons) {
        open("https://www.chitai-gorod.ru");
        PopupTest.removePopup();
        $(".catalog__nav").$(byText("Канцтовары")).hover();
        $(byText(stationery.getTitle())).click();
        PopupTest.removePopup();
        $$(".navigation__item .navigation__item").shouldHave(CollectionCondition.texts(expectedButtons));
    }
}


