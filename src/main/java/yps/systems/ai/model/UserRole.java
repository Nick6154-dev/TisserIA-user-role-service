package yps.systems.ai.model;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Node("UserRole")
public record UserRole(@Id @Property("id_user_role") Long idUserRole,
                       @Property("id_user") Long idUser,
                       @Property("id_role") Long idRole) {
}
