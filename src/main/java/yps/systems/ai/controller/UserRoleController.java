package yps.systems.ai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import yps.systems.ai.repository.IUserRoleRepository;

@RestController
@RequestMapping("/userRoleService")
public class UserRoleController {

    @Autowired
    private IUserRoleRepository nodeRepository;

    @PostMapping("/createUserRoleRelation/{idUser},{idRole}")
    public Mono<Boolean> createUserRoleRelation(@PathVariable Long idUser, @PathVariable Long idRole) {
        return nodeRepository.createUserRoleRelation(idUser, idRole)
                .thenReturn(true)
                .onErrorResume(error -> {
                    System.out.println(error.getMessage());
                    return Mono.just(false);
                });
    }

    @GetMapping("/getRolesAssignedToIdUser/{idUser}")
    public Flux<Long> getRolesAssignedToIdUser(@PathVariable Long idUser) {
        return nodeRepository.findRolesByIdUser(idUser)
                .onErrorResume(error -> {
                    System.out.println(error.getMessage());
                    return Mono.just(0L);
                });
    }

    @GetMapping("/getRolesAssignedToIdPerson/{idPerson}")
    public Flux<Long> getRolesAssignedToIdPerson(@PathVariable Long idPerson) {
        return nodeRepository.findRolesByIdPerson(idPerson)
                .onErrorResume(error -> {
                    System.out.println(error.getMessage());
                    return Mono.just(0L);
                });
    }

    @GetMapping("/getUserHasIdRole/{idRole}")
    public Flux<Long> getUserHasIdRole(@PathVariable Long idRole) {
        return nodeRepository.findUsersByIdRole(idRole)
                .onErrorResume(error -> {
                    System.out.println(error.getMessage());
                    return Mono.just(0L);
                });
    }

}
