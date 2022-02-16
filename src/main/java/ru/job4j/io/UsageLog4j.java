package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        boolean answer = true;
        byte count = 120;
        short number = 1205;
        int distance = 123456789;
        long longDistance = 987654321123456789L;
        float size = 12.12f;
        double bigSize = 15648.245445454454;
        char symbol = 'a';
        LOG.debug(
            "Values of primitive types answer: {}, count : {}, number : {}, distance : {}, "
                + "longDistance : {}, size : {}, bigSize : {}, symbol : {}",
            answer, count, number, distance, longDistance, size, bigSize, symbol);
    }
}