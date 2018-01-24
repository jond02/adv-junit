package com.jon.scrapbook;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

public class ATest {

    @Mock
    private B b;

    private A a;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        a = new A(b);
    }

    @Test
    public void usesVoidMethodShouldCallVoidMethod() throws Exception {
        a.usesVoidMethod();
        verify(b).voidMethod();
    }

}
