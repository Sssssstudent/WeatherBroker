package com.bellintegrator.service;

import com.bellintegrator.dao.astronomy.AstronomyDao;
import com.bellintegrator.dao.atmosphere.AtmosphereDao;
import com.bellintegrator.dao.condition.ConditionDao;
import com.bellintegrator.dao.currentobservation.CurrentObservationDao;
import com.bellintegrator.dao.forecast.ForecastDao;
import com.bellintegrator.dao.location.LocationDao;
import com.bellintegrator.dao.wind.WindDao;
import com.bellintegrator.model.*;
import com.caucho.hessian.server.HessianServlet;
import dto.yahooforecast.DayCondition;
import dto.yahooforecast.LocationView;
import dto.yahooforecast.YahooForecast;
import dto.yahooforecast.currobservation.*;
import dto.yahooforecast.currobservation.CurrentObservationView;
import ma.glasnost.orika.MapperFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;



/**
 * {@inheritDoc}
 */
@ApplicationScoped
public class WeatherServiceImpl extends HessianServlet implements WeatherService {

    private final Logger log = LoggerFactory.getLogger(WeatherServiceImpl.class);

    private LocationDao locationDao;
    private AtmosphereDao atmosphereDao;
    private AstronomyDao astronomyDao;
    private ConditionDao conditionDao;
    private WindDao windDao;
    private CurrentObservationDao currentObservationDao;
    private ForecastDao forecastDao;
    private MapperFacade mapperFacade;


    @Inject
    public WeatherServiceImpl(LocationDao locationDao,
                              AtmosphereDao atmosphereDao,
                              AstronomyDao astronomyDao,
                              ConditionDao conditionDao,
                              WindDao windDao,
                              CurrentObservationDao currentObservationDao,
                              ForecastDao forecastDao,
                              MapperFacade mapperFacade) {
        this.locationDao = locationDao;
        this.atmosphereDao = atmosphereDao;
        this.astronomyDao = astronomyDao;
        this.conditionDao = conditionDao;
        this.windDao = windDao;
        this.currentObservationDao = currentObservationDao;
        this.forecastDao = forecastDao;
        this.mapperFacade = mapperFacade;
    }

