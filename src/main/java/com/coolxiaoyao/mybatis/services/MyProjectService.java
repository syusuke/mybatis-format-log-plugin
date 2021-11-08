package com.coolxiaoyao.mybatis.services;

import com.intellij.openapi.project.Project;

/**
 * @author kerryzhang on 2021/10/29
 */


public class MyProjectService {
    public MyProjectService(Project project) {
        System.out.println("MyProjectService." + project.getName());
    }
}
