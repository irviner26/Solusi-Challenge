package org.binaracademy.challenge4.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String uname;

    @NotBlank
    @Email
    private String gmail;

    private Set<String> role;

    @NotBlank
    @Size(min = 6, max = 20)
    private String password;
}
