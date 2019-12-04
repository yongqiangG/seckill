package com.johnny.controller;

import com.johnny.dto.Exposer;
import com.johnny.dto.SecKillExecution;
import com.johnny.dto.SecKillResult;
import com.johnny.entity.SecKill;
import com.johnny.enums.SecKillStateEnum;
import com.johnny.exception.RepeatKillException;
import com.johnny.exception.SecKillCloseException;
import com.johnny.exception.SecKillException;
import com.johnny.service.SecKillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
//符合Restful规范,/模块/资源/{id}/操作(名词)
@RequestMapping("/seckill")
public class SecKillController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SecKillService secKillService;

    /**
     * 秒杀列表页
     *
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String list(Model model) {
        List<SecKill> list = secKillService.getSecKillList();
        model.addAttribute("list", list);
        //返回WEB-INF/jsp/list.jsp
        return "list";
    }

    /**
     * 秒杀详情页
     *
     * @param secKillId
     * @param model
     * @return
     */
    @RequestMapping("/{secKillId}/detail")
    public String detail(@PathVariable("secKillId") Long secKillId, Model model) {
        if (secKillId == null) {
            return "redirect:/seckill/list";
        }
        SecKill secKill = secKillService.getById(secKillId);
        if (secKill == null) {
            return "forward:/seckill/list";
        }
        model.addAttribute("secKill", secKill);
        return "detail";
    }

    /**
     * 暴露秒杀接口
     */
    @RequestMapping(value = "/{secKillId}/exposer",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SecKillResult<Exposer> exposer(@PathVariable("secKillId") Long secKillId) {
        Exposer exposer = secKillService.exportSecKillUrl(secKillId);
        SecKillResult<Exposer> result;
        if (exposer.isExposed()) {
            result = new SecKillResult<>(true, exposer);
            return result;
        }
        result = new SecKillResult<>(false, exposer);
        return result;
    }

    /**
     * 秒杀执行结果
     */
    @RequestMapping(value = "/{secKillId}/{md5}/execution",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"}
    )
    @ResponseBody
    public SecKillResult<SecKillExecution> execute(@PathVariable("secKillId") Long secKillId,
                                                   @CookieValue(value = "killPhone") Long userPhone,
                                                   @PathVariable("md5") String md5) {
        SecKillResult<SecKillExecution> result;
        if (userPhone == null) {
            return new SecKillResult<SecKillExecution>(false, "该手机号码未注册");
        }
        try {
            SecKillExecution secKillExecution = secKillService.executeSecKill(secKillId, userPhone, md5);
            result = new SecKillResult<>(true, secKillExecution);
            return result;
        } catch (RepeatKillException e) {
            SecKillExecution secKillExecution = new SecKillExecution(secKillId, SecKillStateEnum.REPEAT_KILL);
            result = new SecKillResult<>(false, secKillExecution);
            return result;
        } catch (SecKillCloseException e) {
            SecKillExecution secKillExecution = new SecKillExecution(secKillId, SecKillStateEnum.END);
            result = new SecKillResult<>(false, secKillExecution);
            return result;
        } catch (SecKillException e) {
            SecKillExecution secKillExecution = new SecKillExecution(secKillId, SecKillStateEnum.DATA_REWRITE);
            result = new SecKillResult<>(false, secKillExecution);
            return result;
        }
    }

    /**
     * 服务器当前时间
     */
    @RequestMapping(value = "/nowTime",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SecKillResult<Long> nowTime() {
        Date date = new Date();
        SecKillResult<Long> result;
        result = new SecKillResult<>(true, date.getTime());
        return result;
    }
}
