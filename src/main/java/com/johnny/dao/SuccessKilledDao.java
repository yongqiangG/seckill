package com.johnny.dao;

import com.johnny.entity.SuccessKilled;
import org.apache.ibatis.annotations.Param;

public interface SuccessKilledDao {

    int insertSuccessKilled(@Param("secKillId") Long secKillId, @Param("userPhone")Long userPhone);

    SuccessKilled queryByIdWithSecKill(Long secKillId);
}
