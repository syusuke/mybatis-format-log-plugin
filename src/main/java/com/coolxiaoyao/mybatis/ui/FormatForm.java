package com.coolxiaoyao.mybatis.ui;

import com.coolxiaoyao.mybatis.delegate.ServiceDelegate;
import com.coolxiaoyao.mybatis.event.FormatDialogEvent;
import com.intellij.ui.JBColor;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * @author kerryzhang on 2021/10/29
 */


public class FormatForm {

    private FormatDialogEvent dialogEvent;

    private JPanel mainPanel;
    private JTextArea taLogInput;
    private JComboBox<String> cbDbType;
    private JButton btnResolve;
    private JLabel lbResolveResult;
    private JCheckBox cbLineWrap;
    private JButton copyAllButton;
    private JTextArea taResult;
    private JButton button1;
    private JList list1;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public FormatForm() {
        taLogInput.setLineWrap(cbLineWrap.isSelected());

        cbLineWrap.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                taLogInput.setLineWrap(cbLineWrap.isSelected());
            }
        });
        List<String> supportSqlType = ServiceDelegate.getSupportSqlType();
        for (String type : supportSqlType) {
            cbDbType.addItem(type);
        }

        btnResolve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                List<String> results = ServiceDelegate.formatSqlLog(taLogInput.getText(), (String) cbDbType.getSelectedItem());
                if (!results.isEmpty()) {
                    lbResolveResult.setForeground(JBColor.GREEN);
                    lbResolveResult.setText("解析成功: " + results.size());
                    String line = String.join("\n\n", results);
                    taResult.setText(line);
                } else {
                    lbResolveResult.setForeground(JBColor.RED);
                    lbResolveResult.setText("解析失败");
                }


            }
        });

        copyAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                copyTextToClipboard(taResult.getText());
                if (dialogEvent != null) {
                    dialogEvent.close();
                }
            }
        });
    }

    private void copyTextToClipboard(String text) {
        if (text == null || text.trim().length() == 0) {
            return;
        }
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable trans = new StringSelection(text);
        // 把文本内容设置到系统剪贴板
        clipboard.setContents(trans, null);
    }


    public void setDialogEvent(FormatDialogEvent dialogEvent) {
        this.dialogEvent = dialogEvent;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("FormatForm");
        frame.setContentPane(new FormatForm().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
