package com.wlt.webm.business;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.commsoft.epay.util.logging.Log;
import com.wlt.webm.business.dianx.bean.DxFill;
import com.wlt.webm.business.junbao.bean.JBFill;
import com.wlt.webm.business.kaimi.action.KMfill;
import com.wlt.webm.scpcommon.Constant;
import com.wlt.webm.scpdb.DBLog;
import com.wlt.webm.scpdb.DBService;
import com.wlt.webm.scpdb.FundAcctDao;
import com.wlt.webm.scputil.bean.FacctBillBean;
import com.wlt.webm.uictool.Tools;

/**
 * �ڲ�������������
 * @author Administrator
 *
 */
public class InnerProcessDeal {

	public InnerProcessDeal() {

	}

   /**
    * ��¼���׶�����Ϣ
    * @param area   ��������
    * @param tradeserial  ������
    * @param tradeobject  ���׺���
    * @param buyid      �ӿ���id
    * @param service    ҵ������
    * @param fee        ���׽��(��)
    * @param fundacct   �ʻ����
    * @param tradetime  �����ύʱ��ʱ��
    * @param chgtime    ״̬�޸�ʱ�� ���ڶ����ύʱ��ʱ�� 
    * @param writeoff   ������������
    * @param writecheck  ������������
    * @param termType   �ն����
    * @param userID   �û�ID
    * @param user_name  ��¼��	
    * @param Phone_type  �ֻ���������
    * @return 0��ʾ������¼�ɹ� 1��ʾ������¼ʧ��
    */
	public static int logOrder(String area, String tradeserial,
			String tradeobject, String buyid, String service, int fee,
			String fundacct, String tradetime, String chgtime,
			String writeoff, String writecheck,String termType,String userID,int accountleft,
			String user_name,String Phone_type) {
		int flag = 0;
		String tableName = "wlt_orderForm_"
				+ tradetime.substring(2, 6);
		StringBuffer sf = new StringBuffer();
		sf.append("insert into ").append(tableName).append(
				"(areacode,tradeserial,tradeobject,buyid,service,fee,fundacct,tradetime,chgtime,state,writeoff,writecheck,term_type,userid,accountleft,user_name,phone_type)" +
				" values(?,?,?,?,?,?,?,?,?,?,?,? ,?,?,?,?,?)");
		Object[] params = {  area, tradeserial, tradeobject, buyid, service,
				fee, fundacct, tradetime, chgtime, 3, writeoff,writecheck,termType,userID,accountleft,user_name,Phone_type};
		DBService servce =null;
		try {
		    servce =new DBService();
			servce.update(sf.toString(), params);
		} catch (SQLException e) {
			flag = 1;
			Log.error("���붩���쳣");
			e.printStackTrace();
			servce.close();
		}
		servce.close();
		return flag;
	}

