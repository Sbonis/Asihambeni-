package com.example.asihambeni;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)

public class LoginActivityTest {

    SignInActivity SignIn;

    @Before
    public void setUp() throws Exception {
        SignIn = Robolectric.setupActivity(SignInActivity.class);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() {

        assertNotNull(SignIn);
    }

    @Test
    public void testUser1() {
        SignIn.inputEmailAddress.setText("1442930@students.wits.ac.za");
        SignIn.findViewById(R.id.SignInButton).performClick();
    }

    @Test
    public void testUser2() {
        SignIn.inputEmailAddress.setText("1442930@students.wits.ac.za");
        SignIn.inputPassword.setText("");
        SignIn.findViewById(R.id.SignInButton).performClick();
    }

    @Test
    public void testUser3() {
        SignIn.inputEmailAddress.setText("");
        SignIn.inputPassword.setText("");
        SignIn.findViewById(R.id.SignInButton).performClick();

    }

}
