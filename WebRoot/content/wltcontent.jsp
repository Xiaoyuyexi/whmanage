<%@ page contentType="text/html; charset=gb2312" language="java"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>万汇通-左侧导航</title>
<script type="text/javascript" src="../js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="../js/tab.js"></script>
<link href="../css/main_style.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div id="tabbox">
<!--位置导航和选择菜单-->
  <div id="tabs_title">
    <ul class="tabs_wzdh"><li>交易查询</li></ul>
    <ul class="tabs" id="tabs">
        <li><a href="#">交易查询</a></li>    
        <li><a href="#">手机充值</a></li>
        <li><a href="#">充值明细</a></li>
        <li><a href="#">消费记录</a></li>
    </ul>
  </div>
<!--选择内容-->
	<ul class="tab_conbox" id="tab_conbox">
<!--第1个盒子-->
      <li class="tab_con">
      <div id="search_tiaojian"><iframe frameborder="0" height="100%" width="100%" scrolling="no" src="../sysuser/sysusermanager.jsp">您的浏览器版本不支持iframe，请升级或者跟换其他浏览器访问！</iframe></div>
      </li>
<!--第2个盒子-->          
      <li class="tab_con">
       <div id="search_tiaojian"><iframe frameborder="0" height="100%" width="100%"scrolling="no" src="../business/business.jsp">您的浏览器版本不支持iframe，请升级或者跟换其他浏览器访问！</iframe></div>
      </li>
<!--第3个盒子-->  
       <li class="tab_con"></li>
<!--第4个盒子-->   
        <li class="tab_con"></li>
	</ul>
</div>

</body>
</html>
