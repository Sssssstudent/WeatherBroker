package com.bellintegrator.dto.yahooforecast.currobservation;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Objects;

/**
 * Текущие данные о характеристиках ветра
 */
public class WindView implements Serializable {

    private static final long serialVersionUID = 123456789012345609L;

    /**
     * Уникальный идентификатор
     */
    @JsonIgnore
    private Integer id;

    /**
     * Жёсткость погоды (wind chill), в градусах Цельсия
     */
    public String chill;

    /**
     * Направление ветра, в градусах
     */
    private String direction;

    /**
     * Скорость ветра, в км/ч
     */
    private String speed;

    /**
     * Текущий обзор погоды
     */
    @JsonBackReference
    private CurrentObservationView currentObservationView;

    public WindView() {
    }

    public WindView(String chill, String direction, String speed, CurrentObservationView currentObservationView) {
        this.chill = chill;
        this.direction = direction;
        this.speed = speed;
        this.currentObservationView = currentObservationView;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChill() {
        return chill;
    }

    public void setChill(String chill) {
        this.chill = chill;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
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
        WindView windView = (WindView) o;
        return Objects.equals(id, windView.id) &&
                Objects.equals(chill, windView.chill) &&
                Objects.equals(direction, windView.direction) &&
                Objects.equals(speed, windView.speed);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, chill, direction, speed);
    }

    @Override
    public String toString() {
        return "WindView{" +
                "id=" + id +
                ", chill=" + chill +
                ", direction=" + direction +
                ", speed=" + speed +
                '}';
    }
}
