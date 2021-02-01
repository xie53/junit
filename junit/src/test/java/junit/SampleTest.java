package junit;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) // 让 junit 与 spring 环境进行整合
@SpringBootTest(classes = {App.class})  // 其中App.class是主程序入口类,即springboot的启动类
public class SampleTest {

	@Test
	public void test() {
		// MVC形式调用 使用springboot
		int num = new Integer(1);
        Assert.assertEquals(num, 1);
	}

}
