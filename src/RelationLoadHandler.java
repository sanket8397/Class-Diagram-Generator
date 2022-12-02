public interface RelationLoadHandler {
    void loadConnection(String[] tokens, ClassBox fromClass, ClassBox toClass);

    void setSuccessor(RelationLoadHandler successor);
}
