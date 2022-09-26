package ru.netology.rest;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;

public class OrderDeliveryTest {

    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @BeforeEach
    void openBrowser() {
        open("http://localhost:9999");
    }

    @Test
    void submitFormPass() {
        String date = generateDate(7);
        $("[placeholder=\"Город\"]").setValue("Брянск");
        $("[placeholder=\"Дата встречи\"]").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[placeholder=\"Дата встречи\"]").setValue(date);
        $("[name=\"name\"]").setValue("Петров Петя");
        $("[name=\"phone\"]").setValue("+79001112233");
        $x("//input[@name=\"agreement\"]/..").click();
        $x("//span[contains(text(),\"Забронировать\")]").click();
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + date), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    @Test
    void submitFormFail() {
        String date = generateDate(7);
        $("[placeholder=\"Город\"]").setValue("Брянск");
        $("[placeholder=\"Дата встречи\"]").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[placeholder=\"Дата встречи\"]").setValue(date);
        $("[name=\"name\"]").setValue("Петров Пётр");
        $("[name=\"phone\"]").setValue("+79011111111");
        $x("//input[@name=\"agreement\"]/..").click();
        $x("//span[contains(text(),\"Забронировать\")]").click();
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + date), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }
}
