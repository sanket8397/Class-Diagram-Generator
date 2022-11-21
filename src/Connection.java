public abstract class Connection {
    private String type;
    private ClassBox fromClass;
    private ClassBox toClass;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ClassBox getFromClass() {
        return fromClass;
    }

    public void setFromClass(ClassBox fromClass) {
        this.fromClass = fromClass;
    }

    public ClassBox getToClass() {
        return toClass;
    }

    public void setToClass(ClassBox toClass) {
        this.toClass = toClass;
    }
}
