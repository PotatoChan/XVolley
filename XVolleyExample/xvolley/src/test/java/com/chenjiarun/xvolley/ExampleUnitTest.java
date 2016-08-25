package com.chenjiarun.xvolley;

import com.google.gson.Gson;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {


        Map<String, String> s = new HashMap<String, String>();

//        s.put("hello", "world");

        Gson gson = new Gson();

        String str = gson.toJson(s);

        System.out.println("Potato:" + str);




    }
}