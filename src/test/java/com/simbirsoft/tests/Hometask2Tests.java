package com.simbirsoft.tests;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.io.File;
import java.lang.reflect.Array;
import java.time.Duration;
import java.util.Arrays;

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
        String searchMaths = "Maths";
        String searchComputerScience = "Computer Science";
        String searchEnglish = "English";

        open("https://demoqa.com/automation-practice-form");
        $("input#firstName").setValue(firstName);
        $("input#lastName").setValue(lastName);
        $("input#userEmail").setValue(email);
        $x("//label[@for='gender-radio-1']").click();
        String selectedGender = $x("//input[@id='gender-radio-1']").getValue();
        $("input#userNumber").setValue(mobilePhone);
        $("input#dateOfBirthInput").click();
        $x("//select[@class='react-datepicker__month-select']").selectOptionByValue("4");
        $x("//select[@class='react-datepicker__year-select']").selectOptionByValue("1992");
        $x("//div[@class='react-datepicker__day react-datepicker__day--011']").click();
        /*
        В этом месте я думал, что проверить надо таким образом: фрагмент названия предмета вставить, а потом нажать на элементе из списка.
        Благо в списке подходящих по фильтру элементов у div'ов уникальные id. Так совпало, что нужный предмет всегда шёл первым.
        Когда по другому поводу смотрел разбор ДЗ, разузнал, что можно гораздо проще.
        Свой старый код закомментил для истории.

        $("input#subjectsInput").setValue(searchMaths);
        $x("//div[@id='react-select-2-option-0']").click();
        $("input#subjectsInput").setValue(searchComputerScience);
        $x("//div[@id='react-select-2-option-0']").click();
        $("input#subjectsInput").setValue(searchEnglish);
        $x("//div[@id='react-select-2-option-0']").click();*/

        $("input#subjectsInput").setValue(searchMaths).pressEnter();
        $("input#subjectsInput").setValue(searchComputerScience).pressEnter();
        $("input#subjectsInput").setValue(searchEnglish).pressEnter();
        $x("//label[@for='hobbies-checkbox-2']").click();
        $x("//label[@for='hobbies-checkbox-3']").click();
        String selectedHobbies = $x("//label[@for='hobbies-checkbox-2']").getText()
                + ", "
                + $x("//label[@for='hobbies-checkbox-3']").getText();
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
                text(selectedGender),
                text(mobilePhone),
                text("11 May,1992"), // через переменную не удалось сделать, т.к. если брать значение, там вместо запятой ещё пробел
                        // и я не смог найти рабочий способ заменить второй пробел на запятую
                text(searchMaths + ", " + searchComputerScience + ", " + searchEnglish),
                text(selectedHobbies),
                text("7dBulQtN5gc.jpg"),
                text(currentAddress),
                text("NCR Noida") // тоже хотел сделать String, где два значения из выпадашек склеились бы в один String
                                  // но не нашёл такого в видео с разбором :(
        );
        $("button#closeLargeModal").shouldBe(visible);
    }
}

