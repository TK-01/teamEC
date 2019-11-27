package com.internousdev.pumpkin.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.pumpkin.dao.PurchaseHistoryInfoDAO;
import com.internousdev.pumpkin.dto.PurchaseHistoryInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class DeletePurchaseHistoryAction extends ActionSupport implements SessionAware{
	private List<PurchaseHistoryInfoDTO> purchaseHistoryInfoDTOList;
	private Map<String,Object> session;

	//商品購入履歴の削除
	public String execute(){

		String tempLogined = String.valueOf(session.get("logined"));
		int logined = "null".equals(tempLogined)? 0 : Integer.parseInt(tempLogined);
		if(logined != 1) {
		return "loginError";
		}

		String result=ERROR;

		PurchaseHistoryInfoDAO purchaseHistoryInfoDAO=new PurchaseHistoryInfoDAO();
		int count=purchaseHistoryInfoDAO.deleteAll(String.valueOf(session.get("userId")));
		if(count>0){
			purchaseHistoryInfoDTOList=purchaseHistoryInfoDAO.getPurchaseHistoryList(String.valueOf(session.get("userId")));
			result=SUCCESS;
		}
		return result;
	}

	public List<PurchaseHistoryInfoDTO> getPurchaseHistoryInfoDTOList(){
		return purchaseHistoryInfoDTOList;
	}
	public void setPurchaseHistoryInfoDTOList(List<PurchaseHistoryInfoDTO> purchaseHistoryInfoDTOList){
		this.purchaseHistoryInfoDTOList=purchaseHistoryInfoDTOList;
	}

	public Map<String,Object> getSession(){
		return session;
	}
	public void setSession(Map<String,Object> session){
		this.session=session;
	}
}