package com.ssafy.nomnom.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafy.nomnom.model.dto.attachment.Attachment;

@Repository
public interface AttachmentDao {

	List<Attachment> selectAttachmentByTargetAndNo(Attachment attachment);
	int insertAttachment(Attachment attachment);
	int deleteAttachmentByTargetAndNo(Attachment attachment);
	int deleteAttachment(int fileNo);
	
}
