import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CodeGeneratorTest {

    @Test
    void generateCode_extension() {
        ClassBox first = new ClassBox("TestFirst", 0, 0);
        ClassBox second = new ClassBox("TestSecond", 10, 10);
        ClassBox third = new ClassBox("TestThird", 20, 20);
        ClassSource.getClassBoxes().add(first);
        ClassSource.getClassBoxes().add(second);
        ClassSource.getClassBoxes().add(third);

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
        ClassBox first = new ClassBox("TestFirst", 0, 0);
        ClassBox second = new ClassBox("TestSecond", 10, 10);
        ClassBox third = new ClassBox("TestThird", 20, 20);
        ClassSource.getClassBoxes().add(first);
        ClassSource.getClassBoxes().add(second);
        ClassSource.getClassBoxes().add(third);

        Line firstLine = new Line();
        Arrow firstArrow = new Arrow(firstLine);
        firstArrow.setFromClass(first);
        firstArrow.setToClass(second);

        Line secondLine = new Line();
        Arrow secondArrow = new Arrow(secondLine);
        secondArrow.setFromClass(first);
        secondArrow.setToClass(third);

        ClassSource.getConnections().add(firstArrow);
        ClassSource.getConnections().add(secondArrow);
        CodeGenerator codeGenerator = new CodeGenerator();

        System.out.println(codeGenerator.generateCode());
    }
}