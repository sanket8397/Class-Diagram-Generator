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

    @Override
    public void setSuccessor(RelationLoadHandler successor) {

    }
}
