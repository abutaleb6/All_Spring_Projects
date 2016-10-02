package com.nazdaqTechnologies.sebl.service;

import java.lang.reflect.Type;
import java.net.Inet4Address;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.rowset.ResultSetWrappingSqlRowSet;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nazdaqTechnologies.core.message.Message;
import com.nazdaqTechnologies.core.message.MessageBuilder;
import com.nazdaqTechnologies.core.message.MessageHeader;
import com.nazdaqTechnologies.core.message.processor.json.gson.GsonJsonMessageProcessor;
import com.nazdaqTechnologies.core.service.AbstractService;
import com.nazdaqTechnologies.jdbc.JdbcService;
import com.nazdaqTechnologies.jdbc.util.JdbcUtils;
import com.nazdaqTechnologies.sebl.constants.ActionType;
import com.nazdaqTechnologies.sebl.model.SEBLTask;
import com.nazdaqTechnologies.sebl.model.SEBLTaskNote;

public class SeblService extends AbstractService<List<SEBLTask>> {

	private static final Logger log = LoggerFactory.getLogger(SeblService.class);

	//private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");

	//	private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

	private static final String PAYMENT_TYPE_CC = "Cash Collection";
	private static final String BRANCH_ANY = "ANY BRANCH";
	private static final String BRANCH_DISTRICT_ANY = "ANY DISTRICT";

	private static final String COL_NAME_TASK_STATUS = "tx_task_status";
	private static final String COL_NAME_TASK_LIST_ID = "id_task_list_key";

	private static final Integer COL_VALUE_TASK_CURRENT_STATUS_NEW = 10;
	private static final Integer COL_VALUE_TASK_CURRENT_SEND_TO_BRANCH = 50;

	// Http config
	public static final String HTTP_CONTENT_TYPE = "Content-type";
	public static final String HTTP_JSON_CONTENT_VALUE = "application/json";
	//public static final String URL = "http://localhost:8080/SEBL-server/jsonRequest";
	public static final String CONTENT_TYPE_SEBL = "SEBLTask";
	public static final String ACTION_TYPE_SEBL_SA = "SEND_SA_TO_DAC";
	public static final String DEST_SERVER = "SEBL-server";
	public static final String COMMA = ",";

	private static String SQL_LOGIN;
	private static String SQL_SELECT_TASK;
	private static String SQL_UPDATE_TASK;
	private static String SQL_SELECT_NOTE;
	private static String SQL_INSERT_NOTE;

	private static String SQL_NEXT_TASK_ID_BD;
	private static String SQL_SELECT_TASK_BD;
	private static String SQL_INSERT_TASK_BD;

	private static String SQL_NEXT_TXN_ID_BD;
	private static String SQL_SELECT_TASK_LIST_BD;
	private static String SQL_INSERT_TASK_LIST_BD;

	private static String SQL_INSERT_TASK_TRACE_BD;

	private Map<String, String> sqlQueryMap;

	private NamedParameterJdbcTemplate jdbcTemplateLocal;
	private NamedParameterJdbcTemplate jdbcTemplateBD;

	private Integer newTaskStatusId = 2000203;
	private Integer defaultUpdateTaskStatusId = 2000204;

	private static final String EXH_SFSL = "SFSL";
	private static final String EXH_SA = "SA";

	private Integer exchangeHouseKey = 100;
	private String exchangeHouseName = "SFSL";
	private String countryName = "UK";
	private Integer localCcyId = 8;   // 6 = BDT, 8 = GBP

	private Integer defaultUserId = 101;

	private Integer remoteUserId = -10;				// remote user id in BD when creating task list
	private Integer seblBranchMapKey = -10;
	private Integer seblDistrictMapKey = -10;

	private GsonJsonMessageProcessor messageProcessor;
	private String url;

	// START getters / setters

	public Map<String, String> getSqlQueryMap() {
		return sqlQueryMap;
	}

	public void setSqlQueryMap(Map<String, String> sqlQueryMap) {
		this.sqlQueryMap = sqlQueryMap;
	}

	public NamedParameterJdbcTemplate getJdbcTemplateLocal() {
		return jdbcTemplateLocal;
	}

	public void setJdbcTemplateLocal(NamedParameterJdbcTemplate jdbcTemplateLocal) {
		this.jdbcTemplateLocal = jdbcTemplateLocal;
	}

	public NamedParameterJdbcTemplate getJdbcTemplateBD() {
		return jdbcTemplateBD;
	}

