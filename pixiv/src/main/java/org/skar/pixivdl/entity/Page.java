package org.skar.pixivdl.entity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Page {
    // illusts
    List<Image> illusts;

    // next_url
    String nextUrl;

    public Page() {

    }

    public List<Image> getIllusts() {
        return illusts;
    }

    public void setIllusts(List<Image> illusts) {
        this.illusts = illusts;
    }

    public String getNextUrl() {
        return nextUrl;
    }

    public void setNextUrl(String nextUrl) {
        this.nextUrl = nextUrl;
    }

    static public Page parsePage(JSONObject obj) {
        Page page = new Page();
        JSONArray illustsJSON = obj.getJSONArray("illusts");
        ArrayList illusts = new ArrayList(illustsJSON.length());
        for(int i = 0; i < illustsJSON.length(); i++) {
            illusts.add(i, Image.parseImage(illustsJSON.getJSONObject(i)));
        }
        page.setIllusts(illusts);
        page.setNextUrl(obj.getString("next_url"));

        return page;
    }
}
