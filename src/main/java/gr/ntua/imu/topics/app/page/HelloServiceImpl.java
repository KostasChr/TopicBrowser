package gr.ntua.imu.topics.app.page;

import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {

	public String getHelloWorldMsg() {
		return "Spring : hello world";
	}

}