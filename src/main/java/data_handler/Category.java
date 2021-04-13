package data_handler;

public class Category {
    private String name;
    private String fillColor;
    private String strokeColor;
    private String textcolor;
    private String police;
    private int level;
    private Image icon;
    private String[] extendsFrom;

    public String getName() {
        return this.name;
    }

    public String getFillColor() {
        return this.fillColor;
    }

    public String getStrokecolor() {
        return this.strokeColor;
    }

    public String getTextColor() {
        return this.textcolor;
    }

    public String getPolice() {
        return this.police;
    }

    public int getLevel() {
        return this.level;
    }

    public Image getIcon() {
        return this.icon;
    }

    public String[] getExtends() {
        return this.extendsFrom;
    }
}