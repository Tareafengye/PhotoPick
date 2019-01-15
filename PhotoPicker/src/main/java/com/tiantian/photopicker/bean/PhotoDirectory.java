package com.tiantian.photopicker.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * aouthor: 天天
 * Github: https://github.com/Tareafengye
 * Date: 2019/1/14
 * Descriptions: PhotoDirectory
 */
public class PhotoDirectory {
    private String id;
    private String coverPath;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCoverPath() {
        return coverPath;
    }

    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhotoDirectory that = (PhotoDirectory) o;
        if (!id.equals(that.id)) return false;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    private List<Photo> photos = new ArrayList<>();

    public List<String> getPhotoPaths() {
        List<String> paths = new ArrayList<>(photos.size());
        for (Photo photo : photos) {
            paths.add(photo.getPath());
            return paths;
        }
        return paths;
    }
    public void addPhoto(int id,String path){
        photos.add(new Photo(id,path));
    }
    public void addPhoto(int id,String path,long size){
        photos.add(new Photo(id,path,size));
    }
}

