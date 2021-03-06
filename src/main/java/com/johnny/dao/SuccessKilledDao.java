package com.johnny.dao;

import com.johnny.entity.SuccessKilled;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SuccessKilledDao {

    int insertSuccessKilled(@Param("secKillId") Long secKillId, @Param("userPhone")Long userPhone);

    List<SuccessKilled> queryByIdWithSecKill(Long secKillId);
}
