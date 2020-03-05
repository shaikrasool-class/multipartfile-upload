package com.kn.eshipping.resource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kn.eshipping.domain.FileResponse;
import com.kn.eshipping.service.StorageService;

/**
 * @author rasool
 *
 */
@RestController
public class FileController {

	@Autowired
	private StorageService storageService;

	@PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public FileResponse uploadFile(@RequestParam("file") MultipartFile file) {
		FileResponse resource = storageService.store(file);
		return new FileResponse(resource.getId(), resource.getName(), resource.getUri(), resource.getType(),
				resource.getSize());
	}

	@PostMapping(value = "/uploadFiles", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<FileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
		return Arrays.stream(files).map(file -> uploadFile(file)).collect(Collectors.toList());
	}

}