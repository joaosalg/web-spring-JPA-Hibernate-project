package com.springBoot.JPA.webService.services;

import com.springBoot.JPA.webService.entities.User;
import com.springBoot.JPA.webService.repositories.UserRepository;
import com.springBoot.JPA.webService.services.exceptions.ResourceNotFoundException;
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

    // .ORELSETHROW VAI TENTAR DAR O GET PORÉM SE NÃO TIVER USUÁRIO ELE LANÇARÁ UMA EXCEÇÃO //
    public User findById(Long id) {
        Optional<User> obj = userRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    // MÉTODO PARA INSERÇÃO DE USER - ESSE MÉTODO DO REPOSITORY JÁ RETORNA O OBJ SALVO //
    public User insert(User obj){
        return userRepository.save(obj);
    }

    //MÉTODO DE DELEÇÃO DE USER //
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    // PARA ATUALIZAR - UTILIZAR ID PARA IDENTIFICAR E USER OBJ COM OS DADOS A SEREM ATUALIZADOS //
    // GET ONE NÃO VASCULHA O BANCO DE DADOS, ELE PREPARA UM OBJ MONITORADO PARA DEPOIS VOCÊ MEXER NELE //
    public User update(Long id, User obj){
        User entity = userRepository.getOne(id);
        updateData(entity, obj);
        return userRepository.save(entity);
    }

    // MÉTODO PARA ATUALIZAR OS MÉTODOS DO ENTITY OU SEJA OS DADOS QUE ESTÃO NOS BANCOS DE DADOS COM O QUE CHEGOU NO OBJ //
    // NÃO IRÁ ATUALIZAR O ID E NEM A SENHA //
    private void updateData(User entity, User obj) {
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setPhone(obj.getPhone());
    }


}
