package com.ssafy.nomnom.model.service.attachment;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.nomnom.model.dto.attachment.Attachment;
import com.ssafy.nomnom.model.dto.attachment.AttachmentTargetEnum;

import jakarta.annotation.Resource;

public interface AttachmentService {
	void writeAttachment(MultipartFile fileList, AttachmentTargetEnum target, int targetNo);
	List<Attachment> getAttachment(AttachmentTargetEnum target, int targetNo);
	void deleteAllAttachment(AttachmentTargetEnum target, int targetNo);
	void deleteAttachment(int attachmentNo);
}
