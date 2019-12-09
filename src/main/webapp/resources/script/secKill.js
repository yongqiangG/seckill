var secKill = {
    //封装ajax相关url
    URL: {
        now: function () {
            return '/seckill/seckill/nowTime';
        },
        exposer: function (secKillId) {
            return '/seckill/seckill/' + secKillId + '/exposer';
        },
        execution: function (secKillId, md5) {
            return '/seckill/seckill/' + secKillId + '/' + md5 + '/execution';
        }

    },
    handleSecKill: function (secKillId, node) {
        node.hide().html('<button class="btn btn-primary btn-lg" id="killBtn">开始秒杀</button>')
        $.post(secKill.URL.exposer(secKillId), {}, function (result) {
            if (result && result['success']) {
                var exposer = result['data'];
                if (exposer['exposed']) {
                    //秒杀已开启
                    var md5 = exposer['md5'];
                    console.log(secKillId + ':' + md5);
                    var killUrl = secKill.URL.execution(secKillId, md5);
                    console.log("killUrl:" + killUrl);
                    //绑定秒杀按钮
                    $("#killBtn").one('click', function () {
                        //先禁用按钮
                        $(this).addClass('disable');
                        $.post(secKill.URL.execution(secKillId, md5), {}, function (result) {
                            if (result && result['success']) {
                                var killResult = result['data'];
                                var state = killResult['state'];
                                var stateInfo = killResult['stateInfo'];
                                console.log(stateInfo);
                                node.html('<span class="label label-success">' + stateInfo + '</span>');
                            }
                        });
                    });
                    node.show();
                } else {
                    //秒杀未开启
                    var nowTime = exposer['now'];
                    var startTime = exposer['start'];
                    var endTime = exposer['end'];
                    secKill.countdown(secKillId, nowTime, startTime, endTime);
                }
            } else {
                console.log('result:' + result);
            }
        })
    },
    //计时判断
    countdown: function (secKillId, nowTime, startTime, endTime) {
        var secKillBox = $("#secKill-box");
        if (nowTime > endTime) {
            //秒杀结束
            secKillBox.html('秒杀结束');
        } else if (nowTime < startTime) {
            //秒杀未开始
            var killTime = new Date(startTime + 1000);
            secKillBox.countdown(killTime, function (event) {
                var format = event.strftime('秒杀倒计时: %D天 %H时 %M分 %S秒');
                secKillBox.html(format);
            }).on('finish.countdown', function () {
                //获取秒杀地址
                secKill.handleSecKill(secKillId, secKillBox);
            })
        } else {
            //秒杀进行中
            secKill.handleSecKill(secKillId, secKillBox);
        }
    },

    //验证手机号
    validatePhone: function (phone) {
        if (phone && phone.length == 11 && !isNaN(phone)) {
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
            if (!secKill.validatePhone(killPhone)) {
                var killPhoneModal = $("#killPhoneModal");
                killPhoneModal.modal({
                    show: true,//显示弹出层
                    backdrop: 'static',//禁止位置关闭
                    keyboard: false,//关闭键盘事件
                });
                $("#killPhoneBtn").click(function () {
                    var inputPhone = $("#killPhone").val();
                    console.log("inputPhone:" + inputPhone);
                    if (secKill.validatePhone(inputPhone)) {
                        $.cookie('killPhone', inputPhone, {expires: 7, path: '/seckill'});
                        console.log("写入Cookie:" + inputPhone);
                        window.location.reload();
                    } else {
                        $('#killPhoneMessage').hide().html('<label class="label label-danger">手机号错误!</label>').show(300);
                    }
                });
            }
            //已经可以从cookie中获取手机号码
            //计时交互
            var startTime = params['startTime'];
            var endTime = params['endTime'];
            var secKillId = params['secKillId'];
            $.post(secKill.URL.now(), {}, function (result) {
                if (result && result['success']) {
                    var nowTime = result['data'];
                    //时间判断
                    secKill.countdown(secKillId, nowTime, startTime, endTime);
                } else {
                    console.log('result:' + result);
                }
            });

        }
    }

}