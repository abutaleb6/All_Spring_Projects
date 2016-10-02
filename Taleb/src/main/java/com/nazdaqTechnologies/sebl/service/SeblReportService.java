package com.nazdaqTechnologies.sebl.service;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.Inet4Address;
import java.sql.Date;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.rowset.ResultSetWrappingSqlRowSet;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nazdaqTechnologies.core.message.Message;
import com.nazdaqTechnologies.core.message.MessageBuilder;
import com.nazdaqTechnologies.core.message.MessageHeader;
import com.nazdaqTechnologies.core.message.processor.json.gson.GsonJsonMessageProcessor;
import com.nazdaqTechnologies.core.service.AbstractService;
import com.nazdaqTechnologies.jdbc.JdbcResult;
import com.nazdaqTechnologies.jdbc.JdbcService;
import com.nazdaqTechnologies.jdbc.util.JdbcUtils;
import com.nazdaqTechnologies.sebl.constants.ActionType;
import com.nazdaqTechnologies.sebl.model.Report;
import com.nazdaqTechnologies.sebl.model.SEBLTask;
import com.nazdaqTechnologies.sebl.model.SEBLTaskNote;

public class SeblReportService extends AbstractService<List<Report>> {

	private static final Logger log = LoggerFactory.getLogger(SeblReportService.class);

	private static String SQL_SELECT_REPORT;
	private Resource seblReport;
	private Map<String, String> sqlQueryMap;
	private NamedParameterJdbcTemplate jdbcTemplateSA;
	private GsonJsonMessageProcessor messageProcessor;
	private String url;

	// START getters / setters

	public Map<String, String> getSqlQueryMap() {
		return sqlQueryMap;
	}

	public void setSqlQueryMap(Map<String, String> sqlQueryMap) {
		this.sqlQueryMap = sqlQueryMap;
	}

	public NamedParameterJdbcTemplate getJdbcTemplateSA() {
		return jdbcTemplateSA;
	}

	public void setJdbcTemplateSA(NamedParameterJdbcTemplate jdbcTemplateSA) {
		this.jdbcTemplateSA = jdbcTemplateSA;
	}
	
	public Resource getSeblReport() {
		return seblReport;
	}
	
	public void setSeblReport(Resource seblReport) {
		this.seblReport = seblReport;
	}

	public GsonJsonMessageProcessor getMessageProcessor() {
		return messageProcessor;
	}

	public void setMessageProcessor(GsonJsonMessageProcessor messageProcessor) {
		this.messageProcessor = messageProcessor;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void init() {
		log.info("init");
		SQL_SELECT_REPORT = sqlQueryMap.get("sql.report.select.task");
	}

	public JasperPrint getJasperPrintForSebl(String id) throws Exception {

		JasperDesign jasperDesign;
		JasperPrint jasperPrint = null;
		JasperReport jasperReport;
		Map<String, Object> spArgsMap = new LinkedHashMap<String, Object>();
		spArgsMap.put("id_task_key", Integer.parseInt(id));

		try {

			log.debug("start SEBL report");

			ResultSetWrappingSqlRowSet rs = (ResultSetWrappingSqlRowSet) jdbcTemplateSA.queryForRowSet(SQL_SELECT_REPORT, spArgsMap);
		
			ResultSet resultSet = rs.getResultSet();
			
				//resultSet=rs.getResultSet();
				//resultSetDataSource = new JRResultSetDataSource(resultSet);
			JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(resultSet);

			InputStream template = this.seblReport.getInputStream();
			jasperDesign = JRXmlLoader.load(template);
			jasperReport = JasperCompileManager.compileReport(jasperDesign);
			//jasperPrint = JasperFillManager.fillReport(jasperReport, propertyMap, resultSetDataSource); // remove by taleb
			jasperPrint = JasperFillManager.fillReport(jasperReport, null, resultSetDataSource);

			log.debug("Sebl details report");

		}
		catch (JRException ex) {

			log.error("Sebl report error : {}", ex.getLocalizedMessage());

			throw ex;
		}

		return jasperPrint;
	}

}