	public void setJdbcTemplateBD(NamedParameterJdbcTemplate jdbcTemplateBD) {
		this.jdbcTemplateBD = jdbcTemplateBD;
	}

	public int getExchangeHouseKey() {
		return exchangeHouseKey;
	}

	public void setExchangeHouseKey(int exchangeHouseKey) {
		this.exchangeHouseKey = exchangeHouseKey;
	}

	public String getExchangeHouseName() {
		return exchangeHouseName;
	}

	public void setExchangeHouseName(String exchangeHouseName) {
		this.exchangeHouseName = exchangeHouseName;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public Integer getLocalCcyId() {
		return localCcyId;
	}

	public void setLocalCcyId(Integer localCcyId) {
		this.localCcyId = localCcyId;
	}

	public int getNewTaskStatusId() {
		return newTaskStatusId;
	}

	public void setNewTaskStatusId(int newTaskStatusId) {
		this.newTaskStatusId = newTaskStatusId;
	}

	public int getDefaultUpdateTaskStatusId() {
		return defaultUpdateTaskStatusId;
	}

	public void setDefaultUpdateTaskStatusId(int defaultUpdateTaskStatusId) {
		this.defaultUpdateTaskStatusId = defaultUpdateTaskStatusId;
	}

	public int getDefaultUserId() {
		return defaultUserId;
	}

	public void setDefaultUserId(int defaultUserId) {
		this.defaultUserId = defaultUserId;
	}

	public int getRemoteUserId() {
		return remoteUserId;
	}

	public void setRemoteUserId(int remoteUserId) {
		this.remoteUserId = remoteUserId;
	}

	// END getters / setters

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

		SQL_LOGIN = "SELECT id, login_id, password FROM app_user WHERE login_id = 'admin@sebl.com' AND login_id = :tx_login AND password = :tx_password_encrypted ";

		SQL_SELECT_TASK = sqlQueryMap.get("sql.select.task");
		SQL_UPDATE_TASK = sqlQueryMap.get("sql.update.task");

		SQL_SELECT_NOTE = sqlQueryMap.get("sql.select.note");
		SQL_INSERT_NOTE = sqlQueryMap.get("sql.insert.note");

		SQL_NEXT_TASK_ID_BD = sqlQueryMap.get("sql.next.task.id.bd");

		SQL_SELECT_TASK_BD = sqlQueryMap.get("sql.select.task.bd");
		SQL_INSERT_TASK_BD = sqlQueryMap.get("sql.insert.task.bd");

		SQL_NEXT_TXN_ID_BD = sqlQueryMap.get("sql.next.txn.id.bd");

		SQL_SELECT_TASK_LIST_BD = sqlQueryMap.get("sql.select.task_list.bd");
		SQL_INSERT_TASK_LIST_BD = sqlQueryMap.get("sql.insert.task_list.bd");

		SQL_INSERT_TASK_TRACE_BD = sqlQueryMap.get("sql.insert.task_trace.bd");

	}

