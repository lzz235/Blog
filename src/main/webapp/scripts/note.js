/**
 * 加载笔记本对应的笔记
 */
function loadBookNotes() {
    //1.获取请求参数
    //清除所有选中
    $("#book_ul").find("a").removeClass("checked");
    var bookId = $(this).data("bookId");
    //选中本身
    $(this).find("a").addClass("checked");
    //2.参数格式效验
    //3.发送Ajax
    $.ajax({
        url:base_path+"/note/loadNotes.do",
        type:"post",
        data:{"bookId":bookId},
        dataType:"json",
        success:function (result) {
            //添加数据之前先清空数据
            $("#note_ul").empty();//empty清空内容保留结构，remove是直接删除结构
            if (result.status == 0){
                var notes = result.data;
                for (var i = 0; i < notes.length; i++) {
                    var noteTitle = notes[i].cn_note_title;
                    var noteId = notes[i].cn_note_id;
                    createNoteLi(noteTitle,noteId);
                }
            }
        },
        error:function () {
            alert("显示笔记异常");
        }
    });
}

/**
 * 创建笔记li
 * @param noteTitle
 * @param noteId
 */
function createNoteLi(noteTitle,noteId) {
    var sli = '';
    sli += '<li class="online">';
    sli += '<a>';
    sli += '<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+noteTitle+'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
    sli += '</a>';
    sli += '<div class="note_menu" tabindex=\'-1\'>';
    sli += '<dl>';
    sli += '<dt><button type="button" class="btn btn-default btn-xs btn_move" title=\'移动至...\'><i class="fa fa-random"></i></button></dt>';
    sli += '<dt><button type="button" class="btn btn-default btn-xs btn_share" title=\'分享\'><i class="fa fa-sitemap"></i></button></dt>';
    sli += '<dt><button type="button" class="btn btn-default btn-xs btn_delete" title=\'删除\'><i class="fa fa-times"></i></button></dt>';
    sli += '</dl>';
    sli += ' </div>';
    sli += '</li>';
    //将DOM对象转换为JQuery对象
    var $sli = $(sli);
    //将noteId绑定到li上
    $sli.data("noteId",noteId);
    //追加到父类元素上
    $("#note_ul").append($sli);
}

/**
 * 根据笔记id加载笔记详情
 */
function loadBookNote() {
    //1.获取请求参数
    $("#note_ul a").removeClass("checked");
    $(this).find("a").addClass("checked");
    var noteId = $(this).data("noteId");
    //2.参数格式效验
    //3.发送Ajax
    $.ajax({
        url:base_path+"/note/loadNote.do",
        type:"post",
        data:{"noteId":noteId},
        dataType: "json",
        success:function (result) {
            if (result.status == 0){
                var note = result.data;
                $("#input_note_title").val(note.cn_note_title);
                //使用富文本编译器设置body
                um.setContent(note.cn_note_body);
            }
        },
        error:function () {
            alert("笔记详情显示异常");
        }
    });
}
/**
 * 保存笔记
 */
function updateNote() {
    //1.获取请求参数
    var title = $("#input_note_title").val().trim();
    var body = um.getContent();
    var $li = $("#note_ul a.checked").parent();
    var noteId = $li.data("noteId");
    //2.参数格式效验
    if ($li.length == 0){
        alert("请选择要保存的笔记");
    }else if (title == ""){
        $("#note_title_span").html("<font color='red'>标题不能为空</font>");
    }else {
        //3.发送Ajax
        $.ajax({
            url:base_path+"/note/update.do",
            type:"post",
            data:{"title":title,"body":body,"noteId":noteId},
            dataType:"json",
            success:function (result) {
                //更新笔记列表中得li元素
                var sli = '';
                sli += '<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+title+'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
                //将选中里元素的a内容替换成sli
                $li.find("a").html(sli);
                //提示保存信息
                alert(result.msg);
            },
            error:function () {
                alert("更新笔记异常");
            }
        });
    }

}

/**
 * 笔记的创建
 */
function addNote() {
    //1.获取请求参数
    var userId = getCookie("uid");
    var $li = $("#book_ul a.checked").parent();
    var bookId = $li.data("bookId");
    var noteTitle = $("#input_note").val().trim();
    $("#note_span").html("");
    //2.参数格式效验
    var ok = true;
    if(userId == ""){
        ok = false;
        window.location.href = "log_in.html";
    }
    if (noteTitle == ""){
        ok = false;
        $("#note_span").html("笔记名不能为空");
    }
    if (ok){
        //3.发送Ajax
        $.ajax({
            url:base_path+"/note/add.do",
            type:"post",
            data:{"userId":userId,"bookId":bookId,"noteTitle":noteTitle},
            dataType:"json",
            success:function (result) {
                //1.关闭对话框
                closeAlertWindow();
                if (result.status == 0){
                    //2.添加新的note Li
                    var noteId = result.data.cn_note_id;
                    var noteTitle = result.data.cn_note_title;
                    createNoteLi(noteTitle,noteId);
                }
                //3.弹出提示信息
                alert(result.msg);
            },
            error:function () {
                alert("添加笔记异常");
            }
        });
    }
}

/**
 * 笔记菜单的显示
 */
function showMenu() {
    //下拉之前先隐藏
    hideMenu();
    //this:指的是下拉菜单按钮
    var $menu = $(this).parent().next();
    //下拉0.5秒
    $menu.slideDown(500);
    //清除选中
    $("#note_ul a").removeClass("checked");
    //选中
    $(this).parent().addClass("checked");
    //JQuery解决冒泡事件
    return false;
}
/**
 * 隐藏笔记菜单
 */
function hideMenu() {
    $("#note_ul div").hide();
}
/**
 * 删除笔记操作
 */
function deleteNote() {
    //1.获取请求参数
    var $li = $("#note_ul a.checked").parent();
    var noteId = $li.data("noteId");
    //2.参数格式效验
    //3.发送Ajax
    $.ajax({
        url:base_path+"/note/delete.do",
        type:"post",
        data:{"noteId":noteId},
        dataType:"json",
        success:function (result) {
            //1.关闭删除对话框
            if (result.status == 0){
                //2.
                $li.remove();
            }
            //3.提示信息
            alert(result.msg);
        },
        error:function () {
            alert("删除笔记异常");
        }
    });
}
