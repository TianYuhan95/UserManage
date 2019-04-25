package com.tian.boot.service;

import com.tian.boot.entity.FirstMenu;

import java.util.List;

public interface GetMenus {
    List<FirstMenu> selectMenus(String username);
}
