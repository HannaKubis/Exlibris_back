package com.exlibris.service;

import com.exlibris.domain.model.friend.Friend;
import com.exlibris.domain.model.rental.Rental;
import com.exlibris.domain.model.user.User;
import com.exlibris.exception.FriendNotFoundException;
import com.exlibris.repository.FriendDao;
import com.exlibris.repository.RentalDao;
import com.exlibris.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendDbService {

    @Autowired
    private FriendDao friendDao;

    @Autowired
    private RentalDbService rentalDbService;

    @Autowired
    private UserDao userDao;

    public List<Friend> getAllFriends() {
        return friendDao.findAll();
    }

    public Friend addFriend(Friend friend) {
        User loggedUser = userDao.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        friend.setUser(loggedUser);
        return friendDao.save(friend);
    }

    public void deleteFriend(int id) throws FriendNotFoundException {
        if(friendDao.findById(id) == null) {
            throw new FriendNotFoundException("Cannot delete friend");
        }
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
        friendDao.deleteById(id);
    }

    public Friend getFriendById(int id) throws FriendNotFoundException {
        if(friendDao.findById(id) == null) {
            throw new FriendNotFoundException("Cannot find friend");
        }
        return friendDao.findById(id);
    }

    public Friend updateFriend(Friend friend) {
        Friend updatedFriend = friendDao.findById(friend.getId());
        updatedFriend.setName(friend.getName());
        User loggedUser = userDao.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        updatedFriend.setUser(loggedUser);
        return friendDao.save(updatedFriend);
    }

    public List<Friend> getUserFriendList() {
        User loggedUser = userDao.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        return friendDao.getFriends(loggedUser.getId());
    }
}
