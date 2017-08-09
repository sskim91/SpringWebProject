package sskim.controller;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sskim.util.MediaUtils;
import sskim.util.UploadFileUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.UUID;

@Controller
public class UploadController {

    @Resource(name = "uploadPath")
    private String uploadPath;

    private static final Logger logger = LogManager.getLogger(UploadController.class);

    @RequestMapping(value = "/uploadForm", method = RequestMethod.GET)
    public void uploadForm() {

    }

    @RequestMapping(value = "/uploadForm", method = RequestMethod.POST)
    public String uploadForm(MultipartFile file, Model model) throws Exception {

        logger.info("originalName: " + file.getOriginalFilename());
        logger.info("size: " + file.getSize());
        logger.info("contentType: " + file.getContentType());

        String savedName = uploadFile(file.getOriginalFilename(), file.getBytes());
        model.addAttribute("savedName", savedName);

        return "uploadResult";
    }

    private String uploadFile(String originalName, byte[] fileData) throws Exception {

        UUID uid = UUID.randomUUID();
        String savedName = uid.toString() + "_" + originalName;
        File target = new File(uploadPath, savedName);
        FileCopyUtils.copy(fileData, target);
        return savedName;
    }

    @RequestMapping(value = "/uploadAjax", method = RequestMethod.GET)
    public void uploadAjax() {

    }

    @ResponseBody
    @RequestMapping(value = "/uploadAjax", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    public ResponseEntity<String> uploadAjax(MultipartFile file) throws Exception {

        logger.info("originalName: " + file.getOriginalFilename());
        logger.info("size: " + file.getSize());
        logger.info("contentType: " + file.getContentType());

        return new ResponseEntity<String>(
                UploadFileUtils.uploadFile(uploadPath,
                        file.getOriginalFilename(),
                        file.getBytes()), HttpStatus.OK);
    }

    //파일 데이터 전송
    @ResponseBody
    @RequestMapping("/displayFile")
    public ResponseEntity<byte[]> displayFile(String fileName) throws Exception {

        InputStream in = null;
        ResponseEntity<byte[]> entity = null;

        logger.info("File Name : " + fileName);

        try {
            String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);

            MediaType mType = MediaUtils.getMediaType(formatName);

            HttpHeaders headers = new HttpHeaders();

            in = new FileInputStream(uploadPath + fileName);

            if (mType != null) {
                headers.setContentType(mType);
            } else {

                fileName = fileName.substring(fileName.indexOf("_") + 1);
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                headers.add("Content-Disposition", "attachment; filename=\"" +
                        new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");
            }
            entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
        } finally {
            in.close();
        }
        return entity;
    }

    //파일 삭제처리
    @ResponseBody
    @RequestMapping(value = "/deleteFile", method = RequestMethod.POST)
    public ResponseEntity<String> deleteFile(String fileName) {

        logger.info("delete file : " + fileName);

        String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);

        MediaType mType = MediaUtils.getMediaType(formatName);

        if (mType != null) {
            String front = fileName.substring(0, 12);
            logger.info("front : " + front);
            String end = fileName.substring(14);
            logger.info("end : " + end);
            logger.info("삭제파일 : " + uploadPath + (front + end).replace('/', File.separatorChar));
            new File(uploadPath + (front + end).replace('/', File.separatorChar)).delete();
        }

        new File(uploadPath + fileName.replace('/', File.separatorChar)).delete();

        return new ResponseEntity<String>("deleted", HttpStatus.OK);
    }

    //게시물 삭제 처리 시 기존의 첨부파일 함께 삭제
    @ResponseBody
    @RequestMapping(value = "/deleteAllFiles", method = RequestMethod.POST)
    public ResponseEntity<String> deleteFile(@RequestParam("files[]") String[] files) {

        logger.info("delete all files : " + files);

        if (files == null || files.length == 0) {
            return new ResponseEntity<String>("deleted", HttpStatus.OK);
        }

        for (String fileName : files) {
            String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);

            MediaType mType = MediaUtils.getMediaType(formatName);

            if (mType != null) {

                String front = fileName.substring(0, 12);
                String end = fileName.substring(14);
                new File(uploadPath + (front + end).replace('/', File.separatorChar)).delete();
            }
            new File(uploadPath + fileName.replace('/', File.separatorChar)).delete();
        }
        return new ResponseEntity<String>("deleted", HttpStatus.OK);
    }
}
