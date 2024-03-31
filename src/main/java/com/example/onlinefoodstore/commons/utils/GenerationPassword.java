package com.example.onlinefoodstore.commons.utils;

import java.util.UUID;

public class GenerationPassword {
    public static String generationPassword() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 9);
    }
}