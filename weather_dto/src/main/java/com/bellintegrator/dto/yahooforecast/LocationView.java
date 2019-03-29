package com.bellintegrator.dto.yahooforecast;

import com.bellintegrator.dto.yahooforecast.currobservation.CurrentObservationView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Геоданные о городе
 */
public class LocationView implements Serializable {

    private static final long serialVersionUID = 123456789012345603L;

    /**
     * Уникальный идентификатор
     */
    private Integer woeid;

    /**
     * Город
     */
    private String city;

    /**
     * Регион
     */
    private String region;

    /**
     * Страна
     */
    private String country;

    /**
     * Широта
     */
    @JsonProperty("lat")
    private Double latitude;

    /**
     * Долгота
     */
    @JsonProperty("long")
    private Double longitude;

    /**
     * Часовой пояс
     */
    @JsonProperty("timezone_id")
    private String timeZoneId;

    /**
     * Текущее состояние погоды
     */
    @JsonIgnore
    private List<CurrentObservationView> currObservationViews;

    /**
     * Прогноз погоды на 10 дней
     */
    @JsonIgnore
    private List<DayCondition> forecasts;

    public LocationView() {
    }

    public LocationView(Integer woeid, String city, String region, String country, Double latitude, Double longitude, String timeZoneId) {
        this.woeid = woeid;
        this.city = city;
        this.region = region;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timeZoneId = timeZoneId;
    }

    public Integer getWoeid() {
        return woeid;
    }

    public void setWoeid(Integer woeid) {
        this.woeid = woeid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getTimeZoneId() {
        return timeZoneId;
    }

    public void setTimeZoneId(String timeZoneId) {
        this.timeZoneId = timeZoneId;
    }

    public List<CurrentObservationView> getCurrObservationViews() {
        if (currObservationViews == null) {
            currObservationViews = new ArrayList<>();
        }
        return currObservationViews;
    }

    public void setCurrObservationViews(List<CurrentObservationView> currObservationViews) {
        this.currObservationViews = currObservationViews;
    }

    public void addCurrentObservations(CurrentObservationView currentObservationView) {
        getCurrObservationViews().add(currentObservationView);
        currentObservationView.setLocationView(this);
    }

    public void removeCurrentObservations(CurrentObservationView currentObservationView) {
        getCurrObservationViews().remove(currentObservationView);
        currentObservationView.setLocationView(null);
    }

    public List<DayCondition> getForecasts() {
        if (forecasts == null) {
            forecasts = new ArrayList<>();
        }
        return forecasts;
    }

    public void setForecasts(List<DayCondition> forecasts) {
        this.forecasts = forecasts;
    }

    public void addForecast(DayCondition forecast) {
        getForecasts().add(forecast);
        forecast.setLocationView(this);
    }

    public void removeForecast(DayCondition forecast) {
        getForecasts().remove(forecast);
        forecast.setLocationView(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationView locationView = (LocationView) o;
        return Objects.equals(woeid, locationView.woeid) &&
                Objects.equals(city, locationView.city) &&
                Objects.equals(region, locationView.region) &&
                Objects.equals(country, locationView.country) &&
                Objects.equals(latitude, locationView.latitude) &&
                Objects.equals(longitude, locationView.longitude) &&
                Objects.equals(timeZoneId, locationView.timeZoneId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(woeid, city, region, country, latitude, longitude, timeZoneId);
    }

    @Override
    public String toString() {
        return "LocationView{" +
                "woeid=" + woeid +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", country='" + country + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", timezone=" + timeZoneId +
                '}';
    }
}
