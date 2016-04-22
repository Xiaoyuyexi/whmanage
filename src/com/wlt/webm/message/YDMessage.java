package com.wlt.webm.message;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;

import com.commsoft.epay.util.logging.Log;
import com.wlt.webm.tool.Tools;

public class YDMessage {
	
	private static final String UP_URL="https://gateway.iems.net.cn/GsmsHttp";
	//private static final String UP_URL="http://gateway.iems.net.cn/GsmsHttp";
	
	private static final String userId="69543";
	
	private static final String username="admin";
	
	private static final String password="95096679";
	
	/**
	 * �������� ��Ϣ
	 * @param phone ���� ���������ܸ��߱���Ŷ����Ǳ��˲���
	 * @param mzString ������  500MB ���� 500
	 * @return boolean
	 */
	public static boolean HeandMsg(String phone,String mzString){
		return sendMsg(phone, "�𾴵��û��������˻�������ȫ������"+mzString+"MB������ʹ����Ч����"+Tools.getDateEnd());
	}
	/**
	 * ���ŷ���
	 * @param phone ����
	 * @param content ����
	 * @return boolean
	 */
	public static boolean sendMsg(String phone,String content){
		
		Log.info("���ŷ���,phone:"+phone+",,content:"+content);
		
		StringBuffer buffer=new StringBuffer();
		buffer.append(UP_URL);
		buffer.append("?");
		buffer.append("username="+userId+":"+username);
		buffer.append("&password="+password);
		buffer.append("&from=001");
		buffer.append("&to="+phone);
		buffer.append("&content="+URLEncoder.encode(content));
		buffer.append("&presendTime="+URLEncoder.encode(Tools.getNewDate()));
		HttpClient client=new HttpClient();
		GetMethod method=null;
		try {
			client.getHttpConnectionManager().getParams().setConnectionTimeout(60*1000);
			client.getHttpConnectionManager().getParams().setSoTimeout(30*1000);
			method=new GetMethod(buffer.toString());
			int state=client.executeMethod(method);
			if(state!=200){
				Log.info("���ŷ���ʧ��,,http��Ӧ״̬:"+state+",phone:"+phone+",,content:"+content);
				return false;
			}
			String rs=method.getResponseBodyAsString();
			if(rs==null  || "".equals(rs.trim())){
				Log.info("���ŷ���ʧ��,,http��Ӧ����Ϊ��,phone:"+phone+",,content:"+content);
				return false;
			}
			if(rs.indexOf("OK:")!=-1){
				Log.info("���ŷ��ͳɹ�,,http��Ӧ����:"+rs.trim()+",phone:"+phone+",,content:"+content);
				return true;
			}
			Log.info("���ŷ���ʧ��,,http��Ӧ����:"+rs.trim()+",phone:"+phone+",,content:"+content);
			return false;
		} catch (Exception e) {
			Log.error("���ŷ���ʧ��,,ϵͳ�쳣,phone:"+phone+",,content:"+content+",,,ex:"+e);
			return false;
		} finally {
			method.releaseConnection();
			((SimpleHttpConnectionManager) client.getHttpConnectionManager()).shutdown();
			if (null != client) {
				client = null;
			}
		}
	}
	
	
	public static void main(String[] args) throws UnsupportedEncodingException {
//		System.out.println(sendMsg("18682033916","�𾴵��û��������˻�������ȫ������100MB������ʹ����Ч����2015-11-31"));
	}
}