    /**
     * �ڲ� �ۿ��Ӷ�����Ķ���״̬ 
     * @param Facct         �ʻ����      
     * @param tradeaccount  �ֻ�����
     * @param time          ����ʱ��
     * @param payFee        ���׽��֣�
     * @param area          ��������
     * @param tradeCode     ��������
     * @param remark        ��ע
     * @param busID         �������ͱ�ʶ
     * @param sa_id         �û�����������
     * @param userID        �û�id 
     * @param user_role     ��ɫ���
     * @param phoneCode     �ֻ�������������
     * @param tradeserial   ������
     * @return
     */
//	public static int indeal_old(String Facct,
//			String tradeaccount, String time, int payFee, String area,
//			String tradeCode, String remark,int busID,int sa_id,int userID,
//			int user_role,int phoneCode, String tradeserial) {
//		int flag = 0;
//		 String seqNo=Tools.getSerialNumber();
//		 DBService servce =null;
//		try {
//			servce=new DBService();
//			servce.setAutoCommit(false);
//			FacctBillBean facct = new FacctBillBean();
//			facct.setFacctTrade(Facct+"02", seqNo, tradeaccount, time, "-"
//					+ payFee, area, tradeCode, remark,tradeserial,null,""+1);
//			FundAcctDao facctDB = new FundAcctDao(servce);
//			DBLog dbLog = new DBLog(servce);
//			//��¼�˻���־������� 
//			dbLog.insertAcctLog(facct);
//			facctDB.updateChildLeft(facct.getFundAcct(),-payFee,null);
//			if(sa_id!=phoneCode){//������û�����ͬһ������
//				//������Ӷ
//				double biL=getEmpValue(servce,userID,1,busID,0,payFee)/100.0;
//				int money=(int)Math.round(payFee/100.0*biL*100.0);
//				facct.setFacctTrade(Facct+"03", seqNo+"Z",tradeaccount, time, ""+money, area, Constant.EMPLOY_BACK, remark
//						,tradeserial,null,""+2);
//				dbLog.insertAcctLog(facct);
//				facctDB.updateChildLeft(Facct+"03",money,null);
//				if(user_role==Constant.WLT_REGISTER){//����Ǵ��� ����鿴�Ƿ����ϼ�
//					 String sql1="select user_pt from sys_user where user_id="+userID+"";
//					 int str0=servce.getInt(sql1);
//					   if(str0!=-1){//�ϼ��ڵ�
//						   String Facct1=getFacct(servce,str0);
//						   int prole=getuserrole(servce,str0);
//							facct.setFacctTrade(Facct1+"03", seqNo+"X", Facct+"02", time, ""+money, area, Constant.EMPLOY_BACK, "�¼��ڵ㷵Ӷ"
//									,tradeserial,null,""+3);
//							dbLog.insertAcctLog(facct);
//							facctDB.updateChildLeft(Facct1+"03",money,null);
//							if(prole==Constant.WLT_REGISTER){
//								 String sql2="select user_pt from sys_user where user_id="+str0+"";
//								 int str2=servce.getInt(sql2);
//								   if(str2!=-1){//���ϼ��ڵ�
//									   String Facct2=getFacct(servce,str2);
//									   int pprole=getuserrole(servce,str2);
//										facct.setFacctTrade(Facct2+"03", seqNo+"XX", Facct+"02", time, ""+money, area, Constant.EMPLOY_BACK, "�¼��ڵ㷵Ӷ"
//												,tradeserial,null,""+4);
//										dbLog.insertAcctLog(facct);
//										facctDB.updateChildLeft(Facct2+"03",money,null);
//								   }
//							}
//					   }
//					   
//				}
//			}else{
//				//������Ӷ
//				double biL=getEmpValue(servce,userID,1,busID,0,payFee)/100.0;
//				int money=(int)Math.round(payFee/100.0*biL*100.0);
//				facct.setFacctTrade(Facct+"03", seqNo+"Z", tradeaccount, time, ""+money, area, Constant.EMPLOY_BACK, remark,tradeserial,null,""+2);
//				dbLog.insertAcctLog(facct);
//				facctDB.updateChildLeft(Facct+"03",money,null);
//				if(user_role==Constant.WLT_REGISTER){//����Ǵ�������鿴�Ƿ����ϼ�
//					 String sql1="select user_pt from sys_user where user_id="+userID+"";
//					 int str0=servce.getInt(sql1);
//					   if(str0!=-1){//�ϼ��ڵ�
//						   String Facct1=getFacct(servce,str0);
//						   int prole=getuserrole(servce,str0);
//							double biL1=getEmpValue(servce,userID,2,busID,1,payFee)/100.0;
//							int money1=(int)Math.round(payFee/100.0*biL*100.0);
//							facct.setFacctTrade(Facct1+"03", seqNo+"X", Facct+"02", time, ""+money1, area, Constant.EMPLOY_BACK, "�¼��ڵ㷵Ӷ",tradeserial,null,""+3);
//							dbLog.insertAcctLog(facct);
//							facctDB.updateChildLeft(Facct1+"03",money1,null);
//							if(prole==Constant.WLT_REGISTER){
//								 String sql2="select user_pt from sys_user where user_id="+str0+"";
//								 int str2=servce.getInt(sql2);
//								   if(str2!=-1){//���ϼ��ڵ�
//									   String Facct2=getFacct(servce,str2);
//									   int pprole=getuserrole(servce,str2);
//										double biL2=getEmpValue(servce,userID,3,busID,1,payFee)/100.0;
//										int money2=(int)Math.round(payFee/100.0*biL*100.0);
//										facct.setFacctTrade(Facct2+"03", seqNo+"XX", Facct+"02", time, ""+money2, area, Constant.EMPLOY_BACK, "�¼��ڵ㷵Ӷ",tradeserial,null,""+4);
//										dbLog.insertAcctLog(facct);
//										facctDB.updateChildLeft(Facct2+"03",money,null);
//								   }
//							}
//					   }
//					   
//				}
//			}
//			//���¶���״̬�����
//		   upOrderState(tradeserial,servce,4);
//		   upOrderLeft(tradeserial,servce,Facct);
//			servce.commit();
//		} catch (Exception e) {
//			flag=1;
//			if (servce != null)
//				servce.rollback();
//			servce.close();
//			Log.error("�ɷѳ���", e);
//			e.printStackTrace();
//		}
//		servce.close();
//		return flag;
//	}
	/**
     * �ڲ� �ۿ��Ӷ�����Ķ���״̬ 
     * @param Facct         �ʻ����      
     * @param tradeaccount  �ֻ�����
     * @param time          ����ʱ��
     * @param payFee        ���׽��֣�
     * @param area          ��������
     * @param tradeCode     ��������
     * @param remark        ��ע
     * @param busID         �������ͱ�ʶ
     * @param sa_id         �û�����������
     * @param userID        �û�id 
     * @param user_role     ��ɫ���
     * @param phoneCode     �ֻ�������������
     * @param tradeserial   ������
     * @return
     */
	public static Map indeal(String Facct,
			String tradeaccount, String time, int payFee, String area,
			String tradeCode, String remark,int busID,int sa_id,int userID,
			int user_role,int phoneCode, String tradeserial,String sg_id) {
		int flag = 0;
		Map resultMap = new HashMap();
		String seqNo=Tools.getSerialNumber();
		DBService servce =null;
		MobileChargeService mcs = new MobileChargeService();
		try {
			servce=new DBService();
			servce.setAutoCommit(false);
			//��ȡ�û�����
			String roleType = mcs.getUserRoleType(String.valueOf(user_role));
			if(Integer.parseInt(roleType)==Constant.WLT_REGISTER){//����Ǵ��� ����鿴�Ƿ����ϼ�
			//�ж�Ѻ���˺����
			int acctLeft = Integer.parseInt(mcs.getFundAcctLeft(Facct+"02"));
	        if(0 == acctLeft || acctLeft - payFee < 0){
	        	flag = 1;
	        	resultMap.put("flag", flag);
	        	return resultMap;
	        }
	        //�˻���ϸ��¼
			FacctBillBean facct = new FacctBillBean();
//			childfacct,dealserial,tradeaccount,tradetime,tradefee,tradetype,explain,state,distime,accountleft,tradeserial,other_childfacct,pay_type
			facct.setFacctTrade(Facct+"02", seqNo, tradeaccount, time,
					String.valueOf(payFee), tradeCode,remark,"0",time,String.valueOf(acctLeft - payFee), tradeserial,"",""+1);
			FundAcctDao facctDB = new FundAcctDao(servce);
			DBLog dbLog = new DBLog(servce);
			//��¼�ۿ��˻���־
			dbLog.insertAcctLog(facct);
			//������� 
			facctDB.updateChildLeft(facct.getFundAcct(),-payFee,null);
			//������Ӷ
			double biL=getEmpValue(servce,userID,1,busID,0,payFee,sa_id,phoneCode,sg_id)/100.0;
			int money=(int)Math.round(payFee/100.0*biL*100.0);
			resultMap.put("empFee-self", money);
			String accSql = "select usableleft from wlt_childfacct where childfacct = '"+Facct+"03'";
			int accLeft = servce.getInt(accSql);
			facct.setFacctTrade(Facct+"03", seqNo+"Z", Facct+"03", time, ""+money, tradeCode,remark,"0", time, String.valueOf(accLeft+money)
					,tradeserial,"",""+2);
			//��¼�ۿ��˻���־
			dbLog.insertAcctLog(facct);
			facctDB.updateChildLeft(Facct+"03",money,null);
				//һ��
				 String sql1="select user_pt from sys_user where user_id="+userID+"";
				 int str0=servce.getInt(sql1);
				   if(str0!=0){//�ϼ��ڵ�
					   String sqlRole = "select sr_type from sys_role where sr_id = (select user_role from sys_user where user_id = "+str0+")";
					   int rType = servce.getInt(sqlRole);
					   if(rType == Constant.WLT_REGISTER){
						   //�ж��ϼ��˻�������
						   int isEnable = isEnable(String.valueOf(str0));
						   if(0 == isEnable){
							   //��ȡ�ϼ��û�����
							   String sqlSite = "select user_site from sys_user where user_id = "+str0;
							   int sa_pid = servce.getInt(sqlSite);
							   //��ȡһ�����Ӷ����
							   double empLevOne = getEmpByLevel(payFee, "2",sa_pid,busID)/100.0;
							   int empLevOneMoney=(int)Math.round(payFee/100.0*empLevOne*100.0);
							   if(0 != empLevOneMoney){
								   String Facct1=getFacct(servce,str0);
								   resultMap.put("acct-levelone", Facct1);
								   resultMap.put("empfee-levelone", empLevOneMoney);
								   int prole=getuserrole(servce,str0);
								   String accSql1 = "select usableleft from wlt_childfacct where childfacct = '"+Facct1+"03'";
								   int accLeft1 = servce.getInt(accSql1);
								   facct.setFacctTrade(Facct1+"03", seqNo+"X", Facct+"03", time,String.valueOf(empLevOneMoney),tradeCode,remark,"0",time,String.valueOf(accLeft1+empLevOneMoney),
										   tradeserial,"",""+3);
								   dbLog.insertAcctLog(facct);
								   facctDB.updateChildLeft(Facct1+"03",empLevOneMoney,null);
							   }
							   
						   }
					   }else if(rType == Constant.WLT_PERSION){//�ϼ��Ǵ�����
						 //�ж��ϼ��˻�������
						   int isEnable = isEnable(String.valueOf(str0));
						   if(0 == isEnable){
							   //��ȡ������Ӷ��
							   double empMerchant = getEmpForMerchant(str0, busID)/100.0;
							   int empMerchantMoney=(int)Math.round(payFee/100.0*empMerchant*100.0);
							   if(0 != empMerchantMoney){
								   String Facct1=getFacct(servce,str0);
								   resultMap.put("acct-levelone", Facct1);
								   resultMap.put("empfee-levelone", empMerchantMoney);
								   int prole=getuserrole(servce,str0);
								   String accSql1 = "select usableleft from wlt_childfacct where childfacct = '"+Facct1+"03'";
								   int accLeft1 = servce.getInt(accSql1);
								   facct.setFacctTrade(Facct1+"03", seqNo+"X", Facct+"03", time,String.valueOf(empMerchantMoney), tradeCode,remark,"0",time,String.valueOf(accLeft1+empMerchantMoney)
										   ,tradeserial,null,""+3);
								   dbLog.insertAcctLog(facct);
								   facctDB.updateChildLeft(Facct1+"03",empMerchantMoney,null);
							   }
						   }
					   }
					   //����
//					   String sql2="select user_pt from sys_user where user_id="+str0+"";
//					   int str2=servce.getInt(sql2);
//					   if(str2!=0){//���ϼ��ڵ�
//						   String sqlRole2 = "select sr_type from sys_role where sr_id = (select user_role from sys_user where user_id = "+str2+")";
//						   int rType2 = servce.getInt(sqlRole2);
//						   if(rType2 == Constant.WLT_REGISTER){
//							   //�ж��ϼ��˻�������
//							   int isEnable2 = isEnable(String.valueOf(str2));
//							   if(0 == isEnable2){
//								   //��ȡ�������Ӷ����
//								   double empLevTwo = getEmpByLevel(payFee, "3")/100.0;
//								   int empLevTwoMoney=(int)Math.round(payFee/100.0*empLevTwo*100.0);
//								   if(0 != empLevTwoMoney){
//									   String Facct2=getFacct(servce,str2);
//									   resultMap.put("acct-leveltwo", Facct2);
//									   resultMap.put("empfee-leveltwo", empLevTwoMoney);
//									   String accSql2 = "select usableleft from wlt_childfacct where childfacct = '"+Facct2+"03'";
//									   int accLeft2 = servce.getInt(accSql2);
//									   facct.setFacctTrade(Facct2+"03", seqNo+"XX", Facct2+"03", time, String.valueOf(payFee),tradeCode,remark,"0",time,String.valueOf(accLeft2+empLevTwoMoney)
//											   ,tradeserial,"",""+4);
//									   dbLog.insertAcctLog(facct);
//									   facctDB.updateChildLeft(Facct2+"03",empLevTwoMoney,null);
//								   }
//							   }
//						   }
//					   }
				   }
			}else if(Integer.parseInt(roleType)==Constant.WLT_AEGENT){//�ӿ���
				//�ж�Ѻ���˺����
				int acctLeft = Integer.parseInt(mcs.getFundAcctLeft(Facct+"02"));
		        if(0 == acctLeft || acctLeft - payFee < 0){
		        	flag = 1;
		        	resultMap.put("flag", flag);
		        	return resultMap;
		        }
		        //�˻���ϸ��¼
				FacctBillBean facct = new FacctBillBean();
//				childfacct,dealserial,tradeaccount,tradetime,tradefee,tradetype,explain,state,distime,accountleft,tradeserial,other_childfacct,pay_type
				facct.setFacctTrade(Facct+"02", seqNo, tradeaccount, time,
						String.valueOf(payFee), tradeCode,remark,"0",time,String.valueOf(acctLeft - payFee), tradeserial,"",""+1);
				FundAcctDao facctDB = new FundAcctDao(servce);
				DBLog dbLog = new DBLog(servce);
				//��¼�ۿ��˻���־
				dbLog.insertAcctLog(facct);
				//������� 
				facctDB.updateChildLeft(facct.getFundAcct(),-payFee,null);
				//������Ӷ
				double biL=getEmpValue(servce,userID,1,busID,0,payFee,sa_id,phoneCode,sg_id)/100.0;
				int money=(int)Math.round(payFee/100.0*biL*100.0);
				resultMap.put("empFee-self", money);
				String accSql = "select usableleft from wlt_childfacct where childfacct = '"+Facct+"03'";
				int accLeft = servce.getInt(accSql);
				facct.setFacctTrade(Facct+"03", seqNo+"Z", Facct, time, ""+money, tradeCode,remark,"0", time, String.valueOf(accLeft+money)
						,tradeserial,"",""+2);
				//��¼�ۿ��˻���־
				dbLog.insertAcctLog(facct);
				facctDB.updateChildLeft(Facct+"03",money,null);
			}
			//���¶���״̬�����
		   upOrderState(tradeserial,servce,4);
		   upOrderLeft(tradeserial,servce,Facct);
			servce.commit();
		} catch (Exception e) {
			flag=1;
			if (servce != null)
				servce.rollback();
			servce.close();
			Log.error("�ɷѳ���", e);
			e.printStackTrace();
		}
		servce.close();
		resultMap.put("flag", flag);
		return resultMap;
	}
	/**
     * �ⲿ�ӿ� �ۿ��Ӷ�����Ķ���״̬ 
     * @param Facct         �ʻ����      
     * @param tradeaccount  �ֻ�����
     * @param time          ����ʱ��
     * @param payFee        ���׽��֣�
     * @param area          ��������
     * @param tradeCode     ��������
     * @param remark        ��ע
     * @param busID         �������ͱ�ʶ
     * @param sa_id         �û�����������
     * @param userID        �û�id 
     * @param user_role     ��ɫ���
     * @param phoneCode     �ֻ�������������
     * @param tradeserial   ������
     * @return
     */
	public static Map outDeal(String Facct,
			String tradeaccount, String time, int payFee, String area,
			String tradeCode, String remark,int busID,int sa_id,int userID,
			int user_role,int phoneCode, String tradeserial,String sg_id) {
		int flag = 0;
		Map resultMap = new HashMap();
		String seqNo=Tools.getSerialNumber();
		DBService servce =null;
		MobileChargeService mcs = new MobileChargeService();
		try {
			servce=new DBService();
			servce.setAutoCommit(false);
			//��ȡ�û�����
			String roleType = mcs.getUserRoleType(String.valueOf(user_role));
			if(Integer.parseInt(roleType)==Constant.WLT_AEGENT){//�ӿ���
				//�ж�Ѻ���˺����
				int acctLeft = Integer.parseInt(mcs.getFundAcctLeft(Facct+"02"));
		        if(0 == acctLeft || acctLeft - payFee < 0){
		        	flag = 1;
		        	resultMap.put("flag", flag);
		        	return resultMap;
		        }
		        //�˻���ϸ��¼
				FacctBillBean facct = new FacctBillBean();
//				childfacct,dealserial,tradeaccount,tradetime,tradefee,tradetype,explain,state,distime,accountleft,tradeserial,other_childfacct,pay_type
				facct.setFacctTrade(Facct+"02", seqNo, tradeaccount, time,
						String.valueOf(payFee), tradeCode,remark,"0",time,String.valueOf(acctLeft - payFee), tradeserial,"",""+1);
				FundAcctDao facctDB = new FundAcctDao(servce);
				DBLog dbLog = new DBLog(servce);
				//��¼�ۿ��˻���־
				dbLog.insertAcctLog(facct);
				//������� 
				facctDB.updateChildLeft(facct.getFundAcct(),-payFee,null);
				//������Ӷ
				double biL=getEmpValue(servce,userID,1,busID,0,payFee,sa_id,phoneCode,sg_id)/100.0;
				int money=(int)Math.round(payFee/100.0*biL*100.0);
				resultMap.put("empFee-self", money);
				String accSql = "select usableleft from wlt_childfacct where childfacct = '"+Facct+"03'";
				int accLeft = servce.getInt(accSql);
				facct.setFacctTrade(Facct+"03", seqNo+"Z", Facct, time, ""+money, tradeCode,remark,"0", time, String.valueOf(accLeft+money)
						,tradeserial,"",""+2);
				//��¼�ۿ��˻���־
				dbLog.insertAcctLog(facct);
				facctDB.updateChildLeft(Facct+"03",money,null);
			}
			//���¶���״̬�����
		   upOrderState(tradeserial,servce,4);
		   upOrderLeft(tradeserial,servce,Facct);
			servce.commit();
		} catch (Exception e) {
			flag=1;
			if (servce != null)
				servce.rollback();
			servce.close();
			Log.error("�ɷѳ���", e);
			e.printStackTrace();
		}
		servce.close();
		resultMap.put("flag", flag);
		return resultMap;
	}
	/**
	 * ���������Ӷ��
	 * @param userId 
	 * @return
	 */
	public static double getEmpForMerchant(int userId,int tradeType){
		double value=0.0;
		try {
			DBService servce =new DBService();
			//�����û�Ӷ��
			String sql = "select sg_id from sys_commissionuser where su_id = "+userId;
			String sgId = servce.getString(sql);
			if(null != sgId && !"".equals(sgId)){
				String sqlEmp = "select sc_traderpercent from sys_commission where sg_id = "+sgId+" and sc_tradertype = "+tradeType;
				value = servce.getDouble(sqlEmp);
			}
		} catch (Exception e) {
			Log.info("���������Ӷ�����������");
			e.printStackTrace();
		}
		return value;	
	}
	/**
	 * ���������Ӷ��
	 * @param userId 
	 * @return
	 */
	public static double getEmpByLevel(int fee,String level,int sa_id,int sc_tradertype){
		double value=0.0;
		try {
			DBService servce =new DBService();
			String sqlSg = "select sg_id from sys_commissiongroup where srt_id = 2 and sg_defaut = 1 and sa_id = "+sa_id;
			String sg_id = servce.getString(sqlSg);
			String sql = "select sc_traderpercent from sys_commission where sc_traderbegin < "+fee/100+" and "+fee/100+" <= sc_traderend and sc_level = "+level+" and sg_id = "+sg_id;
			value = servce.getDouble(sql);
		} catch (Exception e) {
			Log.info("���������Ӷ�����������");
			e.printStackTrace();
		}
		return value;	
	}
	/**
	 * �û��Ŀ�����
	 * @param userId 
	 * @return
	 */
	public static int isEnable(String userId){
		int flag=0;
		try {
			DBService servce =new DBService();
			//�û��Ƿ����
			String sql = "select user_status from sys_user where user_id="+userId;
			int state = servce.getInt(sql);
			if(-1 == state){
				flag = 1;
				return flag;
			}
			//�û�״̬
			if(state != 0){
				flag = 1;
				return flag;
			}
			//�ʽ��˻��Ƿ����
			String sql1 = "select fundacct from wlt_facct where termid="+userId;
			String fundAcct = servce.getString(sql1);
			if(null == fundAcct || "".equals(fundAcct)){
				flag = 1;
				return flag;
			}
			//�ʽ��˻�״̬
			String sql2 = "select state from wlt_facct where fundacct='"+fundAcct+"'";
			String state1 = servce.getString(sql2);
			if(!"1".equals(state1)){
				flag = 1;
				return flag;
			}
			//�ʽ����˻��Ƿ����
			String sql3 = "select state from wlt_childfacct where childfacct="+fundAcct+"03";
			String state3 = servce.getString(sql3);
			if(null == state3 || "".equals(state3) || !"1".equals(state3)){
				flag = 1;
				return flag;
			}
		} catch (Exception e) {
			Log.info("�û��Ŀ����Դ����������");
			flag=1;
			e.printStackTrace();
		}
		return flag;	
	}
	/**
	 * ��ȷʧ�ܻ����������
	 * @param seNo ������� 
	 * @param fee   ���֣�
	 * @return
	 */
	public static int dealFailResult(String seNo,int fee){
		int flag=0;

		String tableName = "wlt_acctbill_"
			+ Tools.getNowDate().substring(2, 6);
		String sql="select childfacct from "+tableName+" where tradeserial='"+seNo+"'";
		String sql0="update "+tableName+" set state=1 where tradeserial='"+seNo+"' and pay_type=2";
		try {
			DBService servce =new DBService();
			if(servce.hasData(sql)){
				String facct0=servce.getString(sql);
				servce.update(sql0);
				FundAcctDao facctDB = new FundAcctDao(servce);
				facctDB.updateChildLeft(facct0,fee,null);
				String sql1="update "+tableName+" set state=1 where tradeserial='"+seNo+"' and pay_type=3";
				servce.update(sql1);
				int tradefee=servce.getInt("select tradefee from "+tableName+" where tradeserial='"+seNo+"'");
				facctDB.updateChildLeft(facct0.substring(0,facct0.length()-2)+"03",-tradefee,null);
				String sql2="select childfacct,tradefee from "+tableName+" where tradeserial='"+seNo+"'";
				if(servce.hasData(sql2)){
					List list=servce.getList(sql2);
					String sql20="update "+tableName+" set state=1 where tradeserial='"+seNo+"' and pay_type=4";
					servce.update(sql20);
					String fac=((String)list.get(0)).substring(0,((String)list.get(0)).length()-2);
					int f=(Integer)list.get(1);
					facctDB.updateChildLeft(fac+"03",-f,null);
				}
			}
		} catch (Exception e) {
			Log.info("�����������");
			flag=1;
			e.printStackTrace();
		}
		return flag;	
	}
	
