package com.tian.boot.service;

import com.tian.boot.entity.FrontUserPayLog;

import java.util.List;

public interface InfoStatsService {

    List<FrontUserPayLog> getFrontUserPayLogListByLoginName(String login_name);

    List<FrontUserPayLog> getFrontUserPaylogList();
}
