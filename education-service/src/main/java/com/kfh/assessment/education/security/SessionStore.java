package com.kfh.assessment.education.security;

import org.springframework.stereotype.Component;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionStore {

    private final ConcurrentHashMap<String, String> activeTokens = new ConcurrentHashMap<>();

    public void addToken(String username, String token) {
        activeTokens.put(username, token);
    }

    public void removeToken(String username) {
        activeTokens.remove(username);
    }

    public boolean isTokenActive(String username, String token) {
        return token.equals(activeTokens.get(username));
    }
}
