package com.exlibris.repository;

import com.exlibris.domain.model.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * @Author tomirszulc on 2019-06-29
 */
@Transactional
@Repository
public interface UserDao extends CrudRepository<User, Long> {

    User save(User user);

    User findByUsername(String username);

    User deleteById(long id);
}
