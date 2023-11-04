package org.binaracademy.challenge4.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    private String token;
    private String type = "Bearer ";
    private String id;
    private String uname;
    private String gmail;
    private List<String> roles;

    public JwtResponse(String jwt, String id, String username, String gmail, List<String> roles) {
        this.token = jwt;
        this.id = id;
        this.uname = username;
        this.gmail = gmail;
        this.roles = roles;
    }
}
