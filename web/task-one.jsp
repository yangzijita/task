<%@page import="cn.lyscolar.ly50.javabean.*,java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>



<%
    //从request取出强制类型转换

    List<Task> taskList=(ArrayList<Task>)request.getAttribute("tasklist");
    Task task=(Task) request.getAttribute("task");
    String level=null;
    if(taskList!=null){
        int length=taskList.size();
        out.print("flist size:"+length);
    }
%>

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
</head>

<body>
    <nav class="navbar navbar-expand navbar-light bg-light">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">

            <span class="navbar-toggler-icon"></span></button>

        <a class="navbar-brand" href="#">个人任务管理系统</a>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="navbar-nav">
                <a id=" modal-new" href="#modal-container-new" role="button" class="btn" data-toggle="modal">新建任务</a>

                <li class="nav-item">
                    <a class=" nav-link" href="">当前任务</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="">历史任务</a>
                </li>
            </ul>
            <form class="form-inline"><input class="form-control mr-sm-2" type="text" /><button
                    class="btn   btn-primary my-2 my-sm-0" type="submit">搜索</button></form>
            <ul class="navbar-nav   ml-md-auto">
                <%
                    String username=(String) session.getAttribute("username");
                    if (username==null||username.equals("")) {
                        response.sendRedirect("index.jsp");
                    }else {
                %>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="" id="navbarDropdownMenuLink" data-toggle="dropdown">
                        <%=username%>
                        <% }
                        %>
                    </a>
                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownMenuLink"><a
                            id="modal-personal" href="#modal-container-personal" role="button" class="btn"
                            data-toggle="modal">修改个人信息</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" data-toggle="modal" data-target="#myModal" id="logout">退出</a>
                        <%--                        <button type="button"  data-toggle="modal" data-target="#myModal">--%>
                        <%--                            退出--%>
                        <%--                        </button>--%>

                    </div>
                </li>
            </ul>
        </div>
    </nav>

    <!--退出-->
    <div class="modal fade" id="myModal">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">

                <!-- 模态框头部 -->
                <div class="modal-header">
                    <h4 class="modal-title">注销用户</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <!-- 模态框主体 -->
                <div class="modal-body">
                    您真的要退出当前用户？
                </div>
                <!-- 模态框底部 -->
                <div class="modal-footer">
                    <button type="submit" class="btn btn-secondary"
                        onclick="window.location.href=('<%=request.getContextPath()%>/Logout')"
                        data-dismiss="modal">确定</button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                </div>

            </div>
        </div>
    </div>


    <!--修改个人信息-->

    <div class="row">

        <div class="col-md-12">

            <div class="modal   fade" id="modal-container-personal" role="dialog" aria-labelledby="myModalLabel"
                aria-hidden="true">

                <div class="modal-dialog" role="document">

                    <div class="modal-content">

                        <div class="modal-header">

                            <h5 class="modal-title" id="ModalLabel">修改个人信息</h5>

                            <button type="button" class="close" data-dismiss="modal">

                                <span aria-hidden="true">×</span>

                            </button>

                        </div>

                        <div class="modal-body">
                            <form role="form" action="<%=request.getContextPath()%>/UpdatapwdServlet" method="get"
                                name="reg" id="personal_form">

                                <input type="hidden" name="id" value="" />

                                <input type="hidden" name="name" value="" />

                                <div class="form-group"><label for="personal_realname"> 真实姓名 </label> <input type="text"
                                        name="up_realname" autocomplete="realname" class="form-control" value=""
                                        id="personal_realname" /></div>
                                <div class="form-group"><label for="personal_password"> 密码 </label> <input
                                        type="password" name="up_password" autocomplete="new-password" value=""
                                        class="form-control" id="personal_password" /></div>
                                <div class="form-group">
                                    <label for="personal_password"> 确认密码 </label> <input type="password"
                                        name="up_password2" autocomplete="current-password" class="form-control"
                                        value="" id="personal_password2" />

                                </div>
                                <div class="modal-footer"><button type="submit" class="btn   btn-primary"
                                        id="personal_send">修改</button><button type="button" class="btn btn-secondary"
                                        data-dismiss="modal">关闭</button></div>

                            </form>

                        </div>


                    </div>

                </div>
            </div>
        </div>
    </div>


    <div class="row">

        <div class="col-md-12">

            <div class="modal   fade" id="modal-container-new" role="dialog" aria-labelledby="myModalLabel"
                aria-hidden="true">

                <div class="modal-dialog" role="document">

                    <div class="modal-content">

                        <div class="modal-header">

                            <h5 class="modal-title" id="myModalLabel">新建任务</h5><button type="button" class="close"
                                data-dismiss="modal"><span aria-hidden="true">×</span>

                            </button>

                        </div>

                        <div class="modal-body">

                            <form role="form" action="<%=request.getContextPath()%>/AddTaskServlet" method="get"
                                id="new_task_form">
                                <div class="form-group">

                                    <label for="task_name"> 任务名称 </label> <input type="text" class="form-control"
                                        name="task_name" id="task_name" /> </div>
                                <div class="form-group"><label for="task_desc"> 任务描述 </label><textarea
                                        name="task_description" class="form-control" id="task_desc"></textarea>

                                </div>

                                <div class="form-group"><label for="task_level">任务级别</label> <select
                                        class="form-control" name="task_level" id="task_level">
                                        <option value="1">特急</option>

                                        <option value="2">紧急</option>

                                        <option value="3">常规</option>
                                    </select></div>

                                <div class="form-group">

                                    <!-- datetime-local   -->

                                    <label for="task_due">截至日期</label> <input type="datetime-local" class="form-control"
                                        name="task_due" id="task_due" /></div>


                                <div class="modal-footer"><button type="submit" class="btn btn-primary"
                                        id="task_new">新建</button><button type="button" class="btn btn-secondary"
                                        data-dismiss="modal">关闭</button>

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
            <!-- 表格开头 -->
            <div class="col-lg-12">
                <div class="card">

                    <div class="card-body">
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th scope="col">任务名称</th>
                                <th scope="col">任务描述</th>
                                <th scope="col">紧急程度</th>
                                <th scope="col">截止日期</th>
                                <th scope="col">操作</th>
                            </tr>
                            </thead>
                            <%
                                for(int i=0;i<taskList.size();i++){
                                Task task1=taskList.get(i);
                                if (task1.getLevel().equals("1")){
                                    level="特急";
                                }else if(task1.getLevel().equals("2")){
                                    level="紧急";
                                }else{
                                    level="常规";
                                }

                            %>
                            <tbody style="background:#C1E1CA">

                            <tr>
                                <td><%=task1.getName()%></td>
                                <td><%=task1.getDescription()%></td>
                                <td><%=level%></td>

                                <td><%=task1.getEnd()%></td>

                                <td>

                                    <button type="button" class="btn btn-primary btn-sm">开始</button>

                                    <button type="button" class="btn btn-primary btn-sm">结束</button>
                                </td>
                            </tr>

                            </tbody>
                            <%
                                }
                            %>
                        </table>
                    </div>
                </div>



                <!-- 表格结尾 -->


            </div>
        </div>

    </main>

</body>

</html>