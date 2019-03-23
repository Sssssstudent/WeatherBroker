package dto.yahooforecast.currobservation;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Objects;

/**
 * Текущее состояние погоды
 */
public class ConditionView implements Serializable {
    /**
     * Уникальный идентификатор
     */
    @JsonIgnore
    private Integer id;

    /**
     * Текстовое описание состояния
     */
    private String text;

    /**
     * Код состояния
     */
    private String code;

    /**
     * Текущая температура
     */
    private String temperature;

    /**
     * Текущий обзор погоды
     */
    @JsonBackReference
    private CurrentObservationView currentObservationView;

    public ConditionView() {
    }

    public ConditionView(String text, String code, String temperature, CurrentObservationView currentObservationView) {
        this.text = text;
        this.code = code;
        this.temperature = temperature;
        this.currentObservationView = currentObservationView;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
          return text;
      }

    public void setText(String text) {
          this.text = text;
      }

    public String getCode() {
          return code;
      }

    public void setCode(String code) {
          this.code = code;
      }

    public String getTemperature() {
          return temperature;
      }

    public void setTemperature(String temperature) {
          this.temperature = temperature;
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
        ConditionView conditionView = (ConditionView) o;
        return Objects.equals(id, conditionView.id) &&
                Objects.equals(text, conditionView.text) &&
                Objects.equals(code, conditionView.code) &&
                Objects.equals(temperature, conditionView.temperature);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, text, code, temperature);
    }

    @Override
    public String toString() {
        return "ConditionView{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", code=" + code +
                ", temperature=" + temperature +
                '}';
    }
  }
