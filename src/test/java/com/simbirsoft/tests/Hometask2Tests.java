package com.simbirsoft.tests;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class Hometask2Tests {

    @BeforeAll
    static void beforeAll() {
        Configuration.startMaximized = true;
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void fillFormAndSubmit() {

        open("/automation-practice-form");
        $("input#firstName").setValue("Alexey");
        $("input#lastName").setValue("Makarov");
        $("input#userEmail").setValue("emaildoesnotexist@nodomain.com");
        $("input#gender-radio-1").parent().click();
        $("input#userNumber").setValue("7917624862");
        $("input#dateOfBirthInput").click();
        $("select.react-datepicker__month-select").selectOption("May");
        $("select.react-datepicker__year-select").selectOption("1992");
        $("div.react-datepicker__day--011:not(.react-datepicker__day--outside-month)").click();
        $("input#subjectsInput").setValue("Maths").pressEnter();
        $("input#subjectsInput").setValue("Computer Science").pressEnter();
        $("input#subjectsInput").setValue("English").pressEnter();
        $("#hobbiesWrapper").$(byText("Reading")).click();
        $("#hobbiesWrapper").$(byText("Music")).click();
        $("input#uploadPicture").uploadFromClasspath("img/7dBulQtN5gc.jpg");
        $("textarea#currentAddress").setValue("14 Imaginary St., Nevercity, Landdoesnotexist");
        $("div#state").click();
        $("div#stateCity-wrapper").$(byText("NCR")).click();
        $("div#city").click();
        $("div#stateCity-wrapper").$(byText("Noida")).click();
        $("button#submit").click();

        $("div.modal-content").shouldBe(visible);
        $("div#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $("div.table-responsive").$(byText("Student Name")).parent().shouldHave(text("Alexey Makarov"));
        $("div.table-responsive").$(byText("Student Email")).parent().shouldHave(text("emaildoesnotexist@nodomain.com"));
        $("div.table-responsive").$(byText("Gender")).parent().shouldHave(text("Male"));
        $("div.table-responsive").$(byText("Mobile")).parent().shouldHave(text("7917624862"));
        $("div.table-responsive").$(byText("Date of Birth")).parent().shouldHave(text("11 May,1992"));
        $("div.table-responsive").$(byText("Subjects")).parent().shouldHave(text("Maths, Computer Science, English"));
        $("div.table-responsive").$(byText("Hobbies")).parent().shouldHave(text("Reading, Music"));
        $("div.table-responsive").$(byText("Picture")).parent().shouldHave(text("7dBulQtN5gc.jpg"));
        $("div.table-responsive").$(byText("Address")).parent().shouldHave(text("14 Imaginary St., Nevercity, Landdoesnotexist"));
        $("div.table-responsive").$(byText("State and City")).parent().shouldHave(text("NCR Noida"));
        $("button#closeLargeModal").shouldBe(visible);
    }
}

