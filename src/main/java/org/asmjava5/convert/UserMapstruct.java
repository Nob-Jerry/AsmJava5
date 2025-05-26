package org.asmjava5.convert;


import org.asmjava5.data.dto.response.UserDTOResponse;
import org.asmjava5.data.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapstruct {

    UserDTOResponse toUserDTOResponse(User user);
    List<UserDTOResponse> toUserDTOResponseList(List<User> user);
}
