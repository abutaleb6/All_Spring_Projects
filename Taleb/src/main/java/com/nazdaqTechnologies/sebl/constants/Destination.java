package com.nazdaqTechnologies.sebl.constants;

public enum Destination {


	PATIENT_SERVICE("Patient"),
	USER_SERVICE("User"),
	nRemit("nRemit"),
	nRemitSa("SA"),
	nRemitClient("nRemitClient"),
	nRemitClientSa("SEBL-server");

	private final String destinationName;

	private Destination(String destinationName) {
		this.destinationName = destinationName;
	}

	@Override
	public String toString() {
		return destinationName;
	}

}
