package com.github.yukihane.hello_mvp4g.client.main;

import com.github.yukihane.hello_mvp4g.client.main.presenter.MainPresenter;
import com.mvp4g.client.annotation.Event;
import com.mvp4g.client.annotation.Events;
import com.mvp4g.client.annotation.Start;
import com.mvp4g.client.event.EventBus;

@Events(startPresenter = MainPresenter.class)
public interface MainEventBus extends EventBus {

    @Start
    @Event(handlers = MainPresenter.class)
    void start();

}
