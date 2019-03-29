package com.bellintegrator.dto.yahooforecast.currobservation;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Objects;

/**
 * Информация о текущих астрономических условиях
 */
public class AstronomyView implements Serializable {

    private static final long serialVersionUID = 123456789012345605L;

    /**
     * Уникальный идентификатор
     */
    @JsonIgnore
    private Integer id;

    /**
     * Время вохода
     */
    private String sunrise;

    /**
     * Время заката
     */
    private String sunset;

    /**
     * Текущий обзор погоды
     */
    @JsonBackReference
    private CurrentObservationView currentObservationView;

    public AstronomyView() {
    }

    public AstronomyView(String sunrise, String sunset, CurrentObservationView currentObservationView) {
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.currentObservationView = currentObservationView;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public CurrentObservationView getCurrentObservationView() {
        return currentObservationView;
    }

    public void setCurrentObservationView(CurrentObservationView currentObservationView) {
        this.currentObservationView = currentObservationView;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AstronomyView astronomyView = (AstronomyView) o;
        return Objects.equals(id, astronomyView.id) &&
                Objects.equals(sunrise, astronomyView.sunrise) &&
                Objects.equals(sunset, astronomyView.sunset);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, sunrise, sunset);
    }

    @Override
    public String toString() {
        return "AstronomyView{" +
                "id=" + id +
                ", sunrise='" + sunrise + '\'' +
                ", sunset='" + sunset + '\'' +
                '}';
    }
}
