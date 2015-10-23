package com.github.yukihane.hello_mvp4g.shared;

import java.io.Serializable;

public class Information implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private Timing timing;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timing getTiming() {
        return timing;
    }

    public void setTiming(Timing timing) {
        this.timing = timing;
    }

}
