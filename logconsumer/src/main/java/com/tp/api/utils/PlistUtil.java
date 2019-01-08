package com.tp.api.utils;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.tp.api.entity.ApkInfo;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.JGitInternalException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

/**
 * JGit API测试
 */
@Component
public class PlistUtil {

    static String download_url = "download-url";
    static String app_bundle = "app-bundle";
    static String app_title = "app-title";
    public static boolean createPlist(String filePath, ApkInfo apkInfo) {

        if (apkInfo.getPlatform() == null || apkInfo.getPlatform() != 2) {
            return false;
        }


        URL url = PlistUtil.class.getClassLoader().getResource("temp.plist");
        File file = new File(url.getFile()) ;
        List<String> lines = null;
        try {
            lines = Files.readLines(file, Charsets.UTF_8);


            BufferedWriter bw = Files.newWriter(new File(filePath), Charsets.UTF_8);

            for (int i = 0 ;i<lines.size();i++){
                String line = lines.get(i);
                if (line != null ){
                    if (line.contains(download_url)){
                        line = line.replace(download_url,apkInfo.getPath());
                    }else if (line.contains(app_bundle)){
                        line = line.replace(app_bundle,apkInfo.getAppBundle());
                    }else if (line.contains(app_title)){
                        line = line.replace(app_title,apkInfo.getFileName());
                    }

                }
                bw.write(line);
                bw.newLine();
            }

            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return true;

    }

}
