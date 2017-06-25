package com.anlee.movieapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Lê Đại An on 17-Jun-17.
 */

public class Trailer {
    @SerializedName("name")
    private String name;
    @SerializedName("size")
    private String size;
    @SerializedName("source")
    private String source;
    @SerializedName("type")
    private String type;

    public Trailer(String name, String size, String source, String type) {
        this.name = name;
        this.size = size;
        this.source = source;
        this.type = type;
    }
    public String getName() {
        return name;
    }

    public String getSize() {
        return size;
    }

    public String getSource() {
        return source;
    }

    public String getType() {
        return type;
    }
}
