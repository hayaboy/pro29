package com.myspring.pro29.ex01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;  //Enumeration of HTTP status codes.
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;  //메서드 매개 변수가 URI 템플릿 변수에 바인딩되어야 함을 나타내는 주석입니다. RequestMapping 주석 처리기 메서드에 대해 지원됩니다.
import org.springframework.web.bind.annotation.RequestBody;  //웹 요청의 본문에 바인딩되어야 합니다. 요청 본문은 HttpMessageConverter를 통해 전달되어 요청의 콘텐츠 유형에 따라 메서드 인수를 확인합니다.
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody; //메서드 반환 값이 웹 응답 본문에 바인딩되어야 함을 나타내는 주석
import org.springframework.web.bind.annotation.RestController;



/*@RestController */ //A convenience annotation that is itself annotated with @Controller and @ResponseBody.
public class TestController {

	
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@RequestMapping("/nice")
	String fsdfsd(){
		//System.out.print("여기는 RestController여");		
		logger.info("여기는 RestController여");
		return "nice"  ;
		
	}
	
	
	
	
	//중요 @RestController는 별도의 View를 제공하지 않은 채 데이터를 전달하므로 전달 과정에서
	//예외가 발생할 수 있다. 
	//예외에 대해 좀 더 세밀하게 제어가 필요함
	//그럴 경우 ResponseEntity 클래스를 사용하면 됨, 즉 JSON이나 HTML 자바스크립트를 브라우저로 전송할 수 있어 편리
	//예를 들어 모바일 쇼핑 앱에서 명절 때 주문이 몰려 서버에 부하가 걸릴 경우, 일정 시간이 지나도
	//주문이 처리되지 않으면 서버에서 ResponseEntity클래스에 HTTP 상태 코드를 설정하여 앱으로 전송하도록 하면 됨
	//그러면 앱에서 HTTP 상태 코드를 인식할 수 있는 기능을 이용해 주문 상태나 예외 발생을 알려줌
	
	//암기
	// 200 OK 요청이 성공적으로 완료
	// 201 CREATED 요청이 성공적이고 그 결과 새로운 리소스 생성
	
	
	//HttpEntity	헤더와 본문으로 구성된  HTTP 요청 또는 응답 엔터티를 나타냅니다.
	@RequestMapping("/hi2")       //	HttpStatusCode 상태 코드를 추가하는 HttpEntity의 확장입니다. RestTemplate과 @Controller 메서드에서 사용됩니다.
	ResponseEntity fsdfsd2(){
		//System.out.print("여기는 RestController여");
		HttpHeaders responseHeaders= new HttpHeaders();
		
		responseHeaders.add("Content-Type", "text/html;charset=utf-8");
		
		logger.info("여기는 RestController여2");
		//return "nice" + "한글" ;
		return new ResponseEntity("hihi" + "한글",responseHeaders,  HttpStatus.OK);  //HttpStatus.CREATED 	201 Created. 요청이 성공적이었으며 그 결과로 새로운 리소스가 생성되었다는 의미
	}
	
	
	
	@RequestMapping("/memberList2_when_errored")
	ResponseEntity memberList2(){
		
		List<MemberVO> memberList=new ArrayList<MemberVO>();
		
		
		for(int i=1;i<=5;i++) {
			MemberVO member= new MemberVO();
			member.setId("hong" + i);
			member.setPwd("1111");
			member.setName("홍길동" + i);
			member.setEmail("djk@k.com" + i);

			memberList.add(member);
		}
		
		return new ResponseEntity(memberList, HttpStatus.INTERNAL_SERVER_ERROR );

		
	
	}
	
	@RequestMapping("/addMember")
	ResponseEntity addMember(){
		
		HttpHeaders responseHeaders= new HttpHeaders();
		
		responseHeaders.add("Content-Type", "text/html;charset=utf-8");
		
		String message="<script>" + 
				"" + 
				"    alert('새회원 등록');" + 
				"    location.href= '/pro29/memberList';" + 
				"" + 
				"</script>";
		
		return new ResponseEntity(message, responseHeaders,HttpStatus.CREATED );

		
	
	}
	
	
	
	
	
	
	
	
	// 서버에서 멤버 객체 생성 후 클라이언트에 멤버 객체 찍기 (jackson-databind 필요)
	
	
	
	@RequestMapping("/member")
	MemberVO member(){
		MemberVO member= new MemberVO();
		member.setId("hong");
		member.setPwd("1111");
		member.setName("홍길동");
		member.setEmail("djk@k.com");
		return member;
	}
	
	
	
	// 반대로 클라이언트에서 서버로 멤버 객체를 보내서 서버의 로그창에서 찍기(Ajax 방식 이용)
	
	
	
	@RequestMapping(value = "/member2", method = RequestMethod.POST)
	public void member2(@RequestBody MemberVO vo){
		logger.info(vo.toString());
	}
	
	
	
	@ResponseBody
	@RequestMapping("/thisrequest")
	public Map<String, String> member3() {
		Map<String, String> map=new HashMap<String, String>();
		map.put("thisiskey", "thisisvalue");
		map.put("id", "hong");
		return map;
	}
	
	
	
	
	
	@RequestMapping("/memberList")
	List<MemberVO>  memberList(){
		
		List<MemberVO> memberList=new ArrayList<MemberVO>();
		
		
		for(int i=1;i<=5;i++) {
			MemberVO member= new MemberVO();
			member.setId("hong" + i);
			member.setPwd("1111");
			member.setName("홍길동" + i);
			member.setEmail("djk@k.com" + i);

			memberList.add(member);
		}
		
		

		
		return memberList;
	}
	
		
	@RequestMapping("/article1/{articleNumber}")
	void pathVariableEx(@PathVariable("articleNumber") String articleNumber){
		logger.info("pathVariableEx 메서드에 들어옴");
		logger.info(articleNumber);
	}
	
	
	@RequestMapping("/article2/{articleNumber}")
	String pathVariableEx2(@PathVariable("articleNumber") String articleNumber){
		logger.info("pathVariableEx 메서드에 들어옴");
		logger.info(articleNumber);
		return articleNumber;
	}
	
	
	@RequestMapping("/article3/{articleNumber}")
	int pathVariableEx3(@PathVariable("articleNumber") int articleNumber){
		logger.info("pathVariableEx 메서드에 들어옴");
		logger.info(String.valueOf(articleNumber));
		return articleNumber;
	}
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
}
