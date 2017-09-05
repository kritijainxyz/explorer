package com.fico.kriti.explorer.repositories;

import com.fico.kriti.explorer.model.FileMetadata;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Created by KritiJain on 8/22/2017.
 */
public interface MetadataRepository extends PagingAndSortingRepository <FileMetadata, Integer> {
    FileMetadata findByFileId(@PathVariable("fileId") long fileId);
    List<FileMetadata> findAll();
    FileMetadata findByFileIdAndAndSharedUserId(@PathVariable("fileId") long fileId, @PathVariable("SharedUserId") long SharedUserId);
    List<FileMetadata> findBySharedUserId(@PathVariable("SharedUserId") long SharedUserId);
}
