/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.domain.services;

import co.edu.unicauca.domain.entities.User;

/**
 *
 * @author Brayan
 */
public class UserService {

    private IRepository userRepository;

    public UserService(IRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(String username, String password) {
        User user = (User) userRepository.found(username);
        return (user != null) ? user : null;
    }

}
