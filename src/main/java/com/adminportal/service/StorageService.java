package com.adminportal.service;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
	 void init();
	 void deleteAll();
	 String emailTemplateUpload(MultipartFile file);
	 Stream<Path> loadAll();
	 Resource loadAsResource(String filename);
	 Path load(String filename);
}
