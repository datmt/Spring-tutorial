package com.datmt.springbootrediscache.session;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/session")
public class SessionController {

    private final UserContextCache cache;

    public SessionController(UserContextCache cache) {
        this.cache = cache;
    }

    /**
     * Try:
     *   GET /session/token-alice    -> 400ms (IAM hit)
     *   GET /session/token-alice    -> <5ms  (Redis hit)
     *   DELETE /session/token-alice -> evict
     *   GET /session/token-alice    -> 400ms again
     *
     * Valid tokens: token-alice, token-bob, token-admin
     */
    @GetMapping("/{token}")
    public ResponseEntity<UserContext> getSession(@PathVariable String token) {
        UserContext ctx = cache.getUserContext(token);
        if (ctx == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ctx);
    }

    @DeleteMapping("/{token}")
    public String logout(@PathVariable String token) {
        cache.evict(token);
        return "Session evicted: " + token;
    }
}
