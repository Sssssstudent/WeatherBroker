package dto.yahooforecast;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * Погодные условия на день
 */
public class DayCondition implements Serializable {
    /**
     * Уникальный идентификатор
     */
    @JsonIgnore
    private Integer id;

    /**
     * День недели
     */
    private String day;

    /**
     * Дата в миллисекундах
     */
    private Integer date;

    /**
     * Дата
     */
    @JsonProperty("forecastDate")
    private ZonedDateTime zonedDateTime;

    /**
     * Минимальная температура воздуха для данного дня, в градусах Цельсия
     */
    private byte low;

    /**
     * Максимальная температура воздуха для данного дня, в градусах Цельсия
     */
    private byte high;

    /**
     * Текстовое описание состояния
     */
    private String text;

    /**
     * Код состояния
     */
    private Short code;

    /**
     * Местоположение, город
     */
    @JsonBackReference
    private LocationView locationView;

    public DayCondition() {
    }

    public DayCondition(String day, Integer date, byte low, byte high, String text, Short code) {
        this.day = day;
        this.date = date;
        this.low = low;
        this.high = high;
        this.text = text;
        this.code = code;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
    }

    public void setZonedDateTime(ZonedDateTime zonedDateTime) {
        this.zonedDateTime = zonedDateTime;
    }

    public void setZonedDateTime() {
        if (locationView != null) {
            this.zonedDateTime = ZonedDateTime.ofInstant(Instant.ofEpochMilli((long) date*1000), ZoneId.of(locationView.getTimeZoneId()));
        }
    }

    public byte getLow() {
        return low;
    }

    public void setLow(byte low) {
        this.low = low;
    }

    public byte getHigh() {
        return high;
    }

    public void setHigh(byte high) {
        this.high = high;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Short getCode() {
        return code;
    }

    public void setCode(Short code) {
        this.code = code;
    }

    public LocationView getLocationView() {
        return locationView;
    }

    public void setLocationView(LocationView locationView) {
        this.locationView = locationView;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DayCondition dayCondition = (DayCondition) o;
        return Objects.equals(id, dayCondition.id) &&
                Objects.equals(day, dayCondition.day) &&
                Objects.equals(date, dayCondition.date) &&
                Objects.equals(low, dayCondition.low) &&
                Objects.equals(high, dayCondition.high) &&
                Objects.equals(text, dayCondition.text) &&
                Objects.equals(code, dayCondition.code) &&
                Objects.equals(locationView, dayCondition.locationView);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, day, date, low, high, text, code, locationView);
    }

    @Override
    public String toString() {
        return "DayCondition{" +
                "id=" + id +
                ", day='" + day + '\'' +
                ", zonedDateTime=" + zonedDateTime +
                ", date=" + date +
                ", low=" + low +
                ", high=" + high +
                ", text='" + text + '\'' +
                ", code=" + code +
                '}';
    }
}
