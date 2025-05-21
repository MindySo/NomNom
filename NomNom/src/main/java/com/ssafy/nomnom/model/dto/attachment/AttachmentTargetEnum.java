package com.ssafy.nomnom.model.dto.attachment;

public enum AttachmentTargetEnum {
	MEAL, BOARD;
	
	@Override
    public String toString() {
        return this.name();
    }
}
