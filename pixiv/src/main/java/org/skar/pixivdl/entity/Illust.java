package org.skar.pixivdl.entity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Illust {
    // image_urls:square_medium
    String imageUrlThumb;

    // check meta_single_page:original_image_url if empty check meta_pages:0:image_urls:original
    List<String> imageUrlOriginal;

    // title
    String title;

    // total_bookmarks
    long totalBookmarks;

    // total_view
    long totalViews;

    // user:name
    String authorName;

    // user:id
    long authorId;

    // user:account
    String authorAccount;

    public static Illust parseIllust(JSONObject jObject) {
        Illust img = new Illust();
        img.setImageUrlThumb(
                jObject.getJSONObject("image_urls")
                        .getString("medium"));

        ArrayList<String> originalImageUrls = new ArrayList<>();
        if (jObject.getLong("page_count") == 1) {
            originalImageUrls.add(jObject
                    .getJSONObject("meta_single_page")
                    .getString("original_image_url"));
        } else {
            JSONArray images = jObject.getJSONArray("meta_pages");
            for (int i = 0; i < images.length(); i++) {
                originalImageUrls.add(images
                        .getJSONObject(i)
                        .getJSONObject("image_urls")
                        .getString("original"));
            }
        }

        img.setImageUrlOriginal(originalImageUrls);

        img.setTitle(jObject.getString("title"));
        img.setTotalBookmarks(jObject.getLong("total_bookmarks"));
        img.setTotalViews(jObject.getLong("total_view"));
        img.setAuthorName(jObject.getJSONObject("user").getString("name"));
        img.setAuthorId(jObject.getJSONObject("user").getLong("id"));
        img.setAuthorAccount(jObject.getJSONObject("user").getString("account"));

        return img;
    }


    public String getImageUrlThumb() {
        return imageUrlThumb;
    }

    public List<String> getImageUrlOriginal() {
        return imageUrlOriginal;
    }

    public String getTitle() {
        return title;
    }

    public long getTotalBookmarks() {
        return totalBookmarks;
    }

    public long getTotalViews() {
        return totalViews;
    }

    public String getAuthorName() {
        return authorName;
    }

    public long getAuthorId() {
        return authorId;
    }

    public String getAuthorAccount() {
        return authorAccount;
    }

    public void setImageUrlThumb(String imageUrlThumb) {
        this.imageUrlThumb = imageUrlThumb;
    }

    public void setImageUrlOriginal(List<String> imageUrlOriginals) {
        this.imageUrlOriginal = imageUrlOriginals;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTotalBookmarks(long totalBookmarks) {
        this.totalBookmarks = totalBookmarks;
    }

    public void setTotalViews(long totalViews) {
        this.totalViews = totalViews;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public void setAuthorAccount(String authorAccount) {
        this.authorAccount = authorAccount;
    }
}