	/**
	 * ���Ķ���״̬
	 * @param seNo  �������
	 * @param db  
	 * @param state  ����״̬
	 * @throws SQLException
	 */
	public static void upOrderState(String seNo,DBService db,int state) throws SQLException{
		String tableName = "wlt_orderForm_"
			+ Tools.getNowDate().substring(2, 6);
		String sql="update "+tableName+" set state="+state+" where tradeserial='"+seNo+"'";
		db.update(sql);
	}
	/**
	 * ���Ķ������
	 * @param seNo  �������
	 * @param db  
	 * @param state  ����״̬
	 * @throws SQLException
	 */
	public static void upOrderLeft(String seNo,DBService db,String account) throws SQLException{
		String tableName = "wlt_orderForm_"
			+ Tools.getNowDate().substring(2, 6);
		String sql0="select accountleft from wlt_facct where fundacct='"+account+"'";
		int a=db.getInt(sql0);
		String sql="update "+tableName+" set accountleft="+a+" where tradeserial='"+seNo+"'";
		db.update(sql);
	}
	

	/**���Ӷ�����
	 * @param db
	 * @param userID
	 * @param emplevel
	 * @param tradeType
	 * @param flag
	 * @param fee
	 * @return
	 * @throws SQLException
	 */
	public static double getEmpValue(DBService db,int userID,
			int emplevel,int tradeType,int flag,int fee,int sa_id,int pcode,String sg_id) throws SQLException{
		double value=0.0;
		if(sa_id != pcode){
			String sql="select sc_traderpercent from sys_commission where sc_other=0 and sg_id = "+sg_id;
			value=db.getDouble(sql);
		}else{
			String sql="select sc_traderpercent from sys_commission where sc_other=1 and sg_id="+sg_id+
			" and sc_tradertype="+tradeType+" and sc_traderbegin*100<="+fee+" and sc_traderend*100>="+fee;
			value=db.getDouble(sql);
		}
		return value;
	}
	
