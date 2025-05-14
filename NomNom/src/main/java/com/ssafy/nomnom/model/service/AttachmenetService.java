package com.ssafy.nomnom.model.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ssafy.nomnom.model.dto.attachment.FileTargetEnum;

public interface AttachmenetService {
	void writeAttachment(List<MultipartFile> fileList, FileTargetEnum target);
}
