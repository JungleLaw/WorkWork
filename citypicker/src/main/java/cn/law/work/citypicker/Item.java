package cn.law.work.citypicker;

/**
 * Created by IntelliJ IDEA.
 * User: Jungle Law
 * Date: 2018/12/16
 * Time: 18:31
 */
public class Item {
    private String name = "";
    private int code;
    private boolean isSelected;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
