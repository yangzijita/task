---
title: jsp+servlet个人任务清单管理
date: 2020/03/03
author: Lee Sun
img: 'http://imgal.lyscholar.cn/new/1588763713569.png'
top: false
toc: true
mathjax: false
summary: '个人任务清单管理,基于jsp+servlet mvc开发'
categories: 文章分类
keywords: Java
descripiton: '个人任务清单管理,基于jsp+servlet mvc开发'
tags:
  - Java
  - servlet
abbrlink: 6eaf
cover:
coverImg:
password:
---

# Tip

基于jsp+servlet mvc开发

gayhub地址：https://github.com/yangzijita/task

blog：lyscholar.cn

# 开发环境

- bootstrap4
- jQuery3.41
- MySQL
- vscode
- idea
- Tomcat
- Javaee



# 数据库设计

## 新建一个数据库

create database 数据库名

或者使用图形化工具

## 创建数据表

```sql
CREATE TABLE users(
id INT PRIMARY KEY AUTO_INCREMENT,
username VARCHAR(255) ,
PASSWORD VARCHAR(255),
realname VARCHAR(100)) 
```



```sql
CREATE TABLE `task` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`userid`  int(11) NOT NULL ,
`name`  varchar(255) NULL ,
`description`  varchar(255) NULL ,
`level`  int NULL ,
`cost`  DEC(10,2) NULL ,
`due`  datetime NULL ,
`status`  int NULL ,
`start`  datetime NULL ,
`end`  datetime NULL ,
PRIMARY KEY (`id`, `userid`)
)
;
```



# MVC开发

