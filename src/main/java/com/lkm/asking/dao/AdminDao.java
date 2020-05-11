package com.lkm.asking.dao;

import com.lkm.asking.entity.Admin;
import org.springframework.stereotype.Component;

@Component
public interface AdminDao {
    Admin adminLogin(String adminName,String adminPassword);
}
