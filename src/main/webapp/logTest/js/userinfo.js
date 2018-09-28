
$(function(){
    function getQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = decodeURI(window.location.search.substr(1)).match(reg);
        if (r != null) return r[2]; return null;
    }

    var fileUrl = window.location.host;
    var code = getQueryString("code");
    if(code){
        $.ajax({
            url:'http://'+fileUrl+'/UserInfo.do',
            data:{code:code},
            type:'GET',
            dataType:'json',
            success:function(data){
                if(data.returnCode == "000000"){
                    alert("昵称："+data.user.username+",头像:"+data.user.remark);
                    sessionStorage.setItem("name",data.user.username);
                    sessionStorage.setItem("myphoto",data.user.remark);
                    location.href = "userinfo.html";
                }else{
                    alert(data.returnMsg);
                }
            }
        });
    }else{
        alert("扫码失败，请后退重试");
    }
});
