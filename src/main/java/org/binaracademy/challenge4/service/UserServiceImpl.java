package org.binaracademy.challenge4.service;

import lombok.extern.slf4j.Slf4j;
import org.binaracademy.challenge4.model.User;
import org.binaracademy.challenge4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Async
    @Transactional(readOnly = true)
    @Override
    public CompletableFuture<Boolean> userIsExistAndCorrect(String username, String email) {
        try {
            log.info("Finding user with username {} and email {}", username, email);
            User foundUserByName = userRepository.queryFindUserByName(username);
            User foundUserByEmail = userRepository.queryFindUserByEmail(email);
            return CompletableFuture.supplyAsync(() -> (foundUserByName != null && foundUserByEmail != null) && foundUserByEmail.equals(foundUserByName));
        } catch (Exception e) {
            log.error("User not found.");
            log.error("Cause: " + e.getMessage());
            return CompletableFuture.supplyAsync(() -> Boolean.FALSE);
        }
    }

    //TODO: Replace by signup
    @Async
    @Override
    public CompletableFuture<Boolean> addUser(User user) {
        try {
            log.info("Adding user to database...");
            userRepository.save(Objects.requireNonNull(Optional.ofNullable(user)
                    .filter(val -> val.getUname() != null && val.getPassword() != null && val.getGmail() != null)
                    .orElse(null)));
            log.info("User (username: {}) added successfully.", user.getUname());
            return CompletableFuture.supplyAsync(() -> Boolean.TRUE);
        } catch (Exception e) {
            log.error("User cannot be added");
            return CompletableFuture.supplyAsync(() -> Boolean.FALSE);
        }
    }

    @Async
    @Transactional
    @Override
    public CompletableFuture<Boolean> updateUsername(String newUname, String oldUname, String email) {
        // Cek jika user sudah benar ada atau tidak
        CompletableFuture<Boolean> foundUser = this.userIsExistAndCorrect(oldUname, email);
        try {
            if (foundUser.get()) {
                log.info("User {} found", oldUname);
                log.info("Updating new username...");
                userRepository.queryUpdateUsername(newUname, oldUname);
                log.info("Successfully changed {} -> {}", oldUname, newUname);
                return CompletableFuture.supplyAsync(() -> Boolean.TRUE);
            }
            else {
                log.error("User with username {} and email {} is incorrect", oldUname, email);
                return CompletableFuture.supplyAsync(() -> Boolean.FALSE);
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Async
    @Transactional
    @Override
    public CompletableFuture<Boolean> updatePassword(String newPass, String name, String email) {
        CompletableFuture<Boolean> foundUser = this.userIsExistAndCorrect(name, email);
        try {
            if (foundUser.get()) {
                log.info("User {} found", name);
                log.info("Updating new password...");
                userRepository.queryUpdatePassword(passwordEncoder.encode(newPass),name);
                log.info("Successfully changed {} password", name);
                return CompletableFuture.supplyAsync(() -> Boolean.TRUE);
            }
            else {
                log.error("User with username {} and email {} is incorrect", name, email);
                return CompletableFuture.supplyAsync(() -> Boolean.FALSE);
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Async
    @Transactional
    @Override
    public CompletableFuture<Boolean> updateEmail(String newGmail, String name, String oldEmail) {
        CompletableFuture<Boolean> foundUser = this.userIsExistAndCorrect(name, oldEmail);
        try {
            if (foundUser.get()) {
                log.info("User {} found", name);
                log.info("Updating new gmail...");
                userRepository.queryUpdateEmail(newGmail, name);
                log.info("Successfully changed {} email", name);
                return CompletableFuture.supplyAsync(() -> Boolean.TRUE);
            }
            else {
                log.error("User with username {} and email {} is incorrect", name, oldEmail);
                return CompletableFuture.supplyAsync(() -> Boolean.FALSE);
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Async
    @Transactional
    @Override
    public CompletableFuture<Boolean> deleteUser(String name, String password, String email) {
        CompletableFuture<Boolean> foundUser = this.userIsExistAndCorrect(name, email);
        try {
            if (foundUser.get()) {
                log.info("User {} found", name);
                log.info("Deleting user {} ...", name);
                userRepository.queryDeleteUser(name,password,email);
                log.info("Successfully deleted user {}", name);
                return CompletableFuture.supplyAsync(() -> Boolean.TRUE);
            }
            else {
                log.error("User with username {} and email {} is incorrect", name, email);
                return CompletableFuture.supplyAsync(() -> Boolean.FALSE);
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserByName(String name) {
        if (Objects.nonNull(name)) return userRepository.queryFindUserByName(name);
        else return null;
    }


}
