package com.exlibris.controller;

import com.exlibris.domain.mapper.FriendMapper;
import com.exlibris.domain.model.friend.Friend;
import com.exlibris.domain.model.friend.FriendDto;
import com.exlibris.service.FriendDbService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("db")
public class FriendController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FriendController.class);
    private static final String FETCHING_FRIENDS = "Fetching friends";
    private static final String ADDING_FRIEND = "Adding friend";
    private static final String UPDATING_FRIEND = "Updating friend";
    private static final String DELETING_FRIEND = "Deleting friend";

    @Autowired
    private FriendDbService dbService;

    @Autowired
    private FriendMapper friendMapper;

    @GetMapping("friends")
    public List<FriendDto> getFriends() {
        LOGGER.info(FETCHING_FRIENDS);
        return friendMapper.mapFriendListToFriendDtoList(dbService.getUserFriendList());
    }

    @PostMapping("friends")
    public FriendDto addFriend(@RequestBody Friend friend) {
        LOGGER.info(ADDING_FRIEND);
        return friendMapper.mapFriendToFriendDto(dbService.addFriend(friend));
    }

    @PutMapping("friends")
    public FriendDto updateFriend(@RequestBody Friend friend) {
        LOGGER.info(UPDATING_FRIEND);
        return friendMapper.mapFriendToFriendDto(dbService.updateFriend(friend));
    }

    @DeleteMapping("friends/{id}")
    public void deleteFriend(@PathVariable int id) {
        LOGGER.info(DELETING_FRIEND);
        dbService.deleteFriend(id);
    }
}
