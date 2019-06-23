package com.exlibris.service;

import com.exlibris.domain.model.friend.Friend;
import com.exlibris.domain.model.rental.Rental;
import com.exlibris.repository.FriendDao;
import com.exlibris.repository.RentalDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendDbService {

    @Autowired
    private FriendDao friendDao;

    @Autowired
    private RentalDbService rentalDbService;

    public List<Friend> getAllFriends() {
        return friendDao.findAll();
    }

    public Friend addFriend(Friend friend) {
        return friendDao.save(friend);
    }

    public Friend deleteFriend(int id) {
        Friend friend = friendDao.findById(id);
        List<Rental> friendRentals = friend.getRentals();
        int arraySize = friendRentals.size();
        if(friendRentals != null) {
            for (int i = 0; i < arraySize; i++) {
                friend = friendDao.findById(id);
                List<Rental> rentalList = friend.getRentals();
                rentalDbService.deleteRental(rentalList.get(i).getId());
            }
        }
        return friendDao.deleteById(id);
    }

    public Friend getFriendById(int id) {
        return friendDao.findById(id);
    }

    public Friend updateFriend(Friend friend) {
        Friend updatedFriend = friendDao.findById(friend.getId());
        updatedFriend.setName(friend.getName());
        return friendDao.save(updatedFriend);
    }
}
