package com.ssafy.nomnom.model.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.nomnom.model.dao.AttachmentDao;
import com.ssafy.nomnom.model.dto.attachment.Attachment;
import com.ssafy.nomnom.model.dto.attachment.AttachmentTargetEnum;

@Service
public class AttachmentServiceImpl implements AttachmentService {

	@Value("${file.upload.directory}")
	private String uploadDir;

	@Autowired
	private AttachmentDao attachmentDao;

	@Override
	public void writeAttachment(MultipartFile mpfile, AttachmentTargetEnum target, int targetNo) {
		String originalName = mpfile.getOriginalFilename();
		long fileSize = mpfile.getSize(); // byte 크기
		String uploadName = generateUniqueName(originalName);
		File dirFile = new File(uploadDir);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		File file = new File(dirFile, uploadName);

		Attachment attachment = new Attachment();
		attachment.setAttachmentTarget(target);
		attachment.setTargetNo(targetNo);
		attachment.setAttachmentName(uploadName);
		attachmentDao.insertAttachment(attachment);
	}

	private String generateUniqueName(String originalName) {
		String timeStr = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String uniqueStr = UUID.randomUUID().toString().substring(0, 8);
		int index = originalName.lastIndexOf(".");
		String extName = "";
		if (index != -1) {
			extName = originalName.substring(index);
		}
		return timeStr + "_" + uniqueStr + extName;
	}

}
