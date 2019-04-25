package com.tian.boot.service.impl;

import com.tian.boot.entity.User;
import com.tian.boot.mapper.UserMapper;
import com.tian.boot.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @BelongProject:security
 * @BelongPackage:com.tian.security.service.impl
 * @Author:田宇寒
 * @CreateTime:2019-03-18
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper<User> userMapper;

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userMapper.findByUserName(username);
            if(null == user){
                throw new UsernameNotFoundException("用户不存在");
            }
            //用户权限
            List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
            if(StringUtils.isNotBlank(user.getRole_name())){
                String[] roles = user.getRole_name().split(",");
                for(String role : roles){
                    if(StringUtils.isNotBlank(role)){
                        authorityList.add(new SimpleGrantedAuthority(role.trim()));
                    }
                }
            }
            return new org.springframework.security.core.userdetails.User(user.getUser_name(),user.getPassword(),
                    authorityList);
        }catch (Exception e){
            logger.error("USER NAME NOT FOUND");
            throw e;
        }
    }
}
