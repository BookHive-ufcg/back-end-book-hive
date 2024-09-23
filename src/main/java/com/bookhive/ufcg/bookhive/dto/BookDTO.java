package com.bookhive.ufcg.bookhive.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

public class BookDTO {

    private String ibsn;

    private float rating;

    public BookDTO(String ibsn, float rating) {
        this.ibsn = ibsn;
        this.rating = rating;
    }

    public String getIbsn() {
        return ibsn;
    }

    public float getRating() {
        return rating;
    }

    public void setIbsn(String ibsn) {
        this.ibsn = ibsn;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Book{" +
                "ibsn='" + ibsn + '\'' +
                ", rating=" + rating +
                '}';
    }
}
