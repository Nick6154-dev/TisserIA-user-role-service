package yps.systems.ai.repository;

import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import yps.systems.ai.model.UserRole;

@Repository
public interface IUserRoleRepository extends ReactiveNeo4jRepository<UserRole, Long> {

    @Query("MATCH (u: user {id_user: $idUser}) WITH u " +
            "MATCH (r: role {id_role: $idRole}) " +
            "MERGE (u)-[:HAS_ROLE]->(r) " +
            "MERGE (r)-[:IS_ASSIGNED_TO]->(u)")
    Mono<Void> createUserRoleRelation(Long idUser, Long idRole);

    @Query("MATCH (u: user)-[:HAS_ROLE]->(r: role {id_role: $idRole}) RETURN u.id_user")
    Flux<Long> findUsersByIdRole(Long idRole);

    @Query("MATCH (r: role)-[:IS_ASSIGNED_TO]->(u: user {id_user: $idUser}) RETURN r.id_role")
    Flux<Long> findRolesByIdUser(Long idUser);

    @Query("MATCH (r: role)-[:IS_ASSIGNED_TO]->(u: user {id_person: $idPerson}) RETURN r.id_role")
    Flux<Long> findRolesByIdPerson(Long idPerson);

}
