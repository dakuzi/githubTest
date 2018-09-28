
$(function(){

    var name=sessionStorage.getItem("name");
    var myphoto=sessionStorage.getItem("myphoto");
    if(myphoto==null||myphoto==''){
        myphoto='images/wx.png';
    }
    if(name){
        $("#login_form").empty();
        html='<img width="30" height="30" src="'+myphoto+'" />'+
            '<br><a>'+name+'</a>'+
            '<a href="javascript:;" class="admin-header-user">'+
            '<br><span onclick=loginOut();>注销</span>'+
            '</a>';
        $("#login_form").html(html);
    }else{

    }
});
function loginOut(){
    sessionStorage.clear();
    location.href="index.html";
}