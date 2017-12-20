package com.hw.util.security;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class TokenHandlerTest {

    private static TokenHandler tokenHandler;
    private static Map<String, Object> testingObject;

    @BeforeClass
    public static void init() {
        tokenHandler = new TokenHandler();
        testingObject = Collections.singletonMap("message", "OK");
    }

    @Test
    public void encodeDecodeTest() throws Exception {
        String token = tokenHandler.encode(testingObject);
        Map<String, Object> decodeObject = tokenHandler.decode(token);
        Assert.assertEquals(testingObject, decodeObject);
    }

}