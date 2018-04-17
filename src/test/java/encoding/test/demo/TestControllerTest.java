package encoding.test.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * Created by Lee Tae Su on 2018-04-17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class TestControllerTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	
	@Test
	public void encodingTest() {
		encoding.test.demo.Test test = new encoding.test.demo.Test();
		test.setTestName("가나다 한글");
		test.setTestDescription("설명");
		
		ResponseEntity<encoding.test.demo.Test> responseEntity = restTemplate.postForEntity("/test", test, encoding.test.demo.Test.class);
		log.info("test :" + responseEntity.getBody());
	}
	
	@Test
	public void fileTest() throws Exception {
		MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();
		multiValueMap.add("testName", "가나다");
		multiValueMap.add("testDescription", "ㅅㄷㄴㅅ");
		
		MockMultipartFile mockMultipartFile = new MockMultipartFile("user-file", "testFile.txt",
				"text/plain", "test data".getBytes());
		
		ByteArrayResource resource = new ByteArrayResource(mockMultipartFile.getBytes()) {
			@Override
			public String getFilename() throws IllegalStateException {
				return "testFile.txt";
			}
		};
		multiValueMap.add("file", resource);
		
		encoding.test.demo.Test result = restTemplate.postForObject("/test/file", multiValueMap, encoding.test.demo.Test.class);
		log.info("result :" + result);
	}
}