package com.tian.boot.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tian.boot.entity.FrontUser;
import com.tian.boot.entity.FrontUserItem;
import com.tian.boot.entity.FrontUserPayLog;
import com.tian.boot.service.FrontUserManageService;
import com.tian.boot.service.IAuthenticationFacade;
import com.tian.boot.service.InfoStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class ServiceController {

    @Autowired
    FrontUserManageService frontUserManageService;
    @Autowired
    IAuthenticationFacade authenticationFacade;
    @Autowired
    InfoStatsService infoStatsService;

    /**
     * @Description:根据不同请求返回列表模板
     * @param option : userlist 返回全部用户模板 check:返回注册未审核用户列表 grant:显示待授权用户列表
     * @param pagenum
     * @param pagesize
     * @param model
     * @return
     */
    @RequestMapping(value = "/user/{option}",method = RequestMethod.GET)
    public String UserList(@PathVariable("option") String option, @RequestParam(value = "pagenum",defaultValue = "1") Integer pagenum, @RequestParam(value = "pagesize",defaultValue = "5") Integer pagesize, Model model){
        PageHelper.startPage(pagenum,pagesize);
        if(option.equals("list")){
            List<FrontUser> frontUserList = frontUserManageService.getFrontUserList();
            PageInfo<FrontUser> pageInfo = new PageInfo<FrontUser>(frontUserList);
            model.addAttribute("alluserlist",pageInfo);
            return "fragments/usermanage::userlist";
        }
        else if(option.equals("check")){
            List<FrontUser> frontUserList_check = frontUserManageService.getFrontUserListByStatus((byte) 0);
            PageInfo<FrontUser> pageInfo = new PageInfo<FrontUser>(frontUserList_check);
            model.addAttribute("checkuserlist",pageInfo);
            return "fragments/usermanage::checkregist";
        }
        else if(option.equals("grant")){
            List<FrontUser> frontUserList_grant = frontUserManageService.getFrontUserListByStatus((byte) 1);
            PageInfo<FrontUser> pageInfo = new PageInfo<FrontUser>(frontUserList_grant);
            model.addAttribute("grantuserlist",pageInfo);
            return "fragments/usermanage::grantlist";
        }
        else
            return null;
    }


    @RequestMapping("/user/check/{option}")
    @ResponseBody
    public boolean MakeAccess(@PathVariable("option") String option,@RequestParam("user_id") Long userId) throws Exception {
        if (option.equals("makeaccess")){
            String update_staff_id = authenticationFacade.getAuthentication().getName();
            boolean flag = frontUserManageService.handleUserRegisted(userId,update_staff_id,(byte) 1);
            return flag;
        }
        else if (option.equals("deny")){
            String update_staff_id = authenticationFacade.getAuthentication().getName();
            boolean flag = frontUserManageService.handleUserRegisted(userId,update_staff_id,(byte) 3);
            return flag;
        }
        else
            return false;
    }

    /**
     * @Description:用户授权处理
     * @param userId
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/user/grant/makegrant")
    @ResponseBody
    public boolean MakeGrant(@RequestParam("user_id") Long userId,@RequestParam("status") byte status, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        String update_staff_id = authenticationFacade.getAuthentication().getName();
        boolean flag = frontUserManageService.makeUserGranted(userId,status,update_staff_id);
        if(flag)
            session.setAttribute("userlist", frontUserManageService.getFrontUserList());
        return flag;
    }

    /**
     * @Description:展示用户详情
     * @param userId
     * @param status
     * @return
     * @throws EmptyStackException
     */
    @RequestMapping("/user/details")
    @ResponseBody
    public List<FrontUserItem> UserDetails(@RequestParam("user_id") Long userId, @RequestParam("status") Integer status) throws Exception {
        if(status.equals(2)){
            return frontUserManageService.GetUserDetails(userId);
        }
        else
            throw new Exception("非授权用户");
    }

    /**
     * @Description:用户充值操作接口
     * @param userId
     * @param userName
     * @param chargeMoney
     * @param endDate
     * @param chargeDays
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/user/charge/makecharge")
    public String UserMakeCharge(@RequestParam("user_id") Long userId,
                                  @RequestParam("user_name") String userName,
                                  @RequestParam("charge_money") Float chargeMoney,
                                  @RequestParam("end_date") String endDate,
                                  @RequestParam("charge_days") Integer chargeDays,Model model) throws Exception {
        HashMap<String,Object> result_map = frontUserManageService.charge(userId,chargeMoney,endDate,chargeDays,userName,authenticationFacade.getAuthentication().getName());
        model.addAttribute("chargeResult", result_map);
        return "chargeInfo";
    }

    /**
     * @Description:统计分项模板展示
     * @param option_1 分项1
     * @param option_2 list为全部用户
     * @return
     */
    @RequestMapping("/infostats/{option_1}/{option_2}")
    public String infoStats(@PathVariable("option_1") String option_1,
                            @PathVariable("option_2") String option_2,
                            @RequestParam(value = "pagenum",defaultValue = "1") Integer pagenum,
                            @RequestParam(value = "pagesize",defaultValue = "5") Integer pagesize,
                            Model model){
        PageHelper.startPage(pagenum,pagesize);
        if (option_1.equals("paylog")){
            if (option_2.equals("list")){
                List<FrontUserPayLog> frontUserPayLogList = infoStatsService.getFrontUserPaylogList();
                PageInfo<FrontUserPayLog> pageInfo = new PageInfo<FrontUserPayLog>(frontUserPayLogList);
                model.addAttribute("paylog_pageinfo",pageInfo);
                return "fragments/infostats::paylog_form";
            }
            else {
                List<FrontUserPayLog> frontUserPayLogList = infoStatsService.getFrontUserPayLogListByLoginName(option_2);
                PageInfo<FrontUserPayLog> pageInfo = new PageInfo<FrontUserPayLog>(frontUserPayLogList);
                model.addAttribute("paylog_pageinfo",pageInfo);
                model.addAttribute("searchName",option_2);
                return "fragments/infostats::paylog_result";
            }
        }
        else
            return null;
    }


    @RequestMapping(value = "/authenticate",method = RequestMethod.GET)
    @ResponseBody
    public Map RemoteAuthenticate(@RequestParam("appKey") String app_key, @RequestParam("secretKey") String secret_key){
        return frontUserManageService.authenticate(app_key,secret_key);
    }
}
