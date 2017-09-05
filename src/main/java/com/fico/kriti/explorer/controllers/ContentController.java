package com.fico.kriti.explorer.controllers;

import com.fico.kriti.explorer.model.Content;
import com.fico.kriti.explorer.services.serviceImpl.ContentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by KritiJain on 8/16/2017.
 */
@RestController
public class ContentController {

    @Autowired
    private ContentServiceImpl contentService;

    @ResponseBody
    @RequestMapping(value = "/contents", consumes= {"application/json"}, produces= {"application/json"}, method= {RequestMethod.POST})
    public Content insert(@RequestBody Content obj){
        return contentService.create(obj);
    }

    @ResponseBody
    @RequestMapping(value = "/contents/{fileId}", produces= {"application/json"}, method= {RequestMethod.GET})
    public Content displayById(@PathVariable("fileId") long fileId){
        return contentService.displayById(fileId);
    }
}
