package kr.co.hdable.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;



import kr.co.hdable.service.MainService;
import kr.co.hdable.service.UserService;
import kr.co.hdable.vo.ADUserVO;
import kr.co.hdable.vo.MonTargetDetailVO;
import kr.co.hdable.vo.MonTargetVO;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class MonController  {

	@Autowired
	private MainService service  ;
	
	@Autowired
	private UserService userService;
	private Iterator<String> irKeys;
	private Iterator<String> irValues;
	
	/*
	 * 전체 상태 조회
	 */
	@RequestMapping(value="/getMonState.infra")
	public String getMonState(ModelMap map) {

		List<MonTargetVO> lists = service.getMonState();

		/*
		 * 인프라 항목중 현재 장애 상태인 것이 한건이라도 있는지 확인
		 */
		for(MonTargetVO mtvo :lists){
			if(mtvo.getState().equals("down") && mtvo.getId().indexOf("I_") == 0) {
				map.addAttribute("checkState","require");
			}
		}
		
		map.addAttribute("grid",lists);
		
		return "jsonView";
	}
	
	/*
	 * 인프라 영역 장애 등록 
	 */
	@RequestMapping(value="/setMonTargetDetail.infra")
	public String setMonTargetDetail( MonTargetDetailVO vo  ,ModelMap map) {
		Integer retInt = service.setMonTargetDetail(vo);
		map.addAttribute("cnt", retInt);
		
		return "jsonView";
	}
 	
	/*
	 * 인프라 장애 등록 이력 
	 */
	@RequestMapping(value="/getMonTargetDetail.infra")
	public String getMonTargetDetail(MonTargetDetailVO vo, ModelMap map) {

		List<MonTargetDetailVO> lists = service.getMonTargetDetail(vo);
		map.addAttribute("grid",lists);
		
		return "jsonView";
	}
	
	
	/*
	 * 채널 영역 전체 리스트 check 변경
	 */
	@RequestMapping(value="/updateMonListCheckState.infra")
	public String updateMonListCheckState(ModelMap map) {
		Integer retInt = service.updateMonListCheckState();
		
		map.addAttribute("cnt", retInt);
		
		return "jsonView";
	}
	
	/*
	 * 인프라 영역 장애 등록 관련된 mon_list 테이블 update
	 */
	@RequestMapping(value="/updateMonListFromDetailVO.infra")
	public String updateMonListFromDetailVO(MonTargetDetailVO vo,ModelMap map) {
		Integer retInt = service.updateMonListFromDetailVO(vo);
		
		map.addAttribute("cnt", retInt);
		
		return "jsonView";
	}
		
	/*
	 * 인프라 이외 영역 정상/장애 등록
	 */
	@RequestMapping(value="/updateMonTarget.infra")
	public String updateMonTarget(MonTargetVO vo,ModelMap map) {

		Integer retInt = service.updateMonTarget(vo);
		map.addAttribute("cnt", retInt);
		
		return "jsonView";
	}

	
	/*
	 * 화면 하단 stack chart
	 */
	@RequestMapping(value="/getStackChart.infra")
	public String getStackChart( ModelMap map) {
		
		int intGreen = service.getStackChart("green");
		int intCheck = service.getStackChart("check");
		int intDown = service.getStackChart("down");

		int total = intGreen + intCheck + intDown ;
		
		map.addAttribute("xfield",0);
		map.addAttribute("green",intGreen   );
		map.addAttribute("check",intCheck  );
		map.addAttribute("down", intDown );
		map.addAttribute("total",total );
		
		return "jsonView";
	}	
	
	
	/*
	 * 화면 하단 TextArea
	 */
	@RequestMapping(value="/getIntraStateMsg.infra")
	public String getIntraStateMsg( ModelMap map) {
		
		
		List<MonTargetDetailVO> lists = service.getIntraStateMsg();
		
		map.addAttribute("grid",lists);
		
		return "jsonView";
		
	}
	
	
	/*
	 * AD 인증 로그인
	 */
	@RequestMapping(value="/login.infra")
	public String loginAD(ADUserVO vo, ModelMap map) {

		HashMap<String,String> hm = userService.getUserInfo(vo);
		
		Collection<String> keys = hm.keySet();
		irKeys = keys.iterator();
		
		Collection<String> values = hm.values();
		irValues = values.iterator();
		
		while(irKeys.hasNext()){
			map.addAttribute(irKeys.next(), irValues.next());
		}
		return "jsonView";
	}
	
}
