/**
 * 根据用户id查询笔记本信息
 */
function loadUserBooks() {
    //1.获取请求参数
    //获取用户Id
    var uid = getCookie("uid");
    //2.获取请求效验,如果uid为空跳转到登录
    if (uid == null){
        window.location.href="log_in.html";
    }
    //3.发送aJax
    $.ajax({
        url:base_path+"/book/loadbooks.do",
        type:"post",
        data:{"userId":uid},
        dataType:"json",
        success:function (result) {
            if (result.status == 0){
                var books = result.data;
                for (var i = 0; i < books.length; i++) {
                    var bookName = books[i].cn_notebook_name;
                    var bookId = books[i].cn_notebook_id;
                    createBookLi(bookId,bookName);
                }
            }
        },
        error:function () {
            alert("查询笔记本异常");
        }
    });
}
/**
 *创建笔记Li
 */
function createBookLi(bookId,bookName) {
    var lis = '';
    lis += '<li class="online">';
    lis += '<a>';
    lis += '<i class="fa fa-book" title="online" rel="tooltip-bottom">';
    lis += '</i> '+bookName+'</a></li>';
    //将js转换为jQuery，将DOM对象转换为jQuery对象
    var $lis = $(lis);
    //将js转换为jQuery，要使用该方法必须将DOM对象转换为jQuery对象
    //给li绑定bookId数据
    $lis.data("bookId",bookId);
    //在book_ul下追加li
    $("#book_ul").append($lis);
}
/**
 * 笔记本的创建
 */
function addBook() {
    //1.获取请求参数
    //笔记本名称
    var bookName = $("#input_notebook").val().trim();
    //用户ID
    var userId = getCookie("uid");
    //2.参数格式效验
    var ok = true;
    if (bookName == ""){
        ok = false;
        $("#notebook_span").html("笔记本名称为空");
    }
    if (userId == null){
        ok = false;
        window.location.href = "log_in.html";
    }
    if (ok){
        //3.发送Ajax
        $.ajax({
            url:base_path+"/book/add.do",
            type:"post",
            data:{"bookName":bookName,"userId":userId},
            dataType: "json",
            success:function (result) {
                //1.关闭对话框
                closeAlertWindow();
                if (result.status == 0){
                    var booId = result.data.cn_notebook_id;
                    //2.将新创建的笔记本添加到li中
                    createBookLi(booId,bookName);
                }
                    //3.弹出提示框
                    alert(result.msg);
            },
            error:function () {
                alert("创建笔记本异常");
            }
        });
    }
}
/**
 * 笔记本的重命名
 */
function rename() {
    //1.获取请求参数
    var rename = $("#input_notebook_rename").val().trim();
    var $li = $("#book_ul a.checked").parent();
    var bookId = $li.data("bookId");
    var ok = true;
    //2.参数格式效验
    if (rename == ""){
        ok = false;
        $("#notebook_rename_span").html("笔记本名称为空");
    }
    if (bookId == null){
        ok = false;
        $("#notebook_rename_span").html("没有查询到该笔记本");notebook_del_span
    }
    if (ok){
        //3.发送ajax
        $.ajax({
            url:base_path+"/book/rename.do",
            type:"post",
            data:{"rename":rename,"bookId":bookId},
            dataType:"json",
            success:function (result) {
                //关闭对话框
                closeAlertWindow();
                if (result.status == 0){
                    var li = '';
                    li += '<i class="fa fa-book" title="online" rel="tooltip-bottom">';
                    li += '</i> '+rename+'</a></li>';
                    //将选中里元素的a内容替换成li
                    $li.find("a").html(li);
                }
                //提示信息
                alert(result.msg);
            },
            error:function () {
                alert("重命名笔记本异常");
            }
        });
    }
}
/**
 * 笔记本的删除
 */
function delBook() {
    //1.获取请求参数
    var $li = $("#book_ul a.checked").parent();
    var bookId =$li.data("bookId");
    //2.参数格式效验
    var ok = true;
    if (bookId == ""){
        ok = false;
        $("#notebook_del_span").html("没有查询到该笔记本");
    }
    if (ok){
        //3.发送Ajax
        $.ajax({
            url:base_path+"/book/delete.do",
            type:"post",
            data:{"bookId":bookId},
            dataType:"json",
            success:function (result) {
                closeAlertWindow();
                if (result.status == 0){
                    $li.remove();
                }
                alert(result.msg);
            },
            error:function () {
                alert("删除笔记本异常");
            }
        });
    }
}
/**
 * 笔记本的收藏
 */
function likeBook() {
    //1.获取请求参数
    var $li = $("#book_ul a.checked").parent();
    var bookId = $li.data("bookId");
    //2.参数格式效验
    var ok = true;
    if (bookId == ""){
        ok = false;
        $("#notebook_like_span").html("笔记本不存在");
    }
    if (ok){
        //3.发送Ajax
        $.ajax({
            url:base_path+"/book/like.do",
            type:"post",
            data:{"bookId":bookId},
            dataType:"json",
            success:function (result) {
                closeAlertWindow();
                alert(result.msg);
            },
            error:function () {
                alert("收藏异常");
            }
        });
    }
}

