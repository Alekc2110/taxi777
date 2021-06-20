package my.fin.project.model.entity.pojo;

public class Duration {
    private String value;
    private String text;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Duration{" +
                "value='" + value + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
