package com.kn.eshipping.service;

import org.springframework.web.multipart.MultipartFile;

import com.kn.eshipping.domain.FileResponse;

/**
 * @author rasool
 *
 */
public interface StorageService {

    FileResponse store(MultipartFile file);
}