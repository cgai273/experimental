package org.skar.pixivdl.models;

import javafx.beans.property.*;
import org.skar.pixivdl.entity.Page;

import java.util.ArrayList;
import java.util.List;

public class PageStore {
    private static final int INIT_PAGE_INDEX = -1;
    private List<Page> pages = new ArrayList<>();
    private IntegerProperty pageIndex = new SimpleIntegerProperty(INIT_PAGE_INDEX);

    public IntegerProperty getCurrentPageIndex() { return pageIndex; }

    public Page getCurrentPage() {
        if (pageIndex.getValue() < 0 || pageIndex.getValue() >= pages.size()) {
            return null;
        }

        return pages.get(pageIndex.getValue());
    }

    public void addPage(Page newPage) {
        pages.add(newPage);
    }

    public void reset() {
        pages = new ArrayList<>();
        pageIndex.setValue(INIT_PAGE_INDEX);
    }

    public Page next() {
        if (pageIndex.getValue() < pages.size() - 1) {
            pageIndex.setValue(pageIndex.getValue() + 1);

            return pages.get(pageIndex.getValue());
        }

        return null;
    }

    public Page previous() {
        if (pageIndex.getValue() > 0) {
            pageIndex.setValue(pageIndex.getValue() - 1);

            return pages.get(pageIndex.getValue());
        }

        return null;
    }

    public void init(Page p) {
        reset();
        addPage(p);
        next();
    }
}
