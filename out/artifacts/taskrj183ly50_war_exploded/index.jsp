<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>666666666666</title>
    <link rel="stylesheet" href="static/css/bootstrap.min.css">
    <script src="./static/js/bootstrap.min.js"></script>
    <script src="./static/js/jQuery3.41.js"></script>
    <link href="https://cdn.bootcss.com/twitter-bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/twitter-bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <script>
        function validuser() {
            var username=document.regform.username.value;   //注意
            console.log("用户名："+username);
            //异步请求对象XMLHttpRequest
            var xhr;
            if(window.ActiveXObject){
                console.log("打开了ie浏览器");
                xhr=new ActiveXObject("XMLHTTP");
            }else if(window.XMLHttpRequest){
                console.log("打开了google浏览器");
                xhr=new XMLHttpRequest();
            }else{
                throw new Error("不支持ajax");
            }
            //回调函数
            xhr.onreadystatechange=function(){
                var validresult=document.getElementById("validresult");//注意
                if(xhr.readyState==4 && xhr.status==200){
                    //  alert("异步请求成功");
                    var flag=xhr.responseText;
                    //  alert("返回结果："+flag);

                    if(flag==1){
                        //可以注册
                        validresult.innerText="可以注册";
                    }else{
                        //已被注册
                        validresult.innerText="已被注册";
                    }

                }else {
                    validresult.innerText="失败"
                }
            }
            //打开
            xhr.open('get',"CheckNameServlet?username="+username);
            //发送
            xhr.send();

        }
    </script>
    <script>
        function reloadimage(){
            //传入时间参数防止缓存
            var time=new Date().getTime();
            document.getElementById("imagecode").src="<%=request.getContextPath()%>/getcode?d="+time;
            document.getElementById("imagecode1").src="<%=request.getContextPath()%>/getcode?d="+time;
            document.getElementById("imagecode2").src="<%=request.getContextPath()%>/getcode?d="+time;
        }

    </script>
</head>
<body>
<nav class="navbar navbar-expand navbar-light bg-light">

    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="navbar-toggler-icon"></span></button>
    <a class="navbar-brand" href="#">个人任务管理系统</a>
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="navbar-nav ml-md-auto">
            <li class="nav-item"><a id="modal-findpwd" href="#modal-container-findwd" role="button" class="btn"
                                    data-toggle="modal">邮箱找回密码</a></li>
            <li class="nav-item "><a id="modal-register" href="#modal-container-register" role="button" class="btn"
                    data-toggle="modal">注册</a></li>
            <li class="nav-item   "><a id="modal-login" href="#modal-container-login" role="button" class="btn"
                    data-toggle="modal">登录</a></li>


        </ul>

    </div>
</nav>

<!--注册模态框-->
<div class="row">
    <div class="col-md-12">
        <div class="modal fade" id="modal-container-register" role="dialog" aria-labelledby="myModalLabel"
            aria-hidden="true">
            <div class="modal-dialog" role="document">

                <div class="modal-content">

                    <div class="modal-header">

                        <h5 class="modal-title" id="myModalLabel">注册</h5>

                        <button type="button" class="close" data-dismiss="modal">

                            <span aria-hidden="true">×</span>

                        </button>

                    </div>
                    <div class="modal-body">
                        <form role="form" name="regform" action="<%=request.getContextPath()%>/reg" method="get"
                            name="reg" id="reg_form">
                            <div class="form-group">

                                <label for="name"> 登录名 </label> <input type="text" name="username" onblur="validuser()" autocomplete="name"
                                    class="form-control" id="reg_user" />

                                <div id="validresult"></div>

                            </div>
                            <div class="form-group"><label for="reg_realname"> 真实姓名 </label> <input type="text"
                                    name="Realname" autocomplete="username" class="form-control" id="reg_realname" />
                            </div>

                            <div class="form-group"><label for="reg_password"> 密码 </label> <input type="password"
                                    name="password" autocomplete="new-password" class="form-control"
                                    id="reg_password" /></div>

                            <div class="form-group"><label for="reg_password"> 确认密码 </label> <input type="password"
                                    name="password2" autocomplete="current-password" class="form-control"
                                    id="reg_password2" />

                            </div>
                            <div class="form-group">
                                <label> 验证码 </label>
                                <div style="display: flex;">
                                    <input type="text" name="checkcode" style="width: 52%;" class="form-control" />
                                    <img id="imagecode" style="width: 24%; margin-left: 20px" src="<%=request.getContextPath()%>/getcode" alt="验证码">
                                    <a href="javascript:reloadimage()">看不清</a>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="submit" class="btn btn-primary" id="register_send">
                                    注册
                                </button>
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
                            </div>
                        </form>

                    </div>



                </div>



            </div>



        </div>



    </div>

