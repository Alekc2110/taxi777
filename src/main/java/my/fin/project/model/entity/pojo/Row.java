package my.fin.project.model.entity.pojo;

import java.util.Arrays;

public class Row {
    private Element[] elements;

    public Element[] getElements() {
        return elements;
    }

    public void setElements(Element[] elements) {
        this.elements = elements;
    }

    @Override
    public String toString() {
        return "Row{" +
                "elements=" + Arrays.toString(elements) +
                '}';
    }
}
