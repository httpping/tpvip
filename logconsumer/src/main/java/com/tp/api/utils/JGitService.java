package com.tp.api.utils;

import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.JGitInternalException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * JGit API测试
 */
@Component
public class JGitService {


    @Value("${mygit.url}")
    public String remotePath;

    @Value("${mygit.local-path}")
    public String localPath ;//下载已有仓库到本地路径

    public String userName = "871625458@qq.com";
    public String pw = "tp4692182";

    public String branch = "master";

    /**
     * 克隆远程库
     * @throws IOException
     * @throws GitAPIException
     */
    public void gitClone() throws IOException, GitAPIException {
        //设置远程服务器上的用户名和密码
        UsernamePasswordCredentialsProvider usernamePasswordCredentialsProvider =new
                UsernamePasswordCredentialsProvider(userName,pw);

        //克隆代码库命令
        CloneCommand cloneCommand = Git.cloneRepository();

       Git git= cloneCommand.setURI(remotePath) //设置远程URI
                .setBranch(branch) //设置clone下来的分支
                .setDirectory(new File(localPath)) //设置下载存放路径
                .setCredentialsProvider(usernamePasswordCredentialsProvider) //设置权限验证
                .call();

        System.out.print(git.tag());
    }

    /**
    * 本地新建仓库
    */
    public void testCreate() throws IOException {
        //本地新建仓库地址
       /* Repository newRepo = FileRepositoryBuilder.create(new File(initPath + "/.git"));
        newRepo.create();*/
    }

    /**
    * 本地仓库新增文件
    */
    public String  gitAdd(String fileName) throws IOException, GitAPIException {
        File myfile = new File(localPath + "/"+fileName);
        boolean exists = myfile.getParentFile().exists();
        if (!exists){
            myfile.getParentFile().mkdirs();
        }

        myfile.createNewFile();
        //git仓库地址
        Git git = new Git(new FileRepository(localPath+"/.git"));

        //添加文件
        git.add().addFilepattern(fileName).call();
        return myfile.getPath();
    }

    /**
    * 本地提交代码
    */
    public void gitCommit(String message) throws IOException, GitAPIException,
            JGitInternalException {
        //git仓库地址
        Git git = new Git(new FileRepository(localPath+"/.git"));
        //提交代码
        git.commit().setMessage(message).call();
    }


    /**
    * 拉取远程仓库内容到本地
    */
    public void gitPull() throws IOException, GitAPIException {

        UsernamePasswordCredentialsProvider usernamePasswordCredentialsProvider =new
                UsernamePasswordCredentialsProvider(userName,pw);
        //git仓库地址
        Git git = new Git(new FileRepository(localPath+"/.git"));
        git.pull().setRemoteBranchName(branch).
                setCredentialsProvider(usernamePasswordCredentialsProvider).call();
    }

    /**
    * push本地代码到远程仓库地址
    */
    public void gitPush() throws IOException, JGitInternalException,
            GitAPIException {

        UsernamePasswordCredentialsProvider usernamePasswordCredentialsProvider =new
                UsernamePasswordCredentialsProvider(userName,pw);
        //git仓库地址
        Git git = new Git(new FileRepository(localPath+"/.git"));   
        git.push().setRemote("origin").setCredentialsProvider(usernamePasswordCredentialsProvider).call();
    }
}
