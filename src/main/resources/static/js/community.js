/**
 * 提交回复
 */

function post() {
    var question_id=$("#question_id").val();
    var comment=$("#comment").text();
    comment2target(question_id,1,comment);

}

/**
 *
 */
function comment2target(targetId,type,content){
    if(!content){
        alert("回复内容不能为空");
        return;
    }

    $.ajax({
        url:"/comment",
        type:"post",
        dataType:"json",
        contentType:"application/json",
        data:JSON.stringify({parentId:targetId,content:content,type:type}),
        success:function (data) {
            console.log(data)
            if(data.code==200){
                //成功
                window.location.reload();
            }else if(data.code==2001){
                //未登录
                var r=confirm("用户未登录，是否登录");
                if(r==true){
                    window.localStorage.setItem("closeable",true);
                    window.open("https://github.com/login/oauth/authorize?client_id=115618a93b2b13719c3f&redirect_uri=http://localhost:8888/callback&scope=user&state=1");
                }else{
                    alert(data.message)
                }

            }

        }
    })

}
function comment(data){
    var id=data.getAttribute("data-id");
    var content=$("#input-"+id).val();
    comment2target(id,2,content);
}

/**
 * 展开二级评论
 */
function collapseComments(data) {
    var id= (data.getAttribute("data-id"));
    var commentID=$("#comment-"+id);
    var collapse = data.getAttribute("data-collapse");
    if(!collapse){
        //根据id查
        if(commentID.children().length!=2){

            data.setAttribute("data-collapse","in");
            commentID.addClass("in");
            data.classList.add("active");
        }else{
            $.getJSON( "/comment/"+id, function( e ) {
                $.each(e.data.reverse(),function (index,commentDTO) {


                    var img=$("<img/>",{
                        "class":"media-object img-rounded",
                        "src":commentDTO.user.avatarUrl
                    });

                    var media_left= $("<div/>",{
                        "class":"media-left"
                    }).append(img)

                    var media=$("<div/>",{
                        "class":"media media-comment"
                    })

                    media.append(media_left);
                    var media_body=$("<div/>",{
                        "class":"media-body"
                    });
                    var commentName=$("<span/>",{
                        html:commentDTO.user.name
                    })
                    var content=$("<span/>",{
                        html:commentDTO.content
                    })

                    media_body.append(commentName);
                    media_body.append("<br>");
                    media_body.append(content);

                    media_body.append($("<span/>",{
                        "class":"pure",
                        html:moment(commentDTO.gtmCreate).format("YYYY-MM-DD")
                    }))
                    media_body.append("<hr>")

                    media.append(media_body);



                    commentID.prepend(media);


                })
            });
            data.setAttribute("data-collapse","in");
            commentID.addClass("in");
            data.classList.add("active");
        }



    }else{
        data.removeAttribute("data-collapse");
        commentID.removeClass("in");
        data.classList.remove("active");

    }
}
function  showTag() {
    $("#publish-tag").show();
}

function Selection(data){
    var tagVal=data.getAttribute("data-toggle");
    var tag=$("#tag").val();
    if(tag.indexOf(tagVal)!=-1){

    }else{
        if(tag){
            $("#tag").val(tag+","+tagVal);
        }else{
            $("#tag").val(tagVal);
        }
    }
}
