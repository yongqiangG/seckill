package com.johnny.dao;

import com.johnny.entity.SecKill;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Administrator
 */
public interface SecKillDao {

    int reduceNumber(Long secKillId, Date killTime);

    SecKill queryById(Long secKillId);

    List<SecKill> queryAll(int offset,int limit);
}
