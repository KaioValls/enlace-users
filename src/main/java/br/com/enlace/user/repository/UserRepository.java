package br.com.enlace.user.repository;

import br.com.enlace.user.domain.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface UserRepository extends PanacheRepository<User> {

}