	/**
	 * �����û�id��ȡ�˻�
	 * @param db
	 * @param userID
	 * @return
	 * @throws SQLException
	 */
	public static String getFacct(DBService db,int userID) throws SQLException{
		String sql="select fundacct from wlt_facct where termID="+userID+"";
		return db.getString(sql);
	}
	
	/**
	 * �����û�id��ȡ��ɫ
	 * @param db
	 * @param userID
	 * @return
	 * @throws SQLException
	 */
	public static int getuserrole(DBService db,int userID) throws SQLException{
		String sql="select user_role from sys_user where user_id="+userID+"";
		return db.getInt(sql);
	}
	
 
	
	/**
	 * ��Ʊ�������ӿ�
	 * @param phone �ֻ�����
	 * @param serialNo ���γ�����ˮ��
	 * @param rollSerialNo  �Ʒ�������ˮ��ԭ��ֵ��ˮ��
	 * @param amount        amount(��)
	 * @param requestTime   yyyyMMddHHmmss
	 * @return  0��ʾ�ɹ� �����ʾʧ��
	 */ 
//	public static int  yplRevert(String phone,String serialNo,String rollSerialNo,String amount,String requestTime){
//		RevertBusiness fill=new RevertBusiness(phone,serialNo,rollSerialNo,amount,requestTime);
//		return fill.deal();
//}
	
	
	
