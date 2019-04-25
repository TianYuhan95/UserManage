package com.tian.boot.service.impl;

import com.tian.boot.entity.FrontUser;
import com.tian.boot.entity.FrontUserItem;
import com.tian.boot.entity.FrontUserPayLog;
import com.tian.boot.mapper.FrontUserItemMapper;
import com.tian.boot.mapper.FrontUserMapper;
import com.tian.boot.mapper.FrontUserPayLogMapper;
import com.tian.boot.service.FrontUserManageService;
import com.tian.boot.utils.AppKey;
import com.tian.boot.utils.DateUtil;
import com.tian.boot.utils.GenerateId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class FrontUserManageServiceImpl implements FrontUserManageService {
    @Autowired
    FrontUserMapper frontUserMapper;
    @Autowired
    FrontUserItemMapper userItemMapper;
    @Autowired
    FrontUserPayLogMapper payLogMapper;

    private final Logger logger = LoggerFactory.getLogger(FrontUserManageServiceImpl.class);

    /**
     * @Description:获取全部用户列表
     * @return
     */
    @Override
    public List<FrontUser> getFrontUserList(){
        return frontUserMapper.getAllUsers();
    }

    /**
     * @Description:根据userid对用户授权,判断用户是失效用户还是未授权用户，失效用户直接更改用户状态，根据触发器自动更新结束时间为用户被的end_date
     * @Description:若用户是未授权用户，生成AppKey和SecretKey，然后更新用户状态，根据触发器自动更新SecretKey的结束时间更新为用户表的end_date
     * @param userId
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public boolean makeUserGranted(Long userId,byte status,String update_staff_id) throws Exception {
        try{
            if(status==(byte)1){
                String appKey = AppKey.getAppKey(AppKey.getGuid());
                String secretKey = GenerateId.getRandomString(16);
                Date date = new Date();
                Date end_date = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").parse("2050/12/31 23:59:59");
                FrontUserItem frontUserItem_AppKey = new FrontUserItem(GenerateId.genItemId(),userId, (byte) 0,appKey,"用户AppKey",date,end_date,update_staff_id);
                userItemMapper.insertSelective(frontUserItem_AppKey);
                FrontUserItem frontUserItem_secretKey = new FrontUserItem(GenerateId.genItemId(),userId, (byte) 1,secretKey,"用户SecretKey",date,null,update_staff_id);
                userItemMapper.insertSelective(frontUserItem_secretKey);
                FrontUser frontUser = new FrontUser();
                frontUser.setUser_id(userId);
                frontUser.setStatus((byte) 2);
                frontUserMapper.updateByPrimaryKeySelective(frontUser);
                logger.info(" User_Id:"+userId+" Created AppKey And SecretKey SuccessFul");
                return true;
            }
            else {
                frontUserMapper.updateStatusByLoginId(userId, (byte) 2, update_staff_id);
                logger.info("User_Id:"+userId+" Changed User Status SuccessFul");
                return true;
            }
        } catch (Exception e){
            logger.error("MAKE USER GRANTED HAD EXCEPTION, ROLL BACK");
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * @Description:获取用户详情
     * @param userId
     * @return
     */
    @Override
    public List<FrontUserItem> GetUserDetails(Long userId){
        List<FrontUserItem> result = new ArrayList<FrontUserItem>();
        result.add(userItemMapper.getItemByUserIdAndItemType(userId,(byte) 0));
        result.add(userItemMapper.getItemByUserIdAndItemType(userId,(byte) 1));
        return result;
    }

    /**
     * @Description:处理用户的注册操作
     * @param userId
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public boolean handleUserRegisted(Long userId,String update_staff_id,byte status) throws Exception {
        if(frontUserMapper.updateStatusByLoginId(userId,status,update_staff_id)==1) {
            logger.info("MAKE USER REGISTED SUCCESSFUL");
            return true;
        }
        else {
            logger.error("MAKE USER REGISTED FAILED, ROLL BACK");
            throw new Exception();
        }
    }

    /**
     * @Description:用户充值操作
     * @param userId
     * @param payMoney
     * @param datetime
     * @param chargeDays
     * @param userName
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public HashMap<String, Object> charge(Long userId, Float payMoney, String datetime, Integer chargeDays, String userName,String update_staff_id) throws Exception {
        Date lastEndDate = DateUtil.getGMT(datetime);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(lastEndDate);
        calendar.add(Calendar.DATE,chargeDays);
        Date currentEndDate = calendar.getTime();
        Integer update_result = frontUserMapper.updateEndDateByUserId(userId,currentEndDate);
        Timestamp payTime = new Timestamp(System.currentTimeMillis());
        Long payId=GenerateId.orderId();
        FrontUserPayLog payLog = new FrontUserPayLog();
        payLog.setPay_id(payId);
        payLog.setUser_id(userId);
        payLog.setPay_money(payMoney);
        payLog.setPay_time(payTime);
        payLog.setPay_type((byte) 0);
        payLog.setLastEndDate(lastEndDate);
        payLog.setCurrentEndDate(currentEndDate);
        payLog.setRemark("线下充值");
        payLog.setUpdate_staff_id(update_staff_id);
        Integer insert_result = payLogMapper.insertSelective(payLog);
        if(update_result==1&&insert_result==1){
            HashMap<String,Object> result_map = new HashMap<String,Object>();
            result_map.put("payId",payId);
            result_map.put("payTime",DateUtil.getGMT(payTime));
            result_map.put("payName",userName);
            result_map.put("payMoney",payMoney);
            result_map.put("chargeDays",chargeDays);
            result_map.put("remark","线下充值");
            result_map.put("lastEndDate",datetime);
            result_map.put("currentEndDate",DateUtil.getGMT(currentEndDate));
            return result_map;
        }
        else {
            logger.error("UPDATE USER STATUS OR INSERT PAYLOG FAILED, ROLL BACK");
            throw new Exception();
        }
    }

    /**
     * @Description:根据用户状态获取用户列表
     * @param i
     * @return
     */
    @Override
    public List<FrontUser> getFrontUserListByStatus(byte i) {
        List<FrontUser> frontUserList = frontUserMapper.selectByStatus(i);
        return frontUserList;
    }

    @Override
    public Map authenticate(String app_key, String secret_key){
        Map<String,Object> result = new HashMap<String,Object>();
        int result_app_key = userItemMapper.authenticateKey(app_key);
        if (result_app_key==0){
            result.put("err",-1);
            result.put("errmsg","用户没有授权的AppKey");
        }
        else {
            if (userItemMapper.authenticateKey(secret_key)==1){
                result.put("err",0);
                result.put("errmsg","success");
            }
            else {
                result.put("err",-2);
                result.put("errmsg","秘钥不正确或已失效");
            }
        }
        return result;
    }
}
