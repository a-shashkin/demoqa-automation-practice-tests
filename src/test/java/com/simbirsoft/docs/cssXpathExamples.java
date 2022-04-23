package com.simbirsoft.docs;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class cssXpathExamples {


    void cssXpathExamples() {
        // <input type="email" class="inputtext login_form_input_box" name="email" id="email" data-testid="email">
        // здесь описан идеальный случай, если есть отдельный id именно для автотестов, т.к. и id могут меняться
        // но это надо прям договориться с разработчиками
        $("[data-testid=email]");
        $(by("data-testid", "email"));

        // <input type="email" class="inputtext login_form_input_box" name="email" id="email">
        // а здесь имеем случай, когда не договорились об отдельных id для автотестов
        $("#email");
        $(byId("email")); // это из Selenide
        $(By.id("email")); // это из чистого Selenium
        $("[id=email]");
        $("[id='email']"); // это на случай пробелов
        $("[id=\"email\"]"); // это тоже на случай пробелов, только с двойными кавычками и escape, а то сломается
        $(by("id", "email"));
        $x("//*[@id='email']"); // начался xpath
        $(byXpath("//*[@id='email']")); // тоже xpath, но хардкорнее

        $("input[id=email]"); // здесь конкретизируется тип элемента вместе с его id
        $("input#email]"); // то же самое, что выше

        // <input type="email" class="inputtext login_form_input_box" name="email">
        // а здесь ещё хлеще, когда и id нет
        $("[name=email]");
        $(byName("email"));

        // <input type="email" class="inputtext login_form_input_box">
        // THE PAIN TRAIN HAS NO BREAKS!
        $(byClassName("login_form_input_box"));
        $(".login_form_input_box");
        $(".inputtext.login_form_input_box"); // более конкретная форма предыдущей строки
        $x("//*[@class='login_form_input_box']");

        // <div>Hello qa.guru</div>
        $(byText("Hello qa.guru"));
        $(withText("lo qa.guru"));
    }
}
