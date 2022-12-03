package model;

import view.ClassBox;

import java.util.Objects;

/**
 * Part of chain of responsibility for loading a saved file
 * This class is responsible for loading triangle - inheritance relation
 * This has a successor to deal with other relations
 */
public class LoadHandlerTriangle implements  RelationLoadHandler{
    private RelationLoadHandler successor;

    @Override
    public void loadConnection(String[] tokens, ClassBox fromClass,
                               ClassBox toClass) {
        if (Objects.equals(tokens[1], "2")){
            Line lineConn = new Line();
            ClassSource classSource = ClassSource.getInstance();

            Triangle triangle = new Triangle(lineConn);
            triangle.setFromClass(fromClass);
            triangle.setToClass(toClass);
            classSource.addConnection(triangle);
        } else {
            successor.loadConnection(tokens, fromClass, toClass);
        }
    }

    public void setSuccessor(RelationLoadHandler successor) {
        this.successor = successor;
    }
}