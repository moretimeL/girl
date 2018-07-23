$(document).ready(function(){
    var url = location.href.split('#').toString();//url不能写死
    $.ajax({
        type : "get",
        url : "/wechatParam",
        dataType : "json",
        async : false,
        data:{url:url},
        success : function(data) {
            // wx.config({
            //     debug: false,////生产环境需要关闭debug模式
            //     appId: data.appid,//appId通过微信服务号后台查看
            //     timestamp: data.timestamp,//生成签名的时间戳
            //     nonceStr: data.nonceStr,//生成签名的随机字符串
            //     signature: data.signature,//签名
            //     jsApiList: [//需要调用的JS接口列表
            //         'checkJsApi',//判断当前客户端版本是否支持指定JS接口
            //         'onMenuShareTimeline',//分享给好友
            //         'onMenuShareAppMessage'//分享到朋友圈
            //     ]
            // });
        },
        error: function(xhr, status, error) {
            //alert(status);
            //alert(xhr.responseText);
        }
    });
    wx.ready(function () {
        var link = 'http://9wg2sy.natappfree.cc/login';
        var protocol = window.location.protocol;
        var host = window.location.host;
        //分享朋友圈
        wx.onMenuShareTimeline({
            title:'湖南省肿瘤医院高清手术直播',
            link: link,
            imgUrl:'http://9wg2sy.natappfree.cc/images/small_logo.png',// 自定义图标
            trigger: function (res) {
                // 不要尝试在trigger中使用ajax异步请求修改本次分享的内容，因为客户端分享操作是一个同步操作，这时候使用ajax的回包会还没有返回.
                //alert('click shared');
            },
            success: function (res) {
                //alert('shared success');
                //some thing you should do
            },
            cancel: function (res) {
                //alert('shared cancle');
            },
            fail: function (res) {
                //alert(JSON.stringify(res));
            }
        });
        //分享给好友
        wx.onMenuShareAppMessage({
            title:'湖南省肿瘤医院高清手术直播', // 分享标题
            desc:'“全国百佳医院”——湖南省肿瘤医院/中南大学湘雅医学院附属肿瘤医院是集医疗、科研、教学、预防、康复于一体的三级甲等肿瘤专科医院，是JCI国际标准认证医院。！', // 分享描述
            link:'http://9wg2sy.natappfree.cc/login', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
            imgUrl:'http://9wg2sy.natappfree.cc/images/small_logo.png', // 自定义图标
            type:'link', // 分享类型,music、video或link，不填默认为link
            dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
            success: function () {
                // 用户确认分享后执行的回调函数
            },
            cancel: function () {
                // 用户取消分享后执行的回调函数
            }
        });
        wx.error(function (res) {
            alert(res.errMsg);
        });
    });




    var myPlayer = videojs('my-player',{
        sources:[
            //  {type:'rtmp/flv',src:'rtmp://39.108.16.98/live/stream2'},
            {type:'application/x-mpegURL',src:'http://live.wang-guanjia.com/live/1.m3u8'}
        ]
    });

    //alert('tttttt');
    myPlayer.ready(function() {
        console.log(myPlayer);
        // myPlayer.src({type:'rtmp/flv',src:'rtmp://live.hkstv.hk.lxdns.com/live/hks'});
        console.log("player ready");
        myPlayer.play();
    });
});