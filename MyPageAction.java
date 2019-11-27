package com.internousdev.pumpkin.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.pumpkin.dao.UserInfoDAO;
import com.internousdev.pumpkin.dto.UserInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class MyPageAction extends ActionSupport implements SessionAware{

	private Map<String,Object> session;
	private UserInfoDTO userInfoDTO;

	//ログインしているユーザー情報の取得
	public String execute(){

		String tempLogined = String.valueOf(session.get("logined"));
		int logined = "null".equals(tempLogined)? 0 : Integer.parseInt(tempLogined);
		if(logined != 1) {
			return "loginError";
		}

		UserInfoDAO userInfoDAO=new UserInfoDAO();
		userInfoDTO=userInfoDAO.getUserInfo(String.valueOf(session.get("userId")));

		return SUCCESS;
	}

	public UserInfoDTO getUserInfoDTO(){
		return userInfoDTO;
	}
	public void setUserInfoDTO(UserInfoDTO userInfoDTO){
		this.userInfoDTO=userInfoDTO;
	}

	public Map<String,Object> getSession(){
		return session;
	}
	public void setSession(Map<String,Object> session){
		this.session=session;
	}
}