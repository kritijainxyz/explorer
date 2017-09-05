package com.fico.kriti.explorer.repositories;

import com.fico.kriti.explorer.model.FileDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Created by KritiJain on 8/16/2017.
 */
//@RepositoryRestResource(collectionResourceRel = "files", path = "files")
public interface FileRepository extends PagingAndSortingRepository<FileDetail, Integer> {

    void deleteByFileIdAndUserId(@PathVariable("fileId") long fileId, @PathVariable("userId") long userId);
    FileDetail findByFileId(@PathVariable("fileId") long fileId);
    FileDetail findByFileIdAndUserId(@PathVariable("fileId") long fileId, @PathVariable("userId") long userId);
    List<FileDetail> findAll();
}

