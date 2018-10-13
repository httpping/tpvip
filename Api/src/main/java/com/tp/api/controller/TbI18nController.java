package com.tp.api.controller;


import com.tp.api.entity.ApiModel;
import com.tp.api.entity.TbLog;
import com.tp.api.entity.TbString;
import com.tp.api.fileupload.Progress;
import com.tp.api.mode.LoggerMessage;
import com.tp.api.mode.StringFilterRequestParam;
import com.tp.api.service.ApiModelService;
import com.tp.api.service.TbLogService;
import com.tp.api.service.TbStringService;
import com.tp.common.bean.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.List;

import static com.tp.common.bean.BaseResponse.SUCCESS;

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

    @Autowired
    TbStringService tbStringService;

    @GetMapping
    public String index(Model model,StringFilterRequestParam param){

        List<TbString> tbStrings =  tbStringService.select(param);

        model.addAttribute("headerTitle","Zaful Android APP国际化数据表");
//        model.addAttribute("domain",request.getDomain());
        model.addAttribute("logs",tbStrings);

        return "/string/index";
    }

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public @ResponseBody String up(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws  IOException {
        String targetPath = null;
        if (!file.isEmpty()){
            File rootPath = new File(ResourceUtils.getURL("classpath:").getPath());

            String path = rootPath + "/upload/";
            new File(path).mkdirs();
            String fileName = file.getName();
            File target = new File(path + fileName);
            file.transferTo(target);
            targetPath = target.getPath();
        }
        return "upload success :" +targetPath ;
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


