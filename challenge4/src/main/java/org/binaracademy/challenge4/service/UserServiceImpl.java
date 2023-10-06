package org.binaracademy.challenge4.service;

import lombok.extern.slf4j.Slf4j;
import org.binaracademy.challenge4.model.User;
import org.binaracademy.challenge4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public void addUser(User user) {
        userRepository.save(Optional.ofNullable(user)
                .filter(val -> val.getUname() != null && val.getPassword() != null && val.getGmail() != null)
                .orElse(User.builder()
                        .uname("DELETETHIS0")
                        .build()));
        try {
            this.deleteUser("DELETETHIS0");
        } catch (Exception e) {
            log.info("Succesfully added to database");
        }
    }

    @Override
    public void updateUsername(String newUname, String oldUname) {
        userRepository.queryUpdateUsername(newUname, oldUname);
    }

    @Override
    public void updatePassword(String newPass, String name) {
        userRepository.queryUpdatePassword(newPass, name);
    }

    @Override
    public void updateEmail(String newGmail, String name) {
        userRepository.queryUpdateEmail(newGmail, name);
    }

    @Override
    public void deleteUser(String name) {
        userRepository.queryDeleteByName(name);
    }

    @Override
    public User getUserByName(String name) {
        return userRepository.queryFindUserByName(name);
    }


}
