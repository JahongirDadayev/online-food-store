package com.example.onlinefoodstore.model.mappers;

import com.example.onlinefoodstore.model.dto.UserDTO;
import com.example.onlinefoodstore.model.entities.User;
import com.example.onlinefoodstore.model.mappers.base.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends GenericMapper<User, UserDTO> {
}
