package controller.commands;

import controller.Clipboard;
import controller.commands.CommandHistory;
import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.interfaces.IUndoable;

import java.io.IOException;
import java.util.ArrayList;

public class CopyCommand implements ICommand, IUndoable {
    ArrayList<IShape> redoClipboard = new ArrayList<>();

    @Override
    public void run() throws IOException {
        CommandHistory.copy();
    }

    @Override
    public void undo() {
        Clipboard.getInstance().clearClipboard();
    }

    @Override
    public void redo(){
        for(IShape shape: redoClipboard){
            try {
                Clipboard.getInstance().addToClipBoard(shape);
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }

    }
}
