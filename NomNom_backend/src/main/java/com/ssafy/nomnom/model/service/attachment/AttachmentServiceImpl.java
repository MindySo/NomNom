package com.ssafy.nomnom.model.service.attachment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.nomnom.model.dao.AttachmentDao;
import com.ssafy.nomnom.model.dto.attachment.Attachment;
import com.ssafy.nomnom.model.dto.attachment.AttachmentTargetEnum;
import com.ssafy.nomnom.util.S3Uploader;

@Service
public class AttachmentServiceImpl implements AttachmentService {
	
	@Autowired
	private AttachmentDao attachmentDao;

	@Autowired
	private S3Uploader s3Uploader;

	@Override
	public void writeAttachment(MultipartFile mpfile, AttachmentTargetEnum target, int targetNo) {
		String originalName = mpfile.getOriginalFilename();
		String imageUrl = "";
		try {
			imageUrl = s3Uploader.upload(mpfile, "images");
		} catch (Exception e) {
			throw new RuntimeException("파일 저장 중 오류 발생", e);
		}

		Attachment attachment = new Attachment();
		attachment.setAttachmentTarget(target);
		attachment.setTargetNo(targetNo);
		attachment.setAttachmentOriginalName(originalName);
		attachment.setAttachmentName(imageUrl);
		attachmentDao.insertAttachment(attachment);
	}

	

	@Override
	public List<Attachment> getAttachment(AttachmentTargetEnum target, int targetNo) {
		Attachment attachment = new Attachment();
		attachment.setAttachmentTarget(target);
		attachment.setTargetNo(targetNo);
		
		List<Attachment> list = attachmentDao.selectAttachmentByTargetAndNo(attachment);
		for(Attachment file : list) {
			file.setAttachmentName(file.getAttachmentName());
		}
		return list;
	}

	@Override
	public void deleteAllAttachment(AttachmentTargetEnum target, int targetNo) {
		Attachment attachment = new Attachment();
		attachment.setAttachmentTarget(target);
		attachment.setTargetNo(targetNo);

		attachmentDao.deleteAttachmentByTargetAndNo(attachment);
	}

	@Override
	public void deleteAttachment(int attachmentNo) {
		attachmentDao.deleteAttachment(attachmentNo);
	}

}
