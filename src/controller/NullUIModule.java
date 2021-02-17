package controller;

import view.EventName;
import view.interfaces.IDialogChoice;
import view.interfaces.IEventCallback;
import view.interfaces.IUiModule;

public class NullUIModule implements IUiModule {
    @Override
    public void addEvent(EventName eventName, IEventCallback command) {

    }

    @Override
    public <T> T getDialogResponse(IDialogChoice dialogChoice) {
        return null;
    }
}
