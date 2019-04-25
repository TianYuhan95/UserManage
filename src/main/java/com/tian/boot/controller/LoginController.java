package com.tian.boot.controller;


import com.tian.boot.entity.FirstMenu;
import com.tian.boot.service.FrontUserManageService;
import com.tian.boot.service.GetMenus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private GetMenus getMenus;
    @Autowired
    FrontUserManageService frontUserManageService;

    @RequestMapping(value={"/index","/","#"})
    public String index(Model model, HttpServletRequest request){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HttpSession session = request.getSession(true);
        if(session.getAttribute("firstMenuList")==null) {
            List<FirstMenu> firstMenuList = getMenus.selectMenus(auth.getName());
            session.setAttribute("firstMenuList", firstMenuList);
        }
        return "index";
    }
}
