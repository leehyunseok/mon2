package kr.co.hdable.dao.impl;

import java.util.List;

import kr.co.hdable.dao.MainDao;
import kr.co.hdable.vo.MonTargetDetailVO;
import kr.co.hdable.vo.MonTargetVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

@Repository  
public class MainDaoImpl implements MainDao{
	
	@Autowired
	private SqlMapClientTemplate sqlMapClientTemplate ;

	@Override
	public List<MonTargetVO> getMonState() {
		@SuppressWarnings("unchecked")
		List<MonTargetVO> lists = sqlMapClientTemplate.queryForList("common.getMonState");
		return lists ;
	}

	@Override
	public Integer setMonTatgetDetail(MonTargetDetailVO vo) {
		
		Integer retInt = sqlMapClientTemplate.update("common.setMonTatgetDetail",vo);
		
		return retInt;
	}

	@Override
	public List<MonTargetDetailVO> getMonTargetDetail(MonTargetDetailVO vo) {
		
		@SuppressWarnings("unchecked")
		List<MonTargetDetailVO> lists = sqlMapClientTemplate.queryForList("common.getMonTargetDetail",vo);
		
		return lists ;
	}

	@Override
	public Integer updateMonListCheckState() {

		Integer retInt = sqlMapClientTemplate.update("common.updateMonListCheckState");
		return retInt;
	}

	@Override
	public Integer updateMonListFromDetailVO(MonTargetDetailVO vo) {

		Integer retInt = sqlMapClientTemplate.update("common.updateMonListFromDetailVO",vo);
		
		return retInt;
		
	}

	@Override
	public Integer updateMonTarget(MonTargetVO vo) {

		Integer retInt = sqlMapClientTemplate.update("common.updateMonTarget",vo);
		
		return retInt;
	}

	@Override
	public Integer getStackChart(String strCon) {
		
		Integer retInt = (Integer) sqlMapClientTemplate.queryForObject("common.getStackChart",strCon);
		
		return retInt;
	}

	@Override
	public List<MonTargetDetailVO> getIntraStateMsg() {

		@SuppressWarnings("unchecked")
		List<MonTargetDetailVO> lists = sqlMapClientTemplate.queryForList("common.getIntraStateMsg");

		return lists ;
		
	};

}