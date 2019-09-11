package com.tp.db.jdbc.mysql;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql({"data1.sql", "data2.sql"})
public class TpaDbJdbcMysqlApplicationTests {

	@Test
	public void contextLoads() {
	}

}
