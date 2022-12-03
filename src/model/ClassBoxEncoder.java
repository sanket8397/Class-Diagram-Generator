package model;

import view.ClassBox;

import java.util.List;

/**
 * Encoder to encode class box data from class source
 */
public class ClassBoxEncoder implements Encoder{
    @Override
    public String encode() {
        List<ClassBox> classBoxes = ClassSource.getInstance().getClassBoxes();
        StringBuilder classesString = new StringBuilder();

        for (ClassBox classbox: classBoxes){
            String currBoxString = "0,";
            currBoxString += classbox.getClassName();
            currBoxString += ",";

            currBoxString += classbox.getRectangle().x;
            currBoxString += ",";

            currBoxString += classbox.getRectangle().y;
            currBoxString += ",";

            currBoxString += classbox.getRectangle().height;
            currBoxString += ",";

            currBoxString += classbox.getRectangle().width;
            currBoxString += "\n";

            classesString.append(currBoxString);
        }

        return classesString.toString();
    }
}
