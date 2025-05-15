package com.ssafy.nomnom.model.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.nomnom.model.dao.AttachmentDao;
import com.ssafy.nomnom.model.dto.attachment.Attachment;
import com.ssafy.nomnom.model.dto.attachment.FileTargetEnum;

@Service
public class AttachmentServiceImpl implements AttachmenetService {

	@Value("${file.upload.directory}")
	private String uploadDir;
	
	@Autowired
	private AttachmentDao attachmentDao;

	@Override
	public void writeAttachment(List<MultipartFile> fileList, FileTargetEnum target) {
//		if (attach == null) // 화면에서 attach 파라미터 자체가 없는 경우
		// 사용자가 파일을 선택 했다면..
		for (MultipartFile mtfile : fileList) {
			if (!fileList.isEmpty()) {
				String originalName = mtfile.getOriginalFilename();
				long fileSize = mtfile.getSize(); // byte 크기
				String uploadName = generateUniqueName(originalName);
				File dirFile = new File(uploadDir);
				if (!dirFile.exists()) {
					dirFile.mkdirs();
				}
				File file = new File(dirFile, uploadName);
				// 1. 서버에 저장
//				mtfile.transferTo(file);

				// 2. 디비에 저장
				Attachment attachment = new Attachment();
				// original_name, upload_name, file_size
				attachment.setFileTarget(target);
				attachment.setTargetNo(null);
				attachment.setFileName(uploadName);
				attachment.setFilePath(uploadName);
				//attachmentDao.insertBoardFile(boardFile);
			}
		}
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
