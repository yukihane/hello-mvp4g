package com.github.yukihane.hello_mvp4g.client.main.presenter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

import com.github.yukihane.hello_mvp4g.client.GreetingServiceAsync;
import com.github.yukihane.hello_mvp4g.shared.Information;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.core.shared.GWTBridge;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class MainPresenterTest {

    private MainPresenter presenter;
    private IMainView view;
    GreetingServiceAsync service;

    @Before
    public void setUp() {
        GWTBridge gwtBridge = mock(GWTBridge.class);
        GWT.setBridge(gwtBridge);
        presenter = new MainPresenter();
        view = mock(IMainView.class);
        presenter.setView(view);
        service = mock(GreetingServiceAsync.class);
        presenter.greetingService = service;
    }

    @Test
    public void サーバへ情報を送信する_正常ケース() {
        when(view.getTextToServer()).thenReturn("abcde");
        presenter.onSendButtonClick();

        verify(service).greetServer(Mockito.<Information> any(), Mockito.<AsyncCallback<String>> any());
    }

    @Test
    public void サーバへ情報を送信する_空文字列() {
        when(view.getTextToServer()).thenReturn("");
        presenter.onSendButtonClick();

        // エラー出力される
        verify(view).printError(anyString());
        // 要求は行われない
        verify(service, never()).greetServer(Mockito.<Information> any(), Mockito.<AsyncCallback<String>> any());
    }

    @Test
    public void サーバへ情報を送信する_文字数不足() {
        when(view.getTextToServer()).thenReturn("abc");
        presenter.onSendButtonClick();

        // エラー出力される
        verify(view).printError(anyString());
        // 要求は行われない
        verify(service, never()).greetServer(Mockito.<Information> any(), Mockito.<AsyncCallback<String>> any());
    }
}
