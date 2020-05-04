package com.papalam.help.model;

import android.graphics.drawable.Drawable;

public class Contact {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    String description;
    String icon;

    public Contact(String name, String description, String icon) {
        this.name = name;
        this.description = description;
        this.icon = icon;
    }

}