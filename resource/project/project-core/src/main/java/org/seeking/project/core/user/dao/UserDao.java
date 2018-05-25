package org.seeking.{{ project }}.core.user.dao;

import org.seeking.{{ project }}.core.user.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Transactional
@Repository
public interface UserDao extends CrudRepository<User, String> {
    User findByUsername(String username);
}