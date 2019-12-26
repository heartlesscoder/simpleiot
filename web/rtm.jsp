<%--
  Created by IntelliJ IDEA.
  User: 59287
  Date: 2019/12/21
  Time: 17:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>

<html lang="en">

<head>

    <title>中国地质大学</title>

    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no' name='viewport' >
    <meta name="viewport" content="width=device-width" >

    <!-- Bootstrap core CSS     -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet" >

    <!--  Paper Dashboard CSS    -->
    <link href="assets/css/amaze.css" rel="stylesheet" >
    <!--  CSS for Demo Purpose, don't include it in your project     -->
    <link href="assets/css/demo.css" rel="stylesheet" >

    <!--     Fonts and icons     -->
    <link href="assets/css/font-awesome.min.css" rel="stylesheet">
    <link href="assets/css/font-muli.css" rel='stylesheet' type='text/css'>
    <link href="assets/css/themify-icons.css" rel="stylesheet">
    <link href="assets/vendors/sweetalert/css/sweetalert2.min.css" rel="Stylesheet" >
    <script>
        var res="0";
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
        //状态发生改变时回调的函数
        //回滚函数
        function handle()
        {
//准备状态为4
            if(xmlHttpReq.readyState==4)
            {
                //响应状态码为200，代表一切正常
                if(xmlHttpReq.status==200)
                {
                    res = xmlHttpReq.responseText;
                    document.getElementById("button1").innerHTML=res;
                }
            }
        }
    </script>

</head>

<body>
<div class="wrapper">
    <div class="sidebar" data-background-color="brown" data-active-color="danger">
        <div class="logo">
            <a href="http://www.cug.edu.cn/" class="simple-text">
                <img src="assets/img/logo3.png" />
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
                <li>
                    <a href="index.jsp">
                        <i class="ti-panel"></i>
                        <p style="font-size:18px;font-family:SimSun">&nbsp;首&nbsp;&nbsp;页</p>
                    </a>
                </li>
                <br>
                <li class="active">
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
            </ul>
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
                    <a class="navbar-brand" href="#">实时数据</a>
                </div>
            </div>
        </nav>
        <div id="main" style="position:absolute;top:160px;left:20px;width:60%;height:300px;text-align: center;">
        </div>
    </div>
</div>
<!--echart动态更新数据图-->
<script type="text/javascript" src="js/echarts.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>
<script src="js/jquery-3.1.1.min.js" type="text/javascript"></script>
<script src="js/jquery-ui.min.js" type="text/javascript"></script>
<!--
<script type="text/javascript">
    var myChart = echarts.init(document.getElementById('main2'));
    var date = [];
    var data = [];
    var now = new Date();
    var init0=0;
    function addData(shift0) {
        now = new Date();
        now = [now.getHours(), now.getMinutes() , now.getSeconds()].join(':');
        date.push(now);
        data.push(init0+=2);
        if (shift0) {
            date.shift();
            data.shift();
        }
    }
    for (var i = 1; i < 10; i++) {
        addData();
    }

    option = {
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: date
        },
        yAxis: {
            boundaryGap: [0, '50%'],
            type: 'value'
        },
        series: [
            {
                name:'数据',
                type:'line',
                smooth:true,
                symbol: 'none',
                stack: 'a',
                areaStyle: {
                    normal: {}
                },
                data: data
            }
        ]
    };
    setInterval(function (data,date) {
        addData(true);
        myChart.setOption({
            xAxis: {
                data: date
            },
            series: [{
                name:'数据',
                data: data
            }]
        });
    }, 1000);
</script>
-->
<script>
    var myChart = echarts.init(document.getElementById('main'));
    var date = [];
    var randomData = [];
    var now=new Date();
    for(var i = 0; i<100; i++){
        date.push(0);
        randomData.push(0);
    }
    // 指定图表的配置项和数据
    var option = {
        xAxis: {
            type: 'category',
            data: date
        },
        yAxis: {
            type: 'value'
        },
        series: [{
            data: randomData,
            type: 'line'
        }]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);


    setInterval(function(){
        var commend = "requiredata";
//首先创建精灵对象
        createXmlHttpRequest();
//指明准备状态改变时回调的函数名
        xmlHttpReq.onreadystatechange=handle;
//尝试以异步的get方式访问某个URL
//请求服务器端的一个servlet
        var url = "com.cur_mana.CurServlet?commend="+commend;
        xmlHttpReq.open("get",url,false);
//向服务器发送请求
        xmlHttpReq.send(null);



        randomData.push(res);
        randomData.shift();
        now=new Date();
        now = [now.getHours(), now.getMinutes() , now.getSeconds()].join(':');
        date.push(now);
        date.shift();
        myChart.setOption({
            xAxis: {
                data: date
            },
            series: [{
                data: randomData
            }]
        });
    }, 3000)
</script>
<!--
<script type="text/javascript">
    // based on prepared DOM, initialize echarts instance
    var myChart = echarts.init(document.getElementById('main2'));

    // specify chart configuration item and data
    var option = {
        title: {
            text: 'ECharts entry example'
        },
        tooltip: {},
        legend: {
            data:['Sales']
        },
        xAxis: {
            data: ["shirt","cardign","chiffon shirt","pants","heels","socks"]
        },
        yAxis: {},
        series: [{
            name: 'Sales',
            type: 'bar',
            data: [5, 20, 36, 10, 10, 20]
        }]
    };

    // use configuration item and data specified to show chart
    myChart.setOption(option);
</script>
-->
<button id="button1"style="background-color: #000000;width: 76px;height: 36px;color: #FFFFFF">123</button>
</body>
</html>

