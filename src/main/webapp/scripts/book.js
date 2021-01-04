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