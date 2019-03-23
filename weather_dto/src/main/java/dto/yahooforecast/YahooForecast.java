package dto.yahooforecast;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import dto.yahooforecast.currobservation.CurrentObservationView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Результат запроса от Yahoo
 */
public class YahooForecast implements Serializable {
    /**
     * Данные о геоположении
     */
    private LocationView locationView;

    /**
     * Данные о текущей погоде
     */
    @JsonProperty("current_observation")
    private CurrentObservationView currObservationView;

    /**
     * Данные о прогнозе погоды на 10 дней
     */
    private List<DayCondition> forecasts;

    public YahooForecast() {

    }

    public YahooForecast(LocationView locationView, CurrentObservationView currObservationView, List<DayCondition> forecasts) {
        this.locationView = locationView;
        this.currObservationView = currObservationView;
        this.forecasts = forecasts;
    }

    public LocationView getLocationView() {
        return locationView;
    }

    public void setLocationView(LocationView locationView) {
        this.locationView = locationView;
    }

    public CurrentObservationView getCurrObservationView() {
        return currObservationView;
    }

    @JsonSetter("current_observation")
    public void setCurrObservationView(CurrentObservationView currObservationView) {
        this.currObservationView = currObservationView;
    }

    public List<DayCondition> getForecasts() {
        if (forecasts == null) {
            forecasts = new ArrayList<>();
        }
        return forecasts;
    }

    public void setForecasts(List<DayCondition> forecasts) {
        this.forecasts = forecasts;
        for (DayCondition forecast : this.forecasts) {
            forecast.setZonedDateTime();
        }
    }

    @Override
    public String toString() {
        return "YahooForecast{" +
                "locationView=" + locationView +
                ", currentObservation=" + currObservationView +
                ", forecasts=" + forecasts +
                '}';
    }

}

