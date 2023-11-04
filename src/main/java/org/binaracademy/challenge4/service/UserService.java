package org.binaracademy.challenge4.service;

import org.binaracademy.challenge4.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    boolean userIsExistAndCorrect(String username, String email);

    // Method berikut ini berfungsi untuk menambahkan user:
    boolean addUser(User user);
    // Method berikut berfungsi untuk mengupdate user:
    boolean updateUsername(String newUname, String oldUname, String email);
    boolean updatePassword(String newPass, String name, String email);
    boolean updateEmail(String newGmail, String name, String oldEmail);

    // Method ini berfungsi untuk menghapus user:
    boolean deleteUser(String uname, String password, String email);

    User getUserByName(String name);
}
