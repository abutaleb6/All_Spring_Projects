package com.nazdaqTechnologies.sebl.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SEBLTask {
	private static Logger log = LoggerFactory.getLogger(SEBLTask.class);

	//private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	//private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

	private static Map<String, String> sql2BeanMap = null;
	private static Map<String, String> rs2BeanMap = null;

	//private static Map<String, Integer> instrumentMap = new HashMap<String, Integer>(4);

	private String fromDate;
	private String toDate;

	private Integer taskId;
	private Date createdDate;
	private String createdDateX;
	private String status;
	private String statusBD;
	private String referenceId;

	private String remitterName;
	private String remitterPhone;
	private String remitterAcctId;

	private Double amountLocal;
	private Double amountBDT;
	private Double totalDue;

	private Boolean paid;

	private String benificiaryName;
	private String benificiaryPhone;
	private String benificiaryAcctId;
	private Integer benificiaryBankId;
	private String benificiaryBankName;
	private String benificiaryBranch;
	private String benificiaryBranchDistrict;

	private Integer paymentTypeId;
	private String paymentType;
	private String pinNo;

	private String identityType;
	private String identityValue;

	private Integer exchangeHouseId;
	private String exchangeHouseName;

	private Integer countryFromId;
	private Integer ccyIdLocal;
	private Integer ccyIdBDT = 6;

	private Integer instrumentId;

	private String userId;
	private String password;

	private Integer loginUserId;
	
	private String note;

	private Boolean verify;

	// fields required for auto-CC processing for SEBL-CC Tasks

	private Long taskIdDAC;
	private Integer currentStatusId = 10;
	private Long taskListId;
	private Long txnId;
	//	private Integer bankMapKey;
	private Integer branchMapkey;
	private Integer districtMapKey;
	private Integer processId;
	private Double commision;
	private java.sql.Date approveDateSql;

	//private List<SEBLTask>referenceId;

	/* ***** BEGIN setters & getters ***** */

	//	public static Map<String, Integer> getInstrumentMap() {
	//		return instrumentMap;
	//	}
	//
	//	public static void setInstrumentMap(Map<String, Integer> instrumentMap) {
	//		SEBLTask.instrumentMap = instrumentMap;
	//	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
		//this.createdDateX = createdDate.format(formatter);
		this.createdDateX = dateFormat.format(createdDate);
	}

	public Timestamp getSqlCreatedDate() throws Exception {

		//return Timestamp.valueOf(createdDate.atStartOfDay());

		if (createdDate == null) {

			createdDate = dateFormat.parse(createdDateX);
		}

		return new Timestamp(createdDate.getTime());
	}

	public void setSqlCreatedDate(Timestamp sqlCreatedDate) {
		//this.createdDate = sqlCreatedDate.toLocalDateTime().toLocalDate();
		//this.createdDateX = sqlCreatedDate.toLocalDateTime().toLocalDate().format(formatter);
		this.createdDateX = dateFormat.format(sqlCreatedDate);
	}

	public String getCreatedDateX() {
		return createdDateX;
	}

	public void setCreatedDateX(String createdDateX) {
		this.createdDateX = createdDateX;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusBD() {
		return statusBD;
	}

	public void setStatusBD(String statusBD) {
		this.statusBD = statusBD;
	}

	public String getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}

	public String getRemitterName() {
		return remitterName;
	}

	public void setRemitterName(String remitterName) {
		this.remitterName = remitterName;
	}

	public String getRemitterPhone() {
		return remitterPhone;
	}

	public void setRemitterPhone(String remitterPhone) {
		this.remitterPhone = remitterPhone;
	}

	public String getRemitterAcctId() {
		return remitterAcctId;
	}

	public void setRemitterAcctId(String remitterAcctId) {
		this.remitterAcctId = remitterAcctId;
	}

	public Double getAmountLocal() {
		return amountLocal;
	}

	public void setAmountLocal(Double amountLocal) {
		this.amountLocal = amountLocal;
	}

	public Double getAmountBDT() {
		return amountBDT;
	}

	public void setAmountBDT(Double amountBDT) {
		this.amountBDT = amountBDT;
	}

	public Double getTotalDue() {
		return totalDue;
	}

	public void setTotalDue(Double totalDue) {
		this.totalDue = totalDue;
	}

	public Boolean getPaid() {
		return paid;
	}

	public void setPaid(Boolean paid) {
		this.paid = paid;
	}

	public String getBenificiaryName() {
		return benificiaryName;
	}

	public void setBenificiaryName(String benificiaryName) {
		this.benificiaryName = benificiaryName;
	}

	public String getBenificiaryPhone() {
		return benificiaryPhone;
	}

	public void setBenificiaryPhone(String benificiaryPhone) {
		this.benificiaryPhone = benificiaryPhone;
	}

	public String getBenificiaryAcctId() {
		return benificiaryAcctId;
	}

	public void setBenificiaryAcctId(String benificiaryAcctId) {
		this.benificiaryAcctId = benificiaryAcctId;
	}

	public Integer getBenificiaryBankId() {
		return benificiaryBankId;
	}

	public void setBenificiaryBankId(Integer benificiaryBankId) {
		this.benificiaryBankId = benificiaryBankId;
	}

	public String getBenificiaryBankName() {
		return benificiaryBankName;
	}

	public void setBenificiaryBankName(String benificiaryBankName) {
		this.benificiaryBankName = benificiaryBankName;
	}

	public String getBenificiaryBranch() {
		return benificiaryBranch;
	}

	public void setBenificiaryBranch(String benificiaryBranch) {
		this.benificiaryBranch = benificiaryBranch;
	}

	public String getBenificiaryBranchDistrict() {
		return benificiaryBranchDistrict;
	}

	public void setBenificiaryBranchDistrict(String benificiaryBranchDistrict) {
		this.benificiaryBranchDistrict = benificiaryBranchDistrict;
	}

	public Integer getPaymentTypeId() {
		return paymentTypeId;
	}

	public void setPaymentTypeId(Integer paymentTypeId) {
		this.paymentTypeId = paymentTypeId;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
		//this.instrumentId = instrumentMap.get(paymentType);
	}

	public String getPinNo() {
		return pinNo;
	}

	public void setPinNo(String pinNo) {
		this.pinNo = pinNo;
	}

	public String getIdentityType() {
		return identityType;
	}

	public void setIdentityType(String identityType) {
		this.identityType = identityType;
	}

	public String getIdentityValue() {
		return identityValue;
	}

	public void setIdentityValue(String identityValue) {
		this.identityValue = identityValue;
	}

	public Integer getExchangeHouseId() {
		return exchangeHouseId;
	}

	public void setExchangeHouseId(Integer exchangeHouseId) {
		this.exchangeHouseId = exchangeHouseId;
	}

	public String getExchangeHouseName() {
		return exchangeHouseName;
	}

	public void setExchangeHouseName(String exchangeHouseName) {
		this.exchangeHouseName = exchangeHouseName;
	}

	public Integer getCountryFromId() {
		return countryFromId;
	}

	public void setCountryFromId(Integer countryFromId) {
		this.countryFromId = countryFromId;
	}

	public Integer getCcyIdLocal() {
		return ccyIdLocal;
	}

	public void setCcyIdLocal(Integer ccyIdLocal) {
		this.ccyIdLocal = ccyIdLocal;
	}

	public Integer getCcyIdBDT() {
		return ccyIdBDT;
	}

	public void setCcyIdBDT(Integer ccyIdBDT) {
		this.ccyIdBDT = ccyIdBDT;
	}

	public Integer getInstrumentId() {
		return instrumentId;
	}

	public void setInstrumentId(Integer instrumentId) {
		this.instrumentId = instrumentId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getLoginUserId() {
		return loginUserId;
	}

	public void setLoginUserId(Integer loginUserId) {
		this.loginUserId = loginUserId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Boolean isVerify() {
		return verify;
	}

	public void setVerify(Boolean verify) {
		this.verify = verify;
	}

	public Long getTaskIdDAC() {
		return taskIdDAC;
	}

	public void setTaskIdDAC(Long taskIdDAC) {
		this.taskIdDAC = taskIdDAC;
	}

	public Integer getCurrentStatusId() {
		return currentStatusId;
	}

	public void setCurrentStatusId(Integer currentStatusId) {
		this.currentStatusId = currentStatusId;
	}

	public Long getTaskListId() {
		return taskListId;
	}

	public void setTaskListId(Long taskListId) {
		this.taskListId = taskListId;
	}

	public Long getTxnId() {
		return txnId;
	}

	public void setTxnId(Long txnId) {
		this.txnId = txnId;
	}

	//	public Integer getBankMapKey() {
	//		return bankMapKey;
	//	}
	//
	//	public void setBankMapKey(Integer bankMapKey) {
	//		this.bankMapKey = bankMapKey;
	//	}

	public Integer getBranchMapkey() {
		return branchMapkey;
	}

	public void setBranchMapkey(Integer branchMapkey) {
		this.branchMapkey = branchMapkey;
	}

	public Integer getDistrictMapKey() {
		return districtMapKey;
	}

	public void setDistrictMapKey(Integer districtMapKey) {
		this.districtMapKey = districtMapKey;
	}

	public Integer getProcessId() {
		return processId;
	}

	public void setProcessId(Integer processId) {
		this.processId = processId;
	}

	public Double getCommision() {
		return commision;
	}

	public void setCommision(Double commision) {
		this.commision = commision;
	}

	public java.sql.Date getApproveDateSql() {
		return approveDateSql;
	}

	public void setApproveDateSql(java.sql.Date approveDateSql) {
		this.approveDateSql = approveDateSql;
	}

	public static final Map<String, String> getSql2BeanMap() {

		if (sql2BeanMap == null) {
			sql2BeanMap = new LinkedHashMap<String, String>();

			sql2BeanMap.put("tx_benificiary_acct_id", "benificiaryAcctId");
			sql2BeanMap.put("dec_amt_bdt", "amountBDT");
			sql2BeanMap.put("dec_amt_lcy", "amountLocal");
			sql2BeanMap.put("tx_beneficiary_name", "benificiaryName");
			sql2BeanMap.put("tx_benificiary_phone", "benificiaryPhone");
			sql2BeanMap.put("id_country_from_key", "countryFromId");
			sql2BeanMap.put("id_ccy_key_bdt", "ccyIdBDT");
			sql2BeanMap.put("id_exchange_house_key", "exchangeHouseId");
			sql2BeanMap.put("tx_benificiary_identity_type", "identityType");
			sql2BeanMap.put("tx_benificiary_identity_value", "identityValue");
			sql2BeanMap.put("id_instrument_key", "instrumentId");
			sql2BeanMap.put("id_ccy_key_lcy", "ccyIdLocal");
			sql2BeanMap.put("id_benificiary_bank_key", "benificiaryBankId");
			sql2BeanMap.put("tx_benificiary_branch_name", "benificiaryBranch");
			sql2BeanMap.put("tx_benificiary_branch_district", "benificiaryBranchDistrict");
			sql2BeanMap.put("tx_benificiary_bank_name", "benificiaryBankName");
			sql2BeanMap.put("id_payment_key", "paymentTypeId");
			sql2BeanMap.put("tx_pin_no", "pinNo");
			sql2BeanMap.put("tx_remitter_phone", "remitterPhone");
			sql2BeanMap.put("tx_remitter_name", "remitterName");
			sql2BeanMap.put("tx_ref_id", "referenceId");
			sql2BeanMap.put("dt_created", "sqlCreatedDate");

			sql2BeanMap.put("id_task_key_dac", "taskIdDAC");
			sql2BeanMap.put("id_current_status_key", "currentStatusId");
			sql2BeanMap.put("id_task_list_key", "taskListId");
			sql2BeanMap.put("id_txn_key", "txnId");
			sql2BeanMap.put("id_process_key", "processId");
			sql2BeanMap.put("id_branch_map_key", "branchMapkey");
			sql2BeanMap.put("id_district_map_key", "districtMapKey");
			sql2BeanMap.put("dbl_commission", "commision");
			sql2BeanMap.put("dt_approve", "approveDateSql");

		}

		return sql2BeanMap;
	}

	public static void setSql2BeanMap(Map<String, String> sql2BeanMap) {
		SEBLTask.sql2BeanMap = sql2BeanMap;
	}

	public static final Map<String, String> getRs2BeanMap() {

		if (rs2BeanMap == null) {
			rs2BeanMap = new LinkedHashMap<String, String>();

			rs2BeanMap.put("id_task_key", "taskId");
			rs2BeanMap.put("dt_created", "sqlCreatedDate");
			rs2BeanMap.put("tx_task_status", "status");
			rs2BeanMap.put("tx_ref_id", "referenceId");
			rs2BeanMap.put("tx_remitter_name", "remitterName");
			rs2BeanMap.put("id_remitter_acct_id", "remitterAcctId");
			rs2BeanMap.put("tx_remitter_phone", "remitterPhone");
			rs2BeanMap.put("dec_amt_lcy", "amountLocal");
			rs2BeanMap.put("dec_amt_bdt", "amountBDT");
			rs2BeanMap.put("dec_total_due", "totalDue");
			rs2BeanMap.put("is_paid", "paid");
			rs2BeanMap.put("tx_beneficiary_name", "benificiaryName");
			rs2BeanMap.put("tx_benificiary_phone", "benificiaryPhone");
			rs2BeanMap.put("tx_benificiary_acct_id", "benificiaryAcctId");
			rs2BeanMap.put("tx_benificiary_bank_name_orig", "");
			rs2BeanMap.put("id_benificiary_bank_key", "benificiaryBankId");
			rs2BeanMap.put("tx_benificiary_bank_name", "benificiaryBankName");
			rs2BeanMap.put("tx_benificiary_branch_name", "benificiaryBranch");
			rs2BeanMap.put("tx_benificiary_branch_district", "benificiaryBranchDistrict");
			rs2BeanMap.put("id_payment_key", "paymentTypeId");
			rs2BeanMap.put("tx_payment_type", "paymentType");
			rs2BeanMap.put("tx_pin_no", "pinNo");
			rs2BeanMap.put("tx_benificiary_identity_type", "identityType");
			rs2BeanMap.put("tx_benificiary_identity_value", "identityValue");
			rs2BeanMap.put("id_exchange_house_key", "exchangeHouseId");
			rs2BeanMap.put("tx_exchange_house_name", "exchangeHouseName");
			rs2BeanMap.put("id_country_from_key", "countryFromId");
			//	rs2BeanMap.put("id_ccy_key_lcy", "ccyIdLocal");
			//	rs2BeanMap.put("id_ccy_key_bdt", "ccyIdBDT");
			rs2BeanMap.put("id_instrument_key", "instrumentId");
			rs2BeanMap.put("is_verify", "verify");

		}

		return rs2BeanMap;
	}

	public static void setRs2BeanMap(Map<String, String> rs2BeanMap) {
		SEBLTask.rs2BeanMap = rs2BeanMap;
	}

	/* ***** END setters & getters ***** */

}
