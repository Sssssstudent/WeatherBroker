package dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * Запрос от admin на обновление информации о погоде
 */
public class AdminRequest implements Serializable {



    /**
     * Название города
     */
    private String city;

    /**
     * Регион
     */
    private String region;

    public AdminRequest() {
    }

    public AdminRequest(String city, String region) {
        this.city = city;
        this.region = region;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminRequest adminRequest = (AdminRequest) o;
        return Objects.equals(city, adminRequest.city) &&
                Objects.equals(region, adminRequest.region);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, region);
    }

    @Override
    public String toString() {
        return "City{" +
                "city='" + city + '\'' +
                ", region='" + region + '\'' +
                '}';
    }
}
