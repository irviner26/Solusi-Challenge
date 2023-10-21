package org.binaracademy.challenge4.service;

import lombok.extern.slf4j.Slf4j;
import org.binaracademy.challenge4.model.User;
import org.binaracademy.challenge4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean userIsExistAndCorrect(String username, String email) {
        try {
            log.info("Finding user with username {} and email {}", username, email);
            User foundUserByName = userRepository.queryFindUserByName(username);
            User foundUserByEmail = userRepository.queryFindUserByEmail(email);
            return (foundUserByName != null && foundUserByEmail != null) && foundUserByEmail.equals(foundUserByName);
        } catch (Exception e) {
            log.error("User not found.");
            log.error("Cause: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean addUser(User user) {
        try {
            log.info("Adding user to database...");
            userRepository.save(Objects.requireNonNull(Optional.ofNullable(user)
                    .filter(val -> val.getUname() != null && val.getPassword() != null && val.getGmail() != null)
                    .orElse(null)));
            log.info("User (username: {}) added successfully.", user.getUname());
            return true;
        } catch (Exception e) {
            log.error("User cannot be added");
            return false;
        }
    }

    @Override
    public boolean updateUsername(String newUname, String oldUname, String email) {
        // Cek jika user sudah benar ada atau tidak
        boolean foundUser = this.userIsExistAndCorrect(oldUname, email);
        if (foundUser) {
            log.info("User {} found", oldUname);
            log.info("Updating new username...");
            userRepository.queryUpdateUsername(newUname, oldUname);
            log.info("Successfully changed {} -> {}", oldUname, newUname);
            return true;
        }
        else {
            log.error("User with username {} and email {} is incorrect", oldUname, email);
            return false;
        }
    }

    @Override
    public boolean updatePassword(String newPass, String name, String email) {
        boolean foundUser = this.userIsExistAndCorrect(name, email);
        if (foundUser) {
            log.info("User {} found", name);
            log.info("Updating new password...");
            userRepository.queryUpdatePassword(newPass,name);
            log.info("Successfully changed {} password", name);
            return true;
        }
        else {
            log.error("User with username {} and email {} is incorrect", name, email);
            return false;
        }
    }

    @Override
    public boolean updateEmail(String newGmail, String name, String oldEmail) {
        boolean foundUser = this.userIsExistAndCorrect(name, oldEmail);
        if (foundUser) {
            log.info("User {} found", name);
            log.info("Updating new gmail...");
            userRepository.queryUpdateEmail(newGmail, name);
            log.info("Successfully changed {} email", name);
            return true;
        }
        else {
            log.error("User with username {} and email {} is incorrect", name, oldEmail);
            return false;
        }
    }

    @Override
    public boolean deleteUser(String name, String password, String email) {
        boolean foundUser = this.userIsExistAndCorrect(name, email);
        if (foundUser) {
            log.info("User {} found", name);
            log.info("Deleting user {} ...", name);
            userRepository.queryDeleteUser(name,password,email);
            log.info("Successfully deleted user {}", name);
            return true;
        }
        else {
            log.error("User with username {} and email {} is incorrect", name, email);
            return false;
        }
    }

    @Override
    public User getUserByName(String name) {
        if (Objects.nonNull(name)) return userRepository.queryFindUserByName(name);
        else return null;
    }


}
