package com.ozan.be.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ozan.be.token.Token;
import com.ozan.be.token.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.Principal;
import java.util.List;


@RequiredArgsConstructor
@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {

        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        // check if the current password is correct
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }
        // check if the two new passwords are the same
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new IllegalStateException("Password are not the same");
        }

        // update the password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        // save the new password
        repository.save(user);
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public User getUser(String email){
        // only allows acces from admin panel so return type is not optional
        return repository.findByEmail(email).get();
    }



    public User getUserDetails(Integer userID) {
        return repository.findUserById(userID).orElse(null);
        /*

        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String tokenString = authorizationHeader.substring(7); // "Bearer ".length() = 7
            Token token = tokenRepository.findByToken(tokenString).orElse(null);

            assert token != null;
            if (token.getUser().getId().equals(userID)){
                User user = repository.findUserById(userID).orElse(null);
                new ObjectMapper().writeValue(response.getOutputStream(), user);
            }
        }
        */
    }

    public List<User> getUserSearchResult(String searchWord){
        return repository.findUsersByEmailNameOrSurnameContainingIgnoreCase(searchWord);
    }

    public List<User> getManagerUsers(){
        return repository.findUsersByRoleIsNot(Role.USER);
    }
}