![img](https://upload-images.jianshu.io/upload_images/2042707-220c020970455feb?imageMogr2/auto-orient/strip|imageView2/2/format/webp)

**web层（表现层），业务层，数据访问层**

**目的：高内聚，低耦合，方便后期维护**

## 创建目录结构

![1588763765251](http://imgal.lyscholar.cn/new/1588764770462.png)

## 目录说明

- cn.lyscolar.ly50.dao 数据访问层接口
- cn.lyscolar.ly50.daoimpl接口实现类
- cn.lyscolar.ly50.javabean 实体类
- cn.lyscolar.ly50.servlet  业务层
- cn.lyscolar.ly50.utils 工具类



**首页**



![1588763713569](http://imgal.lyscholar.cn/new/1588764757016.png)

**登录**



![1588763683345](http://imgal.lyscholar.cn/new/1588764732403.png)

**登录成功**



![1588763667595](http://imgal.lyscholar.cn/new/1588764726101.png)

**新建任务**



![1588763649406](http://imgal.lyscholar.cn/new/1588764747457.png)

**修改个人信息**



![1588763631529](http://imgal.lyscholar.cn/new/1588764706208.png)

**邮箱找回密码**



![email](http://imgal.lyscholar.cn/new/1588764681977.png)

**注册**



![reg](http://imgal.lyscholar.cn/new/1588764676207.png)

# 前端代码

## 首页

**使用bootstrap开发**

```html
	<link rel="stylesheet" href="static/css/bootstrap.min.css">
    <script src="/static/js/bootstrap.min.js"></script>
    <script src="/static/js/jquery-3.2.1.min.js"></script>
```

*以上三句是bootstrap使用的基础*



```html
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>666666666666</title>
    <link rel="stylesheet" href="static/css/bootstrap.min.css">
    <script src="/static/js/bootstrap.min.js"></script>
    <script src="/static/js/jQuery3.41.js"></script>
    <link href="https://cdn.bootcss.com/twitter-bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/twitter-bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<nav class="navbar navbar-expand navbar-light bg-light">

    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="navbar-toggler-icon"></span></button>
    <a class="navbar-brand" href="#">个人任务管理系统</a>
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="navbar-nav ml-md-auto">
            <li class="nav-item "><a id="modal-register" href="#modal-container-register" role="button" class="btn"
                    data-toggle="modal">注册</a></li>
            <li class="nav-item   "><a id="modal-login" href="#modal-container-login" role="button" class="btn"
                    data-toggle="modal">登录</a></li>
            <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="" id="navbarDropdownMenuLink"
                    data-toggle="dropdown"></a>
                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownMenuLink"><a
                        id="modal-personal" href="#modal-container-personal" role="button" class="btn"
                        data-toggle="modal">修改个人信息</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="#" id="logout">退出</a>
                </div>
            </li>
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
                        <form role="form" action="${pageContext.servletContext.contextPath}/register.do" method="post"
                            name="reg" id="reg_form">
                            <div class="form-group">

                                <label for="name"> 登录名 </label> <input type="text" name="name" autocomplete="name"
                                    class="form-control" id="reg_user" /></div>

                            <div class="form-group"><label for="reg_realname"> 真实姓名 </label> <input type="text"
                                    name="realname" autocomplete="username" class="form-control" id="reg_realname" />
                            </div>

                            <div class="form-group"><label for="reg_password"> 密码 </label> <input type="password"
                                    name="password" autocomplete="new-password" class="form-control"
                                    id="reg_password" /></div>

                            <div class="form-group"><label for="reg_password"> 确认密码 </label> <input type="password"
                                    name="password2" autocomplete="current-password" class="form-control"
                                    id="reg_password2" />

                            </div>

                        </form>

                    </div>

                    <div class="modal-footer">

                        <button type="button" class="btn btn-primary" id="register_send">

                            注册</button>

                        <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>

                    </div>

                </div>



            </div>



        </div>



    </div>

</div>
<!--注册模态框-->
<div class="row">
    <div class="col-md-12">

        <div class="modal fade" id="modal-container-personal" role="dialog" aria-labelledby="myModalLabel"
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
                        <form role="form" action="${pageContext.servletContext.contextPath}/modifypersonal.do"
                            method="post" name="reg" id="personal_form">

                            <input type="hidden" name="id" value="${user.id}" />

                            <input type="hidden" name="name" value="${user.name}" />

                            <div class="form-group"><label for="personal_realname"> 真实姓名 </label> <input type="text"
                                    name="realname" autocomplete="realname" class="form-control"
                                    value="${user.realname}" id="personal_realname" /></div>
                            <div class="form-group">label for="personal_password"> 密码 </label> <input type="password"
                                    name="password" autocomplete="new-password" value="${user.password}"
                                    class="form-control" id="personal_password" /></div>
                            <div class="form-group">
                                <label for="personal_password"> 确认密码 </label> <input type="password" name="password2"
                                    autocomplete="current-password" class="form-control" value="${user.password}"
                                    id="personal_password2" />
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer"><button type="button" class="btn   btn-primary"
                            id="personal_send">修改</button><button type="button" class="btn btn-secondary"
                            data-dismiss="modal">关闭</button></div>
                </div>

            </div>
        </div>
    </div>
</div>
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
                        <form role="form" method="post" action="${pageContext.servletContext.contextPath}/login.do"
                            id="logon_form">
                            <div class="form-group"><label for="name"> 用户名 </label> <input type="text" name="name"
                                    autocomplete="name" class="form-control" id="username" /></div>
                            <div class="form-group">

                                <label for="password"> 密码 </label> <input type="password" name="password"
                                    autocomplete="current-password" class="form-control" id="password" /></div>
                        </form>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" id="logon_send">
                            登录</button>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>

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

                        <form role="form" id="new_task_form">
                            <div class="form-group">

                                <label for="task_name"> 任务名称 </label> <input type="text" class="form-control"
                                    name="name" id="task_name" /> </div>
                            <div class="form-group"><label for="task_desc"> 任务描述 </label><textarea name="description"
                                    class="form-control" id="task_desc"></textarea>

                            </div>

                            <div class="form-group"><label for="task_level">任务级别</label> <select class="form-control"
                                    name="level" id="task_level">
                                    <option value="1">特急</option>

                                    <option value="2">紧急</option>

                                    <option value="3">常规</option>
                                </select></div>

                            <div class="form-group">

                                <!-- datetime-local   -->

                                <label for="task_due">截至日期</label> <input type="datetime-local" class="form-control"
                                    name="due" id="task_due" /></div>
                        </form>
                    </div>

                    <div class="modal-footer"><button type="button" class="btn   btn-primary"
                            id="task_new">新建</button><button type="button" class="btn btn-secondary"
                            data-dismiss="modal">关闭</button>

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


<body>

</body>

</html>
```



# 后端代码

## 注册功能





![reg](http://imgal.lyscholar.cn/new/1588764655500.png)

### utils包创建数据库访问类

------

**DBhelper.java**

```java
package cn.lyscolar.ly50.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by 24794 on 2020/2/20.
 */
public class DBhelper {
        private  static  final String DRIVER="com.mysql.jdbc.Driver";
        private static final String URL="jdbc:mysql://localhost:3306/task_db?useUnicode=true&amp;characterEncoding=UTF-8";
        private static final String USERNAME="root";
        private static final String PASSWORD="xxxx";
    private static Connection conn=null;
    static {
        //注册驱动
        try {
            Class.forName(DRIVER);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
        //单例模式，返回数据库连接对象
        public static Connection getConn() throws SQLException{
            if(conn==null){
                conn= DriverManager.getConnection(URL,USERNAME,PASSWORD);

            }
            return conn;
        }
    public static  void  main(String [] args){
        try {
            Connection conn=DBhelper.getConn();
            if (conn!=null){
                System.out.print("数据库连接成功");
            }else {
                System.out.print("数据库连接失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
```

### javabean包创建注册实体类

**Reg.java**

```java
package cn.lyscolar.ly50.javabean;

public class Reg {
    private int id;
    private String username;
    private String password;
    private String realname;
    public Reg(){
        super();
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRealname() {
        return realname;
    }
    public void setRealname(String realname) {
        this.realname = realname;
    }

}
```



### dao包创建注册接口类

**RegDao.java**

```java
package cn.lyscolar.ly50.dao;

import cn.lyscolar.ly50.javabean.Reg;

/**
 * @author LeeSun
 */
public interface RegDao {
    public int inserUsername(Reg reg);
}
```

### daoimpl创建注册接口实现类

**RegDaolmpl.java**

```java
package cn.lyscolar.ly50.daoimpl;

import cn.lyscolar.ly50.dao.RegDao;
import cn.lyscolar.ly50.javabean.Reg;
import cn.lyscolar.ly50.utils.DBhelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author LeeSun
 */
public class RegDaoImpl implements RegDao {
    protected Connection conn;
    protected PreparedStatement pstmt;
    protected ResultSet rs;
    protected String  sql;

    @Override
    public int inserUsername(Reg reg) {
        int affectrow = 0;
        String sql="INSERT INTO users (id,username,PASSWORD,realname) VALUES (null,?,?,?)";
        try {
            conn= DBhelper.getConn();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,reg.getUsername());
            pstmt.setString(2,reg.getPassword());
            pstmt.setString(3,reg.getRealname());
            affectrow = pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return affectrow;
    }

}
```

### servlet 包创建注册servlet

**Regservlet.java**

```java
package cn.lyscolar.ly50.servlet;

import cn.lyscolar.ly50.dao.RegDao;
import cn.lyscolar.ly50.daoimpl.RegDaoImpl;
import cn.lyscolar.ly50.javabean.Reg;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author LeeSun
 */
@WebServlet("/reg")
public class RegServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String Realname=request.getParameter("Realname");


        Reg reg = new Reg();
        reg.setUsername(username);
        reg.setPassword(password);
        reg.setRealname(Realname);

        RegDao regDao = new RegDaoImpl();
        int affectrow = regDao.inserUsername(reg);

        if(affectrow>0) {
            System.out.print(Charset.defaultCharset());
            System.out.print("注册成功");
            response.getWriter().print("<script>window.alert('注册成功！');window.location.href='index.jsp';</script>");
        }else {
            System.out.print("注册失败");
            response.getWriter().print("<script>window.alert('注册失败！');window.location.href='index.jsp';</script>");
        }
    }
}
```



### 首页的jsp代码

```html
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
</head>
<nav class="navbar navbar-expand navbar-light bg-light">

    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="navbar-toggler-icon"></span></button>
    <a class="navbar-brand" href="#">个人任务管理系统</a>
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="navbar-nav ml-md-auto">
            <li class="nav-item "><a id="modal-register" href="#modal-container-register" role="button" class="btn"
                    data-toggle="modal">注册</a></li>
            <li class="nav-item   "><a id="modal-login" href="#modal-container-login" role="button" class="btn"
                    data-toggle="modal">登录</a></li>
            <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="" id="navbarDropdownMenuLink"
                    data-toggle="dropdown"></a>
                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownMenuLink"><a
                        id="modal-personal" href="#modal-container-personal" role="button" class="btn"
                        data-toggle="modal">修改个人信息</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="#" id="logout">退出</a>
                </div>
            </li>
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
                        <form role="form" action="/taskrj183ly50_war_exploded/reg" method="get"
                            name="reg" id="reg_form">
                            <div class="form-group">

                                <label for="name"> 登录名 </label> <input type="text" name="username" autocomplete="name"
                                    class="form-control" id="reg_user" /></div>

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
                            <div class="modal-footer">
                                <button type="button" class="btn btn-primary" id="register_send">
                                    <input type="submit" value="注册" >
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

        <div class="modal fade" id="modal-container-personal" role="dialog" aria-labelledby="myModalLabel"
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
                        <form role="form" action="${pageContext.servletContext.contextPath}/modifypersonal.do"
                            method="post" name="reg" id="personal_form">

                            <input type="hidden" name="id" value="${user.id}" />

                            <input type="hidden" name="name" value="${user.name}" />

                            <div class="form-group"><label for="personal_realname"> 真实姓名 </label> <input type="text"
                                    name="realname" autocomplete="realname" class="form-control"
                                    value="${user.realname}" id="personal_realname" /></div>
                            <div class="form-group">label for="personal_password"> 密码 </label> <input type="password"
                                    name="password" autocomplete="new-password" value="${user.password}"
                                    class="form-control" id="personal_password" /></div>
                            <div class="form-group">
                                <label for="personal_password"> 确认密码 </label> <input type="password" name="password2"
                                    autocomplete="current-password" class="form-control" value="${user.password}"
                                    id="personal_password2" />
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer"><button type="button" class="btn   btn-primary"
                            id="personal_send">修改</button><button type="button" class="btn btn-secondary"
                            data-dismiss="modal">关闭</button></div>
                </div>

            </div>
        </div>
    </div>
</div>
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
                        <form role="form" method="post" action=""
                            id="logon_form">
                            <div class="form-group"><label for="name"> 用户名 </label> <input type="text" name="name"
                                    autocomplete="name" class="form-control" id="username" /></div>
                            <div class="form-group">

                                <label for="password"> 密码 </label> <input type="password" name="password"
                                    autocomplete="current-password" class="form-control" id="password" /></div>
                        </form>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" id="logon_send">
                            登录</button>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>

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

                        <form role="form" id="new_task_form">
                            <div class="form-group">

                                <label for="task_name"> 任务名称 </label> <input type="text" class="form-control"
                                    name="name" id="task_name" /> </div>
                            <div class="form-group"><label for="task_desc"> 任务描述 </label><textarea name="description"
                                    class="form-control" id="task_desc"></textarea>

                            </div>

                            <div class="form-group"><label for="task_level">任务级别</label> <select class="form-control"
                                    name="level" id="task_level">
                                    <option value="1">特急</option>

                                    <option value="2">紧急</option>

                                    <option value="3">常规</option>
                                </select></div>

                            <div class="form-group">

                                <!-- datetime-local   -->

                                <label for="task_due">截至日期</label> <input type="datetime-local" class="form-control"
                                    name="due" id="task_due" /></div>
                        </form>
                    </div>

                    <div class="modal-footer"><button type="button" class="btn   btn-primary"
                            id="task_new">新建</button><button type="button" class="btn btn-secondary"
                            data-dismiss="modal">关闭</button>

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


<body>

</body>

</html>
```

## 注册功能的用户名验证

**从数据库去判断username是否存在，前端ajax去请求**

### 在dao包Reg文件中添加

```java
public int CheckName(String username);
```

### 在dao包实现类中regdaoimpl添加

```java
public int CheckName(String username) {
    int result=1;
    //可以注册
    sql="select username from users where username=?";
    String sql2 = "select email from account where email=?";
    //2、连接
    try {
        conn= DBhelper.getConn();
        pstmt=conn.prepareStatement(sql);
        pstmt.setString(1,username);
        rs=pstmt.executeQuery();
        if (rs.next()){
            result=0;
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return result;
}
```

### 新增servlet

**CheckNameServlet.java**

```java
package cn.lyscolar.ly50.servlet;

import cn.lyscolar.ly50.dao.RegDao;
import cn.lyscolar.ly50.daoimpl.RegDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CheckNameServlet")
public class CheckNameServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        int result=0;
        RegDao regDao = new RegDaoImpl();
        result = regDao.CheckName(username);
        response.getWriter().print(result);
        response.getWriter().flush();
        response.getWriter().close();

    }
}
```

### 首页注册页面添加以下代码

```javascript
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
```

**用户输入框加入事件**

![js事件](http://imgal.lyscholar.cn/new/1588764628565.png)







```html
<div class="form-group">

    <label for="name"> 登录名 </label> <input type="text" name="username" onblur="validuser()" autocomplete="name"
        class="form-control" id="reg_user" />

    <div id="validresult"></div>

</div>
```

## 注册功能的验证码实现

### 新建servlet

**ImageServlet.java**

```java
package cn.lyscolar.ly50.servlet;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/getcode")
public class ImageServlet extends HttpServlet {
    private static final long serialVersionUID=1L;
    public ImageServlet(){
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        //1、定义BufferedImage对象
        BufferedImage image=new BufferedImage(80,30,BufferedImage.TYPE_INT_RGB);
        //获得Graphics对象
        Graphics g=image.getGraphics();
        //Color对象
        Color c=new Color(130,200,165);
        g.setColor(c);
        g.fillRect(0,0,80,30);

        //字符数组
        char [] ch="ABCDEFGHRGKLMNOPKRSTUVWXYZ0123456789".toCharArray();
        //通过Random产生随机验证码信息
        Random r =new Random();
        int len=ch.length;
        int index;
        //变长字符串对象StringBuffer();
        StringBuffer buffer=new StringBuffer();
        //使用Graphics绘制图片
        for(int i=0;i<4;i++){
            //获得随机索引
            index=r.nextInt(len);
            //73  165 119
            g.setColor(new Color(r.nextInt(73),r.nextInt(165),r.nextInt(119)));
            g.drawString(ch[index]+"",(i*20)+3,20);
            //变长字符串对象追加
            buffer.append(ch[index]);


        }
        //5、记录验证码信息到session中
        request.getSession().setAttribute("code",buffer.toString());
        System.out.println("验证码："+buffer.toString());
        //使用ImageIO输出图片
//        ImageIO.write(image,"JPG",resp.getOutputStream());
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(response.getOutputStream());
        encoder.encode(image);
    }
}
```

### 首页添加代码

```java
<script>
    function reloadimage(){
        //传入时间参数防止缓存
        var time=new Date().getTime();
        document.getElementById("imagecode").src="<%=request.getContextPath()%>/getcode?d="+time;
    }
</script>
```

```html
<div class="form-group">
    <label> 验证码 </label>
    <div style="display: flex;">
        <input type="text" name="checkcode" style="width: 52%;" class="form-control" />
        <img id="imagecode" style="width: 24%; margin-left: 20px" src="<%=request.getContextPath()%>/getcode" alt="验证码">
        <a href="javascript:reloadimage()">看不清</a>
    </div>
</div>
```

**全部首页代码**

```html
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
        }
    </script>
</head>
<nav class="navbar navbar-expand navbar-light bg-light">

    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="navbar-toggler-icon"></span></button>
    <a class="navbar-brand" href="#">个人任务管理系统</a>
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="navbar-nav ml-md-auto">
            <li class="nav-item "><a id="modal-register" href="#modal-container-register" role="button" class="btn"
                    data-toggle="modal">注册</a></li>
            <li class="nav-item   "><a id="modal-login" href="#modal-container-login" role="button" class="btn"
                    data-toggle="modal">登录</a></li>
            <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="" id="navbarDropdownMenuLink"
                    data-toggle="dropdown"></a>
                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownMenuLink"><a
                        id="modal-personal" href="#modal-container-personal" role="button" class="btn"
                        data-toggle="modal">修改个人信息</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="#" id="logout">退出</a>
                </div>
            </li>
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
                        <form role="form" name="regform" action="/taskrj183ly50_war_exploded/reg" method="get"
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

        <div class="modal fade" id="modal-container-personal" role="dialog" aria-labelledby="myModalLabel"
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
                        <form role="form" action="${pageContext.servletContext.contextPath}/modifypersonal.do"
                            method="post" name="reg" id="personal_form">

                            <input type="hidden" name="id" value="${user.id}" />

                            <input type="hidden" name="name" value="${user.name}" />

                            <div class="form-group"><label for="personal_realname"> 真实姓名 </label> <input type="text"
                                    name="realname" autocomplete="realname" class="form-control"
                                    value="${user.realname}" id="personal_realname" /></div>
                            <div class="form-group">label for="personal_password"> 密码 </label> <input type="password"
                                    name="password" autocomplete="new-password" value="${user.password}"
                                    class="form-control" id="personal_password" /></div>
                            <div class="form-group">
                                <label for="personal_password"> 确认密码 </label> <input type="password" name="password2"
                                    autocomplete="current-password" class="form-control" value="${user.password}"
                                    id="personal_password2" />
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer"><button type="button" class="btn   btn-primary"
                            id="personal_send">修改</button><button type="button" class="btn btn-secondary"
                            data-dismiss="modal">关闭</button></div>
                </div>

            </div>
        </div>
    </div>
</div>
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
                        <form role="form" method="post" action=""
                            id="logon_form">
                            <div class="form-group"><label for="name"> 用户名 </label> <input type="text" name="name"
                                    autocomplete="name" class="form-control" id="username" /></div>
                            <div class="form-group">

                                <label for="password"> 密码 </label> <input type="password" name="password"
                                    autocomplete="current-password" class="form-control" id="password" /></div>
                        </form>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" id="logon_send">
                            登录</button>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>

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

                        <form role="form" id="new_task_form">
                            <div class="form-group">

                                <label for="task_name"> 任务名称 </label> <input type="text" class="form-control"
                                    name="name" id="task_name" /> </div>
                            <div class="form-group"><label for="task_desc"> 任务描述 </label><textarea name="description"
                                    class="form-control" id="task_desc"></textarea>

                            </div>

                            <div class="form-group"><label for="task_level">任务级别</label> <select class="form-control"
                                    name="level" id="task_level">
                                    <option value="1">特急</option>

                                    <option value="2">紧急</option>

                                    <option value="3">常规</option>
                                </select></div>

                            <div class="form-group">

                                <!-- datetime-local   -->

                                <label for="task_due">截至日期</label> <input type="datetime-local" class="form-control"
                                    name="due" id="task_due" /></div>
                        </form>
                    </div>

                    <div class="modal-footer"><button type="button" class="btn   btn-primary"
                            id="task_new">新建</button><button type="button" class="btn btn-secondary"
                            data-dismiss="modal">关闭</button>

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


<body>

</body>

</html>
```

### 注册servlet修改逻辑

```java
package cn.lyscolar.ly50.servlet;

import cn.lyscolar.ly50.dao.RegDao;
import cn.lyscolar.ly50.daoimpl.RegDaoImpl;
import cn.lyscolar.ly50.javabean.Reg;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author LeeSun
 */
@WebServlet("/reg")
public class RegServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        String code = (String) session.getAttribute("code");
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String Realname=request.getParameter("Realname");
        String checkcode = request.getParameter("checkcode");

        checkcode = checkcode.toUpperCase();
        if(!checkcode.equals(code)) {
            response.getWriter().println("<script>alert('验证码输入有误！');location.href='index.jsp';</script>");
        }else {
            Reg reg = new Reg();
            reg.setUsername(username);
            reg.setPassword(password);
            reg.setRealname(Realname);

            RegDao regDao = new RegDaoImpl();
            int affectrow = regDao.inserUsername(reg);

            if(affectrow>0) {
                System.out.print(Charset.defaultCharset());
                System.out.print("注册成功");
                response.getWriter().print("<script>window.alert('注册成功！');window.location.href='index.jsp';</script>");
            }else {
                System.out.print("注册失败");
                response.getWriter().print("<script>window.alert('注册失败！');window.location.href='index.jsp';</script>");
            }
        }



    }
}
```

## 登录功能

### 新建servlet

**LoginServlet.java**

```java
package cn.lyscolar.ly50.servlet;

import cn.lyscolar.ly50.javabean.Reg;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;

/**
 * @author LeeSun
 */


@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
    Connection conn=null;
    PreparedStatement pstmt=null;
    ResultSet rs=null;
    @Override
    public void init(ServletConfig config) throws ServletException {
        String URL="jdbc:mysql://localhost:3306/task_db?useUnicode=true&amp;characterEncoding=UTF-8";
        String mysqlusername="root";
        String mysqlpwd="xxxx";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn= DriverManager.getConnection(URL,mysqlusername,mysqlpwd);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        String code = (String) session.getAttribute("code");
        String username=request.getParameter("loginname");
        String password=request.getParameter("loginpassword");

        String sql = "select username,PASSWORD,realname from users where username=? and PASSWORD=?";
        System.out.println("判断"+username+password);

        try {
            this.pstmt=this.conn.prepareStatement(sql);
            this.pstmt.setString(1, username);
            this.pstmt.setString(2, password);
            this.rs=this.pstmt.executeQuery();
            if (rs.next()){

                session.setAttribute("username", username);
                Reg login = new Reg();
                login.setUsername(rs.getString("username"));
                login.setUsername(rs.getString("password"));
                session.setAttribute("login", login);
                String url=(String)session.getAttribute("url");
                response.getWriter().println("<script>alert('success！');</script>");
                response.sendRedirect("user.jsp");


            }else {
                response.getWriter().println("<script>alert('用户名或密码错误！');location.href='index.jsp';</script>");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        //3-传参


    }

}
```

### 首页登录处修改

```html
<div class="modal-body">
    <form role="form" method="post" action="/taskrj183ly50_war_exploded/Login"
        id="logon_form">
        <div class="form-group"><label for="loginname"> 用户名 </label> <input type="text" name="loginname"
                autocomplete="name" class="form-control" id="loginname" /></div>
        <div class="form-group">

            <label for="loginpassword"> 密码 </label> <input type="password" name="loginpassword"
                autocomplete="current-password" class="form-control" id="loginpassword" /></div>

        <div class="modal-footer">
            <button type="submit" class="btn btn-primary" id="logon_send">
                登录</button>
            <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>

        </div>
    </form>
</div>
```







## 登录功能的验证码

### 首页添加代码

```
<div class="form-group">
    <label> 验证码 </label>
    <div style="display: flex;">
        <input type="text" name="logincheckcode" style="width: 52%;" class="form-control" />
        <img id="imagecode1" style="width: 24%; margin-left: 20px" src="<%=request.getContextPath()%>/getcode" alt="验证码">
        <a href="javascript:reloadimage()">看不清</a>
    </div>
</div>
```

### 修改图片验证码刷新代码

```js
<script>
    function reloadimage(){
        //传入时间参数防止缓存
        var time=new Date().getTime();
        document.getElementById("imagecode").src="<%=request.getContextPath()%>/getcode?d="+time;
        document.getElementById("imagecode1").src="<%=request.getContextPath()%>/getcode?d="+time;
    }

</script>
```

### 修改servlet逻辑代码

```
package cn.lyscolar.ly50.servlet;

import cn.lyscolar.ly50.javabean.Reg;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;

/**
 * @author LeeSun
 */


@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
    Connection conn=null;
    PreparedStatement pstmt=null;
    ResultSet rs=null;
    @Override
    public void init(ServletConfig config) throws ServletException {
        String URL="jdbc:mysql://localhost:3306/task_db?useUnicode=true&amp;characterEncoding=UTF-8";
        String mysqlusername="root";
        String mysqlpwd="1";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn= DriverManager.getConnection(URL,mysqlusername,mysqlpwd);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        String code = (String) session.getAttribute("code");
        String username=request.getParameter("loginname");
        String password=request.getParameter("loginpassword");
        String ymcode=request.getParameter("logincheckcode");

        ymcode = ymcode.toUpperCase();
        if(!ymcode.equals(code)) {
            response.getWriter().println("<script>alert('验证码输入有误！');location.href='index.jsp';</script>");
        }else {

            String sql = "select username,PASSWORD,realname from users where username=? and PASSWORD=?";

            try {
                this.pstmt = this.conn.prepareStatement(sql);
                this.pstmt.setString(1, username);
                this.pstmt.setString(2, password);
                this.rs = this.pstmt.executeQuery();
                if (rs.next()) {

                    session.setAttribute("username", username);
                    Reg login = new Reg();
                    login.setUsername(rs.getString("username"));
                    login.setUsername(rs.getString("password"));
                    session.setAttribute("login", login);
                    String url = (String) session.getAttribute("url");
                    response.getWriter().println("<script>alert('success！');</script>");
                    response.sendRedirect("user.jsp");


                } else {
                    response.getWriter().println("<script>alert('用户名或密码错误！');location.href='index.jsp';</script>");
                }


            } catch (SQLException e) {
                e.printStackTrace();
            }
            //3-传参
        }

    }

}

```

## 用户界面鉴权

**通过在登录功能的正确前提下，存储session，在用户界面下，通过判断session是否存在以及取出session值当中的username，渲染到页面中，来实现用户界面的鉴权和显示当前登录的用户**

![1588763378788](A:\CompetitionItem\task\assets\1588763378788.png)



### 用户界面代码

```html
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
                    <a class="nav-link dropdown-toggle" href="" id="navbarDropdownMenuLink"
                        data-toggle="dropdown">
                        <%=username%>
                        <% }
                        %>
                    </a>
                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownMenuLink"><a
                            id="modal-personal" href="#modal-container-personal" role="button" class="btn"
                            data-toggle="modal">修改个人信息</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="#" id="logout">退出</a>
                    </div>
                </li>














            </ul>

        </div>

    </nav>

    <!--注册模态框-->

    <div class="row">

        <div class="col-md-12">

            <div class="modal   fade" id="modal-container-register" role="dialog" aria-labelledby="myModalLabel"
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
                            <form role="form" action="${pageContext.servletContext.contextPath}/register.do"
                                method="post" name="reg" id="reg_form">
                                <div class="form-group">

                                    <label for="name"> 登录名 </label> <input type="text" name="name" autocomplete="name"
                                        class="form-control" id="reg_user" /></div>

                                <div class="form-group"><label for="reg_realname"> 真实姓名 </label> <input type="text"
                                        name="realname" autocomplete="username" class="form-control"
                                        id="reg_realname" /></div>

                                <div class="form-group"><label for="reg_password"> 密码 </label> <input type="password"
                                        name="password" autocomplete="new-password" class="form-control"
                                        id="reg_password" /></div>

                                <div class="form-group"><label for="reg_password"> 确认密码 </label> <input type="password"
                                        name="password2" autocomplete="current-password" class="form-control"
                                        id="reg_password2" />

                                </div>

                            </form>

                        </div>

                        <div class="modal-footer">

                            <button type="button" class="btn btn-primary" id="register_send">

                                注册</button>

                            <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>

                        </div>

                    </div>



                </div>



            </div>



        </div>

    </div>

    <!--注册模态框-->

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
                            <form role="form" action=""
                                method="post" name="reg" id="personal_form">

                                <input type="hidden" name="id" value="" />

                                <input type="hidden" name="name" value="" />

                                <div class="form-group"><label for="personal_realname"> 真实姓名 </label> <input type="text"
                                        name="realname" autocomplete="realname" class="form-control"
                                        value="" id="personal_realname" /></div>
                                <div class="form-group">label for="personal_password"> 密码 </label> <input
                                        type="password" name="password" autocomplete="new-password"
                                        value="" class="form-control" id="personal_password" /></div>
                                <div class="form-group">

                                    <label for="personal_password"> 确认密码 </label> <input type="password"
                                        name="password2" autocomplete="current-password" class="form-control"
                                        value="" id="personal_password2" />

                                </div>

                            </form>

                        </div>

                        <div class="modal-footer"><button type="button" class="btn   btn-primary"
                                id="personal_send">修改</button><button type="button" class="btn btn-secondary"
                                data-dismiss="modal">关闭</button></div>
                    </div>

                </div>
            </div>
        </div>
    </div>
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
                            <form role="form" method="post" action=""
                                id="logon_form">
                                <div class="form-group"><label for="name"> 用户名 </label> <input type="text" name="name"
                                        autocomplete="name" class="form-control" id="username" /></div>
                                <div class="form-group">

                                    <label for="password"> 密码 </label> <input type="password" name="password"
                                        autocomplete="current-password" class="form-control" id="password" /></div>
                            </form>
                        </div>

                        <div class="modal-footer">



                            <button type="button" class="btn btn-primary" id="logon_send">

                                登录</button>

                            <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>

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

                            <form role="form" id="new_task_form">
                                <div class="form-group">

                                    <label for="task_name"> 任务名称 </label> <input type="text" class="form-control"
                                        name="name" id="task_name" /> </div>
                                <div class="form-group"><label for="task_desc"> 任务描述 </label><textarea
                                        name="description" class="form-control" id="task_desc"></textarea>

                                </div>

                                <div class="form-group"><label for="task_level">任务级别</label> <select
                                        class="form-control" name="level" id="task_level">
                                        <option value="1">特急</option>

                                        <option value="2">紧急</option>

                                        <option value="3">常规</option>
                                    </select></div>

                                <div class="form-group">

                                    <!-- datetime-local   -->

                                    <label for="task_due">截至日期</label> <input type="datetime-local" class="form-control"
                                        name="due" id="task_due" /></div>
                            </form>
                        </div>

                        div class="modal-footer"><button type="button" class="btn   btn-primary"
                            id="task_new">新建</button><button type="button" class="btn btn-secondary"
                            data-dismiss="modal">关闭</button>

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
```

## 用户退出登录功能

### 新建servlet 

**Logoutservlet.java**

```java
package cn.lyscolar.ly50.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author LeeSun
 */
@WebServlet("/Logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        session.removeAttribute("username");
        request.getRequestDispatcher("./index.jsp").forward(request, response);
    }
}
```

### 用户界面代码修改

```html
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
                    <a class="nav-link dropdown-toggle" href="" id="navbarDropdownMenuLink"
                        data-toggle="dropdown">
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
                    <button type="submit" class="btn btn-secondary" onclick="window.location.href=('<%=request.getContextPath()%>/Logout')" data-dismiss="modal">确定</button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                </div>

            </div>
        </div>
    </div>


    <!--注册模态框-->

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
                            <form role="form" action=""
                                method="post" name="reg" id="personal_form">

                                <input type="hidden" name="id" value="" />

                                <input type="hidden" name="name" value="" />

                                <div class="form-group"><label for="personal_realname"> 真实姓名 </label> <input type="text"
                                        name="realname" autocomplete="realname" class="form-control"
                                        value="" id="personal_realname" /></div>
                                <div class="form-group">label for="personal_password"> 密码 </label> <input
                                        type="password" name="password" autocomplete="new-password"
                                        value="" class="form-control" id="personal_password" /></div>
                                <div class="form-group">

                                    <label for="personal_password"> 确认密码 </label> <input type="password"
                                        name="password2" autocomplete="current-password" class="form-control"
                                        value="" id="personal_password2" />

                                </div>

                            </form>

                        </div>

                        <div class="modal-footer"><button type="button" class="btn   btn-primary"
                                id="personal_send">修改</button><button type="button" class="btn btn-secondary"
                                data-dismiss="modal">关闭</button></div>
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

                            <form role="form" id="new_task_form">
                                <div class="form-group">

                                    <label for="task_name"> 任务名称 </label> <input type="text" class="form-control"
                                        name="name" id="task_name" /> </div>
                                <div class="form-group"><label for="task_desc"> 任务描述 </label><textarea
                                        name="description" class="form-control" id="task_desc"></textarea>

                                </div>

                                <div class="form-group"><label for="task_level">任务级别</label> <select
                                        class="form-control" name="level" id="task_level">
                                        <option value="1">特急</option>

                                        <option value="2">紧急</option>

                                        <option value="3">常规</option>
                                    </select></div>

                                <div class="form-group">

                                    <!-- datetime-local   -->

                                    <label for="task_due">截至日期</label> <input type="datetime-local" class="form-control"
                                        name="due" id="task_due" /></div>
                            </form>
                        </div>

                        div class="modal-footer"><button type="button" class="btn   btn-primary"
                            id="task_new">新建</button><button type="button" class="btn btn-secondary"
                            data-dismiss="modal">关闭</button>

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
```

## 用户修改密码操作

### dao包 RegDao接口中添加

```java
package cn.lyscolar.ly50.dao;

import cn.lyscolar.ly50.javabean.Reg;

/**
 * @author LeeSun
 */
public interface RegDao {
    public int inserUsername(Reg reg);
    public int CheckName(String username);
    public int updateUser(Reg reg);
}
```

### 实现类中添加

```
@Override
public int updateUser(Reg reg) {
    int affectrow = 0;
    String sql="update users set realname=?,password=? where username=?";
    try {
        conn= DBhelper.getConn();
        pstmt=conn.prepareStatement(sql);
        pstmt.setString(1,reg.getRealname());
        pstmt.setString(2,reg.getPassword());
        pstmt.setString(3,reg.getUsername());
        affectrow = pstmt.executeUpdate();
    }catch (Exception e){
        e.printStackTrace();
    }
    return affectrow;
}
```

### 新增servlet

**UpdatapwdServlet.java**

```
package cn.lyscolar.ly50.servlet;

import cn.lyscolar.ly50.dao.RegDao;
import cn.lyscolar.ly50.daoimpl.RegDaoImpl;
import cn.lyscolar.ly50.javabean.Reg;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author LeeSun
 */
@WebServlet("/UpdatapwdServlet")
public class UpdatapwdServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        String realname=request.getParameter("up_realname");
        String password=request.getParameter("up_password");
        String password2=request.getParameter("up_password2");
        HttpSession session = request.getSession();
        String username1 = (String) session.getAttribute("username");
        System.out.println(realname+password+username1);

        if (!password.equals(password2)){
            response.getWriter().print("<script>window.alert('2次密码不一致！');</script>");
        }else {
            Reg updatapwd = new Reg();
            updatapwd.setRealname(realname);
            updatapwd.setPassword(password);
            updatapwd.setUsername(username1);

            RegDao updatapwdDao = new RegDaoImpl();
            int affectrow = updatapwdDao.updateUser(updatapwd);

            if (affectrow > 0){
                response.getWriter().print("<script>window.alert('密码修改成功！即将返回登录界面');window.location.href='index.jsp';</script>");
                session.removeAttribute("username");
            }else {
                response.getWriter().print("<script>window.alert('密码修改失败！');window.location.href='user.jsp';</script>");
            }

        }
    }
}
```

### 前端代码添加

```
<div class="modal-body">
    <form role="form" action="<%=request.getContextPath()%>/UpdatapwdServlet"
        method="get" name="reg" id="personal_form">

        <input type="hidden" name="id" value="" />

        <input type="hidden" name="name" value="" />

        <div class="form-group"><label for="personal_realname"> 真实姓名 </label> <input type="text"
                name="up_realname" autocomplete="realname" class="form-control"
                value="" id="personal_realname" /></div>
        <div class="form-group"><label for="personal_password"> 密码 </label> <input
                type="password" name="up_password" autocomplete="new-password"
                value="" class="form-control" id="personal_password" /></div>
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
```

**完整代码**

```html
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
                    <a class="nav-link dropdown-toggle" href="" id="navbarDropdownMenuLink"
                        data-toggle="dropdown">
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
                    <button type="submit" class="btn btn-secondary" onclick="window.location.href=('<%=request.getContextPath()%>/Logout')" data-dismiss="modal">确定</button>
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
                            <form role="form" action="<%=request.getContextPath()%>/UpdatapwdServlet"
                                method="get" name="reg" id="personal_form">

                                <input type="hidden" name="id" value="" />

                                <input type="hidden" name="name" value="" />

                                <div class="form-group"><label for="personal_realname"> 真实姓名 </label> <input type="text"
                                        name="up_realname" autocomplete="realname" class="form-control"
                                        value="" id="personal_realname" /></div>
                                <div class="form-group"><label for="personal_password"> 密码 </label> <input
                                        type="password" name="up_password" autocomplete="new-password"
                                        value="" class="form-control" id="personal_password" /></div>
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

                            <form role="form" id="new_task_form">
                                <div class="form-group">

                                    <label for="task_name"> 任务名称 </label> <input type="text" class="form-control"
                                        name="name" id="task_name" /> </div>
                                <div class="form-group"><label for="task_desc"> 任务描述 </label><textarea
                                        name="description" class="form-control" id="task_desc"></textarea>

                                </div>

                                <div class="form-group"><label for="task_level">任务级别</label> <select
                                        class="form-control" name="level" id="task_level">
                                        <option value="1">特急</option>

                                        <option value="2">紧急</option>

                                        <option value="3">常规</option>
                                    </select></div>

                                <div class="form-group">

                                    <!-- datetime-local   -->

                                    <label for="task_due">截至日期</label> <input type="datetime-local" class="form-control"
                                        name="due" id="task_due" /></div>
                            </form>
                        </div>

                        div class="modal-footer"><button type="button" class="btn   btn-primary"
                            id="task_new">新建</button><button type="button" class="btn btn-secondary"
                            data-dismiss="modal">关闭</button>

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
```

## 用户添加任务

### 新建javabean

**Task.java**

```
package cn.lyscolar.ly50.javabean;

/**
 * @author LeeSun
 */
public class Task {
    private int id;
    private int userid;
    private String name;
    private  String description;
    private String level;
    private String cost;
    private String due;
    private int status;
    private String start;
    private String end;
    public Task(){
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getDue() {
        return due;
    }

    public void setDue(String due) {
        this.due = due;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
```

### 新建dao层

**TaskDao**

```
package cn.lyscolar.ly50.dao;

import cn.lyscolar.ly50.javabean.Task;

/**
 * @author LeeSun
 */
public interface TaskDao {
    public int inserTask(Task task);
}
```

### 添加实现类

```
package cn.lyscolar.ly50.daoimpl;

import cn.lyscolar.ly50.dao.TaskDao;
import cn.lyscolar.ly50.javabean.Task;
import cn.lyscolar.ly50.utils.DBhelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author LeeSun
 */
public class TaskDaoImpl implements TaskDao {
    protected Connection conn;
    protected PreparedStatement pstmt;
    protected ResultSet rs;
    protected String  sql;
    @Override
    public int inserTask(Task task) {
        int affectrow = 0;
        String sql="INSERT INTO task (name,userid,description,level,end) VALUES (?,?,?,?,?)";
        try {
            conn= DBhelper.getConn();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,task.getName());
            pstmt.setInt(2,task.getUserid());
            pstmt.setString(3,task.getDescription());
            pstmt.setString(4,task.getLevel());
            pstmt.setString(5,task.getEnd());

            affectrow = pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return affectrow;
    }
}
```

### 新增servlet

```
package cn.lyscolar.ly50.servlet;

import cn.lyscolar.ly50.dao.TaskDao;
import cn.lyscolar.ly50.daoimpl.TaskDaoImpl;
import cn.lyscolar.ly50.javabean.Reg;
import cn.lyscolar.ly50.javabean.Task;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author LeeSun
 */
@WebServlet("/AddTaskServlet")
public class AddTaskServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        int user1 = (int) session.getAttribute("id");
        System.out.println("当前用户的id值为："+user1);
        String taskName =request.getParameter("task_name");

        String taskDescription =request.getParameter("task_description");

        String taskLevel = request.getParameter("task_level");
        String taskDue =request.getParameter("task_due");



        Task task = new Task();
        task.setUserid(user1);
        task.setEnd(taskDue);
        task.setDescription(taskDescription);
        task.setName(taskName);
        task.setLevel(taskLevel);

        TaskDao taskDao = new TaskDaoImpl();
        int affect = taskDao.inserTask(task);
        if(affect>0){
            System.out.print(Charset.defaultCharset());
            System.out.print("写入成功");
            response.getWriter().print("<script>window.alert('成功！');window.location.href='user.jsp';</script>");


        }else {
            System.out.print("写入失败");
            response.getWriter().print("<script>window.alert('注册失败！');</script>");
        }
    }
}
```

### 修改登录servlet 增加session

![login](http://imgal.lyscholar.cn/new/1588764553593.png)

```
package cn.lyscolar.ly50.servlet;

import cn.lyscolar.ly50.javabean.Reg;
import com.sun.xml.internal.bind.v2.model.core.ID;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;

/**
 * @author LeeSun
 */


@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
    Connection conn=null;
    PreparedStatement pstmt=null;
    ResultSet rs=null;
    @Override
    public void init(ServletConfig config) throws ServletException {
        String URL="jdbc:mysql://localhost:3306/task_db?useUnicode=true&amp;characterEncoding=UTF-8";
        String mysqlusername="root";
        String mysqlpwd="654123";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn= DriverManager.getConnection(URL,mysqlusername,mysqlpwd);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        String code = (String) session.getAttribute("code");
        String username=request.getParameter("loginname");
        String password=request.getParameter("loginpassword");
        String ymcode=request.getParameter("logincheckcode");

        ymcode = ymcode.toUpperCase();
        if(!ymcode.equals(code)) {
            response.getWriter().println("<script>alert('验证码输入有误！');location.href='index.jsp';</script>");
        }else {
            String sql = "select id,username,PASSWORD,realname from users where username=? and PASSWORD=?";
            try {
                this.pstmt = this.conn.prepareStatement(sql);
                this.pstmt.setString(1, username);
                this.pstmt.setString(2, password);
                this.rs = this.pstmt.executeQuery();
                if (rs.next()) {
                    int id=rs.getInt(1);
                    // 获取第一个列的值 编号id
                    session.setAttribute("username", username);
                    session.setAttribute("id", id);
                    Reg login = new Reg();
                    login.setUsername(rs.getString("username"));
                    login.setUsername(rs.getString("password"));
//                    session.setAttribute("id", id);
                    String url = (String) session.getAttribute("url");
                    response.getWriter().println("<script>alert('success！');</script>");
                    response.sendRedirect("user.jsp");


                } else {
                    response.getWriter().println("<script>alert('用户名或密码错误！');location.href='index.jsp';</script>");
                }


            } catch (SQLException e) {
                e.printStackTrace();
            }
            //3-传参
        }

    }

}
```

### 前端增加任务代码

```
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
```

## 用户查看任务





## 用户找回密码机制

### 修改数据库

**添加字段email**



![sql](http://imgal.lyscholar.cn/new/1588764510045.png)

### 先导入包

**javax.mail .jar**

### utils创建发送邮件工具类

**SendMail.java**

```
package cn.lyscolar.ly50.utils;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * @author LeeSun
 */
public class SendMail {
    // 发件人的邮箱账号如：xxx@163.com
    public static String sendEmailAccount = "xxxxxxxx@163.com";

    // 发件人的邮箱的授权码(自己在邮箱服务器中开启并设置)

    public static String sendEmailPassword = "xxxxxxxxxxxx";
    // 发件人邮箱的SMTP服务器地址，如：smtp.163.com
    public static String sendEmailSMTPHost = "smtp.163.com";
    // 收件人的邮箱账号
    public static String receiveMailAccount = "";

    // 把发送邮件封装为函数，参数为收件人的邮箱账号和要发送的内容
    public static void sendMail(String receiveMailAccount, String mailContent) {
        // 创建用于连接邮件服务器的参数配置
        Properties props = new Properties();
        // 设置使用SMTP协议
        props.setProperty("mail.transport.protocol", "smtp");
        // 设置发件人的SMTP服务器地址
        props.setProperty("mail.smtp.host", sendEmailSMTPHost);
        // 设置需要验证
        props.setProperty("mail.smtp.auth", "true");

        // 根据配置创建会话对象, 用于和邮件服务器交互
        Session session = Session.getInstance(props);
        // 设置debug模式，便于查看发送过程所产生的日志
        session.setDebug(true);

        try {
            // 创建一封邮件
            MimeMessage message = createMimeMessage(session, sendEmailAccount, receiveMailAccount, mailContent);

            // 根据 Session 获取邮件传输对象
            Transport transport = session.getTransport();

            transport.connect(sendEmailAccount, sendEmailPassword);

            // 发送邮件, 发到所有的收件地址, 通过message.getAllRecipients() 可以获取到在创建邮件对象时添加的所有收件人
            transport.sendMessage(message, message.getAllRecipients());

            // 关闭连接
            transport.close();
        } catch (NoSuchProviderException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     *
     * @param session
     *            和服务器交互的会话
     * @param sendMail
     *            发件人邮箱
     * @param receiveMail
     *            收件人邮箱
     * @return
     * @throws Exception
     */
    public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail,
                                                String mailContent) throws Exception {
        // 创建一封邮件
        MimeMessage message = new MimeMessage(session);

        // 设置发件人姓名和编码格式
        message.setFrom(new InternetAddress(sendMail, "个人任务系统", "UTF-8"));

        // 收件人
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "尊敬的用户", "UTF-8"));

        // 设置邮件主题
        message.setSubject("找回密码提醒", "UTF-8");

        // 设置邮件正文
        message.setContent(mailContent, "text/html;charset=UTF-8");

        // 设置发件时间
        message.setSentDate(new Date());

        // 保存设置
        message.saveChanges();

        return message;
    }
}
```

### 修改Javabean代码

**Reg.java**

+

```
private String email;
public String getEmail(String email) {
    return this.email;
}
public void setEmail(String email) {
    this.email = email;
}
```

### dao层添加接口

**FindDao.java**

```
package cn.lyscolar.ly50.dao;

import cn.lyscolar.ly50.javabean.Reg;

import java.sql.SQLException;

/**
 * @author LeeSun
 */
public interface FindDao {
    public Reg getAccountByEmail(String email) throws SQLException, Exception;
}
```

### 添加实现类

**FindpwdImpl**

```
package cn.lyscolar.ly50.daoimpl;

import cn.lyscolar.ly50.dao.FindDao;
import cn.lyscolar.ly50.javabean.Reg;
import cn.lyscolar.ly50.utils.DBhelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author LeeSun
 */
public class FindpwdImpl implements FindDao {
    protected Connection conn;
    protected PreparedStatement pstmt;
    protected ResultSet rs;
    protected String  sql;
    @Override
    public Reg getAccountByEmail(String email) throws SQLException, Exception {
        Reg account = null;
        sql = "select username,id,PASSWORD,realname,email " +
                "from users where " +
                "email=?";
        conn = DBhelper.getConn();
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, email);
        rs = pstmt.executeQuery();
        if (rs.next()) {
            account = new Reg();
            account.setUsername(rs.getString("username"));
            account.setId(rs.getInt("id"));
            account.setPassword(rs.getString("PASSWORD"));
            account.setRealname(rs.getString("realname"));
            account.setEmail(rs.getString("email"));
            return account;
        }
        return account;
    }
}
```



### 新增servlet

**findpwdservlet**

```
package cn.lyscolar.ly50.servlet;

import cn.lyscolar.ly50.dao.FindDao;
import cn.lyscolar.ly50.dao.RegDao;
import cn.lyscolar.ly50.daoimpl.FindpwdImpl;
import cn.lyscolar.ly50.daoimpl.RegDaoImpl;
import cn.lyscolar.ly50.javabean.Reg;
import cn.lyscolar.ly50.utils.SendMail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author LeeSun
 */
@WebServlet("/findpwdServlet")
public class findpwdServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String code = (String) session.getAttribute("code");
        String email = request.getParameter("findem");
        String user = request.getParameter("findname");
        String ymcode=request.getParameter("findpwdcheckcode");
        System.out.println("输入的邮箱"+email);
        PrintWriter out = response.getWriter();
        String username1 ;
        ymcode = ymcode.toUpperCase();
        if(!ymcode.equals(code)) {
            response.getWriter().println("<script>alert('验证码输入有误！');location.href='index.jsp';</script>");
        }else {

            FindDao findDao = new FindpwdImpl();
            try {
                //            Reg reg = new Reg();
                Reg dao = findDao.getAccountByEmail(email);


                        //            reg.setUsername(username);
                        //            reg.setEmail(email);
                        //            RegDao regDao = new RegDaoImpl();
                        if (dao != null) {
                            username1 = dao.getUsername();
                            if (!username1.equals(user)) {
                                out.println("<script>alert('账号和邮箱不一致！！！核实后重填');window.location.href='index.jsp'</script>");
                            } else {
                                SendMail sendMail = new SendMail();
                                SendMail.sendMail(email, "666系统提醒，您的密码为：" + dao.getPassword());
                                System.out.println(dao.getPassword());
                                out.println("<script>alert('恭喜，找回密码成功');window.location.href='index.jsp'</script>");
                            }

                        } else {
                            out.println("<script>alert('账号和邮箱不一致！！！核实后重填');window.location.href='index.jsp'</script>");
                        }

                } catch(Exception e){
                    e.printStackTrace();
                }

        }
    }
}
```



### 前端代码修改

```
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
```