</div>
<!--注册模态框-->
<div class="row">
    <div class="col-md-12">
        <div class="modal   fade" id="modal-container-login" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">

            <div class="modal-dialog" role="document">

                <div class="modal-content">

                    <div class="modal-header">

                        <h5 class="modal-title" id="myModalLabel">登录</h5>

                        <button type="button" class="close" data-dismiss="modal">

                            <span aria-hidden="true">×</span>

                        </button>

                    </div>

                    <div class="modal-body">
                        <form role="form" method="post" action="<%=request.getContextPath()%>/Login"
                              id="logon_form">
                            <div class="form-group"><label for="loginname"> 用户名 </label> <input type="text" name="loginname"
                                                                                                autocomplete="name" class="form-control" id="loginname" /></div>
                            <div class="form-group">

                                <label for="loginpassword"> 密码 </label> <input type="password" name="loginpassword"
                                                                               autocomplete="current-password" class="form-control" id="loginpassword" /></div>

                            <div class="form-group">
                                <label> 验证码 </label>
                                <div style="display: flex;">
                                    <input type="text" name="logincheckcode" style="width: 52%;" class="form-control" />
                                    <img id="imagecode1" style="width: 24%; margin-left: 20px" src="<%=request.getContextPath()%>/getcode" alt="验证码">
                                    <a href="javascript:reloadimage()">看不清</a>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="submit" class="btn btn-primary" id="logon_send">
                                    登录</button>
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>

                            </div>
                        </form>
                    </div>



                </div>
            </div>

        </div>
    </div>

</div>
<%--findpwd--%>
<div class="row">
    <div class="col-md-12">
        <div class="modal   fade" id="modal-container-findwd" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">

            <div class="modal-dialog" role="document">

                <div class="modal-content">

                    <div class="modal-header">

                        <h5 class="modal-title" id="myModalLabel">找回注册用户密码</h5>

                        <button type="button" class="close" data-dismiss="modal">

                            <span aria-hidden="true">×</span>

                        </button>

                    </div>
                    <div class="modal-body">
                        <form role="form" method="post" action="/taskrj183ly50_war_exploded/findpwdServlet"
                              id="logon_form">
                            <div class="form-group"><label for="findname"> 用户名 </label> <input type="text" name="findname"
                                                                                                autocomplete="name" class="form-control" id="findname" /></div>
                            <div class="form-group">
                                <label for="loginpassword"> 邮箱 </label> <input type="email" name="findem"
                                                                               autocomplete="current-email" class="form-control" id="findem" /></div>

                            <div class="form-group">
                                <label> 验证码 </label>
                                <div style="display: flex;">
                                    <input type="text" name="findpwdcheckcode" style="width: 52%;" class="form-control" />
                                    <img id="imagecode2" style="width: 24%; margin-left: 20px" src="<%=request.getContextPath()%>/getcode" alt="验证码">
                                    <a href="javascript:reloadimage()">看不清</a>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="submit" class="btn btn-primary" id="logon_send">
                                    找回密码</button>
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>

                            </div>
                        </form>
                    </div>



                </div>
            </div>

        </div>
    </div>

</div>

<main role="main" class="container">

    <div class="row">

        <div class="col-md-12">

            <div class="jumbotron">

                <h2>

                    个人任务管理系统

                </h2>

                <p>

                    用于记录提醒人个工作及生活、学习等事务，用户可以自定义任务分类，不同任务分类使用不同图标显示。

                </p>

                <p>

                    <a class="btn   btn-primary btn-large" href="#">了解更多</a>

                </p>

            </div>

        </div>

    </div>


</main>




</body>

</html>