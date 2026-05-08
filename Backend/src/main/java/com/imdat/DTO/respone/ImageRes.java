package com.imdat.DTO.respone;

import jakarta.validation.constraints.NotNull;

public class ImageRes {
    @NotNull
    private Integer id;

    @NotNull
    private String url;

    @NotNull
    private String publicId;

    public ImageRes(Integer id ,String url, String publicId) {
        this.id = id;
        this.url = url;
        this.publicId = publicId;
    }

    public ImageRes() {
    }

    public String getUrl() {
        return url;
    }

    public String getPublicId() {
        return publicId;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }
}
