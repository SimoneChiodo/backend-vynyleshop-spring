package com.vynyleshop.org.vynyleshop.model;

import java.time.Year;

// Adaptation of the Vynil class to be used as a Data Transfer Object (DTO)
// This class will be transferred to the frontend via REST API
public class VynilDTO {

    private Integer id;
    private String name;
    private String artistName;
    private Integer available;
    private String tracklist;
    private String genre;
    private String format;
    private String color;
    private String edition;
    private String code;
    private Year releaseYear;
    private String label;
    private String country;

    // Costruttore che riceve direttamente un oggetto Vynil
    public VynilDTO(Vynil v) {
        this.id = v.getId();
        this.name = v.getName();
        this.artistName = v.getArtist() != null ? v.getArtist().getName() : null;
        this.available = v.getAvailable();
        this.tracklist = v.getTracklist();
        this.genre = v.getGenre();
        this.format = v.getFormat();
        this.color = v.getColor();
        this.edition = v.getEdition();
        this.code = v.getCode();
        this.releaseYear = v.getReleaseYear();
        this.label = v.getLabel();
        this.country = v.getCountry();
    }

    // Getter e Setter

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public String getTracklist() {
        return tracklist;
    }

    public void setTracklist(String tracklist) {
        this.tracklist = tracklist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Year getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Year releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
