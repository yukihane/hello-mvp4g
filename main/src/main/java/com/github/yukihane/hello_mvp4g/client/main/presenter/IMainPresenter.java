package com.github.yukihane.hello_mvp4g.client.main.presenter;

import com.github.yukihane.hello_mvp4g.client.main.MainEventBus;
import com.mvp4g.client.presenter.PresenterInterface;

public interface IMainPresenter extends PresenterInterface<IMainView, MainEventBus> {

    /**
     * 入力した名前を送信する.
     */
    void sendNameToServer();

}
