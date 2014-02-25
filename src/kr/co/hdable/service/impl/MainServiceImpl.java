package kr.co.hdable.service.impl;

import java.util.List;



import kr.co.hdable.dao.MainDao;
import kr.co.hdable.service.MainService;
import kr.co.hdable.vo.MonTargetDetailVO;
import kr.co.hdable.vo.MonTargetVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainServiceImpl implements MainService {

	@Autowired
	private MainDao dao ;

	@Override
	public List<MonTargetVO> getMonState() {
		List<MonTargetVO> lists = dao.getMonState();
		return lists;
	}

	@Override
	public Integer setMonTargetDetail(MonTargetDetailVO vo) {
		Integer retInt = dao.setMonTatgetDetail(vo);
		return retInt;
	}

	@Override
	public List<MonTargetDetailVO> getMonTargetDetail(MonTargetDetailVO vo) {
		
		List<MonTargetDetailVO> lists = dao.getMonTargetDetail(vo);

		return lists;
	}

	@Override
	public Integer updateMonListCheckState() {
		
		Integer retInt = dao.updateMonListCheckState();
		return retInt;
	}

	@Override
	public Integer updateMonListFromDetailVO(MonTargetDetailVO vo) {
		
		Integer retInt = dao.updateMonListFromDetailVO(vo);
		return retInt;
		
	}

	@Override
	public Integer updateMonTarget(MonTargetVO vo) {

		Integer retInt = dao.updateMonTarget(vo);
		
		return retInt;
	}

	@Override
	public Integer getStackChart(String strCon) {
		
		Integer retInt = dao.getStackChart(strCon);
		
		return retInt;
	}

	@Override
	public List<MonTargetDetailVO> getIntraStateMsg() {

		List<MonTargetDetailVO> lists = dao.getIntraStateMsg();
		return lists;
	}


	
	
//	getMonInfo
	
}
