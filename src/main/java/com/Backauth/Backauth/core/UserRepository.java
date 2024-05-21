package com.Backauth.Backauth.core;


import com.Backauth.Backauth.core.dominio.User;
import com.Backauth.Backauth.core.interfaces.UserCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository{
    @Autowired
    private UserCrudRepository userCrudRepository;

    public Iterable<User> findAllUsers(){
        return userCrudRepository.findAll();
    }
    public Optional<User> getUser(String userId)
    {
        return userCrudRepository.findById(userId);
    }

    public User save(User user)
    {
        return (User) userCrudRepository.save(user);
    }

    public User updateUser(User user)
    {
        Optional<User> userOptionalExist = getUser(user.getUserId());
        if (userOptionalExist.isPresent()){
            User existUser = userOptionalExist.get();
            if (userOptionalExist.get().getUserEmail().equals(user.getUserEmail())){
                existUser.setUserName(user.getUserName());
                existUser.setUserLastname(user.getUserLastname());
                existUser.setUserPhoneNumber(user.getUserPhoneNumber());
                return userCrudRepository.save(existUser);
            }else{
                existUser.setUserName(user.getUserName());
                existUser.setUserLastname(user.getUserLastname());
                existUser.setUserEmail(user.getUserEmail());
                existUser.setUserPhoneNumber(user.getUserPhoneNumber());
                return userCrudRepository.save(existUser);
            }
        }
        return null;
    }

    public void delete(String userId)
    {
        userCrudRepository.deleteById(userId);
    }

    public Optional<User> findByUserEmail(String userEmail) {
        return userCrudRepository.findByUserEmail(userEmail);
    }

    public User updatePassword(User user)
    {
        return userCrudRepository.save(user);
    }

}
