package com.ssafy.nomnom.model.service.attachment;


import org.springframework.web.multipart.MultipartFile;

import com.ssafy.nomnom.model.dto.attachment.AttachmentTargetEnum;

public interface AttachmentService {
	void writeAttachment(MultipartFile fileList, AttachmentTargetEnum target, int targetNo);
}
