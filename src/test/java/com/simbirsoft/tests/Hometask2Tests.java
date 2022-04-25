package com.simbirsoft.tests;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.io.File;
import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.ElementsCollection.texts;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class Hometask2Tests {

    @BeforeAll
    static void beforeAll() {
        Configuration.startMaximized = true;
    }

    @Test
    void fillFormAndSubmit() {
        String firstName = "Alexey";
        String lastName = "Makarov";
        String email = "emaildoesnotexist@nodomain.com";
        String mobilePhone = "7917624862";
        String currentAddress = "14 Imaginary St., Nevercity, Landdoesnotexist";
        String searchMaths = "Ma";
        String searchComputerScience = "Co";
        String searchEnglish = "E";

        open("https://demoqa.com/automation-practice-form");
        $("input#firstName").setValue(firstName);
        $("input#lastName").setValue(lastName);
        $("input#userEmail").setValue(email);
        $x("//label[@for='gender-radio-1']").click();
        $("input#userNumber").setValue(mobilePhone);
        $("input#dateOfBirthInput").click();
        $x("//select[@class='react-datepicker__month-select']").selectOptionByValue("4");
        $x("//select[@class='react-datepicker__year-select']").selectOptionByValue("1992");
        $x("//div[@class='react-datepicker__day react-datepicker__day--011']").click();
        $("input#subjectsInput").setValue(searchMaths);
        $x("//div[@id='react-select-2-option-0']").click();
        $("input#subjectsInput").setValue(searchComputerScience);
        $x("//div[@id='react-select-2-option-0']").click();
        $("input#subjectsInput").setValue(searchEnglish);
        $x("//div[@id='react-select-2-option-0']").click();
        $x("//label[@for='hobbies-checkbox-2']").click();
        $x("//label[@for='hobbies-checkbox-3']").click();
        File file = new File("src/test/java/com/simbirsoft/tests/7dBulQtN5gc.jpg");
        $x("//input[@id='uploadPicture']").uploadFile(file);
        $("textarea#currentAddress").setValue(currentAddress);
        $x("//div[@id='state']").click();
        $x("//div[@id='react-select-3-option-0']").click();
        $x("//div[@id='city']").click();
        $x("//div[@id='react-select-4-option-2']").click();
        $("button#submit").click();

        $x("//div[@class='modal-content']").shouldBe(visible);
        $("div#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $x("//table[@class='table table-dark table-striped table-bordered table-hover']").shouldHave(
                text(firstName + " " + lastName),
                text(email),
                text("Male"),
                text(mobilePhone),
                text("11 May,1992"),
                text("Maths, Computer Science, English"),
                text("Reading, Music"),
                text("7dBulQtN5gc.jpg"),
                text(currentAddress),
                text("NCR Noida")
        );
        $("button#closeLargeModal").shouldBe(visible);

        $("textarea#currentAddress").setValue(currentAddress);
    }
}
