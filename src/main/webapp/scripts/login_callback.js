$(function () {
    $("#last_password").val("");
    $("#new_password").val("");
    $("#final_password").val("");
    $("#changePassword").click(rePassword);
})
function rePassword() {
    //1.获取参数
    var lastPwd = $("#last_password").val().trim();
    var newPwd = $("#new_password").val().trim();
    var fPwd = $("#final_password").val().trim();
    var uname = getCookie("uname");
    //2.参数格式效验
    //2.对请求进行格式效验
    $("#warning_1 span").html("");
    $("#warning_2 span").html("");
    $("#warning_3 span").html("");
    var ok = true;
    if (uname == null){
        alert("请先登录");
        window.location.href="log_in.html";
    }
    if(lastPwd == ""){
        ok = false;
        $("#warning_1").show("warning");
        $("#warning_1 span").html("原始密码为空");
    }
    if (newPwd == ""){
        ok = false;
        $("#warning_2").show("warning");
        $("#warning_2 span").html("新密码不能为空");
    }else if(newPwd.length <6){
        ok = false;
        $("#warning_2").show("warning");
        $("#warning_2 span").html("新密码长度过短");
    }else if (fPwd != newPwd){
        ok = false;
        $("#warning_3").show("warning");
        $("#warning_3 span").html("密码输入不一致");
    }
    if (ok){
        //3.发送Ajax
        $.ajax({
            url:base_path+"/user/rePwd.do",
            type:"post",
            data:{"lastPwd":lastPwd,"newPwd":newPwd,"uname":uname},
            dataType:"json",
            success:function (result) {
                if (result.status == 1){
                    $("#warning_1").show("warning");
                    $("#warning_1 span").html(result.msg);
                }else if (result.status == 0){
                    alert(result.msg);
                    window.location.href='log_in.html';
                }else {
                    alert(result.msg);
                }
            },
            error:function () {
                alert("修改密码异常");
            }
        });
    }
}