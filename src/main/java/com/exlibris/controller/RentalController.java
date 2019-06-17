package com.exlibris.controller;

import com.exlibris.domain.mapper.RentalMapper;
import com.exlibris.domain.model.rental.Rental;
import com.exlibris.domain.model.rental.RentalDto;
import com.exlibris.service.RentalDbService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class RentalController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);
    private static final String FETCHING_RENTALS = "Fetching rentals";
    private static final String ADDING_RENTAL = "Adding rental";
    private static final String UPDATING_RENTAL = "Updating rental";
    private static final String DELETING_RENTAL = "Deleting rental";

    @Autowired
    private RentalDbService dbService;

    @Autowired
    private RentalMapper rentalMapper;

    @GetMapping("rentals")
    public List<RentalDto> getRentals() {
        LOGGER.info(FETCHING_RENTALS);
        return rentalMapper.mapRentalListToRentalDtoList(dbService.getAllRentals());
    }

    @PostMapping("rentals")
    public RentalDto addRental(@RequestBody Rental rental) {
        LOGGER.info(ADDING_RENTAL);
        return rentalMapper.mapRentalToRentalDto(dbService.addRental(rental));
    }

    @PutMapping("rentals")
    public RentalDto updateRental(@RequestBody Rental rental) {
        LOGGER.info(UPDATING_RENTAL);
        return rentalMapper.mapRentalToRentalDto(dbService.updateRental(rental));
    }

    @DeleteMapping("rentals/{id}")
    public Rental deleteRental(@PathVariable int id) {
        LOGGER.info(DELETING_RENTAL);
        return dbService.deleteRental(id);
    }
}
