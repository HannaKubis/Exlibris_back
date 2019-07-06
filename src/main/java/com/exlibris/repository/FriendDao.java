package com.exlibris.repository;

import com.exlibris.domain.model.friend.Friend;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface FriendDao extends CrudRepository<Friend, Integer> {

    @Override
    List<Friend> findAll();

    @Override
    Friend save(Friend friend);

    void deleteById(int id);

    Friend findById(int id);

    @Query
    List<Friend> getFriends(@Param("USER") long userId);
}
