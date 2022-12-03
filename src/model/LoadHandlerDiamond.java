package model;

import view.ClassBox;

/**
 * Part of chain of responsibility for loading a saved file
 * This class is responsible for loading diamond - composition relation
 * This does not have a successor, it is a tail of the chain
 */
public class LoadHandlerDiamond implements RelationLoadHandler{
    @Override
    public void loadConnection(String[] tokens, ClassBox fromClass,
                               ClassBox toClass) {
        Line lineConn = new Line();
        ClassSource classSource = ClassSource.getInstance();
        Diamond diamond = new Diamond(lineConn);
        diamond.setFromClass(fromClass);
        diamond.setToClass(toClass);
        classSource.addConnection(diamond);
    }
}