package com.simbirsoft.docs;

import org.junit.jupiter.api.*;

public class JUnit5ExamplesTest {

    @BeforeAll
    static void beforeAll() {
        System.out.println("Before all tests\n");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("Before test\n");
    }

    @Test
    void firstTest() {
        System.out.println("I love SimbirSoft\n");
    }


    @AfterEach
    void afterEach() {
        System.out.println("After test\n");
    }

    @Test
    void secondTest() {
        System.out.println("I really love SimbirSoft\n");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After all tests\n");
    }

}