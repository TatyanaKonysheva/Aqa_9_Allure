package ru.netology.qa.delivery_patterns.test;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.qa.delivery_patterns.data.DataGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class CardDeliveryTest {

    @Test
    void shouldCardDeliverySuccess() {
        DataGenerator.UserInfo validUser = DataGenerator.Registration.generateUser("ru");
        int dayFirstMeeting = 5;
        String firstMeeting = DataGenerator.generateDate(dayFirstMeeting);
        int daySecondMeeting = 10;
        String secondMeeting = DataGenerator.generateDate(daySecondMeeting);
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue(validUser.getCity());
        $("[data-test-id=date] input").doubleClick().sendKeys(firstMeeting);
        $("[data-test-id=name] input").setValue(validUser.getName());
        $("[data-test-id=phone] input").setValue(validUser.getPhone());
        $("[data-test-id='agreement'] .checkbox__text").click();
        $(byText("Запланировать")).click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(12));
        $("[data-test-id='success-notification'].notification .notification__content")
                .shouldBe(visible, Duration.ofSeconds(12))
                .should(exactText("Встреча успешно запланирована на " + firstMeeting));
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(secondMeeting);
        $(byText("Запланировать")).click();
        $(withText("Необходимо подтверждение")).shouldBe(visible, Duration.ofSeconds(15));
        $(byText("Перепланировать")).click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id='success-notification'].notification .notification__content").shouldHave(exactText("Встреча успешно запланирована на " + secondMeeting)).shouldBe(visible, Duration.ofSeconds(12));
    }
}