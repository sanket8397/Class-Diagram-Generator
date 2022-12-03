package model;

import view.ClassBox;

/**
 * Interface used in chain of responsibility for loading a saved file
 * This is used to load with different types of relations
 */
public interface RelationLoadHandler {
    void loadConnection(String[] tokens, ClassBox fromClass, ClassBox toClass);
}