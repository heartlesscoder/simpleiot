<%--
  Created by IntelliJ IDEA.
  User: 59287
  Date: 2019/12/20
  Time: 17:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
  //判断传感器状态
  String redCondition="open";
  String rsc="";
  String ssc="";
  if(redCondition.equals("open"))
  {
      rsc="close";
  }else{
      rsc="open";
  }
  String superCondition="open";
  if(superCondition.equals("open"))
  {
    ssc="close";
  }else{
    ssc="open";
  }
  //添加初始的开关状态
%>
<html lang="en">

<head>
  <meta charset="utf-8" >
  <link rel="apple-touch-icon" sizes="76x76" href="assets/img/apple-icon.png" >
  <link rel="icon" type="image/png" href="assets/img/favicon.png" >
  <meta http-equiv="X-UA-Compatible" content="IE=edge" >

  <title>中国地质大学</title>

  <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no' name='viewport' >
  <meta name="viewport" content="width=device-width" >

  <!-- Bootstrap core CSS     -->
  <link href="assets/css/bootstrap.min.css" rel="stylesheet" >

  <!--  Material Dashboard CSS    -->
  <link href="assets/css/amaze.css" rel="stylesheet" >

  <!--  CSS for Demo Purpose, don't include it in your project     -->
  <link href="assets/css/demo.css" rel="stylesheet" >

  <!--     Fonts and icons     -->
  <link href="assets/css/font-awesome.min.css" rel="stylesheet">
  <link href="assets/css/font-muli.css" rel='stylesheet' type='text/css'>
  <link href="assets/css/themify-icons.css" rel="stylesheet">
  <link href="assets/vendors/sweetalert/css/sweetalert2.min.css" rel="Stylesheet" >
  <script>
      var xmlHttpReq;
      //创建一个XmlHttpRequest对象
      function createXmlHttpRequest()
      {
          if(window.XMLHttpRequest)
          {
              xmlHttpReq = new XMLHttpRequest();//非IE浏览器
          }else
          {
              xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");//IE浏览器
          }
      }
      //检测用户名是否已经被注册
      function checkUser()
      {
          var condition = document.getElementById("red").value;
//首先创建精灵对象
          createXmlHttpRequest();
//指明准备状态改变时回调的函数名
          xmlHttpReq.onreadystatechange=handle;
//尝试以异步的get方式访问某个URL
//请求服务器端的一个servlet
          var url = "com.sensor_mana.SensorServlet?username="+condition;
          xmlHttpReq.open("get",url,false);
//向服务器发送请求
          xmlHttpReq.send(null);
      }
      //状态发生改变时回调的函数
      //红外的回滚函数
      function redhandle()
      {
//准备状态为4
          if(xmlHttpReq.readyState==4)
          {
              //响应状态码为200，代表一切正常
              if(xmlHttpReq.status==200)
              {
                  var res = xmlHttpReq.responseText;
                  alert(res);
                  var result = document.getElementById("red");
                  result.value = res;
              }
          }
      }
      //超声波传感器的回滚函数
      function superhandle()
      {
//准备状态为4
          if(xmlHttpReq.readyState==4)
          {
              //响应状态码为200，代表一切正常
              if(xmlHttpReq.status==200)
              {
                  var res = xmlHttpReq.responseText;
                  alert(res);
                  var result = document.getElementById("super");
                  result.value = res;//按钮上显示当前开关状态
              }
          }
      }
  </script>
</head>

<body>
<script>
    function redClick(){
        var commend = "red";
//首先创建精灵对象
        createXmlHttpRequest();
//指明准备状态改变时回调的函数名
        xmlHttpReq.onreadystatechange=redhandle;
//尝试以异步的get方式访问某个URL
//请求服务器端的一个servlet
        var url = "com.sensor_mana.SensorServlet?sensor="+commend;
        xmlHttpReq.open("get",url,false);
//向服务器发送请求
        xmlHttpReq.send(null);
    }
    function superClick(){
        var commend = "super";
//首先创建精灵对象
        createXmlHttpRequest();
//指明准备状态改变时回调的函数名
        xmlHttpReq.onreadystatechange=superhandle;
//尝试以异步的get方式访问某个URL
//请求服务器端的一个servlet
        var url = "com.sensor_mana.SensorServlet?sensor="+commend;
        xmlHttpReq.open("get",url,false);
//向服务器发送请求
        xmlHttpReq.send(null);
    }
</script>
<div class="wrapper">
  <div class="sidebar" data-background-color="brown" data-active-color="danger">
    <div class="logo">
      <a href="http://www.cug.edu.cn/" class="simple-text">
        <img src="assets/img/logo3.png"/>
      </a>
    </div>
    <div class="logo logo-mini">
      <a href="#" class="simple-text">
        A
      </a>
    </div>
    <br><br>
    <div class="sidebar-wrapper">
      <ul class="nav">
        <li class="active">
          <a href="index.html">
            <i class="ti-panel"></i>
            <p style="font-size:18px;font-family:SimSun">&nbsp;首&nbsp;&nbsp;页</p>
          </a>
        </li>
        <br>
        <li>
          <a href="rtm.jsp">
            <i class="ti-widget"></i>
            <p style="font-size:18px ;font-family:SimSun">&nbsp;实时监控</p>
          </a>
        </li>
        <br>
        <li>
          <a href="history.jsp">
            <i class="ti-widget"></i>
            <p style="font-size:18px;font-family:SimSun">&nbsp;历史数据</p>
          </a>
        </li>
      </ul>>
    </div>
  </div>
  <div class="main-panel">
    <nav class="navbar navbar-default">
      <div class="container-fluid">
        <div class="navbar-minimize">
          <button id="minimizeSidebar" class="btn btn-round btn-white btn-fill btn-just-icon">
            <i class="ti-arrow-left"></i>
          </button>
        </div>
        <div class="navbar-header style=" position:absolute;top:0px;left:0px;width:100%;>
        <a class="navbar-brand" href="#">首页</a>
      </div>
      </div>
    </nav>

    <form id="control" name="control" action="<%=basePath%>com.sensor_mana.SensorServlet?type=control" method="post">
    <table width="400" height="500" border="1" align="center" style= "text-align: center;text-valign:center">
      <tr>
        <td>传感器</td>
        <td>开/关</td>
      </tr>
      <tr>
        <td>红外</td>
        <td>
          <input type="button" onClick=redClick() id="red" value = "<%=rsc%>"style="background-color: #000000;width: 76px;height: 36px;color: #FFFFFF">
        </td>
      </tr>
      <tr>
        <td>超声波</td>
        <td>
          <input type="button" onClick=superClick()  id="super" value = "<%=ssc%>"style="background-color: #000000;width: 76px;height: 36px;color: #FFFFFF">
        </td>
      </tr>
    </table>
    </form>
  </div>

  </div>
</body>
</html>

