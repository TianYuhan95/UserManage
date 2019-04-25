package com.tian.boot.service;

import com.tian.boot.entity.FrontUser;
import com.tian.boot.entity.FrontUserItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface FrontUserManageService {
    List<FrontUser> getFrontUserList();

    boolean makeUserGranted(Long userId,byte status,String update_staff_id) throws Exception;

    List<FrontUserItem> GetUserDetails(Long userId);

    boolean handleUserRegisted(Long userId,String update_staff_id,byte status) throws Exception;

    HashMap<String, Object> charge(Long userId, Float payMoney, String datetime, Integer chargeDays, String userName,String update_staff_id) throws Exception;

    List<FrontUser> getFrontUserListByStatus(byte i);

    Map authenticate(String app_key, String secret_key);
}
