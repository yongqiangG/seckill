package com.johnny.service.impl;

import com.johnny.dao.SecKillDao;
import com.johnny.dao.SuccessKilledDao;
import com.johnny.dao.cache.RedisDao;
import com.johnny.dto.Exposer;
import com.johnny.dto.SecKillExecution;
import com.johnny.entity.SecKill;
import com.johnny.entity.SuccessKilled;
import com.johnny.enums.SecKillStateEnum;
import com.johnny.exception.RepeatKillException;
import com.johnny.exception.SecKillCloseException;
import com.johnny.exception.SecKillException;
import com.johnny.service.SecKillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;
@Service(value = "secKillService")
public class SecKillServiceImpl implements SecKillService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String slat = "asdasASDASDf21q3123dasfas@!@^&!#%&***";

    @Autowired
    private SecKillDao secKillDao;
    @Autowired
    private SuccessKilledDao successKilledDao;
    @Autowired
    private RedisDao redisDao;

    @Override
    public List<SecKill> getSecKillList() {
        return secKillDao.queryAll(0, 4);
    }

    @Override
    public SecKill getById(long secKillId) {
        return secKillDao.queryById(secKillId);
    }

    @Override
    public Exposer exportSecKillUrl(long secKillId) {
        //SecKill secKill = secKillDao.queryById(secKillId);
        //redis优化,从redis缓存中查找
        SecKill secKill = redisDao.getSecKill(secKillId);
        if (secKill == null) {
            //redis缓存中没有从数据库中查找
            secKill = secKillDao.queryById(secKillId);
            if(secKill==null){
                //数据库中查找不到对应记录
                return new Exposer(false, secKillId);
            }else{
                //放入redis缓存中
                redisDao.putSecKill(secKill);
            }
        }
        Date startTime = secKill.getStartTime();
        Date endTime = secKill.getEndTime();
        Date nowTime = new Date();
        if (nowTime.getTime() < startTime.getTime() || nowTime.getTime() > endTime.getTime()) {
            return new Exposer(false, secKillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
        }
        String md5 = getMd5(secKillId);//TODO
        return new Exposer(true, secKillId, md5);
    }

    @Override
    @Transactional
    /**
     * 控制事务的执行时间尽可能短,不要穿插其他的操作RPC/HTTP请求,或者将其剥离到事务方法之外
     */
    public SecKillExecution executeSecKill(long secKillId, long userPhone, String md5) throws SecKillException, SecKillCloseException, SecKillCloseException {
        //校验url的md5
        if (md5 == null || !getMd5(secKillId).equals(md5)) {
            throw new SecKillException("secKill data rewrite");
        }
        //执行秒杀
        Date nowTime = new Date();
        int updateCount = secKillDao.reduceNumber(secKillId, nowTime);
        try {
            if (updateCount <= 0) {
                throw new SecKillCloseException("secKill is closed");
            } else {
                int insertCount = successKilledDao.insertSuccessKilled(secKillId, userPhone);
                if (insertCount <= 0) {
                    //重复秒杀
                    throw new RepeatKillException("secKill repeated");
                }else{
                    //秒杀成功
                    List<SuccessKilled> successKilled = successKilledDao.queryByIdWithSecKill(secKillId);
                    return new SecKillExecution(secKillId, SecKillStateEnum.stateOf(1),successKilled);
                }
            }
        } catch (SecKillCloseException e) {
            throw e;
        } catch (RepeatKillException e) {
            throw e;
        } catch (Exception e){
            logger.error(e.getMessage(),e);
            //所有编译期异常,转为运行期异常
            throw new SecKillException("secKill inner error:"+e.getMessage());
        }
    }

    private String getMd5(long secKillId) {
        String base = secKillId + "/" + slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }
}
