package com.springBoot.JPA.webService.resources;

import com.springBoot.JPA.webService.entities.User;
import com.springBoot.JPA.webService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

// CLASSE CONTROLADOR REST FAZ CONEXÃO COM A APPLICAÇÃO BACK END PARA FRONT //
// É ATRAVÉS DELA QUE CONSEGUIMOS CONECTAR E FAZER PROCESSOS DOS DADOS NO LOCALHOST E POSTMAN //
@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService service;

    // PARA RETORNAR RESPOSTAS DE REQUISÃO TIPO WEB = REPSPONSE ENTITY //
    // ENDPOINT GETMAPPING - SERVE PARA RECUPERAR DADOS DO BANCO DE DADOS //
    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        List<User> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    // @PathVariable = RECONHCER O LONG ID COMO VARIÁVEL DA URL //
    // (value = "/{id]") = QUANDO FOR RECUPERAR OS DADOS É NECESSÁRIO DO ID //
    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        User obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    // PARA INSERIR UM NOVO RECURSO - MÉTODO HTTP POST - PARA QUE O OBJ USER CHEGUE AO JSON E SEJA DESCERIALIZADO PARA UM OBJ USER =@REQUESTBODY //
    // CREATED SERVE PARA QUE NO TEST DO POSTMAN AO INSERIR O STATUS DO PROCESSO VENHA 201 OK PORÉM PRECISA DESSA URI ACIMA//
    @PostMapping
    public ResponseEntity<User> insert(@RequestBody User obj){
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    // ENDPOINT PARA DELETAR //
    // VOID PQ A RESPOSTA DESSA REQUISIÇÃO NÃO VAI RETORNAR NEMHUM CORPO(VALOR) POR ISSO USA-SE O NO CONTENT //
    // @PathVariable = RECONHCER O LONG ID COMO VARIÁVEL DA URL //
    // (value = "/{id]") = QUANDO FOR DELETAR É NECESSÁRIO DO ID //
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // ENDPOINT PARA ATUALIZAR OS DADOS //
    // PARA QUE O OBJ USER CHEGUE AO JSON E SEJA DESCERIALIZADO PARA UM OBJ USER =@REQUESTBODY //
    // @PathVariable = RECONHCER O LONG ID COMO VARIÁVEL DA URL //
    // (value = "/{id]") = QUANDO FOR ATUALIZAR É NECESSÁRIO DO ID //
    @PutMapping(value = "/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj) {
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }

}