	@Override
	@SuppressWarnings("rawtypes")
	public Message<?> serviceSingle(Message msg) throws Exception {

		MessageHeader msgHeader = null;
		Message<?> msgResponse = null;

		List<SEBLTaskNote> seblTaskNoteList = new ArrayList<SEBLTaskNote>();

		try {

			msgHeader = msg.getHeader();

			int userId = defaultUserId;

			if (msgHeader.get("userId") != null) {

				userId = Integer.parseInt((String) msgHeader.get("userId"));
			}

			String fromDateStr = (String) msgHeader.get("fromDate");
			String toDateStr = (String) msgHeader.get("toDate");

			java.util.Date fromDate = (fromDateStr == null) ? null : dateFormat.parse(fromDateStr);
			java.util.Date toDate = (toDateStr == null) ? null : dateFormat.parse(toDateStr);

			String actionType = msgHeader.getActionType();

			log.debug("Processing ACTION [{}]", actionType);

			if (actionType.equals(ActionType.LOGIN.toString())) {

				SEBLTask t = ((Message<List<SEBLTask>>) msg).getPayload().get(0);

				int status = handleLogin(t.getUserId(), t.getPassword());

				msgResponse = MessageBuilder.withPayload(status).copyHeadersIfAbsent(msgHeader).build();
			}
			else if (actionType.equals(ActionType.SELECT_TASK.toString())) {
				
				// Select all records from db
				List<SEBLTask> seblTaskList = handleSelectTask(fromDate, toDate, newTaskStatusId, true);

				msgResponse = MessageBuilder.withPayload(seblTaskList).copyHeadersIfAbsent(msgHeader).build();

			}
			else if (actionType.equals(ActionType.SELECT_NOTE.toString())) {

				SEBLTaskNote note = ((Message<List<SEBLTaskNote>>) msg).getPayload().get(0);
				
				// select notes
				seblTaskNoteList = handleSelectNote(note);

				msgResponse = MessageBuilder.withPayload(seblTaskNoteList).copyHeadersIfAbsent(msgHeader).build();
			}
			else if (actionType.equals(ActionType.UPDATE_NOTE.toString())) {

				SEBLTaskNote note = ((Message<List<SEBLTaskNote>>) msg).getPayload().get(0);
				
				// update note/insert new notes
				seblTaskNoteList = handleInsertTaskNote(note);

				msgResponse = MessageBuilder.withPayload(seblTaskNoteList).copyHeadersIfAbsent(msgHeader).build();
			}
			else if (actionType.equals(ActionType.SEND_TO_BANK.toString())) {

				if (exchangeHouseName.equalsIgnoreCase(EXH_SA)) {
					
					// send http request to UK-server
					String[] taskList = sendHttpRequest(msg, userId);
					
					// update SA status
					List<String> successList  = handleSaLocalStatusUpdate(taskList, userId);

					msgResponse = MessageBuilder.withPayload(successList).copyHeadersIfAbsent(msgHeader).build();
				}
				else if (exchangeHouseName.equalsIgnoreCase(EXH_SFSL)) {
					
					// send data to dac db
					List<SEBLTask> successList = handleSendToBank((List<SEBLTask>) msg.getPayload(), userId);
					List<SEBLTask> seblTaskList = handleSelectTask(fromDate, toDate, newTaskStatusId, true);

					msgResponse = MessageBuilder.withPayload(seblTaskList).copyHeadersIfAbsent(msgHeader).build();
				}

			}
			else if (actionType.equals(ActionType.SEND_SA_TO_DAC.toString())) {

				// send data to dac db from sa
				List<SEBLTask> successList = handleSendSAToDAC((List<SEBLTask>) msg.getPayload(), userId);

				StringBuilder sbId = new StringBuilder();

				for (SEBLTask task : successList) {
					sbId.append(task.getTaskId().toString()).append(COMMA);
				}

				msgResponse = MessageBuilder.withPayload(sbId.toString()).copyHeadersIfAbsent(msgHeader).build();
			}
			else if (actionType.equals(ActionType.ADD_NOTE_AND_SEND.toString())) {

				SEBLTask task = ((List<SEBLTask>) msg.getPayload()).get(0);

				// Create Note
				if (task.getNote() != null) {

					SEBLTaskNote note = new SEBLTaskNote();
					note.setTaskId(task.getTaskId());
					note.setUserId(userId);
					note.setCreatedDate(new java.util.Date());
					note.setNote(task.getNote());

					seblTaskNoteList = handleInsertTaskNote(note);

					note = null;
				}
				
				if (exchangeHouseName.equalsIgnoreCase(EXH_SA)) {
					
					// send http request to UK-server
					String[] taskList = sendHttpRequest(msg, userId);
					
					// update SA status
					List<String> successList  = handleSaLocalStatusUpdate(taskList, userId);

					msgResponse = MessageBuilder.withPayload(successList).copyHeadersIfAbsent(msgHeader).build();
				}
				else if (exchangeHouseName.equalsIgnoreCase(EXH_SFSL)) {
					
					// SEND_TO_BANK (send data to dac db)
					List<SEBLTask> successList = handleSendToBank((List<SEBLTask>) msg.getPayload(), userId);
					
					List<SEBLTask> seblTaskList = handleSelectTask(fromDate, toDate, newTaskStatusId, true);
					
					msgResponse = MessageBuilder.withPayload(seblTaskList).copyHeadersIfAbsent(msgHeader).build();
				}

			}
			else if (actionType.equals(ActionType.UPDATE_LOCAL_STATUS.toString())) {
				
				// update local status if already send to dac
				List<SEBLTask> successList = updateLocalStatus(fromDate, toDate, userId);

				msgResponse = MessageBuilder.withPayload(successList).copyHeadersIfAbsent(msgHeader).build();

			}
			else {
				throw new Exception("Unknow action " + actionType);
			}
		}
		catch (Exception ex) {
			log.error("Error {}", ex);
			throw ex;
		}

		return msgResponse;
	}
	
