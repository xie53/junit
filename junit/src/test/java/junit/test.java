package junit;

import org.junit.Assert;
import org.junit.Test;

public class test {

	@Test
	public void test() {
		// 单文件测试
        int num = new Integer(1);
        Assert.assertEquals(num, 1);
	}

}
