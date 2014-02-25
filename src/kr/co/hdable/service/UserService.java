package kr.co.hdable.service;

import java.util.HashMap;

import kr.co.hdable.vo.ADUserVO;

public interface UserService {
	
	public HashMap<String, String> getUserInfo();
	
	public HashMap<String, String> getUserInfo(ADUserVO vo);
	
}
