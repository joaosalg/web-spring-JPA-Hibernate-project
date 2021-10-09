package com.springBoot.JPA.webService.services;

import com.springBoot.JPA.webService.entities.User;
import com.springBoot.JPA.webService.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// PARA QUE O @AUTOWIRED RESOLVA AS QUESTÕES DE DEPENDENCIA QUANDO ESSA CLASSE É INSTANCIADA, USA-SE @Service(de acordo com a finalidade da clasee) PARA REGISTRA-LA NO FRAMEWORK //
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // PARA RETORNAR TODOS OS USUÁRIOS //
    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(Long id) {
        Optional<User> obj = userRepository.findById(id);
        return obj.get();
    }
}
