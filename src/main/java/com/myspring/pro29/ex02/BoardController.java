package com.myspring.pro29.ex02;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myspring.pro29.ex01.TestController;

@RestController
@RequestMapping(value = "/boards")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(TestController.class);

	// ��ü �� ��ȸ(GET ���)
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	ResponseEntity listArticles() {
		logger.info("��� ��  �� �ٿ��� �޼ҵ� ");
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		for (int i = 0; i < 10; i++) {
			ArticleVO vo = new ArticleVO();
			vo.setArticleNO(i);
			vo.setWriter("�̼���" + i);
			vo.setTitle("�ȳ��ϼ���" + i);
			vo.setContent("�� ��ǰ�� �Ұ��մϴ�." + i);
			list.add(vo);
		}
		return new ResponseEntity(list, HttpStatus.OK);
	}

	// �� �ϳ� ��ȸ(GET ���)
	@RequestMapping(value = "/{articleNumber}", method = RequestMethod.GET)
	ResponseEntity choiceArticleOne(@PathVariable("articleNumber") int articleNumber) {
		logger.info(String.valueOf(articleNumber));
		ArticleVO articleVO = new ArticleVO();
		articleVO.setArticleNO(articleNumber);
		articleVO.setWriter("ȫ");
		articleVO.setTitle("ȫ�浿��");
		articleVO.setContent("�ƹ����� �ƹ�����ƾƾƾ�");
		return new ResponseEntity(articleVO, HttpStatus.OK);
	}

	// �� �߰�
	@RequestMapping(value = "", method = RequestMethod.POST)
	public void addArticleOne(@RequestBody ArticleVO articleVO) {
		logger.info(articleVO.toString());
//		return new ResponseEntity(null,HttpStatus.OK );
	}

	// �� ����
	@RequestMapping(value = "/{articleNO}", method = RequestMethod.PUT)
	public ResponseEntity updateArticleOne(@PathVariable("articleNO") int articleNO, @RequestBody ArticleVO articleVO) {

		ResponseEntity<String> resEntity = null;
		try {
			logger.info("modArticle �޼��� ȣ��");
			logger.info(articleVO.toString());
			resEntity = new ResponseEntity("MOD_SUCCEEDED", HttpStatus.OK);
		} catch (Exception e) {
			resEntity = new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return resEntity;
	}

	// �� ����
	@RequestMapping(value = "/{articleNO}", method = RequestMethod.DELETE)
	public ResponseEntity deleteArticleOne(@PathVariable("articleNO") int articleNO, @RequestBody ArticleVO articleVO) {

		ResponseEntity<String> resEntity = null;
		try {
			logger.info("���� �޼��� ȣ��");
			logger.info(articleVO.toString());
			resEntity = new ResponseEntity("DELETEDED", HttpStatus.OK);
		} catch (Exception e) {
			resEntity = new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return resEntity;

	}

}
