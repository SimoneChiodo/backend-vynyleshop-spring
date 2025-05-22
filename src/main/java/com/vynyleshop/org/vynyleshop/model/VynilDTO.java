package com.vynyleshop.org.vynyleshop.model;

import java.time.Year;
import java.util.List;

// Adaptation of the Vynil class to be used as a Data Transfer Object (DTO)
// This class will be transferred to the frontend via REST API
public class VynilDTO {

    private Integer id;
    private String name;
    private String artistName;
    private Integer artistId;
    private Integer available;
    private List<String> sideone;
    private List<String> sidetwo;
    private List<String> images;
    private String genre;
    private String format;
    private String color;
    private String edition;
    private String code;
    private Year releaseYear;
    private String label;
    private String country;

    // Costructors
    
    public VynilDTO(Vynil v) {
        this.id = v.getId();
        this.name = v.getName();
        this.artistName = v.getArtist() != null ? v.getArtist().getName() : null;
        this.artistId = v.getArtist() != null ? v.getArtist().getId() : null;
        this.available = v.getAvailable();
        this.sideone = v.getSideoneAsList();
        this.sidetwo = v.getSidetwoAsList();
        this.images = v.getImages();
        this.genre = v.getGenre();
        this.format = v.getFormat();
        this.color = v.getColor();
        this.edition = v.getEdition();
        this.code = v.getCode();
        this.releaseYear = v.getReleaseYear();
        this.label = v.getLabel();
        this.country = v.getCountry();
    }
    
    public VynilDTO(Vynil v, List<String> images) {
        this.id = v.getId();
        this.name = v.getName();
        this.artistName = v.getArtist() != null ? v.getArtist().getName() : null;
        this.artistId = v.getArtist() != null ? v.getArtist().getId() : null;
        this.available = v.getAvailable();
        this.sideone = v.getSideoneAsList();
        this.sidetwo = v.getSidetwoAsList();
        this.images = images;
        this.genre = v.getGenre();
        this.format = v.getFormat();
        this.color = v.getColor();
        this.edition = v.getEdition();
        this.code = v.getCode();
        this.releaseYear = v.getReleaseYear();
        this.label = v.getLabel();
        this.country = v.getCountry();
    }
    
    public VynilDTO(VynilDTO v, List<String> images) {
        this.id = v.getId();
        this.name = v.getName();
        this.artistName = v.getArtistName();
        this.artistId = v.getArtistId();
        this.available = v.getAvailable();
        this.sideone = v.getSideone();
        this.sidetwo = v.getSidetwo();
        this.images = images;
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

    public List<String> getSideone() {
        return this.sideone;
    }

    public void setSideone(List<String> sideone) {
        this.sideone = sideone;
    }

    public List<String> getSidetwo() {
        return this.sidetwo;
    }

    public void setSidetwo(List<String> sidetwo) {
        this.sidetwo = sidetwo;
    }

    public List<String> getImages() {
        return this.images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public Integer getArtistId() {
        return this.artistId;
    }

    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
    }

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
