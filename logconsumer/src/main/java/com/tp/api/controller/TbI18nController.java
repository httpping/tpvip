package com.tp.api.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.plugins.Page;
import com.tp.api.config.VipConfig;
import com.tp.api.entity.TbString;
import com.tp.api.entity.enums.LangEnum;
import com.tp.api.fileupload.Progress;
import com.tp.api.mode.BaseResponse;
import com.tp.api.mode.StringFilterRequestParam;
import com.tp.api.service.TbStringService;
import com.tp.api.utils.PageUtils;
import com.tp.api.utils.StringXmlParse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static com.tp.api.config.WebConfig.filePatterns;
import static com.tp.api.mode.BaseResponse.SUCCESS;

/**
 * <p>
 *  前端控制器 图片
 * </p>
 *
 * @author tanping
 * @since 2018-09-19
 */
@Slf4j
@Controller
@RequestMapping("/string")
public class TbI18nController {
    @Reference
    private VipConfig vipConfig;
    @Reference
    TbStringService tbStringService;



    @GetMapping
    public String index(Model model,StringFilterRequestParam param){
        model.addAttribute("headerTitle","Zaful Android APP国际化数据表");

        List<TbString> appVersions = tbStringService.groupBy("app_version");
        model.addAttribute("appVersions",appVersions);

        List<TbString> domains = tbStringService.groupBy("domain");
        model.addAttribute("domains",domains);

        return "/string/index";
    }


    @GetMapping("/upload_index")
    public String indexUpload(Model model,StringFilterRequestParam param){
        model.addAttribute("headerTitle","Zaful Android APP国际化数据表");

        log.info(param.toString());

        return "/string/index_upload";
    }


    @GetMapping("/list")
    @ResponseBody
    public PageUtils list(Model model,StringFilterRequestParam param){

        Page<TbString> page =  tbStringService.select(param);

        model.addAttribute("headerTitle","Zaful Android APP国际化数据表");

        PageUtils pageUtils = new PageUtils(page.getRecords(),page.getTotal());

        return pageUtils;
    }

    @RequestMapping(value = "/upload",method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public @ResponseBody BaseResponse up(@RequestParam("file") MultipartFile file, HttpServletRequest request, StringFilterRequestParam param) throws IOException, ParserConfigurationException, SAXException {
        String targetPath = null;
        String fileName = null;
        log.info(param.toString());
        if (param.getDomain() ==null || param.getAppVersion() == null){
            throw new IllegalArgumentException("domain and  version is null");
        }
        if (!file.isEmpty()){
//            ResourceUtils.getURL("classpath:").getPath()
            String path = vipConfig.getUploadPath();
            new File(path).mkdirs();
            String type = file.getContentType();


            MimeType mimeType =  MimeTypeUtils.parseMimeType(type);
            type = mimeType.getSubtype();


            fileName = UUID.randomUUID().toString()+"."+type;
            File target = new File(path + fileName);
            file.transferTo(target);
            targetPath = target.getPath();

            List<TbString> tbStrings =  StringXmlParse.genData(LangEnum.valueOf(param.getLang().toUpperCase()),targetPath);
            for (TbString tbString :tbStrings){
                tbString.setAppVersion(param.getAppVersion());
                tbString.setDomain(param.getDomain());
            }
            tbStringService.saveOrUpdateList(tbStrings);
        }
        return BaseResponse.created(SUCCESS, filePatterns +"/"+ fileName) ;
    }


    @GetMapping(value = "/size")
    public @ResponseBody
    Progress getProgress(HttpServletRequest request){
        HttpSession session = request.getSession();
        Progress progress = (Progress) session.getAttribute("status");
        System.out.println(progress + "controller");
        return progress;
    }


}


