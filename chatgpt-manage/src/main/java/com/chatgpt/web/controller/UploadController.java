package com.chatgpt.web.controller;

import com.chatgpt.constant.Constant;
import com.chatgpt.dto.UploadResponseDTO;
import com.chatgpt.util.IdGenerateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Api(tags = "Upload.Manage", description = "upload控制器")
@Controller
@RequestMapping(value = "/upload")
public class UploadController {
    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
    private static final String EDITOR = "editor";
    private static final String BANNER = "banner";

    @Value("${image.editor.dir:/data/images/editor/}")
    private String editorDir;
    @Value("${image.banner.dir:/data/images/banner/}")
    private String bannerDir;
    @Value("${image.default.dir:/data/images/}")
    private String defaultDir;

    @ApiOperation("editor上传")
    @RequestMapping(value = "/editor")
    @ResponseBody
    public UploadResponseDTO editorUpload(@RequestParam(value = "file") MultipartFile file){
        return upload(editorDir, EDITOR, file);
    }

    @ApiOperation("banner上传")
    @RequestMapping(value = "/banner")
    @ResponseBody
    public UploadResponseDTO bannerUpload(@RequestParam(value = "file") MultipartFile file){
        return upload(bannerDir, BANNER, file);
    }

    @ApiOperation("通用上传")
    @RequestMapping(value = "/img")
    @ResponseBody
    public UploadResponseDTO defaultUpload(@RequestParam(value = "file") MultipartFile file){
        return upload(defaultDir, Constant.EMPTY, file);
    }

    @ApiOperation("markdown上传")
    @RequestMapping(value = "/markdown")
    @ResponseBody
    public UploadResponseDTO markdownUpload(@RequestParam(value = "file") MultipartFile file){
        return uploadMarkdown(defaultDir, Constant.EMPTY, file);
    }

    /**
     * 上传文件
     *
     * @param dir
     * @param prefix
     * @param file
     * @return
     */
    private UploadResponseDTO upload(String dir, String prefix, MultipartFile file) {
        try {
            String filename = IdGenerateUtils.nextId() + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(Constant.POINT));
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(dir + filename));
            return UploadResponseDTO.success(new UploadResponseDTO.UploadResultDTO().title(filename).src(Constant.XG + Constant.PICTURE + Constant.XG + (StringUtils.isEmpty(prefix) ? prefix : (prefix + Constant.XG)) + filename));
        } catch (Exception e){
            logger.error("文件上传失败～", e);
            return UploadResponseDTO.fail("文件上传失败～");
        }
    }

    /**
     * 上传文件
     *
     * @param dir
     * @param prefix
     * @param file
     * @return
     */
    private UploadResponseDTO uploadMarkdown(String dir, String prefix, MultipartFile file) {
        try {
            String filename = IdGenerateUtils.nextId() + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(Constant.POINT));
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(dir + filename));
            return UploadResponseDTO.success(Constant.XG + Constant.PICTURE + Constant.XG + (StringUtils.isEmpty(prefix) ? prefix : (prefix + Constant.XG)) + filename);
        } catch (Exception e){
            logger.error("文件上传失败～", e);
            return UploadResponseDTO.fail("文件上传失败～");
        }
    }
}
