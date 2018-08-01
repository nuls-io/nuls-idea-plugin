package org.river.nuls.form;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.project.Project;
import org.apache.commons.lang3.StringUtils;
import org.river.nuls.logic.TreeItemManager;
import org.river.nuls.model.NulsNode;

import javax.swing.*;
import java.awt.*;

public class AddNodePanel extends JPanel {
    private JPanel rootPanel;
    private JTextField para01TextField;
    private JLabel feedbackLabel;
    private JLabel param01Label;
    private JTextField remarkTextField;
    private JLabel remarkLabel;

    private final Project project;
    private final TreeItemManager treeItemManager;

    private String errorInfo = "";

    public AddNodePanel(Project project, TreeItemManager treeItemManager){
        this.project = project;
        this.treeItemManager = treeItemManager;
        setLayout(new BorderLayout());
        add(rootPanel);
    }

    public void loadNodeData(NulsNode node){
        if (node == null) return;
        para01TextField.setText(node.getAgentAddress());
    }

    public void setErrorMessage(String message) {
        feedbackLabel.setIcon(AllIcons.Ide.Error);
        feedbackLabel.setText(message);
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public boolean validateInputs(){
        String agentAddress = para01TextField.getText();
        if (StringUtils.isEmpty(agentAddress)){
            this.errorInfo = "The agent address can not be null!";
            return false;
        }
        return true;
    }

    public NulsNode getEntitybyFields(){
        NulsNode node = new NulsNode();
        node.setAgentAddress(para01TextField.getText());
        node.setRemark(remarkTextField.getText());
        return node;
    }
}
