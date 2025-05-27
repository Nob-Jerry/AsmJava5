package org.asmjava5.service;

import org.asmjava5.data.dto.response.UserDTOResponse;
import java.util.List;

public interface UserService {
    List<UserDTOResponse> getUsers();
}
