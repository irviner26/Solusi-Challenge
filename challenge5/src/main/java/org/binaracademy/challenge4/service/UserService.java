package org.binaracademy.challenge4.service;

import org.binaracademy.challenge4.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    // Method berikut ini berfungsi untuk menambahkan user:
    void addUser(User user);

    // Method berikut berfungsi untuk mengupdate user:
    void updateUsername(String newUname, String oldUname);
    void updatePassword(String newPass, String name);
    void updateEmail(String newGmail, String name);

    // Method ini berfungsi untuk menghapus user:
    void deleteUser(String uname);

    User getUserByName(String name);
}
