package infrastructure.widgets.bean;

import java.io.Serializable;
import java.util.ArrayList;


public class NewsItem implements Serializable {
    private int i_id;

    private int channelId;

    private int id;


    private String title;


    private String content;

    private int type;

    private ArrayList<String> images;

    private String icon;

    private String url;

    private String date;

    private String source;

    private String media;


    private String relPhotos;

    private boolean isTopic = false;

    private boolean isStar = false;

    private ArrayList<NewsItem> newsItems;

    private int parentChannelType;


    public int getI_id() {
        return i_id;
    }

    public void setI_id(int i_id) {
        this.i_id = i_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    public ArrayList<String> getImgs() {
        return images;
    }

    public void setImgs(ArrayList<String> imgs) {
        this.images = imgs;
    }
//    public ArrayList<TRSImage> getImg() {
//        return imgs;
//    }
//
//    public void setImg(ArrayList<TRSImage> imgs) {
//        this.imgs = imgs;
//    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTime() {
        return date;
    }

    public void setTime(String date) {
        this.date = date;
    }

    public String getSummary() {
        return source;
    }

    public void setSummary(String summary) {
        this.source = summary;
    }

    public boolean isTopic() {
        return isTopic;
    }

    public void setTopic(boolean topic) {
        isTopic = topic;
    }

//    public ArrayList<TRSVideo> getVideo() {
//        return video;
//    }
//
//    public void setVideo(ArrayList<TRSVideo> video) {
//        this.video = video;
//    }

    public boolean isStar() {
        return isStar;
    }

    public void setStar(boolean star) {
        isStar = star;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

//    public String getImages() {
//        return images;
//    }
//
//    public void setImages(String images) {
//        this.images = images;
//    }

    public String getRelphotos() {
        return relPhotos;
    }

    public void setRelphotos(String relphotos) {
        relPhotos = relphotos;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRelPhotos() {
        return relPhotos;
    }

    public void setRelPhotos(String relPhotos) {
        this.relPhotos = relPhotos;
    }

    public ArrayList<NewsItem> getNewsItems() {
        return newsItems;
    }

    public void setNewsItems(ArrayList<NewsItem> newsItems) {
        this.newsItems = newsItems;
    }

    public int getParentChannelType() {
        return parentChannelType;
    }

    public void setParentChannelType(int parentChannelType) {
        this.parentChannelType = parentChannelType;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

}
