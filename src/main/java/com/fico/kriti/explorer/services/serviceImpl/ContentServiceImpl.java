package com.fico.kriti.explorer.services.serviceImpl;

import com.fico.kriti.explorer.model.Content;
import com.fico.kriti.explorer.repositories.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by KritiJain on 8/19/2017.
 */
@Service
public class ContentServiceImpl {

    @Autowired
    private ContentRepository contentRepository;

    public Content create(Content obj){
        contentRepository.save(obj);
        return obj;
    }

    public Content displayById(long fileId){
        return contentRepository.findByFileId(fileId);
    }
}
