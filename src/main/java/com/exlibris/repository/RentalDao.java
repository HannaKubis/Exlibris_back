package com.exlibris.repository;

import com.exlibris.domain.model.rental.Rental;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface RentalDao extends CrudRepository<Rental, Integer> {

    @Override
    List<Rental> findAll();

    @Override
    Rental save(Rental rental);

    void deleteById(int id);

    Rental findById(int id);

    @Query
    List<Rental> getRentals(@Param("USER") long userId);
}
