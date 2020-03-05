package com.kn.eshipping.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.kn.eshipping.domain.FileResponse;

public interface FileUploadRepository extends MongoRepository<FileResponse, Long> {

}
