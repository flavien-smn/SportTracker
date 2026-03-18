package com.flavien.sporttracker.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.flavien.sporttracker.dto.request.user.UserRequest;
import com.flavien.sporttracker.dto.response.user.UserResponse;
import com.flavien.sporttracker.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    User toEntity(UserRequest userRequest);

    UserResponse toDto(User user);
}
