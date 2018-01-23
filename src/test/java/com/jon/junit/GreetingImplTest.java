package com.jon.junit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GreetingImplTest {

    //all test methods should be public void
    //give meaningful names
    //write separate tests for each scenario

    Greeting greeting;

    @Before
    public void setup() {
        greeting = new GreetingImpl();
    }

    @Test
    public void greetShouldReturnAValidOutput() {

        String result = greeting.greet("Junit");
        assertNotNull(result);
        assertEquals("Hello Junit", result);

    }

    @Test(expected = IllegalArgumentException.class)
    public void greetShouldThrowExceptionForNullName() {

        greeting.greet(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void greetShouldThrowExceptionForBlankName() {

        greeting.greet(" ");
    }

    @After
    public void teardown() {
        greeting = null;
    }

}