    public WeatherServiceImpl() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public YahooForecast getWeatherFromDB(String city) {
        Location location = locationDao.findByCity(city);
        if(location == null) {
            return null;
        }
        CurrentObservation currentObservation = currentObservationDao.findByParameters(location.getWoeid());
        if(currentObservation == null) {
            return null;
        }
        currentObservation.setLocation(null);
        YahooForecast yahooForecast = new YahooForecast();
        LocationView locationView = convertLocationToLocationView(location);
        yahooForecast.setLocationView(locationView);
        yahooForecast.setCurrObservationView(mapperFacade.map(currentObservation, CurrentObservationView.class));
        List<Forecast> forecasts = location.getForecasts();
        yahooForecast.setForecasts(convertForecastToDayCondition(forecasts));
        return yahooForecast;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void saveWeather(YahooForecast yahooForecast) {
        Location locationFromDB = locationDao.findByWoeid(yahooForecast.getLocationView().getWoeid());
        if (locationFromDB != null) {
            saveCurrentObservation(yahooForecast.getCurrObservationView(), locationFromDB);
            if (!locationFromDB.getForecasts().isEmpty()) {
                locationFromDB.getForecasts().clear();
            }
            saveForecasts(yahooForecast.getForecasts(), locationFromDB);
            log.info("Added new weather data to location");
        } else {
            Location location = mapperFacade.map(yahooForecast.getLocationView(), Location.class);
            locationDao.save(location);
            saveCurrentObservation(yahooForecast.getCurrObservationView(), location);
            saveForecasts(yahooForecast.getForecasts(), location);
            log.info("Saved new location and weather data to a database");
        }
    }

    /**
     * Сохранить прогноз погоды на 10 дней
     *
     * @param forecasts прогноз погоды на 10 дней
     * @param location местоположение
     */
    private void saveForecasts(List<DayCondition> forecasts, Location location) {
        for (DayCondition forecastView : forecasts) {
            Forecast forecast = mapperFacade.map(forecastView, Forecast.class);
            forecast.setLocation(location);
            forecastDao.save(forecast);
        }
    }

    /**
     * Сохранить текущий обзор погоды
     *
     * @param currentObservationView текущий обзор погоды
     * @param location местоположение
     */
    private void saveCurrentObservation(CurrentObservationView currentObservationView, Location location) {
        if(currentObservationView.getAstronomyView() != null && currentObservationView.getAtmosphereView() != null &&
                currentObservationView.getConditionView() != null && currentObservationView.getWindView() != null) {
            com.bellintegrator.model.CurrentObservation newCurrentObservation = new com.bellintegrator.model.CurrentObservation(location, currentObservationView.getPubDate());
            currentObservationDao.save(newCurrentObservation);

            saveAstronomy(currentObservationView.getAstronomyView(), newCurrentObservation);
            saveAtmosphere(currentObservationView.getAtmosphereView(), newCurrentObservation);
            saveCondition(currentObservationView.getConditionView(), newCurrentObservation);
            saveWind(currentObservationView.getWindView(), newCurrentObservation);
        }
    }

    /**
     * Сохранить информацию о текущих астрономических условиях
     *
     * @param astronomyView информация о текущих астрономических условиях
     * @param currentObservation текущий обзор погоды
     */
    private void saveAstronomy(AstronomyView astronomyView, com.bellintegrator.model.CurrentObservation currentObservation) {
        Astronomy astronomy = mapperFacade.map(astronomyView, Astronomy.class);
        astronomy.setCurrentObservation(currentObservation);
        astronomyDao.save(astronomy);
    }

    /**
     * Сохранить информацию о текущем атмосферном давлении, влажности и видимости
     *
     * @param atmosphereView информация о текущем атмосферном давлении, влажности и видимости
     * @param currentObservation текущий обзор погоды
     */
    private void saveAtmosphere(AtmosphereView atmosphereView, com.bellintegrator.model.CurrentObservation currentObservation) {
        Atmosphere atmosphere = mapperFacade.map(atmosphereView, Atmosphere.class);
        atmosphere.setCurrentObservation(currentObservation);
        atmosphereDao.save(atmosphere);
    }

    /**
     * Сохранить информацию о текущем состоянии погоды
     *
     * @param conditionView текущее состояние погоды
     * @param currentObservation текущий обзор погоды
     */
    private void saveCondition(ConditionView conditionView, com.bellintegrator.model.CurrentObservation currentObservation) {
        Condition condition = mapperFacade.map(conditionView, Condition.class);
        condition.setCurrentObservation(currentObservation);
        conditionDao.save(condition);
    }

    /**
     * Сохранить текущую информацию о ветре
     *
     * @param windView текущая информация о ветре
     * @param currentObservation текущий обзор погоды
     */
    private void saveWind(WindView windView, com.bellintegrator.model.CurrentObservation currentObservation) {
        Wind wind = mapperFacade.map(windView, Wind.class);
        wind.setCurrentObservation(currentObservation);
        windDao.save(wind);
    }

    /**
     * Преобразовать LocationView в LocationView без ленивой инициализации
     *
     * @param location местоположение
     * @return dto LocationView
     */
    private LocationView convertLocationToLocationView(Location location) {
        return new LocationView(location.getWoeid(), location.getCity(), location.getRegion(), location.getCountry(),
                location.getLatitude(), location.getLongitude(), location.getTimezone());
    }

    /**
     * Преобразовать List<Forecast> в List<DayCondition> без ленивой инициализации
     *
     * @param forecasts прогноз погоды на 10 дней
     * @return dto List<ForecastView>
     */
    private List<DayCondition> convertForecastToDayCondition(List<Forecast> forecasts) {
        List<DayCondition> dayConditions = new ArrayList<>();
        for (Forecast forecast : forecasts) {
            DayCondition dayCondition = new DayCondition(forecast.getDay(), forecast.getDate(), forecast.getLow(),
                    forecast.getHigh(), forecast.getText(), forecast.getCode());
            dayConditions.add(dayCondition);
        }
        return dayConditions;
    }
}
