package com.ssafy.nomnom.model.dto.attachment;

import java.time.LocalDate;

public class Attachment {
	private int attachmentNo;
    private AttachmentTargetEnum attachmentTarget;
    private int targetNo;
    private String attachmentName;
    private String attachmentOriginalName;
    private LocalDate attachmentRegDate;

    
    public Attachment() {
    }

    public Attachment(int attachmentNo, AttachmentTargetEnum attachmentTarget, int targetNo, String attachmentName,
			String attachmentOriginalName, LocalDate attachmentRegDate) {
		super();
		this.attachmentNo = attachmentNo;
		this.attachmentTarget = attachmentTarget;
		this.targetNo = targetNo;
		this.attachmentName = attachmentName;
		this.attachmentOriginalName = attachmentOriginalName;
		this.attachmentRegDate = attachmentRegDate;
	}

	public int getAttachmentNo() {
		return attachmentNo;
	}
	public void setAttachmentNo(int attachmentNo) {
		this.attachmentNo = attachmentNo;
	}
	public AttachmentTargetEnum getAttachmentTarget() {
		return attachmentTarget;
	}
	public void setAttachmentTarget(AttachmentTargetEnum attachmentTarget) {
		this.attachmentTarget = attachmentTarget;
	}
	public int getTargetNo() {
		return targetNo;
	}
	public void setTargetNo(int targetNo) {
		this.targetNo = targetNo;
	}
	public String getAttachmentName() {
		return attachmentName;
	}
	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}
	public LocalDate getAttachmentRegDate() {
		return attachmentRegDate;
	}
	public void setAttachmentRegDate(LocalDate attachmentRegDate) {
		this.attachmentRegDate = attachmentRegDate;
	}

	public String getAttachmentOriginalName() {
		return attachmentOriginalName;
	}

	public void setAttachmentOriginalName(String attachmentOriginalName) {
		this.attachmentOriginalName = attachmentOriginalName;
	}

	@Override
	public String toString() {
		return "Attachment [attachmentNo=" + attachmentNo + ", attachmentTarget=" + attachmentTarget + ", targetNo="
				+ targetNo + ", attachmentName=" + attachmentName + ", attachmentOriginalName=" + attachmentOriginalName
				+ ", attachmentRegDate=" + attachmentRegDate + "]";
	}
	
}
