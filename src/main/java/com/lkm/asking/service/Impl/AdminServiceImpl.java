package com.lkm.asking.service.Impl;

import com.lkm.asking.dao.AdminDao;
import com.lkm.asking.entity.Admin;
import com.lkm.asking.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminDao adminDao;

    @Override
    public Admin adminLogin(String adminName, String adminPassword) {
        return adminDao.adminLogin(adminName,adminPassword);
    }
}
