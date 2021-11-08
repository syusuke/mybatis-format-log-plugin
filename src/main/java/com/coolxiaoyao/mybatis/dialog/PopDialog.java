package com.coolxiaoyao.mybatis.dialog;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author kerryzhang on 2021/10/29
 */


public class PopDialog extends DialogWrapper {
    public PopDialog(@Nullable Project project) {
        super(project);
        setTitle("Format Log");
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        return null;
    }
}
