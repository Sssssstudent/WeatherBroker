package com.bellintegrator.dto.yahooforecast.currobservation;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Objects;

/**
 * Данные о текущем атмосферном давлении, влажности и видимости
 */
public class AtmosphereView implements Serializable {

    private static final long serialVersionUID = 123456789012345606L;

    /**
     * Уникальный идентификатор
     */
    @JsonIgnore
    private Integer id;

    /**
     * Влажность, в процентах
     */
    private String humidity;

    /**
     * Видимость, в километрах
     */
    private String visibility;
    /**
     * Давление, в мбар
     */
    private String pressure;

    /**
     * Состояние барометрического давления: устойчивое (0), повышение (1) или падение (2)
     */
    private String rising;

    /**
     * Текущий обзор погоды
     */
    @JsonBackReference
    private CurrentObservationView currentObservationView;

    public AtmosphereView() {
    }

    public AtmosphereView(String humidity, String visibility, String pressure, String rising, CurrentObservationView currentObservationView) {
        this.humidity = humidity;
        this.visibility = visibility;
        this.pressure = pressure;
        this.rising = rising;
        this.currentObservationView = currentObservationView;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getRising() {
        return rising;
    }

    public void setRising(String rising) {
        this.rising = rising;
    }

    public void setCurrentObservationView(CurrentObservationView currentObservationView) {
        this.currentObservationView = currentObservationView;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AtmosphereView that = (AtmosphereView) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(humidity, that.humidity) &&
                Objects.equals(visibility, that.visibility) &&
                Objects.equals(pressure, that.pressure) &&
                Objects.equals(rising, that.rising);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, humidity, visibility, pressure, rising);
    }

    @Override
    public String toString() {
        return "AtmosphereView{" +
                "id=" + id +
                ", humidity=" + humidity +
                ", visibility=" + visibility +
                ", pressure=" + pressure +
                ", rising=" + rising +
                '}';
    }
}

