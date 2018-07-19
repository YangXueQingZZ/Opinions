package com.taowei.opinions.data;

import android.content.Intent;
import android.graphics.drawable.Drawable;

public class MainBean {

    public Drawable imageId;

    public String titleId;

    public Intent intent;

    public String packageName;

    public MainBean(Drawable imageId, String titleId, String packageName) {
        this.imageId = imageId;
        this.titleId = titleId;
        this.packageName = packageName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Drawable getImageId() {
        return imageId;
    }

    public void setImageId(Drawable imageId) {
        this.imageId = imageId;
    }

    public String getTitleId() {
        return titleId;
    }

    public void setTitleId(String titleId) {
        this.titleId = titleId;
    }
}
