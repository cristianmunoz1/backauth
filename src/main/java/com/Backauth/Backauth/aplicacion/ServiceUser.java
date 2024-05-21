package com.Backauth.Backauth.aplicacion;


import com.Backauth.Backauth.core.UserRepository;
import com.Backauth.Backauth.core.dominio.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



import java.util.Optional;

@Service
public class ServiceUser {
    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUser(String userId)
    {
        return userRepository.getUser(userId);
    }

    public ResponseEntity<User> save(User user)
    {
        if(!userRepository.getUser(user.getUserId()).isPresent()){
            return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    public User updateUser(User user) {

        Optional<User> userOptionalExist = getUser(user.getUserId());
        if (userOptionalExist.isEmpty()) {
            throw new IllegalArgumentException("El usuario no existe en la base de datos.");
        }

        User existingUser = userOptionalExist.get();

        if (user.getUserName() != null) {
            existingUser.setUserName(user.getUserName());
        }

        if (user.getUserLastname() != null) {
            existingUser.setUserLastname(user.getUserLastname());
        }

        if (user.getUserPhoneNumber() != null) {
            existingUser.setUserPhoneNumber(user.getUserPhoneNumber());
        }

        if (user.getUserEmail() != null) {
            existingUser.setUserEmail(user.getUserEmail());
        }

        return userRepository.save(existingUser);
    }

    public User updateUserRole(String id, int role){
        Optional<User> userOptionalExist = getUser(id);
        if (userOptionalExist.isEmpty()) {
            throw new IllegalArgumentException("El usuario no existe en la base de datos.");
        }

        if ((role != 100)&&(role != 101)&&(role != 102)){
            throw new IllegalArgumentException("El role que ingresó no está definido en el sistema.");
        }

        User existingUser = userOptionalExist.get();

        existingUser.setUserRole(role);

        return userRepository.save(existingUser);
    }

    public User updatePassword(User user)
    {
        return userRepository.updatePassword(user);
    }

    public boolean delete(String userId)
    {
        return getUser(userId).map(user -> {
            userRepository.delete(userId);
            return true;
        }).orElse(false);
    }

    public Iterable<User> getAllUsers(){
        return userRepository.findAllUsers();
    }

    public Optional<User> getUserEmail(String userEmail)
    {
        return userRepository.findByUserEmail(userEmail);
    }
}
