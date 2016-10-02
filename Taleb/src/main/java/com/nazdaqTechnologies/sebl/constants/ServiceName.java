package com.nazdaqTechnologies.sebl.constants;

/**
 * @author sarwar.kamal
 */
public enum ServiceName {

	SERVICE_POSTFIX("Service"),
	Patient("PatientService");

	private final String serviceName;

	private ServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	@Override
	public String toString() {
		return serviceName;
	}

}
