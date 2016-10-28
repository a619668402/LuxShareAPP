package com.luxshare.recylerviewadapter;

/**
 * Created by Administrator on 2016/9/30.
 */
public class News {

    public static final int ITEM_TYPE_TEXT = 0;
    public static final int ITEM_TYPE_IMAGE = 1;
    public static final int ITEM_TYPE_BUTTON = 2;

    private String text;

    private int image;

    private String button;

    private int itemType;

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
