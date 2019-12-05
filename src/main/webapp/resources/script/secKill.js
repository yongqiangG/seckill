var secKill = {
    //封装ajax相关url
    URL: {},
    //验证手机号
    validatePhone: function (phone) {
        if (phone && phone.length == 11 && !isNaN(phone)){
            return true;
        }
        return false;
            },
    //详情页秒杀逻辑
    detail: {
        init: function (params) {
            //手机验证和登录,计时交互
            //在cookie中查找手机号
            var killPhone = $.cookie("killPhone");
            var secKillId = params['secKillId'];
            var startTime = params['startTime'];
            var endTime = params['endTime'];
            //验证手机号
            if (secKill.validatePhone(killPhone)) {
                var killPhoneModal = $("#killPhoneModal");

            }

        }
    }

}