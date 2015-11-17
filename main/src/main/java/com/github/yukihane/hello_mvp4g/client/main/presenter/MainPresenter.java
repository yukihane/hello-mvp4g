package com.github.yukihane.hello_mvp4g.client.main.presenter;

import com.github.yukihane.hello_mvp4g.client.GreetingService;
import com.github.yukihane.hello_mvp4g.client.GreetingServiceAsync;
import com.github.yukihane.hello_mvp4g.client.main.MainEventBus;
import com.github.yukihane.hello_mvp4g.client.main.view.MainView;
import com.github.yukihane.hello_mvp4g.shared.FieldVerifier;
import com.github.yukihane.hello_mvp4g.shared.Information;
import com.github.yukihane.hello_mvp4g.shared.Timing;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;

@Presenter(view = MainView.class)
public class MainPresenter extends BasePresenter<IMainView, MainEventBus>implements IMainPresenter {

    /**
     * Create a remote service proxy to talk to the server-side Greeting
     * service.
     */
    GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

    public void onStart() {
        getView().selectNameField();
    }

    /**
     * Send the name from the nameField to the server and wait for a response.
     */
    @Override
    public void onSendButtonClick() {
        // First, we validate the input.
        getView().clearError();
        String textToServer = getView().getTextToServer();

        if (!FieldVerifier.isValidName(textToServer)) {
            getView().printError("Please enter at least four characters");
            return;
        }

        // Then, we send the input to the server.
        getView().setSendButtonEnabled(false);
        getView().setTextToServer(textToServer);
        getView().setResponseText("");
        Information info = new Information();
        info.setName(textToServer);
        info.setTiming(Timing.MORNING);
        greetingService.greetServer(info, new AsyncCallback<String>() {
            public void onFailure(Throwable caught) {
                // Show the RPC error message to the user
                getView().showErrorDialog();
            }

            public void onSuccess(String result) {
                getView().showSuccessDialog(result);
            }
        });
    }
}