	private String[] sendHttpRequest(Message<List<SEBLTask>> msg, Integer userId) throws Exception {

		log.debug("executing handleHttpRequest");

		Message<?> message = null;

		String jsonString = null;

		log.debug("Sending to gateway {}", msg);

		CloseableHttpClient httpclient = HttpClients.createDefault();

		CloseableHttpResponse httpResponse = null;

		HttpPost httpPost = new HttpPost(url);

		// set the Content-type
		httpPost.setHeader(HTTP_CONTENT_TYPE, HTTP_JSON_CONTENT_VALUE);

		// create msg 
		message = MessageBuilder.withPayload((List<SEBLTask>) msg.getPayload()).setHeader(MessageHeader.CONTENT_TYPE, CONTENT_TYPE_SEBL)
		        .setHeader(MessageHeader.ACTION_TYPE, ACTION_TYPE_SEBL_SA)
		        .setHeader(MessageHeader.LOCATION_ID, Inet4Address.getLocalHost().getHostAddress()).setHeader(MessageHeader.SOURCE, EXH_SA)
		        .setHeader(MessageHeader.DESTINATION, DEST_SERVER).build();

		// parse Msg to json string

		jsonString = messageProcessor.toJson(message);
		
		log.debug("JsonString [{}]", jsonString);
		
		// add the JSON as a StringEntity
		httpPost.setEntity(new StringEntity(jsonString));

		httpResponse = httpclient.execute(httpPost);
		
		// HTTP Success
		HttpEntity entity = httpResponse.getEntity();

		String msgStr = EntityUtils.toString(entity);
		
		EntityUtils.consume(entity);

		log.debug("HttpResponse [{}]", msgStr);		
		
		String[] idArray = msgStr.split(",");
		
		log.debug("ResponseArray [{}]", idArray);

		return idArray;
	}

	private int handleLogin(String userName, String password) throws Exception {

		int userId = -1;

		try {

			String encryptedPassword = org.apache.commons.codec.digest.DigestUtils.sha256Hex(password);

			Map<String, Object> argsMap = new HashMap<String, Object>();
			argsMap.put("tx_login", userName);
			argsMap.put("tx_password_encrypted", encryptedPassword);

			Map<String, Object> loginResponseMap = jdbcTemplateLocal.queryForMap(SQL_LOGIN, argsMap);

			if (loginResponseMap.size() > 0 && loginResponseMap.get("login_id").equals(userName)) {
				userId = ((Long) loginResponseMap.get("id")).intValue();
			}
		}
		catch (Exception ex) {
			log.error("Error {} / {}", ex, ex);
			throw new Exception("Error Authentcating user");
		}

		return userId;
	}

	private List handleSelectNote(SEBLTaskNote taskNote) throws Exception {

		log.debug("executing handleSelectNote");

		List<SEBLTaskNote> seblTaskNote = handleSelectTaskNote(taskNote.getTaskId());

		return seblTaskNote;
	}

	private List<SEBLTask> handleSelectTask(java.util.Date fromDate, java.util.Date toDate, Integer taskStatus, Boolean fetchBDStatus)
	        throws Exception {

		Date sqlFromDate = (fromDate == null) ? new Date(new java.util.Date().getTime()) : new Date(fromDate.getTime());
		Date sqlToDate = (toDate == null) ? new Date(new java.util.Date().getTime()) : new Date(toDate.getTime());

		taskStatus = (taskStatus == null) ? newTaskStatusId : taskStatus;
		fetchBDStatus = (fetchBDStatus == null) ? false : fetchBDStatus;

		log.info("fromDate [{}], toDate : [{}], taskStatus : [{}]", new Object[] { fromDate, toDate, taskStatus });

		Map<String, Object> argsMap = new HashMap<String, Object>();
		argsMap.put("fromDate", sqlFromDate);
		argsMap.put("toDate", sqlToDate);
		argsMap.put("taskStatus", taskStatus);
		argsMap.put("id_exchange_house_key", exchangeHouseKey);
		argsMap.put("tx_exchange_house_id", exchangeHouseName);
		argsMap.put("tx_country_name", countryName);

		ResultSetWrappingSqlRowSet rs = (ResultSetWrappingSqlRowSet) jdbcTemplateLocal.queryForRowSet(SQL_SELECT_TASK, argsMap);

		List<SEBLTask> seblTaskList = JdbcUtils.mapRows(SEBLTask.class, SEBLTask.getRs2BeanMap(), rs.getResultSet());

		log.debug("rs size {}", seblTaskList.size());

		if (fetchBDStatus) {

			for (SEBLTask seblTask : seblTaskList) {
				String bdTaskStatus = selTaskStatusBD(seblTask.getReferenceId());
				seblTask.setStatusBD(bdTaskStatus);
			}
		}

		rs = null;
		return seblTaskList;
	}

