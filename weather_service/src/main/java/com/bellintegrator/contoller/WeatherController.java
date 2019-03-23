package com.bellintegrator.contoller;

import com.bellintegrator.service.WeatherService;
import dto.yahooforecast.YahooForecast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Контроллер для получения погоды из БД
 */
@RestController
public class WeatherController {

    private Logger log = LoggerFactory.getLogger(WeatherController.class);

    private WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    /**
     * Получить данные из параметров запроса и отобразить пользователю прогноз погоды из БД в формате JSON
     * Пример запроса:
     * http://127.0.0.1:8080/weather_service-1.0-SNAPSHOT/forecast?city=saratov
     *
     * @param city город
     * @return данные о погоде
     */
    @RequestMapping(value = "/forecast", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public YahooForecast getForecast(@RequestParam(value="city", defaultValue = "saratov") String city) {
        YahooForecast yahooForecast = weatherService.getWeatherFromDB(city);
        if (yahooForecast == null) {
            throw new NotFoundException("Current weather data has not been found");
        }
        return yahooForecast;
    }

    /**
     * Обработчик ошибок о том, что актуальные данные о погоде не найдены в БД
     *
     * @param ex ошибка класса NotFoundException
     * @return строку с сообщением об ошибке
     */
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundException(NotFoundException ex) {
        return ex.getMessage();
    }
}
