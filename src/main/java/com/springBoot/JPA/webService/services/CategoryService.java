package com.springBoot.JPA.webService.services;

import com.springBoot.JPA.webService.entities.Category;
import com.springBoot.JPA.webService.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// PARA QUE O @AUTOWIRED RESOLVA AS QUESTÕES DE DEPENDENCIA QUANDO ESSA CLASSE É INSTANCIADA, USA-SE @Service(de acordo com a finalidade da clasee) PARA REGISTRA-LA NO FRAMEWORK //
@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // PARA RETORNAR TODOS OS USUÁRIOS //
    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public Category findById(Long id) {
        Optional<Category> obj = categoryRepository.findById(id);
        return obj.get();
    }
}
