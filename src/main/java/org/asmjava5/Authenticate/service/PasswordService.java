package org.asmjava5.Authenticate.service;

import org.asmjava5.Authenticate.data.dto.request.PasswordUpdateRequest;

public interface PasswordService {
    Boolean updatePassword(PasswordUpdateRequest request);
}
