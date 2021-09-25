package com.wufanfirstkotlin.materialdesign.bean;

/**
 * @author : wf
 * @date : 2021年09月23日 15:42
 */
public class CatBean {
    private String type;
    private int imageId;

    public CatBean(String type, int imageId) {
        this.type = type;
        this.imageId = imageId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}