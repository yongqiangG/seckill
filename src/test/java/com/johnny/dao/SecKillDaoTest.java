package com.johnny.dao;

import com.johnny.entity.SecKill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SecKillDaoTest {
    @Autowired
    private SecKillDao secKillDao;
    @Test
    public void reduceNumber() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse("2019-11-11 12:00:00");
        int n = secKillDao.reduceNumber(Long.valueOf(1000),date);
        System.out.println(n);
    }

    @Test
    public void queryById() {
        Long id = Long.valueOf(1000);
        SecKill secKill = secKillDao.queryById(id);
        System.out.println(secKill);
    }

    @Test
    public void queryAll() {
        List<SecKill> list = secKillDao.queryAll(1,2);
        for(SecKill s:list){
            System.out.println(s);
        }

    }
}