	private int updateLocalTaskStatus(int taskId, int statusKey, int userId) {

		Map<String, Object> spArgsMap = new HashMap<String, Object>();

		spArgsMap.put("id_task_key", taskId);
		spArgsMap.put("id_new_status_key", statusKey);
		spArgsMap.put("id_user_key", userId);

		return jdbcTemplateLocal.update(SQL_UPDATE_TASK, spArgsMap);
	}

	private List<SEBLTaskNote> handleSelectTaskNote(Integer taskId) throws Exception {

		Map<String, Object> argsMap = new HashMap<String, Object>(1);
		argsMap.put("id_task_key", taskId);

		ResultSetWrappingSqlRowSet rs = (ResultSetWrappingSqlRowSet) jdbcTemplateLocal.queryForRowSet(SQL_SELECT_NOTE, argsMap);

		List<SEBLTaskNote> seblTaskNoteList = JdbcUtils.mapRows(SEBLTaskNote.class, SEBLTaskNote.getRs2BeanMap(), rs.getResultSet());

		log.debug("TaskNoteCount : [{}]", seblTaskNoteList.size());

		rs = null;
		return seblTaskNoteList;
	}

	private List<SEBLTaskNote> handleInsertTaskNote(SEBLTaskNote taskNote) throws Exception {

		Map<String, Object> spArgsMap = JdbcService.createSqlMap(taskNote, SEBLTaskNote.getSql2BeanMap());
		spArgsMap.put("id_exchange_house_key", exchangeHouseKey);

		jdbcTemplateLocal.update(SQL_INSERT_NOTE, spArgsMap);

		log.debug("Inserted note for TaskId : [{}]", taskNote.getTaskId());

		return handleSelectTaskNote(taskNote.getTaskId());

	}

	private String selTaskStatusBD(String taskRefId) {

		String dacTaskStatus = null;

		Map<String, Object> selParamMap = new HashMap<String, Object>(2);
		selParamMap.put("tx_ref_id", taskRefId);
		selParamMap.put("id_exchange_house_key", exchangeHouseKey);

		List<Map<String, Object>> rsList = jdbcTemplateBD.queryForList(SQL_SELECT_TASK_BD, selParamMap);

		if (rsList.size() > 0) {
			dacTaskStatus = rsList.get(0).get(COL_NAME_TASK_STATUS).toString();
		}

		log.trace("DAC Task Status for Task Ref : [{}] -> [{}]", taskRefId, dacTaskStatus);

		return dacTaskStatus;

	}

