package org.asmjava5.convert;


import org.asmjava5.data.dto.request.UserDtoRequest;
import org.asmjava5.data.dto.request.update.UserUpdateRequest;
import org.asmjava5.data.dto.response.UserDtoResponse;
import org.asmjava5.data.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapstruct {
    UserDtoResponse toUserDTOResponse(User user);
    User toUser(UserDtoRequest userDtoRequest);
    User toUserM(UserUpdateRequest userUpdateRequest);
    List<UserDtoResponse> toUserDTOResponseList(List<User> user);
}
