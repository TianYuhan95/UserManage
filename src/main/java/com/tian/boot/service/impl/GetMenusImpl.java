package com.tian.boot.service.impl;

import com.tian.boot.entity.FirstMenu;
import com.tian.boot.mapper.GetMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetMenusImpl implements com.tian.boot.service.GetMenus {

    @Autowired
    GetMenu getMenu;

    @Override
    public List<FirstMenu> selectMenus(String username){
        return getMenu.getFirstMenu(username);
    }
}
