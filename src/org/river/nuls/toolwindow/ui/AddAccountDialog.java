package org.river.nuls.toolwindow.ui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;
import org.river.nuls.form.AddAccountPanel;
import org.river.nuls.model.NulsAccount;

import javax.swing.*;

public class AddAccountDialog extends DialogWrapper {
    private final Project project;
    private final NulsToolWindowPanel parent;
    private final NulsAccount account;
    private AddAccountPanel addAccountPanel;

    public AddAccountDialog(Project project, NulsToolWindowPanel parent, NulsAccount account){
        super(parent, true);
        this.project = project;
        this.parent = parent;
        this.account = account;
        init();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel(){
        addAccountPanel = new AddAccountPanel(project);
        addAccountPanel.loadAccountData(account);
        return addAccountPanel;
    }

    @Override
    protected void doOKAction(){
        boolean validateResult = addAccountPanel.validateInputs();
        if (!validateResult){
            addAccountPanel.setErrorMessage(addAccountPanel.getErrorInfo());
            return;
        }
        NulsAccount nulsAccount = addAccountPanel.getEntitybyFields();
        this.parent.addTreeItem(nulsAccount);

        super.doOKAction();
    }

}
