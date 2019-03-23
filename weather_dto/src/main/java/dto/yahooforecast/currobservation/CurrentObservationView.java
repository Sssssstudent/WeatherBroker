package dto.yahooforecast.currobservation;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import dto.yahooforecast.LocationView;

import java.io.Serializable;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * Текущий обзор погоды
 */
public class CurrentObservationView implements Serializable {
    /**
     * Уникальный идентификатор
     */
    @JsonIgnore
    private Integer id;

    /**
     * Текущие данные о ветре
     */
    private WindView windView;

    /**
     * Информация о текущем атмосферном давлении, влажности и видимости
     */
    private AtmosphereView atmosphereView;

    /**
     * Информация о текущих астрономических условиях
     */
    private AstronomyView astronomyView;

    /**
     * Текущее состояние погоды
     */
    private ConditionView conditionView;

    /**
     * Дата и время публикации этого прогноза в миллисекундах
     */
    private Integer pubDate;

    /**
     * Дата и время публикации этого прогноза
     */
    private ZonedDateTime date;

    /**
     * Местоположение, город
     */
    @JsonBackReference
    private LocationView locationView;

    public CurrentObservationView() {
    }

    public CurrentObservationView(Integer pubDate, LocationView locationView) {
        this.pubDate = pubDate;
        this.locationView = locationView;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public WindView getWindView() {
        return windView;
    }

    public void setWindView(WindView windView) {
        this.windView = windView;
    }

    public AtmosphereView getAtmosphereView() {
        return atmosphereView;
    }

    public void setAtmosphereView(AtmosphereView atmosphereView) {
        this.atmosphereView = atmosphereView;
    }

    public AstronomyView getAstronomyView() {
        return astronomyView;
    }

    public void setAstronomyView(AstronomyView astronomyView) {
        this.astronomyView = astronomyView;
    }

    public ConditionView getConditionView() {
        return conditionView;
    }

    public void setConditionView(ConditionView conditionView) {
        this.conditionView = conditionView;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate() {
        if (locationView != null) {
            this.date = ZonedDateTime.ofInstant(Instant.ofEpochMilli((long) pubDate*1000), ZoneId.of(locationView.getTimeZoneId()));
        }
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public Integer getPubDate() {
        return pubDate;
    }

    public void setPubDate(Integer pubDate) {
        this.pubDate = pubDate;
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
        CurrentObservationView that = (CurrentObservationView) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(windView, that.windView) &&
                Objects.equals(atmosphereView, that.atmosphereView) &&
                Objects.equals(astronomyView, that.astronomyView) &&
                Objects.equals(conditionView, that.conditionView) &&
                Objects.equals(pubDate, that.pubDate) &&
                Objects.equals(locationView, that.locationView);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, windView, atmosphereView, astronomyView, conditionView, pubDate, locationView);
    }

    @Override
    public String toString() {
        return "CurrentObservationView{" +
                "id=" + id +
                ", windView=" + windView +
                ", atmosphereView=" + atmosphereView +
                ", astronomyView=" + astronomyView +
                ", conditionView=" + conditionView +
                ", date=" + date +
                ", pubDate=" + pubDate +
                '}';
    }
}

