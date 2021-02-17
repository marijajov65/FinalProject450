package controller;

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
        Clipboard.clearClipboard();
    }

    @Override
    public void redo() {
        for(IShape shape: redoClipboard){
            Clipboard.addToClipBoard(shape);
        }

    }
}
