<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title></title>
<style type="text/css">
 *{ margin:0;padding:0;}
 body{ font:normal 15px Verdana, Arial, Helvetica, sans-serif; text-align:center;}
 #warpper{ position:absolute; left:5px; top:20px;}
 h5{ float:left;}
 a{ text-decoration:none; cursor:pointer; font-weight:bold;}
 dl{ height:18px; line-height:18px; background:#f7f7f7; padding:0 10px;}
 dt,.normal{ float:left; padding:0 5px; border-right:1px solid #ccc; text-decoration:none; width:auto; cursor:pointer;}
 dt.over{ position:relative;border:1px solid #86e5f0; padding:0 10px 15px 10px; border-bottom:1px solid #caf1f1; margin:-1px 0 0 -1px; z-index:1000; color:#ff6026; text-decoration:none; background:#caf1f1; height:22px; }
 li{ float:left; list-style-type:none; margin:5px 10px; width:120px;}
 dl dd{ position:absolute; width:500px; left:0;top:37px!important; border:1px solid #86e5f0; background:#caf1f1; filter:progid:DXImageTransform.Microsoft.Shadow(Strength=4, Direction=120, color=#cccccc); padding:10px 0;}
 .block{ display:block;}
 .none{ display:none;}
</style>
<script language="javascript">
 function $(str){ return document.getElementById(str);}
 function $$(str){ return document.getElementsByTagName(str);}
 function changeMenu(thisObj,num){
  if(thisObj.className=="over") return false;
  var tabObj=thisObj.parentNode.getAttribute("id");
  var obj_dt=$("warpper").getElementsByTagName("dt");
  for(var i=0;i<obj_dt.length;i++){
   if(i==num){
    thisObj.className="over";
    $("c"+(i+1)).className="block";
   }
   else{
    obj_dt[i].className="normal";
    $("c"+(i+1)).className="none";
   }
  }
 }
</script>
</head>
<body>
 <dl id="warpper">
  <dt onclick="changeMenu(this,0);">a</dt>
  <dd id="c1" class="block">
   <ul>
    <li><a herf="#">Adidas</a></li>
    <li><a herf="#">AEE/爱意</a></li>
    <li><a herf="#">AF</a></li>
    <li><a herf="#">AF棒球帽</a></li>
    <li><a herf="#">Agatha</a></li>
    <li><a herf="#">Albion/奥尔滨</a></li>
    <li><a herf="#">AMD</a></li>
    <li><a herf="#">Andox</a></li>
    <li><a herf="#">Artini</a></li>
    <li><a herf="#">爱普生</a></li>
   </ul>
  </dd>
  
  <dt onclick="changeMenu(this,1);">b</dt>
  <dd id="c2" class="none">
   <ul>
    <li><a herf="#">fasfs</a></li>
    <li><a herf="#">fsdfsd</a></li>
   </ul>
  </dd>
  
  <dt onclick="changeMenu(this,2);">c[ch]</dt>
  <dd id="c3" class="none">
   <ul>
    <li><a herf="#">fasfs</a></li>
    <li><a herf="#">fsdfsd</a></li>
    <li><a herf="#">fdsfas</a></li>
   </ul>
  </dd>
  
  <dt onclick="changeMenu(this,3);">d</dt>
  <dd id="c4" class="none">
   <ul>
    <li><a herf="#">fasfs</a></li>
    <li><a herf="#">fsdfsd</a></li>
    <li><a herf="#">fdsfas</a></li>
    <li><a herf="#">fasdffsd</a></li>
   </ul>
  </dd>
  
  <dt onclick="changeMenu(this,4);">e</dt>
  <dd id="c5" class="none">
   <ul>
    <li><a herf="#">fasfs</a></li>
    <li><a herf="#">fsdfsd</a></li>
    <li><a herf="#">fdsfas</a></li>
    <li><a herf="#">fasdffsd</a></li>
    <li><a herf="#">fasdfsaf</a></li>
   </ul>
  </dd>
  
  <dt onclick="changeMenu(this,5);">f</dt>
  <dd id="c6" class="none">
   <ul>
    <li><a herf="#">Fancl</a></li>
    <li><a herf="#">Fancl</a></li>
    <li><a herf="#">Fancl</a></li>
    <li><a herf="#">Fancl</a></li>
    <li><a herf="#">Fancl</a></li>
    <li><a herf="#">Fancl</a></li>
    <li><a herf="#">Fancl</a></li>
    <li><a herf="#">Fancl</a></li>
    <li><a herf="#">Fancl</a></li>
    <li><a herf="#">Fancl</a></li>
   </ul>
  </dd>
 </dl>
</body>
</html>
