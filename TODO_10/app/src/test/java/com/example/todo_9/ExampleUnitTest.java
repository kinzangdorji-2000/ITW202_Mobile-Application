package com.example.todo_9;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(JUnit4.class)

public class ExampleUnitTest {
   private Calculator mCalculator;

   @Before //used to test the method from other java class
    public void setUp(){
       mCalculator = new Calculator();
   }
    //addition of two numbers
   @Test
    public void addTwoNumbers(){
       double Result = mCalculator.add(1d, 2d);
       assertThat(Result, (equalTo(3d)));
   }

   @Test
    public void addNegativeNumbers(){
       double Result = mCalculator.add(-1d, 2d);
       assertThat(Result, (equalTo(1d)));
   }

   //subtracting two numbers
   @Test
    public void subTwoNumbers(){
       double Result = mCalculator.sub(3d, 1d);
       assertThat(Result, (equalTo(2d)));
   }

    @Test
    public void subWorksWithTwoNegativeResults(){
        double Result = mCalculator.sub(-3d, -1d);
        assertThat(Result, (equalTo(-2d)));
    }

    //multiplying two numbers
    @Test
    public void mulTwoNumbers(){
        double Result = mCalculator.mul(3d, 1d);
        assertThat(Result, (equalTo(3d)));
    }

    @Test
    public void mulTwoNumbersZero(){
        double Result = mCalculator.mul(0d, 1d);
        assertThat(Result, (equalTo(0d)));
    }

    //dividing two numbers
    @Test
    public void divTwoNumbers(){
        double Result = mCalculator.div(4d, 2d);
        assertThat(Result, (equalTo(2d)));
    }

    @Test //Here any number divided by 0 will be infinity
    public void divTwoNumbersZero(){
        double Result = mCalculator.div(4d, 0d);
        assertThat(Result, (equalTo(Double.POSITIVE_INFINITY)));
    }


}
