package com.exlibris.controller;

import com.exlibris.domain.mapper.FriendMapper;
import com.exlibris.domain.model.friend.Friend;
import com.exlibris.domain.model.friend.FriendDto;
import com.exlibris.service.FriendDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class FriendController {

    @Autowired
    private FriendDbService dbService;

    @Autowired
    private FriendMapper friendMapper;

    @GetMapping("friends")
    public List<FriendDto> getFriends() {
        return friendMapper.mapFriendListToFriendDtoList(dbService.getAllFriends());
    }

    @PostMapping("friends")
    public FriendDto addFriend(@RequestBody Friend friend) {
        return friendMapper.mapFriendToFriendDto(dbService.addFriend(friend));
    }

    @PutMapping("friends")
    public FriendDto updateFriend(@RequestBody Friend friend) {
        return friendMapper.mapFriendToFriendDto(dbService.updateFriend(friend));
    }

    @DeleteMapping("friends/{id}")
    public Friend deleteFriend(@PathVariable int id) {
        return dbService.deleteFriend(id);
    }
}
