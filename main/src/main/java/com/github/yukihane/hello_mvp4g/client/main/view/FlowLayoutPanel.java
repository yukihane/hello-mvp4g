package com.github.yukihane.hello_mvp4g.client.main.view;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.ProvidesResize;
import com.google.gwt.user.client.ui.RequiresResize;
import com.google.gwt.user.client.ui.Widget;

public class FlowLayoutPanel extends FlowPanel implements ProvidesResize, RequiresResize {

    public FlowLayoutPanel() {
    }

    @Override
    public void onResize() {
        for (Widget widget : getChildren()) {
            if (widget instanceof RequiresResize) {
                ((RequiresResize) widget).onResize();
            }
        }
    }
}
