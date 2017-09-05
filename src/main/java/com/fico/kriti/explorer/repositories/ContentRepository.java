package com.fico.kriti.explorer.repositories;

import com.fico.kriti.explorer.model.Content;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by KritiJain on 8/16/2017.
 */
public interface ContentRepository extends PagingAndSortingRepository<Content, Integer> {
Content findByFileId(@PathVariable("fileId") long fileId);


}
