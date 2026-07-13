package com.agustibayusamudro.services;

import com.agustibayusamudro.dto.LoginDTO;

public interface UserService {
    boolean login(LoginDTO loginDTO);
}
