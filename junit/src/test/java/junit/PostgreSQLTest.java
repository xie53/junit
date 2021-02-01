package junit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) // 让 junit 与 spring 环境进行整合
@SpringBootTest(classes = {App.class})  // 其中App.class是主程序入口类,即springboot的启动类
public class PostgreSQLTest {

    @Autowired
    DataSource dataSource;

	@Test
	public void test() throws SQLException {
		// MVC形式调用 使用springboot,连接postgreSQL数据库
		System.out.println("连接成功");
        System.out.println("dataSource.getClass()内容***"+dataSource.getClass());

        Connection connection = dataSource.getConnection();
        System.out.println("connection内容***"+connection);
        connection.close();
	}

	@Test
	public void testSelect() throws SQLException {
		// 通过Docker工具拉取postgres和pgadmin镜像，并启动
		// jdbc:postgresql://localhost:5432/postgres
		// 检索postgre中的数据
        Connection connection = dataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement("select * from author;");
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            String name = rs.getString("name");
            System.out.println("ResultSet内容: "+name);
        }
        connection.close();
	}
	
	/* 
	 docker：
	// 拉取postgres镜像
	docker pull postgres
	// 查看postgres镜像
	docker images postgres
	// 初始化postgres镜像(默认用户名postgres)
	docker run --name postgres -e POSTGRES_PASSWORD=password -p 5432:5432 -v pgdata:/var/lib/postgresql/data -d postgres
	// 查看
	docker ps
	// 进入镜像
	docker exec -it postgres /bin/bash
	cd /var/lib/postgresql/data	// data目录
	cd /usr/lib/postgresql/13/bin	// 工具目录
	// 连接数据库(命令行操作)
	root@a8ac85a29bc2:/usr/lib/postgresql/13/bin# psql -Upostgres
	// 查看数据库
	postgres=# \l
	// 创建数据库
	create database db2;
	
	// 拉取postgresql可视化工具pgadmin4镜像
	docker pull dpage/pgadmin4
	// 运行pgadmin4
	docker run -d -p 5433:80 --name pgadmin4 -e PGADMIN_DEFAULT_EMAIL=xieys@ibm.com -e PGADMIN_DEFAULT_PASSWORD=123456 dpage/pgadmin4
	// 查看启动的服务
	docker ps
	// 打开浏览器访问pgadmin4：http://ip:5433/
	http://localhost:5433/
	
	// 进入PostgreSQL数据库容器 a8ac为这个容器的id
	docker exec -it a8ac bash
	// 查看容器的ip，即pgadmin连接postgre服务的地址
	cat /etc/hosts
	 */
}
