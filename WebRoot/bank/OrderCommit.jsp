<%@ page language="java" import="chinapay.model.bean.*" pageEncoding="GBK"%>
<%@ page import="java.util.*"%>
<jsp:useBean id="payInput" scope="request" class="chinapay.model.bean.TransactionBean" />
<html>
  <head>
    
    <title>TransactionCommit.jsp</title>
    <meta http-equiv="Content-Type" content="text/html; charset=GBK" />
  </head>
  
  <body>
 	   <center><strong><font size="3"> 交易信息：</font></strong></center><br>
 	   <form name="oraform" action="TransactionReturn" method="POST" >
 	   <%
	String  MerId = payInput.getMerId();
	String  OrdId = payInput.getOrdNo();
	String  TransDate = payInput.getTransDate();
	String  TransType = payInput.getTransType();
	String  TransAmt = payInput.getTransAmt();
	String  CuryId = payInput.getCuryId();
	String  GateId = payInput.getGateId();
	String  Version	= payInput.getVersion();
	String  ChkValue = payInput.getChkValue();
	String  Priv1 = payInput.getPriv1();
	String  Purpose = payInput.getPurpose();
	String  UserName = payInput.getUserName();
	String  CardNo = payInput.getCardNo();
	String  CardType = payInput.getCardType();
	String  OpenBankId = payInput.getOpenBankId();
	String  CertId = payInput.getCertId();
	String  CertType = payInput.getCertType();

%>	
  <table  border="1" cellspacing="0" cellpadding="0" width="650"  bordercolor="#2B91D5" align="center">
	  <tr>
	     <td colspan="2"><center><font color="blue"> 交易银行卡信息 </font></center></td>
	  </tr>
	  <tr>
		  <td>持卡人姓名:</td>
		  <td><%= UserName %></td>
	  </tr>
		   
	  <tr>
		  <td>证件类型：</td>
		  <td><%= CertType %></td>
	   </tr>
	   <tr>
		  <td>证件号码：</td>
		  <td><%= CertId %></td>
	  </tr>
	  <tr>
		  <td>开户银行：</td>
		  <td><%= OpenBankId %></td>
	  </tr>
	  <tr>
		  <td>卡折标志：</td>
		  <td><%= CardType  %></td>
	  </tr>
	  <tr>
		  <td>卡号/折号：</td>
		  <td><%= CardNo  %></td>
	  </tr> 
	  <tr>
	     <td colspan="2"><center><font color="blue"> 商户交易信息 </font></center></td>
	  </tr>  
	  <tr>
		  <td>商户号:</td>
		  <td><%= MerId %></td>
	  </tr>		   
	  <tr>
		  <td>订单号:</td>
		  <td><%= OrdId %></td>
	   </tr>
	   <tr>
		  <td>交易金额:</td>
		  <td><%= TransAmt %></td>
	  </tr>
	  <tr>
		  <td>交易币种:</td>
		  <td><%= CuryId %></td>
	  </tr>
	  <tr>
		  <td>交易日期:</td>
		  <td><%= TransDate  %></td>
	  </tr>
	  <tr>
		  <td>交易类型:</td>
		  <td><%= TransType  %></td>
	  </tr>   
		
	  <tr>
		  <td>接入版本号:</td>
		  <td><%= Version  %></td>
	  </tr> 
	
	  <tr>
		  <td>支付网关号:</td>
		  <td><%= GateId  %></td>
	  </tr>   
	
	  <tr>
		  <td>私有域:</td>
		  <td><%= Priv1 %></td>
	  </tr>
	  <tr>
		  <td>用途:</td>
		  <td><%= Purpose %></td>
	  </tr>
	  <tr>
		  <td>数字签名:</td>
		  <td width="500" style="word-break:break-all">
                     <%= ChkValue %>
                </td>
	  </tr>
  </table>
<input type=hidden name="merId" value="<%=MerId%>">
<input type=hidden name="orderNo" value="<%=OrdId%>">
<input type=hidden name="transAmt" value="<%=TransAmt%>">
<input type=hidden name="curyId" value="156">
<input type=hidden name="transDate" value="<%=TransDate%>">
<input type=hidden name="transType" value="<%=TransType%>">
<input type=hidden name="version" value="<%=Version%>">
<input type=hidden name="priv1" value="<%=Priv1%>">
<input type=hidden name="chkValue" value="<%=ChkValue%>">
<input type=hidden name="gateId" value="<%=GateId%>">
<input type=hidden name="usrName" value="<%=UserName%>">
<input type=hidden name="certType" value="<%=CertType%>">
<input type=hidden name="certId" value="<%=CertId%>">
<input type=hidden name="openBankId" value="<%=OpenBankId%>">
<input type=hidden name="cardType" value="<%=CardType%>">
<input type=hidden name="cardNo" value="<%=CardNo%>">
<input type=hidden name="purpose" value="<%=Purpose%>">
    <br>
  	   <center>
	  	   <input type="submit" name="submitButton" value="提交" class="cmd"/>
	   </center>
	   <br>
       <center>
  	   <a href="./index.jsp">返回</a></center><br><br><br>
  	</form>
  </body>
</html>
