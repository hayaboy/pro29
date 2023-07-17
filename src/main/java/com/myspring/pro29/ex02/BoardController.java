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

	// 전체 글 조회(GET 방식)
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	ResponseEntity listArticles() {
		logger.info("모든 글  다 다오는 메소드 ");
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		for (int i = 0; i < 10; i++) {
			ArticleVO vo = new ArticleVO();
			vo.setArticleNO(i);
			vo.setWriter("이순신" + i);
			vo.setTitle("안녕하세요" + i);
			vo.setContent("새 상품을 소개합니다." + i);
			list.add(vo);
		}
		return new ResponseEntity(list, HttpStatus.OK);
	}

	// 글 하나 조회(GET 방식)
	@RequestMapping(value = "/{articleNumber}", method = RequestMethod.GET)
	ResponseEntity choiceArticleOne(@PathVariable("articleNumber") int articleNumber) {
		logger.info(String.valueOf(articleNumber));
		ArticleVO articleVO = new ArticleVO();
		articleVO.setArticleNO(articleNumber);
		articleVO.setWriter("홍");
		articleVO.setTitle("홍길동전");
		articleVO.setContent("아버지를 아버지라아아아앙");
		return new ResponseEntity(articleVO, HttpStatus.OK);
	}

	// 글 추가
	@RequestMapping(value = "", method = RequestMethod.POST)
	public void addArticleOne(@RequestBody ArticleVO articleVO) {
		logger.info(articleVO.toString());
//		return new ResponseEntity(null,HttpStatus.OK );
	}

	// 글 수정
	@RequestMapping(value = "/{articleNO}", method = RequestMethod.PUT)
	public ResponseEntity updateArticleOne(@PathVariable("articleNO") int articleNO, @RequestBody ArticleVO articleVO) {

		ResponseEntity<String> resEntity = null;
		try {
			logger.info("modArticle 메서드 호출");
			logger.info(articleVO.toString());
			resEntity = new ResponseEntity("MOD_SUCCEEDED", HttpStatus.OK);
		} catch (Exception e) {
			resEntity = new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return resEntity;
	}

	// 글 삭제
	@RequestMapping(value = "/{articleNO}", method = RequestMethod.DELETE)
	public ResponseEntity deleteArticleOne(@PathVariable("articleNO") int articleNO, @RequestBody ArticleVO articleVO) {

		ResponseEntity<String> resEntity = null;
		try {
			logger.info("삭제 메서드 호출");
			logger.info(articleVO.toString());
			resEntity = new ResponseEntity("DELETEDED", HttpStatus.OK);
		} catch (Exception e) {
			resEntity = new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return resEntity;

	}

}
