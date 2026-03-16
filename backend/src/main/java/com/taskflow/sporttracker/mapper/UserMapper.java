package com.taskflow.sporttracker.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.taskflow.sporttracker.dto.request.user.UserRequest;
import com.taskflow.sporttracker.dto.response.user.UserResponse;
import com.taskflow.sporttracker.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    User toEntity(UserRequest userRequest);

    UserResponse toDto(User user);
}
