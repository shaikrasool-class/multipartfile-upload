package com.kn.eshipping.service.impl;

import java.awt.image.BufferedImage;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kn.eshipping.domain.FileResponse;
import com.kn.eshipping.excpetion.StorageException;
import com.kn.eshipping.repository.FileUploadRepository;
import com.kn.eshipping.service.StorageService;

/**
 * @author rasool
 *
 */
@Service
public class FileSystemStorageService implements StorageService {

	@Autowired
	private FileUploadRepository fileUploadRepository;

	@Override
	public FileResponse store(MultipartFile file) {
		String filename = StringUtils.cleanPath(file.getOriginalFilename().replace(".pdf", ""));
		FileResponse response = null;

		if (file.isEmpty()) {
			throw new StorageException("Failed to store empty file " + filename);
		}
		if (filename.contains("..")) {
			// This is a security check
			throw new StorageException("Cannot store file with relative path outside current directory " + filename);
		}
		FileResponse fileResponses = null;

		try (final PDDocument document = PDDocument.load(file.getInputStream())) {
			if (document != null) {
				String fileName = null;
				PDFRenderer pdfRenderer = new PDFRenderer(document);
				for (int page = 0; page < document.getNumberOfPages(); page++) {
					BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);
					fileName = filename + page + ".JPEG";
				//below statement will store images in project folder
				//	ImageIOUtil.writeImage(bim, fileName, 300);
					String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
							.path("/knpoc/")
							.path(fileName).toUriString();
					fileResponses = new FileResponse(fileName, uri, file.getSize(), file.getContentType());
					response = fileUploadRepository.save(fileResponses);
				}
			}
		}catch (IOException e) {
			throw new StorageException("Failed to store file " + filename, e);
		}
		return response;
	}
	
}
