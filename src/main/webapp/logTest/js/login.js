/**
 * Created by kuzi on 2017/11/20.
 */
    function check_login()
    {
        var userName=$("#user_name").val();
        var passWord=$("#password").val();

        if(!userName){
            $("#user_name").removeClass('shake_effect');
            setTimeout(function () {
                $("#user_name").addClass('shake_effect')
            }, 1);
            return;
        }
        if(!passWord){
            $("#password").removeClass('shake_effect');
            setTimeout(function () {
                $("#password").addClass('shake_effect')
            }, 1);
            return;
        }
        $.ajax({
            type:"POST",
            url:"/login.do",
            data:{"userName":userName,"passWord":passWord},
            success:function(data){
                if(data.returnCode == "000000"){
                    alert("登录成功！欢迎您："+data.userInfo.username);
                }else{
                    alert(data.returnMsg);
                }
            }
        });
    }
function check_register() {
    var name = $("#r_user_name").val();
    var pass = $("#r_password").val();
    var email = $("#r_emial").val();

    if(!name){
        $("#r_user_name").removeClass('shake_effect');
        setTimeout(function () {
            $("#r_user_name").addClass('shake_effect')
        }, 1);
        return;
    }
    if(!pass){
        $("#r_password").removeClass('shake_effect');
        setTimeout(function () {
            $("#r_password").addClass('shake_effect')
        }, 1);
        return;
    }
    if(!email){
        $("#r_emial").removeClass('shake_effect');
        setTimeout(function () {
            $("#r_emial").addClass('shake_effect')
        }, 1);
        return;
    }
    $.ajax({
        type: "POST",
        url: "/register.do",
        data: {"userName": name, "passWord": pass, "email": email},
        success: function (data) {
            if (data.returnCode == "000000") {
                alert("注册成功！");
                $("#user_name").val("");
                $("#password").val("");
            }
            else {
                alert(data.returnMsg);
            }
        }
    });
}
function wxlogin(){
    window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx9e912446bc023f86&redirect_uri=http%3A%2F%2Fu42nzg.natappfree.cc%2FlogTest%2Fwxlogin.html&response_type=code&scope=snsapi_userinfo&state=s";
}
$(function(){
    $("#create").click(function(){
        check_register();
        return false;
    })
    $("#login").click(function(){
        check_login();
        return false;
    })
    $('.message a').click(function () {
        $('form').animate({
            height: 'toggle',
            opacity: 'toggle'
        }, 'slow');
    });
    $("#wxlogin").click(function(){
        wxlogin();
    })
})