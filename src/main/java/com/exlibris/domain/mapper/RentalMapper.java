package com.exlibris.domain.mapper;

import com.exlibris.domain.model.rental.Rental;
import com.exlibris.domain.model.rental.RentalDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentalMapper {

    public RentalDto mapRentalToRentalDto(Rental rental) {
        return new RentalDto(
                rental.getId(),
                rental.getFriend(),
                rental.getBook(),
                rental.getLendDate(),
                rental.getReturnDate());
    }

    public List<RentalDto> mapRentalListToRentalDtoList(List<Rental> rentalList) {
        return rentalList.stream()
                .map(rental -> new RentalDto(
                        rental.getId(),
                        rental.getFriend(),
                        rental.getBook(),
                        rental.getLendDate(),
                        rental.getReturnDate()))
                .collect(Collectors.toList());
    }

    public Rental mapRentalDtoToRental(RentalDto rentalDto) {
        return new Rental(
                rentalDto.getId(),
                rentalDto.getFriend(),
                rentalDto.getBook(),
                rentalDto.getLendDate(),
                rentalDto.getReturnDate());
    }

    public List<Rental> mapRentalDtoListToRentalList(List<RentalDto> rentalDtoList) {
        return rentalDtoList.stream()
                .map(rentalDto -> new Rental(
                        rentalDto.getId(),
                        rentalDto.getFriend(),
                        rentalDto.getBook(),
                        rentalDto.getLendDate(),
                        rentalDto.getReturnDate()))
                .collect(Collectors.toList());
    }
}
