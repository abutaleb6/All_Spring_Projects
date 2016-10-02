package com.nazdaqTechnologies.sebl.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Report {
	private static Logger log = LoggerFactory.getLogger(Report.class);

	private static Map<String, String> sql2BeanMap = null;
	private static Map<String, String> rs2BeanMap = null;


	 private Long id;
	 private Double localCurrency;
	 private Double conversionRate;
	 private Double foreignCurrency;
	 private String custFirstName;
	 
	 private String custSurname;
	 private String custResidentAddress;
	 private String custPostCode;
	 private String custPhotoNo;
	 private Date custPhotoIdExpireDate;
	 
	 private Date custDateOfBirth;
	 private String custPhoneNo;
	 private String custEmail;
	 private String custCitySuburb;
	 private String custProvice;
	 
	 private String custRemitencePurpose;
	 private String benFirstName;
	 private String benSurname;
	 private String benResidentAddress;
	 private String benDistrict;
	 
	 private String benEmail;
	 private String benPhoneNumer;
	 private String benAccountName;
	 private String benBankName;
	 private String benBranchName;
	 
	 private String benCountryName;
	 private String benCompanyName;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the localCurrency
	 */
	public Double getLocalCurrency() {
		return localCurrency;
	}

	/**
	 * @param localCurrency the localCurrency to set
	 */
	public void setLocalCurrency(Double localCurrency) {
		this.localCurrency = localCurrency;
	}

	/**
	 * @return the conversionRate
	 */
	public Double getConversionRate() {
		return conversionRate;
	}

	/**
	 * @param conversionRate the conversionRate to set
	 */
	public void setConversionRate(Double conversionRate) {
		this.conversionRate = conversionRate;
	}

	/**
	 * @return the foreignCurrency
	 */
	public Double getForeignCurrency() {
		return foreignCurrency;
	}

	/**
	 * @param foreignCurrency the foreignCurrency to set
	 */
	public void setForeignCurrency(Double foreignCurrency) {
		this.foreignCurrency = foreignCurrency;
	}

	/**
	 * @return the custFirstName
	 */
	public String getCustFirstName() {
		return custFirstName;
	}

	/**
	 * @param custFirstName the custFirstName to set
	 */
	public void setCustFirstName(String custFirstName) {
		this.custFirstName = custFirstName;
	}

	/**
	 * @return the custSurname
	 */
	public String getCustSurname() {
		return custSurname;
	}

	/**
	 * @param custSurname the custSurname to set
	 */
	public void setCustSurname(String custSurname) {
		this.custSurname = custSurname;
	}

	/**
	 * @return the custResidentAddress
	 */
	public String getCustResidentAddress() {
		return custResidentAddress;
	}

	/**
	 * @param custResidentAddress the custResidentAddress to set
	 */
	public void setCustResidentAddress(String custResidentAddress) {
		this.custResidentAddress = custResidentAddress;
	}

	/**
	 * @return the custPostCode
	 */
	public String getCustPostCode() {
		return custPostCode;
	}

	/**
	 * @param custPostCode the custPostCode to set
	 */
	public void setCustPostCode(String custPostCode) {
		this.custPostCode = custPostCode;
	}

	/**
	 * @return the custPhotoNo
	 */
	public String getCustPhotoNo() {
		return custPhotoNo;
	}

	/**
	 * @param custPhotoNo the custPhotoNo to set
	 */
	public void setCustPhotoNo(String custPhotoNo) {
		this.custPhotoNo = custPhotoNo;
	}

	/**
	 * @return the custPhotoIdExpireDate
	 */
	public Date getCustPhotoIdExpireDate() {
		return custPhotoIdExpireDate;
	}

	/**
	 * @param custPhotoIdExpireDate the custPhotoIdExpireDate to set
	 */
	public void setCustPhotoIdExpireDate(Date custPhotoIdExpireDate) {
		this.custPhotoIdExpireDate = custPhotoIdExpireDate;
	}

	/**
	 * @return the custDateOfBirth
	 */
	public Date getCustDateOfBirth() {
		return custDateOfBirth;
	}

	/**
	 * @param custDateOfBirth the custDateOfBirth to set
	 */
	public void setCustDateOfBirth(Date custDateOfBirth) {
		this.custDateOfBirth = custDateOfBirth;
	}

	/**
	 * @return the custPhoneNo
	 */
	public String getCustPhoneNo() {
		return custPhoneNo;
	}

	/**
	 * @param custPhoneNo the custPhoneNo to set
	 */
	public void setCustPhoneNo(String custPhoneNo) {
		this.custPhoneNo = custPhoneNo;
	}

	/**
	 * @return the custEmail
	 */
	public String getCustEmail() {
		return custEmail;
	}

	/**
	 * @param custEmail the custEmail to set
	 */
	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}

	/**
	 * @return the custCitySuburb
	 */
	public String getCustCitySuburb() {
		return custCitySuburb;
	}

	/**
	 * @param custCitySuburb the custCitySuburb to set
	 */
	public void setCustCitySuburb(String custCitySuburb) {
		this.custCitySuburb = custCitySuburb;
	}

	/**
	 * @return the custProvice
	 */
	public String getCustProvice() {
		return custProvice;
	}

	/**
	 * @param custProvice the custProvice to set
	 */
	public void setCustProvice(String custProvice) {
		this.custProvice = custProvice;
	}

	/**
	 * @return the custRemitencePurpose
	 */
	public String getCustRemitencePurpose() {
		return custRemitencePurpose;
	}

	/**
	 * @param custRemitencePurpose the custRemitencePurpose to set
	 */
	public void setCustRemitencePurpose(String custRemitencePurpose) {
		this.custRemitencePurpose = custRemitencePurpose;
	}

	/**
	 * @return the benFirstName
	 */
	public String getBenFirstName() {
		return benFirstName;
	}

	/**
	 * @param benFirstName the benFirstName to set
	 */
	public void setBenFirstName(String benFirstName) {
		this.benFirstName = benFirstName;
	}

	/**
	 * @return the benSurname
	 */
	public String getBenSurname() {
		return benSurname;
	}

	/**
	 * @param benSurname the benSurname to set
	 */
	public void setBenSurname(String benSurname) {
		this.benSurname = benSurname;
	}

	/**
	 * @return the benResidentAddress
	 */
	public String getBenResidentAddress() {
		return benResidentAddress;
	}

	/**
	 * @param benResidentAddress the benResidentAddress to set
	 */
	public void setBenResidentAddress(String benResidentAddress) {
		this.benResidentAddress = benResidentAddress;
	}

	/**
	 * @return the benDistrict
	 */
	public String getBenDistrict() {
		return benDistrict;
	}

	/**
	 * @param benDistrict the benDistrict to set
	 */
	public void setBenDistrict(String benDistrict) {
		this.benDistrict = benDistrict;
	}

	/**
	 * @return the benEmail
	 */
	public String getBenEmail() {
		return benEmail;
	}

	/**
	 * @param benEmail the benEmail to set
	 */
	public void setBenEmail(String benEmail) {
		this.benEmail = benEmail;
	}

	/**
	 * @return the benPhoneNumer
	 */
	public String getBenPhoneNumer() {
		return benPhoneNumer;
	}

	/**
	 * @param benPhoneNumer the benPhoneNumer to set
	 */
	public void setBenPhoneNumer(String benPhoneNumer) {
		this.benPhoneNumer = benPhoneNumer;
	}

	/**
	 * @return the benAccountName
	 */
	public String getBenAccountName() {
		return benAccountName;
	}

	/**
	 * @param benAccountName the benAccountName to set
	 */
	public void setBenAccountName(String benAccountName) {
		this.benAccountName = benAccountName;
	}

	/**
	 * @return the benBankName
	 */
	public String getBenBankName() {
		return benBankName;
	}

	/**
	 * @param benBankName the benBankName to set
	 */
	public void setBenBankName(String benBankName) {
		this.benBankName = benBankName;
	}

	/**
	 * @return the benBranchName
	 */
	public String getBenBranchName() {
		return benBranchName;
	}

	/**
	 * @param benBranchName the benBranchName to set
	 */
	public void setBenBranchName(String benBranchName) {
		this.benBranchName = benBranchName;
	}

	/**
	 * @return the benCountryName
	 */
	public String getBenCountryName() {
		return benCountryName;
	}

	/**
	 * @param benCountryName the benCountryName to set
	 */
	public void setBenCountryName(String benCountryName) {
		this.benCountryName = benCountryName;
	}

	/**
	 * @return the benCompabyName
	 */
	public String getBenCompanyName() {
		return benCompanyName;
	}

	/**
	 * @param benCompabyName the benCompabyName to set
	 */
	public void setBenCompanyName(String benCompanyName) {
		this.benCompanyName = benCompanyName;
	}

	public static final Map<String, String> getSql2BeanMap() {

		if (sql2BeanMap == null) {
			sql2BeanMap = new LinkedHashMap<String, String>();
			
			sql2BeanMap.put("@id_key", "id");
			sql2BeanMap.put("@amount_in_zar", "localCurrency");
			sql2BeanMap.put("@exchange_rate", "conversionRate");
			sql2BeanMap.put("@amount_in_tk", "foreignCurrency");
			sql2BeanMap.put("@cust_first_name", "custFirstName");
			sql2BeanMap.put("@cust_surname", "custSurname");
			sql2BeanMap.put("@resident_address", "custResidentAddress");
			sql2BeanMap.put("@cust_post_code", "custPostCode");
			sql2BeanMap.put("@cust_id_no", "custPhotoNo");
			sql2BeanMap.put("@cust_id_exp_date", "custPhotoIdExpireDate");
			sql2BeanMap.put("@cust_date_of_birth", "custDateOfBirth");
			sql2BeanMap.put("@cust_phone_no", "custPhoneNo");
			sql2BeanMap.put("@cust_email", "custEmail");
			sql2BeanMap.put("@cust_city_suburb", "custCitySuburb");
			sql2BeanMap.put("@cust_povince", "custProvice");
			sql2BeanMap.put("@cust_remit_purpose", "custRemitencePurpose");
			sql2BeanMap.put("@ben_first_name", "benFirstName");
			sql2BeanMap.put("@ben_surname", "benSurname");
			sql2BeanMap.put("@ben_resident_address", "benResidentAddress");
			sql2BeanMap.put("@ben_district", "benDistrict");
			sql2BeanMap.put("@ben_email", "benEmail");
			sql2BeanMap.put("@ben_phone", "benPhoneNumer");
			sql2BeanMap.put("@ben_acct_no", "benAccountName");
			sql2BeanMap.put("@ben_bank_name", "benBankName");
			sql2BeanMap.put("@ben_bank_branch", "benBranchName");
			sql2BeanMap.put("@ben_country", "benCountryName");
			sql2BeanMap.put("@ben_company", "benCompanyName");			
		}

		return sql2BeanMap;
	}

	public static final Map<String, String> getRs2BeanMap() {

		if (rs2BeanMap == null) {
			rs2BeanMap = new LinkedHashMap<String, String>();	
			
			rs2BeanMap.put("id_key", "id");
			rs2BeanMap.put("amount_in_zar", "localCurrency");
			rs2BeanMap.put("exchange_rate", "conversionRate");
			rs2BeanMap.put("amount_in_tk", "foreignCurrency");
			rs2BeanMap.put("cust_first_name", "custFirstName");
			rs2BeanMap.put("cust_surname", "custSurname");
			rs2BeanMap.put("resident_address", "custResidentAddress");
			rs2BeanMap.put("cust_post_code", "custPostCode");
			rs2BeanMap.put("cust_id_no", "custPhotoNo");
			rs2BeanMap.put("cust_id_exp_date", "custPhotoIdExpireDate");
			rs2BeanMap.put("cust_date_of_birth", "custDateOfBirth");
			rs2BeanMap.put("cust_phone_no", "custPhoneNo");
			rs2BeanMap.put("cust_email", "custEmail");
			rs2BeanMap.put("cust_city_suburb", "custCitySuburb");
			rs2BeanMap.put("cust_povince", "custProvice");
			rs2BeanMap.put("cust_remit_purpose", "custRemitencePurpose");
			rs2BeanMap.put("ben_first_name", "benFirstName");
			rs2BeanMap.put("ben_surname", "benSurname");
			rs2BeanMap.put("ben_resident_address", "benResidentAddress");
			rs2BeanMap.put("ben_district", "benDistrict");
			rs2BeanMap.put("ben_email", "benEmail");
			rs2BeanMap.put("ben_phone", "benPhoneNumer");
			rs2BeanMap.put("ben_acct_no", "benAccountName");
			rs2BeanMap.put("ben_bank_name", "benBankName");
			rs2BeanMap.put("ben_bank_branch", "benBranchName");
			rs2BeanMap.put("ben_country", "benCountryName");
			rs2BeanMap.put("ben_company", "benCompanyName");					

		}

		return rs2BeanMap;
	}


}
