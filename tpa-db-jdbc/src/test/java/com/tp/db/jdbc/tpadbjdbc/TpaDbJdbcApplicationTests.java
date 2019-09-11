package com.tp.db.jdbc.tpadbjdbc;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.tp.db.jdbc.dto.Usuario;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql({"data1.sql", "data2.sql"})
public class TpaDbJdbcApplicationTests {

	
	@Autowired
    JdbcTemplate jdbcTemplate;
	
	@Test
	public void contextLoads() {
	}
	
	@Test
    public void testCantidad() {
		
		System.out.println( jdbcTemplate
                .queryForObject("select count(*) from usuario", Integer.class));
		
    }
	
	
	@Test
	    public void findAll() {
		List<Usuario> listUsuario=   jdbcTemplate.query(
	                "select * from usuario",
	                (rs, rowNum) ->
	                        new Usuario(
	                                rs.getString("nombre"),
	                                rs.getString("apellido"),
	                                rs.getString("cdusuario"),
	                                rs.getString("email")
	                        )
	        );
		
		for (Usuario usuario : listUsuario) {
			System.out.println(usuario.toString());
		}
	    }


}
