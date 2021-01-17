package model.dialogs;

import model.MouseMode;
import model.interfaces.IApplicationState;
import view.interfaces.IDialogChoice;

public class ChooseStartAndEndPointModeDialog implements IDialogChoice<MouseMode> {
    private final IApplicationState applicationState;

    public ChooseStartAndEndPointModeDialog(IApplicationState applicationState) {

        this.applicationState = applicationState;
    }

    @Override
    public String getDialogTitle() {
        return "Start and End Point Mode";
    }

    @Override
    public String getDialogText() {
        return "Select a shading type from the menu below:";
    }

    @Override
    public MouseMode[] getDialogOptions() {
        return MouseMode.values();
    }

    @Override
    public MouseMode getCurrentSelection() {
        return applicationState.getActiveMouseMode();
    }
}
