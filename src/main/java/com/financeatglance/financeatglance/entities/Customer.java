package com.financeatglance.financeatglance.entities;

import com.financeatglance.financeatglance.pojos.Role;
import com.financeatglance.financeatglance.validations.StrongPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static com.financeatglance.financeatglance.constants.ValidationMessages.*;

@Data
@Document(collection = "customers")
public class Customer implements UserDetails {
    @Id
    private String id;
    @NotBlank(message = BLANK_FIELD)
    @Pattern(regexp = "^[a-zA-Z]+$", message = CONTAIN_ONLY_LETTERS)
    private String firstName;
    @NotBlank(message = BLANK_FIELD)
    @Pattern(regexp = "^[a-zA-Z]+$", message = CONTAIN_ONLY_LETTERS)
    private String lastName;

    @Email(message = INVALID_EMAIL)
    @NotBlank(message = BLANK_FIELD)
    @Indexed(unique = true)
    private String email;

    @StrongPassword(message = BLANK_FIELD)
    private String password;

    private Role role;

    @DBRef
    private List<Dividend> dividends;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());
        return Collections.singleton(authority);
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
