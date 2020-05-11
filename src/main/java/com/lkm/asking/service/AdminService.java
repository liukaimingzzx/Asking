package com.lkm.asking.service;

import com.lkm.asking.entity.Admin;

public interface AdminService {
    Admin adminLogin(String adminName, String adminPassword);
}
