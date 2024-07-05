package com.login.springlogin.servicesImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.login.springlogin.DTO.UserDTO;
import com.login.springlogin.models.User;
import com.login.springlogin.repositories.UserRepository;
import com.login.springlogin.services.UserService;
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    @Transactional(readOnly = true)
    public User findByEmail(String email){
        return userRepository.findByEmail(email).orElse(null);
    }
    @Override
    @Transactional
    public User save(UserDTO userDTO){
        //Debemos encriptar la contraseña antes de guardarla
        String passwordString = userDTO.getPassword();
        String passwordEncoded = passwordEncoder.encode(passwordString);
        userDTO.setPassword(passwordEncoded);
        User user = new User(userDTO.getNombre(), userDTO.getPassword(),userDTO.getEmail());
        return userRepository.save(user);
    }
    public User update(User user){
        return userRepository.save(user);
    }
    public void delete(User user){
        userRepository.delete(user);
        return;
    }
}
