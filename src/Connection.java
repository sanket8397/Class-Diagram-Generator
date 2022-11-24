import java.awt.*;

public abstract class Connection {
    private int type;
    private ClassBox fromClass;
    private ClassBox toClass;

    public int getType() {
        return type;
    }

    public void setType(int type) {
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
