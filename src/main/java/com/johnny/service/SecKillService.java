package com.johnny.service;

import com.johnny.dto.Exposer;
import com.johnny.dto.SecKillExecution;
import com.johnny.entity.SecKill;
import com.johnny.exception.SecKillCloseException;
import com.johnny.exception.SecKillException;

import java.util.List;

public interface SecKillService {
    /*查询所有秒杀记录*/
    List<SecKill> getSecKillList();

    /*查询单个秒杀记录*/
    SecKill getById(long secKillId);

    /*根据时间暴露秒杀接口,否则返回开始倒计时*/
    Exposer exportSecKillUrl(long secKillId);

    /*执行秒杀操作*/
    SecKillExecution executeSecKill(long secKillId, long userPhone, String md5)
            throws SecKillException, SecKillCloseException, SecKillCloseException;
}