	private List<SEBLTask> handleSendToBank(List<SEBLTask> seblTaskList, int id_user_key) {

		int insCount = 0;
		List<SEBLTask> successTaskList = new ArrayList<SEBLTask>();

		for (SEBLTask task : seblTaskList) {

			try {

				log.debug("Working on : [{}] Status : [{}]", task.getReferenceId(), task.getStatus());

				// check entry does NOT exist in DAC
				String dacTaskStatus = selTaskStatusBD(task.getReferenceId());

				if (dacTaskStatus != null) {
					log.warn("Entry already exists in DAC : [{}] Status : [{}]", task.getReferenceId(), dacTaskStatus);

					int updateCount = updateLocalTaskStatus(task.getTaskId(), defaultUpdateTaskStatusId, id_user_key);

					log.debug("Update count : [{}]", updateCount);

					continue;
				}

				if (task.getPinNo() != null && task.getPinNo().length() > 1) {
					task.setBenificiaryAcctId(null);
				}

				task.setCcyIdLocal(localCcyId);

				// Insert into DAC

				Long taskIdDAC = jdbcTemplateBD.queryForObject(SQL_NEXT_TASK_ID_BD, new HashMap<String, Object>(0), Long.class);
				task.setTaskIdDAC(taskIdDAC);

				if (task.getPaymentType().equalsIgnoreCase(PAYMENT_TYPE_CC)) {

					// if SEBL CC then auto process
					log.debug("Preping [{}] for auto processing", PAYMENT_TYPE_CC);

					if (task.getBenificiaryBankId() == 16) {

						// taskListId
						Long taskListId = createTaskList();
						task.setTaskListId(taskListId);

						if (taskListId == null) {
							log.warn("Null tasklistId - skipping!");
							continue;
						}

						//TxnId
						Long txnId = jdbcTemplateBD.queryForObject(SQL_NEXT_TXN_ID_BD, new HashMap<String, Object>(0), Long.class);
						task.setTxnId(txnId);

						task.setAmountLocal(null);
						task.setCcyIdLocal(null);

						task.setApproveDateSql(new Date(new java.util.Date().getTime()));
						task.setProcessId(3);
						task.setCommision(0.0);
						task.setBranchMapkey(seblBranchMapKey);
						task.setDistrictMapKey(seblDistrictMapKey);
						task.setBenificiaryBranch(BRANCH_ANY);
						task.setBenificiaryBranchDistrict(BRANCH_DISTRICT_ANY);
						task.setCurrentStatusId(COL_VALUE_TASK_CURRENT_SEND_TO_BRANCH);

					}
					else {

						if (task.getBenificiaryBranchDistrict() == null) {
							log.warn("Defaulting District to ANY");
							task.setBenificiaryBranchDistrict(BRANCH_DISTRICT_ANY);
						}
					}
				}
				else {

					task.setCurrentStatusId(COL_VALUE_TASK_CURRENT_STATUS_NEW);
					task.setInstrumentId(null);
				}

				// insert to task_info in DAC
				Map<String, Object> spArgsMap = JdbcService.createSqlMap(task, SEBLTask.getSql2BeanMap());
				jdbcTemplateBD.update(SQL_INSERT_TASK_BD, spArgsMap);
				log.debug("Inserted into task_info refNo : [{}]", task.getReferenceId());

				// insert to task_info_trace in DAC
				spArgsMap.put("id_user_key", 1);
				jdbcTemplateBD.update(SQL_INSERT_TASK_TRACE_BD, spArgsMap);
				log.debug("Inserted into task_info_trace : [{}]", task.getReferenceId());

				// update local task status
				updateLocalTaskStatus(task.getTaskId(), defaultUpdateTaskStatusId, id_user_key);

				// lightweight Task Object with just id, for GUI to remove from its Store
				SEBLTask successTaskId = new SEBLTask();
				successTaskId.setTaskId(task.getTaskId());
				successTaskList.add(task);
				insCount++;
			}

			catch (Exception ex) {
				log.error("Exception {}", ex);
			}
		}

		log.debug("Total INS count : [{}]", insCount);

		return successTaskList;

	}

