var Jser = {
    wximgUrl: "http://www.hexun.com/images/logo_pic.jpg",
    wxnoncestr: "hexun",
    wxtitle: "和讯网",
    wxdescContent: "和讯网",
    wxSha1: function (msg) {
        /**
         *
         *  (SHA1)
         *
         **/
        function rotate_left(n, s) {
            var t4 = (n << s) | (n >>> (32 - s));
            return t4;
        }

        function lsb_hex(val) {
            var str = "";
            var i;
            var vh;
            var vl;

            for (i = 0; i <= 6; i += 2) {
                vh = (val >>> (i * 4 + 4)) & 0x0f;
                vl = (val >>> (i * 4)) & 0x0f;
                str += vh.toString(16) + vl.toString(16);
            }
            return str;
        }

        function cvt_hex(val) {
            var str = "";
            var i;
            var v;

            for (i = 7; i >= 0; i--) {
                v = (val >>> (i * 4)) & 0x0f;
                str += v.toString(16);
            }
            return str;
        }


        function Utf8Encode(string) {
            string = string.replace(/\r\n/g, "\n");
            var utftext = "";

            for (var n = 0; n < string.length; n++) {

                var c = string.charCodeAt(n);

                if (c < 128) {
                    utftext += String.fromCharCode(c);
                }
                else if ((c > 127) && (c < 2048)) {
                    utftext += String.fromCharCode((c >> 6) | 192);
                    utftext += String.fromCharCode((c & 63) | 128);
                }
                else {
                    utftext += String.fromCharCode((c >> 12) | 224);
                    utftext += String.fromCharCode(((c >> 6) & 63) | 128);
                    utftext += String.fromCharCode((c & 63) | 128);
                }

            }

            return utftext;
        }

        var blockstart;
        var i, j;
        var W = new Array(80);
        var H0 = 0x67452301;
        var H1 = 0xEFCDAB89;
        var H2 = 0x98BADCFE;
        var H3 = 0x10325476;
        var H4 = 0xC3D2E1F0;
        var A, B, C, D, E;
        var temp;

        msg = Utf8Encode(msg);

        var msg_len = msg.length;

        var word_array = new Array();
        for (i = 0; i < msg_len - 3; i += 4) {
            j = msg.charCodeAt(i) << 24 | msg.charCodeAt(i + 1) << 16 |
                msg.charCodeAt(i + 2) << 8 | msg.charCodeAt(i + 3);
            word_array.push(j);
        }

        switch (msg_len % 4) {
            case 0:
                i = 0x080000000;
                break;
            case 1:
                i = msg.charCodeAt(msg_len - 1) << 24 | 0x0800000;
                break;

            case 2:
                i = msg.charCodeAt(msg_len - 2) << 24 | msg.charCodeAt(msg_len - 1) << 16 | 0x08000;
                break;

            case 3:
                i = msg.charCodeAt(msg_len - 3) << 24 | msg.charCodeAt(msg_len - 2) << 16 | msg.charCodeAt(msg_len - 1) << 8 | 0x80;
                break;
        }

        word_array.push(i);

        while ((word_array.length % 16) != 14) word_array.push(0);

        word_array.push(msg_len >>> 29);
        word_array.push((msg_len << 3) & 0x0ffffffff);


        for (blockstart = 0; blockstart < word_array.length; blockstart += 16) {

            for (i = 0; i < 16; i++) W[i] = word_array[blockstart + i];
            for (i = 16; i <= 79; i++) W[i] = rotate_left(W[i - 3] ^ W[i - 8] ^ W[i - 14] ^ W[i - 16], 1);

            A = H0;
            B = H1;
            C = H2;
            D = H3;
            E = H4;

            for (i = 0; i <= 19; i++) {
                temp = (rotate_left(A, 5) + ((B & C) | (~B & D)) + E + W[i] + 0x5A827999) & 0x0ffffffff;
                E = D;
                D = C;
                C = rotate_left(B, 30);
                B = A;
                A = temp;
            }

            for (i = 20; i <= 39; i++) {
                temp = (rotate_left(A, 5) + (B ^ C ^ D) + E + W[i] + 0x6ED9EBA1) & 0x0ffffffff;
                E = D;
                D = C;
                C = rotate_left(B, 30);
                B = A;
                A = temp;
            }

            for (i = 40; i <= 59; i++) {
                temp = (rotate_left(A, 5) + ((B & C) | (B & D) | (C & D)) + E + W[i] + 0x8F1BBCDC) & 0x0ffffffff;
                E = D;
                D = C;
                C = rotate_left(B, 30);
                B = A;
                A = temp;
            }

            for (i = 60; i <= 79; i++) {
                temp = (rotate_left(A, 5) + (B ^ C ^ D) + E + W[i] + 0xCA62C1D6) & 0x0ffffffff;
                E = D;
                D = C;
                C = rotate_left(B, 30);
                B = A;
                A = temp;
            }

            H0 = (H0 + A) & 0x0ffffffff;
            H1 = (H1 + B) & 0x0ffffffff;
            H2 = (H2 + C) & 0x0ffffffff;
            H3 = (H3 + D) & 0x0ffffffff;
            H4 = (H4 + E) & 0x0ffffffff;

        }

        var temp = cvt_hex(H0) + cvt_hex(H1) + cvt_hex(H2) + cvt_hex(H3) + cvt_hex(H4);

        return temp.toLowerCase();
    },
    // wxajax:function()
    // {
    //     var  ajaxurl = "//wapi.hexun.com/Api_getTicket.cc?callback=Jser.wxonload";
    //     jQuery.ajax({dataType: "jsonp", type: "get", url:ajaxurl, success: function (str) {
    //
    //         }
    //     });
    // },
    wxonload: function () {
        //注册
        var self = this;
        var wxdate = new Date();
        var wxtimestamp = wxdate.getFullYear() + wxdate.getMonth() + wxdate.getDate() + wxdate.getTime();
        var wxLink = document.location.href;
        var wxticket = "HoagFKDcsGMVCIY2vOjf9mgSJ0Qgo-UvIeGXqd-Vxf5nZ82YZE_ZrhxQyNrxN9X2aQ2fP3Vkg5y1k4lZ0vVXEg";
        var wxsignature = Jser.wxSha1("jsapi_ticket=" + wxticket + "&noncestr=" + self.wxnoncestr + "&timestamp=" + wxtimestamp + "&url=" + wxLink);
        wx.config({
            debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
            appId: 'wx9e912446bc023f86', // 必填，公众号的唯一标识
            timestamp: wxtimestamp, // 必填，生成签名的时间戳
            nonceStr: self.wxnoncestr, // 必填，生成签名的随机串
            signature: wxsignature,// 必填，签名，见附录1
            jsApiList: ['onMenuShareTimeline', 'onMenuShareAppMessage', 'onMenuShareQQ', 'onMenuShareWeibo'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
        });
        wx.ready(function () {
            // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
            //分享到朋友圈
            wx.onMenuShareTimeline({
                title: self.wxtitle, // 分享标题
                link: wxLink, // 分享链接
                imgUrl: self.wximgUrl, // 分享图标
                success: function () {
                    // 用户确认分享后执行的回调函数
                },
                cancel: function () {
                    // 用户取消分享后执行的回调函数
                }
            });
            //分享给朋友
            wx.onMenuShareAppMessage({
                title: self.wxtitle, // 分享标题
                desc: self.wxdescContent, // 分享描述
                link: wxLink, // 分享链接
                imgUrl: self.wximgUrl, // 分享图标
                type: 'link', // 分享类型,music、video或link，不填默认为link
                dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
                success: function () {
                    // 用户确认分享后执行的回调函数
                },
                cancel: function () {
                    // 用户取消分享后执行的回调函数
                }
            });
            //分享到QQ
            wx.onMenuShareQQ({
                title: self.wxtitle, // 分享标题
                desc: self.wxdescContent, // 分享描述
                link: wxLink, // 分享链接
                imgUrl: self.wximgUrl,// 分享图标
                success: function () {
                    // 用户确认分享后执行的回调函数
                },
                cancel: function () {
                    // 用户取消分享后执行的回调函数
                }
            });
            wx.onMenuShareWeibo({
                title: self.wxtitle, // 分享标题
                desc: self.wxdescContent, // 分享描述
                link: wxLink, // 分享链接
                imgUrl: self.wximgUrl, // 分享图标
                success: function () {
                    // 用户确认分享后执行的回调函数
                },
                cancel: function () {
                    // 用户取消分享后执行的回调函数
                }
            });
        });
    },
    wxinit: function (arr) {
        var _arr = arr;
        var self = this;
        typeof(_arr.wximgUrl) == "undefined" ? {} : self.wximgUrl = _arr.wximgUrl;
        typeof(_arr.wxnoncestr) == "undefined" ? {} : self.wxnoncestr = _arr.wxnoncestr;
        typeof(_arr.wxtitle) == "undefined" ? {} : self.wxtitle = _arr.wxtitle;
        typeof(_arr.wxdescContent) == "undefined" ? {} : self.wxdescContent = _arr.wxdescContent;
        // Jser.wxajax();
        Jser.wxonload();
    }
};

Jser.wxinit({
    "wximgUrl": "https://i4.hexun.com/2014-09-12/168424005.jpg",
    "wxnoncestr": "123456",
    "wxtitle": "金骏马奖揭晓 中加纯债债券获誉“最稳健基金奖”",
    "wxdescContent": "金骏马奖揭晓 中加纯债债券获誉“最稳健基金奖”"
});