package org.binaracademy.challenge4.service;

import org.binaracademy.challenge4.model.User;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public interface UserService {

    CompletableFuture<Boolean> userIsExistAndCorrect(String username, String email);

    // Method berikut ini berfungsi untuk menambahkan user:
    CompletableFuture<Boolean> addUser(User user);
    // Method berikut berfungsi untuk mengupdate user:
    CompletableFuture<Boolean> updateUsername(String newUname, String oldUname, String email);
    CompletableFuture<Boolean> updatePassword(String newPass, String name, String email);
    CompletableFuture<Boolean> updateEmail(String newGmail, String name, String oldEmail);

    // Method ini berfungsi untuk menghapus user:
    CompletableFuture<Boolean> deleteUser(String uname, String password, String email);

    User getUserByName(String name);
}
