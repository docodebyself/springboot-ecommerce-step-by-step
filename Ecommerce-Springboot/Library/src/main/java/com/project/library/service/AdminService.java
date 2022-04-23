package com.project.library.service;

import com.project.library.dto.AdminDto;
import com.project.library.model.Admin;

public interface AdminService {
    Admin save(AdminDto adminDto);

    Admin findByUsername(String username);
}
