package ru.netology.rest;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Date;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class OrderDeliveryTest {
    @Test
    void submitFormPass () {
        open("http://localhost:9999");
        $("[placeholder=\"Город\"]").setValue("Брянск");
        $("[placeholder=\"Дата встречи\"]").setValue("24.10.2022");
        $("[name=\"name\"]").setValue("Петров Петя");
        $("[name=\"phone\"]").setValue("+79001112233");
        $x("//input[@name=\"agreement\"]/..").click();
        $x("//span[contains(text(),\"Забронировать\")]").click();
        $x("//div[contains(text(),\"Успешно!\")]").should(visible, Duration.ofSeconds(15));
    }

        @Test
        void submitFormFail () {
            open("http://localhost:9999");
            $("[placeholder=\"Город\"]").setValue("Брянск");
            $("[placeholder=\"Дата встречи\"]").setValue("24.10.2022");
            $("[name=\"name\"]").setValue("Петров Пётр");
            $("[name=\"phone\"]").setValue("+79011111111");
            $x("//input[@name=\"agreement\"]/..").click();
            $x("//span[contains(text(),\"Забронировать\")]").click();
            $x("//div[contains(text(),\"Успешно!\")]").should(visible, Duration.ofSeconds(15));
        }
}
