//给按钮绑定单击事件
$(function () {
    $("#login").click(login);
})
//登录处理
function login() {
    //1.获取请求参数
    var username = $("#count").val().trim();
    var password = $("#password").val().trim();
    //添加JSCSS样式之前先去除对应的样式
    $("#count_span").html("");
    $("#password_span").html("");
    //2.参数格式效验
    var ok = true;
    if (username == ""){
        ok = false;
        $("#count_span").html("用户名为空");
    }
    if (password == ""){
        ok = false;
        $("#password_span").html("密码为空");
    }
    if (ok){
        //3.发送Ajax
        $.ajax({
            url:base_path+"/user/login.do",
            type:"post",
            data:{"username":username,"password":password},
            dataType:"json",
            success:function (result) {
                if (result.status == 0){
                    alert(result.msg);
                }else {
                    alert(result.msg)
                }
            },
            error:function () {
                alert("登录异常")
            }
        });
    }
}