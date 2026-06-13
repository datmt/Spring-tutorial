package com.datmt.springbootrediscache.session;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserContext implements Serializable {
    private String userId;
    private String username;
    private String email;
    private List<String> roles;        // e.g. ["ROLE_TRADER", "ROLE_VIEWER"]
    private List<String> entitlements; // e.g. ["READ_PORTFOLIO", "EXECUTE_TRADE"]
}