	private List<SEBLTask> handleSendSAToDAC(List<SEBLTask> seblTaskList, int id_user_key) throws Exception {

		List<SEBLTask> successTaskList = new ArrayList<SEBLTask>();

		int insCount = 0;

		for (SEBLTask task : seblTaskList) {

			try {

				log.debug("Working on : [{}] Status : [{}]", task.getReferenceId(), task.getStatus());

				// check entry does NOT exist in DAC
				String dacTaskStatus = selTaskStatusBD(task.getReferenceId());
				
				int updateCount = 0;
				
				if (dacTaskStatus != null) {
					
					log.warn("Entry already exists in DAC : [{}] Status : [{}]", task.getReferenceId(), dacTaskStatus);
					
					successTaskList.add(task);
					
					updateCount++;
					
					log.debug("Update count : [{}]", updateCount);

					continue;
				}
				else{
					
					if (task.getPinNo() != null && task.getPinNo().length() > 1) {
						task.setBenificiaryAcctId(null);
					}

					task.setCcyIdLocal(localCcyId);

					// Insert into DAC

					Long taskIdDAC = jdbcTemplateBD.queryForObject(SQL_NEXT_TASK_ID_BD, new HashMap<String, Object>(0), Long.class);
					task.setTaskIdDAC(taskIdDAC);

					if (task.getPaymentType().equalsIgnoreCase(PAYMENT_TYPE_CC)) {

						// if SEBL CC then auto process
						log.debug("Preping [{}] for auto processing", PAYMENT_TYPE_CC);

						if (task.getBenificiaryBankId() == 16) {

							// taskListId
							Long taskListId = createTaskList();
							task.setTaskListId(taskListId);

							if (taskListId == null) {
								log.warn("Null tasklistId - skipping!");
								continue;
							}

							//TxnId
							Long txnId = jdbcTemplateBD.queryForObject(SQL_NEXT_TXN_ID_BD, new HashMap<String, Object>(0), Long.class);
							task.setTxnId(txnId);

							task.setAmountLocal(null);
							task.setCcyIdLocal(null);

							task.setApproveDateSql(new Date(new java.util.Date().getTime()));
							task.setProcessId(3);
							task.setCommision(0.0);
							task.setBranchMapkey(seblBranchMapKey);
							task.setDistrictMapKey(seblDistrictMapKey);
							task.setBenificiaryBranch(BRANCH_ANY);
							task.setBenificiaryBranchDistrict(BRANCH_DISTRICT_ANY);
							task.setCurrentStatusId(COL_VALUE_TASK_CURRENT_SEND_TO_BRANCH);

						}
						else {

							if (task.getBenificiaryBranchDistrict() == null) {
								log.warn("Defaulting District to ANY");
								task.setBenificiaryBranchDistrict(BRANCH_DISTRICT_ANY);
							}
						}
					}
					else {

						task.setCurrentStatusId(COL_VALUE_TASK_CURRENT_STATUS_NEW);
						task.setInstrumentId(null);
					}

					// insert to task_info in DAC
					Map<String, Object> spArgsMap = JdbcService.createSqlMap(task, SEBLTask.getSql2BeanMap());
					jdbcTemplateBD.update(SQL_INSERT_TASK_BD, spArgsMap);
					log.debug("Inserted into task_info refNo : [{}]", task.getReferenceId());

					// insert to task_info_trace in DAC
					spArgsMap.put("id_user_key", 1);
					jdbcTemplateBD.update(SQL_INSERT_TASK_TRACE_BD, spArgsMap);
					log.debug("Inserted into task_info_trace : [{}]", task.getReferenceId());

					// lightweight Task Object with just id, for GUI to remove from its Store
					SEBLTask successTaskId = new SEBLTask();
					successTaskId.setTaskId(task.getTaskId());
					successTaskList.add(task);
					insCount++;
				}

				
			}

			catch (Exception ex) {
				log.error("Exception {}", ex);
			}
		}

		log.debug("Total INS count : [{}]", insCount);

		return successTaskList;
	}

	private List<String> handleSaLocalStatusUpdate(String[] taskList, int id_user_key) {

		int insCount = 0;

		List<String> successTaskList = new ArrayList<String>();

		for (String taskId : taskList) {

			try {

				log.debug("Working on : [{}] Status : [{}]", taskId);

				// update local task status
				updateLocalTaskStatus(Integer.parseInt(taskId), defaultUpdateTaskStatusId, id_user_key);

				// lightweight Task Object with just id, for GUI to remove from its Store
				SEBLTask successTaskId = new SEBLTask();
				
				successTaskId.setTaskId(Integer.parseInt(taskId));
				
				successTaskList.add(taskId);
				
				insCount++;
			}

			catch (Exception ex) {
				log.error("Exception {}", ex);
			}
		}

		log.debug("Total INS count : [{}]", insCount);

		return successTaskList;
	}

	public Long createTaskList() {

		// check if taskList exists for today, if not create one

		Long taskListId = null;
		String taskListNameTdy = exchangeHouseName + "-CC-" + dateFormat.format(new java.util.Date());

		Map<String, Object> sqlParamMap = new HashMap<String, Object>(2);
		sqlParamMap.put("id_exchange_house_key", exchangeHouseKey);
		sqlParamMap.put("tx_task_list_name", taskListNameTdy);

		List<Map<String, Object>> rsList = jdbcTemplateBD.queryForList(SQL_SELECT_TASK_LIST_BD, sqlParamMap);

		if (rsList.size() > 0) {

			taskListId = (Long) rsList.get(0).get(COL_NAME_TASK_LIST_ID);
		}
		else {
			// craete task list

			Map<String, Object> sqlInsParamMap = new HashMap<String, Object>(3);

			sqlInsParamMap.put("id_user_key", remoteUserId);
			sqlInsParamMap.put("id_exchange_house_key", exchangeHouseKey);
			sqlInsParamMap.put("tx_task_list_name", taskListNameTdy);

			int rowcount = jdbcTemplateBD.update(SQL_INSERT_TASK_LIST_BD, sqlInsParamMap);

			if (rowcount != 1) {

				log.warn("Unable to create the tasklist with name [{}]", taskListNameTdy);
			}
			else {
				rsList = jdbcTemplateBD.queryForList(SQL_SELECT_TASK_LIST_BD, sqlParamMap);

				if (rsList.size() > 0) {

					taskListId = (Long) rsList.get(0).get(COL_NAME_TASK_LIST_ID);
				}
			}
		}

		return taskListId;
	}

