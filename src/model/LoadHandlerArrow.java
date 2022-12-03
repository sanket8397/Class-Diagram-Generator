package model;

import view.ClassBox;

/**
 * Part of chain of responsibility for loading a saved file
 * This class deals with loading arrows - association relation
 * This has a successor to deal with other relations
 */
public class LoadHandlerArrow implements RelationLoadHandler{
    private RelationLoadHandler successor;

    public void setSuccessor(RelationLoadHandler successor){
        this.successor = successor;
    }

    @Override
    public void loadConnection(String[] tokens, ClassBox fromClass, ClassBox toClass) {
        ClassSource source = ClassSource.getInstance();
        if (tokens[1].equals("1")){
            Line lineConn = new Line();
            Arrow arrow = new Arrow(lineConn);
            arrow.setFromClass(fromClass);
            arrow.setToClass(toClass);
            source.addConnection(arrow);
        } else {
            successor.loadConnection(tokens, fromClass, toClass);
        }
    }
}