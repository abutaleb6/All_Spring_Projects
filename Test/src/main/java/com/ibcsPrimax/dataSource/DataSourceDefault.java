package com.ibcsPrimax.dataSource;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class DataSourceDefault {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
}
