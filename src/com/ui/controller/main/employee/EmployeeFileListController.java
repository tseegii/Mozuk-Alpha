package com.ui.controller.main.employee;

import com.ui.component.base.MainComponent;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Component;

/**
 * Created by ganbat.b on 7/8/2015.
 */
public class EmployeeFileListController extends MainComponent {
    @Init(superclass = true)
    @Override
    public void init() {
        super.init();
    }

    @AfterCompose(superclass = true)
    @Override
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        super.afterCompose(view);
    }

    @Command
    public void refresh() {

    }
}
