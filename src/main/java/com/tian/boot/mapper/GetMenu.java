package com.tian.boot.mapper;

import com.tian.boot.entity.FirstMenu;

import java.util.List;

public interface GetMenu {
    /**
     * @根据用户角色获取用户菜单
     * @param username
     * @return
     */
    List<FirstMenu> getFirstMenu(String username);
}
