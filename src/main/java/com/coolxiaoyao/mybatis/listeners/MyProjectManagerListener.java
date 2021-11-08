package com.coolxiaoyao.mybatis.listeners;

import com.coolxiaoyao.mybatis.services.MyProjectService;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManagerListener;
import org.jetbrains.annotations.NotNull;

/**
 * @author kerryzhang on 2021/10/29
 */


public class MyProjectManagerListener implements ProjectManagerListener {
    @Override
    public void projectOpened(@NotNull Project project) {
        project.getService(MyProjectService.class);
    }
}
