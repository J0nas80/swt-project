package de.fh_dortmund.swt2.backend.security;

import de.fh_dortmund.swt2.backend.model.AppUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.util.Collection;
import java.util.Collections;

public class AppUserDetails implements UserDetails, Principal {

    private final AppUser user;

    public AppUserDetails(AppUser user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList(); 
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail(); // Spring verwendet "username", aber du gibst E-Mail zurück
                                /*getUsername ist vorgeschrieben von Spring Security, 
                                Allerdings verwenden wir die Email zur Authentifizierung und so habe ich passend implementiert  */ 
    }

    @Override
    public String getName() {
        return String.valueOf(user.getId()); // macht Principal.getName() = ID, die im WebSocketController nötig ist
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
        return user.isVisible();
    }

    public AppUser getAppUser() {
        return user;
    }
}
