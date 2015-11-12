package com.github.yukihane.hello_mvp4g.client.main.presenter;

import com.mvp4g.client.view.ReverseViewInterface;

public interface IMainView extends ReverseViewInterface<IMainPresenter> {

    /**
     * 名前フィールドを選択状態にする.
     */
    void selectNameField();

    /**
     * エラー表示をクリアする.
     */
    void clearError();

    /**
     * エラーを表示する
     */
    void printError(String errorText);

    /**
     * 送信するテキスト(名前)を取得する.
     */
    String getTextToServer();

    /**
     * 送信するテキストを表示する.
     */
    void setTextToServer(String text);

    /**
     * サーバから受信した応答テキストを表示する.
     */
    void setResponseText(String text);

    /**
     * 送信ボタン押下可能状態を設定する.
     */
    void setSendButtonEnabled(boolean b);

    /**
     * 送信成功ダイアログを表示する.
     */
    void showSuccessDialog(String result);

    /**
     * 送信エラーダイアログを表示する.
     */
    void showErrorDialog();

}
