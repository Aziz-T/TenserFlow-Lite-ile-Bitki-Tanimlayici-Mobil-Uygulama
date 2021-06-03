package org.tensorflow.lite.examples.classification;

public class FlowerClass {
    private String url;
    private String baslik;
    private String bilgi;

    public FlowerClass() {
    }

    public FlowerClass(String url, String baslik, String bilgi) {
        this.url = url;
        this.baslik = baslik;
        this.bilgi = bilgi;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public String getBilgi() {
        return bilgi;
    }

    public void setBilgi(String bilgi) {
        this.bilgi = bilgi;
    }
}
