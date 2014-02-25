package kr.co.hdable.service;

import java.util.List;

import kr.co.hdable.vo.MonTargetDetailVO;
import kr.co.hdable.vo.MonTargetVO;

public interface MainService {

	public List<MonTargetVO> getMonState();

	public Integer setMonTargetDetail(MonTargetDetailVO vo);

	public List<MonTargetDetailVO> getMonTargetDetail(MonTargetDetailVO vo);

	public Integer updateMonListCheckState();

	public Integer updateMonListFromDetailVO(MonTargetDetailVO vo);

	public Integer updateMonTarget(MonTargetVO vo);

	public Integer getStackChart(String strCon);

	public List<MonTargetDetailVO> getIntraStateMsg();

		

}
