package dev.ua.ikeepcalm.chessupnow.services;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Component
public class IdGenerator {

    private static final int CODE_LENGTH = 5;
    Set<String> usedCodes = new HashSet<>();

    public Long generateUniqueCode() {
        Random random = new Random();
        String uniqueCode;
        do {
            uniqueCode = generateRandomCode(random);
        } while (usedCodes.contains(uniqueCode));
        usedCodes.add(uniqueCode);
        return Long.valueOf(uniqueCode);
    }

    private String generateRandomCode(Random random) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < CODE_LENGTH; i++) {
            int digit = random.nextInt(10);
            sb.append(digit);
        } return sb.toString();
    }
}
