package com.tp.api.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.tp.api.entity.ApkInfo;
import com.tp.api.service.ApkInfosService;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
public class IosIpaControl {


    @Value("${market.ios}")
    String iosUrl ;

    @Reference
    private ApkInfosService apkInfosService;

    //因为没有https 暂时不支持并发下载，并发下载会出bug
    Long curId ;

    @GetMapping("/ios")
    public String ios(Model model, ApkInfo request, HttpServletRequest httpRequest) throws NotFoundException {

        return "/ios";
    }


    /**
     * 提供下载
     * @param model
     * @param id
     * @param httpRequest
     * @return
     * @throws NotFoundException
     */
    @GetMapping("/ios/download/{id}")
    public String iosDownLoad(Model model, @PathVariable long id , HttpServletRequest httpRequest) throws NotFoundException {

        ApkInfo apkInfo = new ApkInfo();
        apkInfo.setId(id);
        List<ApkInfo> apkInfos = apkInfosService.query(apkInfo);
        if (apkInfos !=null && apkInfos.size() ==1){
            apkInfo = apkInfos.get(0);
        }else {
            apkInfo = null;
        }



        if (apkInfo!=null && apkInfo.getPlistUrl() != null){
            model.addAttribute("plistUrl",apkInfo.getPlistUrl());
            model.addAttribute("name",apkInfo.getName() +" (" +apkInfo.getNumber()+") -" + apkInfo.getUpdateTime());

            log.info(apkInfo.getPlistUrl());
            curId = apkInfo.getId();
            return "/ios/ios-download";
        }else{
            return "/ios/ios-download-error";
        }



    }

    /**
     * ipa 下载
     * @param model
     * @param httpRequest
     * @return
     * @throws NotFoundException
     */
    @GetMapping("/ios/ipa")
    public String ipa(Model model, HttpServletRequest httpRequest) throws NotFoundException {

        ApkInfo apkInfo = new ApkInfo();
        apkInfo.setId(curId);
        List<ApkInfo> apkInfos =  apkInfosService.query(apkInfo);

        if (apkInfos !=null && apkInfos.size() ==1){
            apkInfo = apkInfos.get(0);
        }else {
            apkInfo = null;
        }

        //http://10.32.100.232:8080/job/Rosegal/12/artifact/build/RG_iOS_version3.4.0_number12_date2018.12.20.ipa

        log.info("apkid =" + curId);
        String url = "http://10.32.2.253:8090/static/ios/Payload.ipa";

        //匹配正式url
        url = iosUrl ;
        url += "/job/" + apkInfo.getName() +"/" +apkInfo.getNumber() +"/artifact/" + apkInfo.getPath();
        log.info("url =" + url);

        url = "http://10.32.2.253:8090/files/ipa/RG_iOS_version.ipa";
        return "redirect:"+url;
    }

}
