package com.johnny.dao;

import com.johnny.entity.SuccessKilled;

public interface SuccessKilledDao {

    int insertSuccessKilled(Long secKillId,Long userPhone);

    SuccessKilled queryByIdWithSecKill(Long secKillId);
}
