package com.johnny.service.impl;

import com.johnny.dto.Exposer;
import com.johnny.dto.SecKillExecution;
import com.johnny.entity.SecKill;
import com.johnny.exception.RepeatKillException;
import com.johnny.exception.SecKillCloseException;
import com.johnny.exception.SecKillException;
import com.johnny.service.SecKillService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-*.xml"
})
public class SecKillServiceImplTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SecKillService secKillService;
    @Test
    public void getSecKillList() {
        List<SecKill> list =  secKillService.getSecKillList();
        logger.info("SecKill={}",list);
    }

    @Test
    public void getById() {
        SecKill secKill = secKillService.getById(1000L);
        logger.info("SecKill={}",secKill);
    }

    @Test
    public void exportSecKillUrl() {
        Exposer exposer = secKillService.exportSecKillUrl(1006L);
        logger.info("Exposer={}",exposer);
    }

    @Test
    public void executeSecKill() {
        try {
            SecKillExecution secKillExecution = secKillService.executeSecKill(1000L,12345678901L,"45d9e711e79c9c74fd5e2f243b797e7f");
            logger.info("SecKillExecution={}",secKillExecution);
        } catch (SecKillException e) {
            logger.error("result={}",e);
        } catch (SecKillCloseException e) {
            logger.error("result={}",e);
        } catch (RepeatKillException e){
            logger.error("result={}",e);
        }
    }
    @Test
    public  void testSecKillLogic(){
        long secKillId = 1002L;
        Exposer exposer = secKillService.exportSecKillUrl(secKillId);
        if(exposer.isExposed()){
            //秒杀已开启
            logger.info("Exposer={}",exposer);
            long userPhone = 12345678901L;
            String md5 = "d409ce9070ec8a885f651b7f40b3808f";

            try {
                SecKillExecution execution = secKillService.executeSecKill(secKillId,userPhone,md5);
                logger.info("SecKillExecution={}",execution);
            } catch (SecKillException e) {
                logger.error(e.getMessage());
            } catch (SecKillCloseException e) {
                logger.error(e.getMessage());
            } catch(RepeatKillException e) {
                logger.error(e.getMessage());
            }
        }else{
            //秒杀未开启
            logger.info("Exposer={}",exposer);
        }
    }
}