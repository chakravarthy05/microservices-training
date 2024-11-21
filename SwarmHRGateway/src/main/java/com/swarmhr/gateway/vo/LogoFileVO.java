package com.swarmhr.gateway.vo;

public class LogoFileVO {

	private String id;
	private String fileName;
	private String fileType;
	private byte[] file;

	public LogoFileVO() {
	}

	public LogoFileVO(String id, String fileName, String fileType, byte[] file) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.fileType = fileType;
		this.file = file;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

}
