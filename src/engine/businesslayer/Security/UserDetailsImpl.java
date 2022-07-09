package engine.businesslayer.Security;


import engine.persistencelayer.UserCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {

    String username;
    String password;
    Set<Long> quizIds;
    List<GrantedAuthority> grantedAuthorities;

    public boolean addQuizId(Long id) {
        return quizIds.add(id);
    }

    public UserDetailsImpl(User user) {
        this.password = user.getPassword();
        this.username = user.getEmail();
        this.quizIds = user.getQuizIds();
        this.grantedAuthorities = user.getRole()
                .stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    public boolean isAuthorOfQuiz(long id) {
        return quizIds.contains(id);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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