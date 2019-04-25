package com.tian.boot.service.impl;

import com.tian.boot.entity.FrontUserPayLog;
import com.tian.boot.mapper.FrontUserPayLogMapper;
import com.tian.boot.service.InfoStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfoStatsServiceImpl implements InfoStatsService {

    @Autowired
    FrontUserPayLogMapper frontUserPayLogMapper;

    @Override
    public List<FrontUserPayLog> getFrontUserPayLogListByLoginName(String login_name) {

        return frontUserPayLogMapper.selectPayLogListByLoginName(login_name);
    }

    @Override
    public List<FrontUserPayLog> getFrontUserPaylogList() {
        return frontUserPayLogMapper.selectAllPayLogList();
    }
}
