function post() {
    var question_id=$("#question_id").val();
    var comment=$("textarea[name=comment]").text();
    $.ajax({
        url:"/comment",
        type:"post",
        dataType:"json",
        contentType:"application/json",
        data:JSON.stringify({parentId:question_id,content:comment,type:1}),
        success:function (data) {
            if(data.code==200){
               //成功
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
            console.log(data);

        }


    })
}