package com.exlibris.controller;

import com.exlibris.domain.mapper.RentalMapper;
import com.exlibris.domain.model.rental.Rental;
import com.exlibris.domain.model.rental.RentalDto;
import com.exlibris.service.RentalDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class RentalController {

    @Autowired
    private RentalDbService dbService;

    @Autowired
    private RentalMapper rentalMapper;

    @GetMapping("rentals")
    public List<RentalDto> getRentals() {
        return rentalMapper.mapRentalListToRentalDtoList(dbService.getAllRentals());
    }

    @PostMapping("rentals")
    public RentalDto addRental(@RequestBody Rental rental) {
        return rentalMapper.mapRentalToRentalDto(dbService.addRental(rental));
    }

    @PutMapping("rentals")
    public RentalDto updateRental(@RequestBody Rental rental) {
        return rentalMapper.mapRentalToRentalDto(dbService.updateRental(rental));
    }

    @DeleteMapping("rentals/{id}")
    public Rental deleteRental(@PathVariable int id) {
        return dbService.deleteRental(id);
    }
}