	private List<SEBLTask> updateLocalStatus(java.util.Date fromDate, java.util.Date toDate, int id_user_key) throws Exception {

		List<SEBLTask> seblTaskList = handleSelectTask(fromDate, toDate, null, true);

		log.info("seblTaskList size : [{}}", seblTaskList.size());
		
		for (SEBLTask task : seblTaskList) {

			if (task.getStatusBD() != null) {
				
				log.trace("Updating Task status of  [{}] ", task.getReferenceId());
				
				int updateCount = updateLocalTaskStatus(task.getTaskId(), defaultUpdateTaskStatusId, id_user_key);

			}
		}

		return seblTaskList;
	}

	public void testTask() {

		try {

			java.util.Date fromDate = new java.util.Date(2015 - 1900, 5, 9);
			java.util.Date toDate = null;

			Integer userId = testLogin();

			List<SEBLTask> seblTaskList = testSelTask(fromDate, null);

			// testNote(31668);

			//testSendToBank(seblTaskList);

			testAddNoteAndSend(seblTaskList, userId, fromDate, toDate);

		}
		catch (Exception ex) {
			log.error("Error {} {}", ex, ex);
		}

	}

	private Integer testLogin() throws Exception {

		/* Login */
		int userId = handleLogin("admin@sebl.com", "P@55w0rd");
		log.info("Login User id : [{}]", userId);

		return userId;

	}

	private List<SEBLTask> testSelTask(java.util.Date fromDate, java.util.Date toDate) throws Exception {

		List<SEBLTask> seblTaskList = handleSelectTask(fromDate, toDate, null, true);
		log.info("seblTaskList Count : [{}]", seblTaskList.size());
		return seblTaskList;
	}

	private void testNote(int taskId) throws Exception {

		// Note Select
		List<SEBLTaskNote> noteList = handleSelectTaskNote(taskId);
		log.debug("BEFORE INS : TasksNote count : [{}]", noteList.size());

		// Note Insert
		SEBLTaskNote note = new SEBLTaskNote();
		note.setTaskId(taskId);
		note.setUserId(101); // will come from GUI
		note.setNote("Test Note");
		noteList = handleInsertTaskNote(note);
		log.debug("AFTER INS : TasksNote count : [{}]", noteList.size());
	}

	private void testSendToBank(List<SEBLTask> seblTaskList, Date fromDate, Date toDate) throws Exception {
		List<SEBLTask> successList = handleSendToBank(seblTaskList, defaultUserId);
		seblTaskList = testSelTask(fromDate, toDate);
	}

	private void testAddNoteAndSend(List<SEBLTask> taskList, Integer userId, java.util.Date fromDate, java.util.Date toDate) throws Exception {

		List<SEBLTask> seblList = new ArrayList<SEBLTask>();

		List<SEBLTaskNote> seblTaskNoteList = new ArrayList<SEBLTaskNote>();

		SEBLTask task = taskList.get(0);

		task.setNote("Test NOTE");

		// Create Note
		if (task.getNote() != null) {

			SEBLTaskNote note = new SEBLTaskNote();
			note.setTaskId(task.getTaskId());
			note.setUserId(userId);
			note.setCreatedDate(new java.util.Date());
			note.setNote(task.getNote());

			seblTaskNoteList = handleInsertTaskNote(note);

			note = null;
		}

		// SEND_TO_BANK

		List<SEBLTask> successList = handleSendToBank(taskList, userId);
		List<SEBLTask> seblTaskList = handleSelectTask(fromDate, toDate, newTaskStatusId, true);

	}

	private void testTask2() {
		String taskListNamePostfix = exchangeHouseName + "-CC-" + dateFormat.format(new Date(System.currentTimeMillis()));
		log.info("taskListNamePostfix [{}]", taskListNamePostfix);
	}

	public static void main(String[] args) throws Exception {

		ApplicationContext context = new ClassPathXmlApplicationContext("spring-beans-test.xml");

		SeblService seblService = context.getBean("SeblService", SeblService.class);

		log.info(" name - {}", seblService.getServiceName());

		seblService.init();

		seblService.testTask();
	}
}
