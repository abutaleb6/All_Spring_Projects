package com.nazdaqTechnologies.sebl.constants;

public enum SPName {

	ACT_USER("ACT_user"),
	RS_TYPE_USER_CONFIG("RS_TYPE_USER_CONFIG"),
	SEL_USER("SEL_user_config"),
	RS_TYPE_USER("RS_TYPE_USER"),

	ACT_SDNLIST("ACT_sdn_list"),
	RS_SDNLIST("RS_SDN_LIST"),

	SEL_GUI_DATA("SEL_gui_field_data"),
	RS_TYPE_GUI_DATA("RS_TYPE_GUI_FIELD_DATA"),

	ACT_PERSON("ACT_person"),
	RS_TYPE_PERSON("RS_TYPE_PERSON"),
	ACT_COMPANY("ACT_company"),
	RS_COMPANY("RS_COMPANY"),

	ACT_CONTACT_ADDRESS("ACT_person_contact_address"),
	RS_TYPE_CONTACT_ADDRESS("RS_TYPE_CONTACT_ADDRESS"),

	ACT_CONTACT_MISC("ACT_person_contact_misc"),
	RS_TYPE_CONTACT_MISC("RS_TYPE_CONTACT_MISC"),

	ACT_ATTACHMENT("ACT_attachment"),
	RS_TYPE_ATTACHMENT("RS_TYPE_ATTACHMENT"),

	ACT_IDENTIFIER("ACT_identifier"),
	RS_TYPE_IDENTIFIER("RS_TYPE_IDENTIFIER"),

	RS_TYPE_COUNTRY("RS_TYPE_COUNTRY"),
	SEL_COUNTRY("SEL_country"),

	IMP_REMIT_TRAN_SEB("IMP_remit_tran_seb"),

	SEL_REMIT_TRAN("SEL_remit_tran"),

	ACT_REMIT_TRAN("ACT_remit_tran"),

	INS_REMIT_TRAN("INS_remit_tran"),

	RS_TYPE_REMIT_TRAN("RS_TYPE_REMIT_TRAN"),

	UPD_DAILY_REMIT_BALANCE("UPD_daily_remit_balance"),

	ACT_DAILY_REMIT_BALANCE("ACT_daily_remit_balance"),

	RS_TYPE_DAILY_REMIT_BAL("RS_TYPE_DAILY_REMIT_BAL"),

	T_STAGE_SEB_REMIT_TRAN("T_STAGE_SEB_REMIT_TRAN");

	private final String storeProcedureName;

	private SPName(String storeProcedureName) {
		this.storeProcedureName = storeProcedureName;
	}

	@Override
	public String toString() {
		return storeProcedureName;
	}

}
