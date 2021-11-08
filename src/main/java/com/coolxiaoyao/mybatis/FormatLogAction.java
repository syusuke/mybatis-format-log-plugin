package com.coolxiaoyao.mybatis;

import com.coolxiaoyao.mybatis.event.FormatDialogEvent;
import com.coolxiaoyao.mybatis.ui.FormatForm;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.DialogBuilder;


/**
 * @author kerryzhang on 2021/10/29
 */

public class FormatLogAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        //final Project project = e.getProject();
        //final PopDialog popDialog = new PopDialog(project);
        //popDialog.show();

        DialogBuilder dialogBuilder = new DialogBuilder(e.getProject());
        FormatForm formatForm = new FormatForm();
        formatForm.setDialogEvent(new FormatDialogEvent() {
            @Override
            public void close() {
                dialogBuilder.dispose();
            }
        });
        dialogBuilder.setCenterPanel(formatForm.getMainPanel());
        dialogBuilder.show();

    }
}
