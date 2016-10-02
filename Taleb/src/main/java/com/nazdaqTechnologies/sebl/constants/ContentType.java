package com.nazdaqTechnologies.sebl.constants;

public enum ContentType {

	DASHBOARD("DASHBOARD"),
	STATUS("STATUS"),
	MULTI_MESSAGE("MULTI_MESSAGE"),
	MULTI("MULTI");

	private final String contentTypeName;

	private ContentType(String contentTypeName) {
		this.contentTypeName = contentTypeName;
	}

	@Override
	public String toString() {
		return contentTypeName;
	}
}
