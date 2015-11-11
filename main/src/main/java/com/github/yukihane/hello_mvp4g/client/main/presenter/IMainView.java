package com.github.yukihane.hello_mvp4g.client.main.presenter;

import com.mvp4g.client.view.ReverseViewInterface;

public interface IMainView extends ReverseViewInterface<IMainPresenter> {

    void selectNameField();

    void clearError();

    String getTextToServer();

    void printError(String string);

    void setSendButtonEnabled(boolean b);

    void setResponseText(String string);

    void setTextToServer(String textToServer);

    void showErrorDialog();

    void showSuccessDialog(String result);

}