	/**
	 * ������ֵ�ӿ�
	 * @param prodid   ��Ʒid
	 * @param orderid   ������
	 * @param mobilenum  ��ֵ����
	 * @param datetime  ����ʱ�� yyyyMMddHHmmss
	 * @return 0��ʾ�ɹ� �����ʾʧ��
	 */
	public static int junbaoFill(String prodid,String orderid,String mobilenum,
			String datetime){
				return JBFill.fill(prodid, orderid, mobilenum, datetime);
			}
	
	/**
	 * ����ֱ��ӿ�
	 * @param seqNo  ������ˮ�ż�������
	 * @param phone  �ֻ�����
	 * @param payFee ���׽��֣�
	 * @param areaCode ������������
	 * @param numType  01-��һ����ɷѣ�02����һ�����ɷѣ�03-��ͬ�ɷ�
	 * @param tradeSerialNo  ������ˮ��
	 * @return  0��ʾ�ɹ� �����ʾʧ��
	 */
	public static int dxFill(String seqNo,String phone,String payFee,
			String areaCode,String numType,String tradeSerialNo){
		DxFill dx=new DxFill(seqNo,phone,payFee,
				areaCode,numType,tradeSerialNo);
		return dx.deal();
	}
	/**
	 * ���ų����ӿ�
	 * @param seqNo ��Ϣ��ˮ�� ���ñʳ���������
	 * @param context ������������PayNo��RollBack �ֱ��ʾ�绰���� �ͳ�����������
	 * RollBack ��ʽΪ: ��Ӧ��ֵ������ˮ��#���׽��֣�#�û��󶨺���
	 * @return  0��ʾ�ɹ� �����ʾʧ��
	 */
//	public static int dxRevert(Map context,String seqNo){
//		com.wlt.webm.business.dianx.bean.RevertBusiness revert=
//			new com.wlt.webm.business.dianx.bean.RevertBusiness(context, seqNo);
//		return revert.deal();
//	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	        float a=36,b=45;
		    float c=(a/(a+b))*100;
		    float d=(b/(a+b))*100;
	System.out.println(c);
	   double f=10329/100.0;
	   System.out.println(f);
	   double e=0.97;//���ܰٷֱȵ�ֵ
	   double fen=e*f*100.0;
	   System.out.println(fen);
	   System.out.println((int)Math.round(fen));
	   
	   
	   BigDecimal big = new BigDecimal(fen);
	   double f1 = big.setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue();
	   System.out.println(f1);  //8978.4  100.19130000000001
	
		}


}