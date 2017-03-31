package com.suitschool.screeningtest;

/**
 * Created by amal on 3/31/2017.
 */

class Event {
    private String judul;
    private String tanggal;
    private String image;

    Event(String title, String tanggal, String image) {
        this.judul = title;
        this.tanggal = tanggal;
        this.image = image;
    }

    String getJudul() {
        return judul;
    }

    String getTanggal() {
        return tanggal;
    }

    String getImage() {
        return image;
    }
}
