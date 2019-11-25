package com.johnny.dao;

import com.johnny.entity.SecKill;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Administrator
 */
public interface SecKillDao {

    int reduceNumber(@Param("secKillId") Long secKillId, @Param("killTime") Date killTime);

    SecKill queryById(Long secKillId);

    List<SecKill> queryAll(@Param("offset") int offset, @Param("limit") int limit);
}
