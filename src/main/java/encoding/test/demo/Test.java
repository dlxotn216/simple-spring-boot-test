package encoding.test.demo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Lee Tae Su 
 * @project demo
 * @version 1.0
 * @since 2018-04-17
 */
@Data
public class Test {
	
	private String testName;
	private String testDescription;
	
	@JsonIgnore
	private MultipartFile file;
}
