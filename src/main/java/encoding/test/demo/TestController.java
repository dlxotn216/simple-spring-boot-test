package encoding.test.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lee Tae Su 
 * @project demo
 * @version 1.0
 * @since 2018-04-17
 */
@RestController
@Slf4j
public class TestController {
	
	@PostMapping("/test")
	public Test test(@RequestBody Test test){
		log.info("in controller :"+ test.getTestName());
		log.info("in controller :"+ test.getTestDescription());
		return test;
	}
	
	@PostMapping("/test/file")
	public Test fileTst(Test test){
		log.info("in File controller :"+ test.getTestName());
		log.info("in File controller :"+ test.getTestDescription());
		log.info("in File controller :"+ test.getFile().getOriginalFilename());
		return test;
	}
}
