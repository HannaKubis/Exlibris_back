package com.exlibris.domain.mapper;

import com.exlibris.domain.model.friend.Friend;
import com.exlibris.domain.model.friend.FriendDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FriendMapper {

    public FriendDto mapFriendToFriendDto(Friend friend) {
        return new FriendDto(
                friend.getId(),
                friend.getName(),
                friend.getBooks(),
                friend.getRentals(),
                friend.getUser());
    }

    public List<FriendDto> mapFriendListToFriendDtoList(List<Friend> friendList) {
        return friendList.stream()
                .map(friend -> new FriendDto(
                        friend.getId(),
                        friend.getName(),
                        friend.getBooks(),
                        friend.getRentals(),
                        friend.getUser()))
                .collect(Collectors.toList());
    }

    public Friend mapFriendDtoToFriend(FriendDto friendDto) {
        return new Friend(
                friendDto.getId(),
                friendDto.getName(),
                friendDto.getBooks(),
                friendDto.getRental(),
                friendDto.getUser());
    }

    public List<Friend> mapFriendDtoListToFriendList(List<FriendDto> friendDtoList) {
        return friendDtoList.stream()
                .map(friendDto -> new Friend(
                        friendDto.getId(),
                        friendDto.getName(),
                        friendDto.getBooks(),
                        friendDto.getRental(),
                        friendDto.getUser()))
                .collect(Collectors.toList());
    }
}
