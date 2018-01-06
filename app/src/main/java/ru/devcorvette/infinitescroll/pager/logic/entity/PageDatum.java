package ru.devcorvette.infinitescroll.pager.logic.entity;

/**
 * Класс для более удобного доступа к данным страницы.
 */
public class PageDatum {

    String[] imagesURL;

    String[] strings;

    public PageDatum(String[] imagesURL, String[] strings) {
        this.imagesURL = imagesURL;
        this.strings = strings;
    }

    public String[] getImagesURL() {
        return imagesURL;
    }

    public String[] getStrings() {
        return strings;
    }
}
