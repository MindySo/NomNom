package com.ssafy.nomnom.model.dto.attachment;

import java.time.LocalDate;

public class Attachment {
	private Integer fileNo;
    private FileTargetEnum fileTarget;
    private Integer targetNo;
    private String fileName;
    private String filePath;
    private LocalDate fileRegDate;
    
    
	public Attachment() {
	}
	
	
	public Attachment(Integer fileNo, FileTargetEnum fileTarget, Integer targetNo, String fileName, String filePath,
			LocalDate fileRegDate) {
		this.fileNo = fileNo;
		this.fileTarget = fileTarget;
		this.targetNo = targetNo;
		this.fileName = fileName;
		this.filePath = filePath;
		this.fileRegDate = fileRegDate;
	}
	
	
	public Integer getFileNo() {
		return fileNo;
	}
	public void setFileNo(Integer fileNo) {
		this.fileNo = fileNo;
	}
	public FileTargetEnum getFileTarget() {
		return fileTarget;
	}
	public void setFileTarget(FileTargetEnum fileTarget) {
		this.fileTarget = fileTarget;
	}
	public Integer getTargetNo() {
		return targetNo;
	}
	public void setTargetNo(Integer targetNo) {
		this.targetNo = targetNo;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public LocalDate getFileRegDate() {
		return fileRegDate;
	}
	public void setFileRegDate(LocalDate fileRegDate) {
		this.fileRegDate = fileRegDate;
	}
	@Override
	public String toString() {
		return "File [fileNo=" + fileNo + ", fileTarget=" + fileTarget + ", targetNo=" + targetNo + ", fileName="
				+ fileName + ", filePath=" + filePath + ", fileRegDate=" + fileRegDate + "]";
	}
}
