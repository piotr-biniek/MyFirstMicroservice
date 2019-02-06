package pl.biniek;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class MyFileUploadServiceApplication {
	private final static Logger LOGER = LoggerFactory.getLogger(SpringApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MyFileUploadServiceApplication.class, args);
	}
	
	
	// rózne metody konwersji i zapisu
	public File convertMpFToFile(MultipartFile mpFile) throws IOException	{    
		File file = new File(mpFile.getOriginalFilename());
		file.createNewFile(); 
		FileOutputStream fos = new FileOutputStream(file); 
		fos.write(mpFile.getBytes());
		fos.close(); 
		return file;
	}
	
	
	public File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException 	{
		String destinantionDirectory = System.getProperty("user.home")+"\\Downloads\\";
		File file = new File(destinantionDirectory+multipart.getOriginalFilename());
		multipart.transferTo(file);
		return file;
	}
	
	

	@RequestMapping(method=RequestMethod.PUT, value="/")
	@ResponseStatus(value = HttpStatus.OK)
	public void uploadFile(@RequestParam("file") MultipartFile mpFile) throws IllegalStateException, IOException {
	LOGER.info(" size: " +mpFile.getSize()+" " +System.getProperty("user.home"));
	File file = multipartToFile(mpFile);
	LOGER.info("saved to "+file.getPath());
	
	}
	

	

}

