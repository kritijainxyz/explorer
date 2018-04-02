//package com.fico.kriti.explorer.controllers;
//
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fico.dmp.component.descriptor.ComponentDescriptor;
//import com.fico.dmp.context.DMPContext;
//import org.apache.commons.lang3.RandomStringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.Map;
//import java.util.Scanner;
//
//@RestController
//@RequestMapping("/rest/dmp")
//public class ComponentLifecycleController {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(ComponentLifecycleController.class);
//    public static final String TEST_FOLDER = "test";
//    public static final String EXPORTED_FILE = "abc.txt";
//
//    @RequestMapping(value = "/component.json", method = RequestMethod.GET, produces = "application/json")
//    @ResponseBody
//    public ComponentDescriptor get(HttpServletResponse res) throws IOException {
//        res.setStatus(404);
//        return null;
//    }
//
//    @RequestMapping(value = "/pre_pull", method = RequestMethod.POST)
//    @ResponseBody
//    public String prePull(HttpServletRequest request) throws IOException {
//
//        Map reqMap = new ObjectMapper().readValue(request.getReader(), Map.class);
//        Boolean isComponentUpgrade = (Boolean) reqMap.get("prototypeChanges");
//
//        if(isComponentUpgrade){
//            LOGGER.info("prepull");
//            commit();
//        }
//
//        return "Prepull: OK";
//    }
//
//    @RequestMapping(value = "/pull", method = RequestMethod.POST)
//    @ResponseBody
//    public String pull() throws IOException {
//        String path = getPath();
//        File modelFile = new File(path);
//        if (modelFile.exists()) {
//            Scanner scanner = new Scanner(modelFile);
//            String content = scanner.useDelimiter("\\Z").next();
//            scanner.close();
//            LOGGER.info(content);
//        } else {
//            LOGGER.error("File not found");
//            LOGGER.error("Path: " + path);
//        }
//        return "OK";
//    }
//
//    @RequestMapping(value = "/commit", method = RequestMethod.POST)
//    @ResponseBody
//    public String commit() {
//        String path = getPath();
//        File modelFile = new File(path);
//        if (!modelFile.exists()) {
//            modelFile.getParentFile().mkdirs();
//        }
//
//        try (FileOutputStream fileOutputStream = new FileOutputStream(modelFile)) {
//            String exportedData = "Sample Exported Data :" + RandomStringUtils.randomAlphanumeric(50);
//            LOGGER.info(exportedData);
//            fileOutputStream.write(exportedData.getBytes());
//        } catch (IOException e) {
//            LOGGER.info(e.getMessage());
//        }
//        return "Commit: OK";
//    }
//
//    private String getPath() {
//        String dmpDataDir = DMPContext.getLifecycleDataDirectory().getAbsolutePath();
//        if (dmpDataDir == null) {
//            dmpDataDir = "";
//        }
//        return dmpDataDir + File.separator + TEST_FOLDER + File.separator + EXPORTED_FILE;
//    }
//
//}
