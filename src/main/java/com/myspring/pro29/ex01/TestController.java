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
import org.springframework.web.bind.annotation.PathVariable;  //�޼��� �Ű� ������ URI ���ø� ������ ���ε��Ǿ�� ���� ��Ÿ���� �ּ��Դϴ�. RequestMapping �ּ� ó���� �޼��忡 ���� �����˴ϴ�.
import org.springframework.web.bind.annotation.RequestBody;  //�� ��û�� ������ ���ε��Ǿ�� �մϴ�. ��û ������ HttpMessageConverter�� ���� ���޵Ǿ� ��û�� ������ ������ ���� �޼��� �μ��� Ȯ���մϴ�.
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody; //�޼��� ��ȯ ���� �� ���� ������ ���ε��Ǿ�� ���� ��Ÿ���� �ּ�
import org.springframework.web.bind.annotation.RestController;



/*@RestController */ //A convenience annotation that is itself annotated with @Controller and @ResponseBody.
public class TestController {

	
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@RequestMapping("/nice")
	String fsdfsd(){
		//System.out.print("����� RestController��");		
		logger.info("����� RestController��");
		return "nice"  ;
		
	}
	
	
	
	
	//�߿� @RestController�� ������ View�� �������� ���� ä �����͸� �����ϹǷ� ���� ��������
	//���ܰ� �߻��� �� �ִ�. 
	//���ܿ� ���� �� �� �����ϰ� ��� �ʿ���
	//�׷� ��� ResponseEntity Ŭ������ ����ϸ� ��, �� JSON�̳� HTML �ڹٽ�ũ��Ʈ�� �������� ������ �� �־� ��
	//���� ��� ����� ���� �ۿ��� ���� �� �ֹ��� ���� ������ ���ϰ� �ɸ� ���, ���� �ð��� ������
	//�ֹ��� ó������ ������ �������� ResponseEntityŬ������ HTTP ���� �ڵ带 �����Ͽ� ������ �����ϵ��� �ϸ� ��
	//�׷��� �ۿ��� HTTP ���� �ڵ带 �ν��� �� �ִ� ����� �̿��� �ֹ� ���³� ���� �߻��� �˷���
	
	//�ϱ�
	// 200 OK ��û�� ���������� �Ϸ�
	// 201 CREATED ��û�� �������̰� �� ��� ���ο� ���ҽ� ����
	
	
	//HttpEntity	����� �������� ������  HTTP ��û �Ǵ� ���� ����Ƽ�� ��Ÿ���ϴ�.
	@RequestMapping("/hi2")       //	HttpStatusCode ���� �ڵ带 �߰��ϴ� HttpEntity�� Ȯ���Դϴ�. RestTemplate�� @Controller �޼��忡�� ���˴ϴ�.
	ResponseEntity fsdfsd2(){
		//System.out.print("����� RestController��");
		HttpHeaders responseHeaders= new HttpHeaders();
		
		responseHeaders.add("Content-Type", "text/html;charset=utf-8");
		
		logger.info("����� RestController��2");
		//return "nice" + "�ѱ�" ;
		return new ResponseEntity("hihi" + "�ѱ�",responseHeaders,  HttpStatus.OK);  //HttpStatus.CREATED 	201 Created. ��û�� �������̾����� �� ����� ���ο� ���ҽ��� �����Ǿ��ٴ� �ǹ�
	}
	
	
	
	@RequestMapping("/memberList2_when_errored")
	ResponseEntity memberList2(){
		
		List<MemberVO> memberList=new ArrayList<MemberVO>();
		
		
		for(int i=1;i<=5;i++) {
			MemberVO member= new MemberVO();
			member.setId("hong" + i);
			member.setPwd("1111");
			member.setName("ȫ�浿" + i);
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
				"    alert('��ȸ�� ���');" + 
				"    location.href= '/pro29/memberList';" + 
				"" + 
				"</script>";
		
		return new ResponseEntity(message, responseHeaders,HttpStatus.CREATED );

		
	
	}
	
	
	
	
	
	
	
	
	// �������� ��� ��ü ���� �� Ŭ���̾�Ʈ�� ��� ��ü ��� (jackson-databind �ʿ�)
	
	
	
	@RequestMapping("/member")
	MemberVO member(){
		MemberVO member= new MemberVO();
		member.setId("hong");
		member.setPwd("1111");
		member.setName("ȫ�浿");
		member.setEmail("djk@k.com");
		return member;
	}
	
	
	
	// �ݴ�� Ŭ���̾�Ʈ���� ������ ��� ��ü�� ������ ������ �α�â���� ���(Ajax ��� �̿�)
	
	
	
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
			member.setName("ȫ�浿" + i);
			member.setEmail("djk@k.com" + i);

			memberList.add(member);
		}
		
		

		
		return memberList;
	}
	
		
	@RequestMapping("/article1/{articleNumber}")
	void pathVariableEx(@PathVariable("articleNumber") String articleNumber){
		logger.info("pathVariableEx �޼��忡 ����");
		logger.info(articleNumber);
	}
	
	
	@RequestMapping("/article2/{articleNumber}")
	String pathVariableEx2(@PathVariable("articleNumber") String articleNumber){
		logger.info("pathVariableEx �޼��忡 ����");
		logger.info(articleNumber);
		return articleNumber;
	}
	
	
	@RequestMapping("/article3/{articleNumber}")
	int pathVariableEx3(@PathVariable("articleNumber") int articleNumber){
		logger.info("pathVariableEx �޼��忡 ����");
		logger.info(String.valueOf(articleNumber));
		return articleNumber;
	}
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
}
