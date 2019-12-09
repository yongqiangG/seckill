package com.johnny.dao.cache;

import com.johnny.dao.SecKillDao;
import com.johnny.entity.SecKill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class RedisDaoTest {
    @Autowired
    private RedisDao redisDao;
    @Autowired
    private SecKillDao secKillDao;
    @Test
    public void getSecKill() {
        SecKill secKill = redisDao.getSecKill(1000L);
        System.out.println(secKill);
    }

    @Test
    public void putSecKill() {
        SecKill secKill = secKillDao.queryById(1000L);
        String str = redisDao.putSecKill(secKill);
        System.out.println(str);
    }
}