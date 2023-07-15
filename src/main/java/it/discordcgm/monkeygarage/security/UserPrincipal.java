package it.discordcgm.monkeygarage.security;

import com.fasterxml.jackson.annotation.JsonIgnore;

import it.discordcgm.monkeygarage.model.entity.Ruolo;
import it.discordcgm.monkeygarage.model.entity.User;
import it.discordcgm.monkeygarage.model.payload.response.JwtAuthenticationResponse;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserPrincipal implements UserDetails {
	
	private final long id;
	private final String username;
	private final String email;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;
    private final boolean enabled;

    public UserPrincipal(
            long id,
            String username,
            String email,
            String password,
            Collection<? extends GrantedAuthority> authorities,
            Boolean enabled
    ) {
    	this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.enabled = enabled;
    }
    
    public static UserPrincipal create(User user) {
        List<GrantedAuthority> authorities = user.getRuoli().stream().map(role ->
                new SimpleGrantedAuthority(role.getNome())
        ).collect(Collectors.toList());

        return new UserPrincipal(
            user.getId(),
            user.getNome(),
            user.getEmail(),
            user.getPassword(),
            authorities,
            user.isAbilitato()
        );
    }
    
    public User createUserFromUserPrincipal(UserPrincipal up) {
        User u = new User();
        u.setId(up.getId());
        u.setNome(up.getUsername());
        u.setEmail(up.getEmail());
        u.setRuoli(up.getAuthorities().stream().map(a -> new Ruolo(a.getAuthority())).collect(Collectors.toSet()));
	    return u;
    }
    
    public static JwtAuthenticationResponse createJwtAuthenticationResponseFromUserPrincipal(UserPrincipal up, String token) {
		return new JwtAuthenticationResponse(
            up.getId(),
            up.getUsername(),
            up.getEmail(),
            up.getAuthorities().stream().map(a -> a.getAuthority()).collect(Collectors.toSet()),
            token
            );
	}
    
    public long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	@Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
    
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    private static final long serialVersionUID = -3186064719257201546L;

}
   