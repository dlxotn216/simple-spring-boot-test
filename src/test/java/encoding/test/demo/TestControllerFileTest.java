package encoding.test.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @author Lee Tae Su 
 * @project demo
 * @version 1.0
 * @since 2018-04-17
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Slf4j
public class TestControllerFileTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void fileTest() throws Exception {
		ResultMatcher ok = MockMvcResultMatchers.status().isOk();
		
		String fileName = "test.txt";
		MockMultipartFile mockMultipartFile = new MockMultipartFile("user-file", fileName,
				"text/plain", "test data".getBytes());
		log.info("test :" + mockMultipartFile.getOriginalFilename());
		
		
		MockHttpServletRequestBuilder builder =
				MockMvcRequestBuilders.multipart("/test/file")
						.file("file", mockMultipartFile.getBytes())
						.param("testName", "파일테스트")
						.param("testDescription", "파일테스트 설명")
						.contentType("multipart/form-data;charset=ISO-8859-1");
		
		
		this.mockMvc.perform(builder).andExpect(ok)
				.andDo(MockMvcResultHandlers.print());
		
	}
}
