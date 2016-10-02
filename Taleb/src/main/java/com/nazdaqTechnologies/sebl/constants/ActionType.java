package com.nazdaqTechnologies.sebl.constants;

/**
 * @author Md. Mahbub Hasan
 */
public enum ActionType {

	SELECT_TASK("SELECT"),SELECT_NOTE("SELECT_NOTE"),SEND_TO_BANK("SEND_TO_BANK"),UPDATE_LOCAL_STATUS("UPDATE_LOCAL_STATUS"),UPDATE_NOTE("UPDATE_NOTE"),
	LOGIN("LOGIN"), ADD_NOTE_AND_SEND("ADD_NOTE_AND_SEND"), SEND_SA_TO_DAC("SEND_SA_TO_DAC"),SEND_TO_SARB("SEND_TO_SARB"),SELECT_TASK_SARB("SELECT_SARB");

	private final String actionType;

	private ActionType(String actionType) {
		this.actionType = actionType;
	}

	@Override
	public String toString() {
		return actionType;
	}

}
