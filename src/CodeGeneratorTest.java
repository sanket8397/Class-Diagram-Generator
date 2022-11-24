import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CodeGeneratorTest {

    @Test
    void generateCode_extension() {
        ClassSource classSource = ClassSource.getInstance();
        classSource.getConnections().clear();
        classSource.getClassBoxes().clear();
        System.out.println("-------------------- Extension --------------------\n");

        ClassBox first = new ClassBox("TestFirst", 0, 0);
        ClassBox second = new ClassBox("TestSecond", 10, 10);
        ClassBox third = new ClassBox("TestThird", 20, 20);
        classSource.getClassBoxes().add(first);
        classSource.getClassBoxes().add(second);
        classSource.getClassBoxes().add(third);

        Line line = new Line();
        Triangle inheritance = new Triangle(line);
        inheritance.setFromClass(first);
        inheritance.setToClass(second);

        Line secondLine = new Line();
        Triangle secondInheritance = new Triangle(secondLine);
        secondInheritance.setFromClass(first);
        secondInheritance.setToClass(third);

        ClassSource.getConnections().add(inheritance);
        ClassSource.getConnections().add(secondInheritance);
        CodeGenerator codeGenerator = new CodeGenerator();

        System.out.println(codeGenerator.generateCode());
    }

    @Test
    void generateCode_association() {
        ClassSource classSource = ClassSource.getInstance();
        classSource.getConnections().clear();
        classSource.getClassBoxes().clear();
        System.out.println("-------------------- Association --------------------\n");

        ClassBox first = new ClassBox("TestFirst", 0, 0);
        ClassBox second = new ClassBox("TestSecond", 10, 10);
        ClassBox third = new ClassBox("TestThird", 20, 20);
        classSource.getClassBoxes().add(first);
        classSource.getClassBoxes().add(second);
        classSource.getClassBoxes().add(third);

        Line firstLine = new Line();
        Arrow firstArrow = new Arrow(firstLine);
        firstArrow.setFromClass(first);
        firstArrow.setToClass(second);

        Line secondLine = new Line();
        Arrow secondArrow = new Arrow(secondLine);
        secondArrow.setFromClass(first);
        secondArrow.setToClass(third);

        classSource.getConnections().add(firstArrow);
        classSource.getConnections().add(secondArrow);
        CodeGenerator codeGenerator = new CodeGenerator();

        System.out.println(codeGenerator.generateCode());
    }

    @Test
    void generateCode_composition() {
        ClassSource classSource = ClassSource.getInstance();
        classSource.getConnections().clear();
        classSource.getClassBoxes().clear();
        System.out.println("-------------------- Composition --------------------\n");

        ClassBox first = new ClassBox("TestFirst", 0, 0);
        ClassBox second = new ClassBox("TestSecond", 10, 10);
        ClassBox third = new ClassBox("TestThird", 20, 20);
        classSource.getClassBoxes().add(first);
        classSource.getClassBoxes().add(second);
        classSource.getClassBoxes().add(third);

        Line firstLine = new Line();
        Diamond firstDiamond = new Diamond(firstLine);
        firstDiamond.setFromClass(first);
        firstDiamond.setToClass(second);

        Line secondLine = new Line();
        Diamond secondDiamond = new Diamond(secondLine);
        secondDiamond.setFromClass(first);
        secondDiamond.setToClass(third);

        classSource.getConnections().add(firstDiamond);
        classSource.getConnections().add(secondDiamond);
        CodeGenerator codeGenerator = new CodeGenerator();

        System.out.println(codeGenerator.generateCode());
    }

    @Test
    void generateCode_extension_association_composition() {
        ClassSource classSource = ClassSource.getInstance();
        classSource.getConnections().clear();
        classSource.getClassBoxes().clear();
        System.out.println("-------------------- All together --------------------\n");

        ClassBox first = new ClassBox("TestFirst", 0, 0);
        ClassBox second = new ClassBox("TestSecond", 10, 10);
        ClassBox third = new ClassBox("TestThird", 20, 20);
        classSource.getClassBoxes().add(first);
        classSource.getClassBoxes().add(second);
        classSource.getClassBoxes().add(third);

        Triangle inheritance = new Triangle(new Line());
        inheritance.setFromClass(first);
        inheritance.setToClass(second);

        Triangle secondInheritance = new Triangle(new Line());
        secondInheritance.setFromClass(first);
        secondInheritance.setToClass(third);

        Arrow firstArrow = new Arrow(new Line());
        firstArrow.setFromClass(first);
        firstArrow.setToClass(second);

        Arrow secondArrow = new Arrow(new Line());
        secondArrow.setFromClass(first);
        secondArrow.setToClass(third);

        Line firstLine = new Line();
        Diamond firstDiamond = new Diamond(firstLine);
        firstDiamond.setFromClass(first);
        firstDiamond.setToClass(second);

        Line secondLine = new Line();
        Diamond secondDiamond = new Diamond(secondLine);
        secondDiamond.setFromClass(first);
        secondDiamond.setToClass(third);

        classSource.getConnections().add(firstArrow);
        classSource.getConnections().add(secondArrow);
        classSource.getConnections().add(firstDiamond);
        classSource.getConnections().add(secondDiamond);
        classSource.getConnections().add(inheritance);
        classSource.getConnections().add(secondInheritance);
        CodeGenerator codeGenerator = new CodeGenerator();

        System.out.println(codeGenerator.generateCode());
    }
}