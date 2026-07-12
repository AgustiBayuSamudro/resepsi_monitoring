package com.agustibayusamudro.services;

import com.agustibayusamudro.dto.LoginDTO;

public interface AuthService {
    boolean login(LoginDTO loginDTO);
}
