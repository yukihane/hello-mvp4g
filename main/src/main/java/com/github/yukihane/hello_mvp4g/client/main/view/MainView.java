package com.github.yukihane.hello_mvp4g.client.main.view;

import com.github.yukihane.hello_mvp4g.client.Messages;
import com.github.yukihane.hello_mvp4g.client.main.presenter.IMainPresenter;
import com.github.yukihane.hello_mvp4g.client.main.presenter.IMainView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class MainView extends ResizeComposite implements IMainView {

    /**
     * The message displayed to the user when the server cannot be reached or
     * returns an error.
     */
    private static final String SERVER_ERROR = "An error occurred while "
            + "attempting to contact the server. Please check your network " + "connection and try again.";

    private final Messages messages = GWT.create(Messages.class);

    private FlowLayoutPanel container;

    private TextBox nameField;

    private IMainPresenter presenter;

    private Label errorLabel;

    private Button sendButton;

    private Label textToServerLabel;

    private HTML serverResponseLabel;

    private DialogBox dialogBox;

    private Button closeButton;

    public MainView() {
        createView();
        initWidget(container);
    }

    private void createView() {
        container = new FlowLayoutPanel();
        FlowPanel panel = new FlowPanel();
        panel.setSize("500px", "500px");
        container.add(panel);

        sendButton = new Button(messages.sendButton());
        nameField = new TextBox();
        nameField.setText(messages.nameField());
        errorLabel = new Label();

        // We can add style names to widgets
        sendButton.addStyleName("sendButton");

        // Add the nameField and sendButton to the RootPanel
        // Use RootPanel.get() to get the entire body element
        panel.add(nameField);
        panel.add(sendButton);
        panel.add(errorLabel);

        // Create the popup dialog box
        dialogBox = new DialogBox();
        dialogBox.setText("Remote Procedure Call");
        dialogBox.setAnimationEnabled(true);
        closeButton = new Button("Close");
        // We can set the id of a widget by accessing its Element
        closeButton.getElement().setId("closeButton");
        textToServerLabel = new Label();
        serverResponseLabel = new HTML();
        VerticalPanel dialogVPanel = new VerticalPanel();
        dialogVPanel.addStyleName("dialogVPanel");
        dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
        dialogVPanel.add(textToServerLabel);
        dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
        dialogVPanel.add(serverResponseLabel);
        dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
        dialogVPanel.add(closeButton);
        dialogBox.setWidget(dialogVPanel);

        // Add a handler to close the DialogBox
        closeButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                dialogBox.hide();
                sendButton.setEnabled(true);
                sendButton.setFocus(true);
            }
        });

        // Create a handler for the sendButton and nameField
        class MyHandler implements ClickHandler, KeyUpHandler {
            /**
             * Fired when the user clicks on the sendButton.
             */
            public void onClick(ClickEvent event) {
                getPresenter().onSendButtonClick();
            }

            /**
             * Fired when the user types in the nameField.
             */
            public void onKeyUp(KeyUpEvent event) {
                if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
                    getPresenter().onSendButtonClick();
                }
            }
        }

        // Add a handler to send the name to the server
        MyHandler handler = new MyHandler();
        sendButton.addClickHandler(handler);
        nameField.addKeyUpHandler(handler);
    }

    @Override
    public void setPresenter(IMainPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public IMainPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void selectNameField() {
        // Focus the cursor on the name field when the app loads
        nameField.setFocus(true);
        nameField.selectAll();
    }

    @Override
    public void clearError() {
        errorLabel.setText("");
    }

    @Override
    public String getTextToServer() {
        return nameField.getText();
    }

    @Override
    public void printError(String text) {
        errorLabel.setText(text);
    }

    @Override
    public void setSendButtonEnabled(boolean b) {
        sendButton.setEnabled(b);
    }

    @Override
    public void setResponseText(String string) {
        serverResponseLabel.setText("");
    }

    @Override
    public void setTextToServer(String textToServer) {
        textToServerLabel.setText(textToServer);
    }

    @Override
    public void showErrorDialog() {
        dialogBox.setText("Remote Procedure Call - Failure");
        serverResponseLabel.addStyleName("serverResponseLabelError");
        serverResponseLabel.setHTML(SERVER_ERROR);
        dialogBox.center();
        closeButton.setFocus(true);
    }

    @Override
    public void showSuccessDialog(String result) {
        dialogBox.setText("Remote Procedure Call");
        serverResponseLabel.removeStyleName("serverResponseLabelError");
        serverResponseLabel.setHTML(result);
        dialogBox.center();
        closeButton.setFocus(true);
    }
}
