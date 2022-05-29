package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
public class Reading extends Model {


    public Date date;
    public int code;
    public double temperature;
    public double windSpeed;
    public double windDirection;
    public int pressure;

    public Reading(int code, double temperature, double windSpeed, double windDirection, int pressure) {
        this.date = new Date();
        this.code = code;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.pressure = pressure;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public void setWindDirection(int windDirection) {
        this.windDirection = windDirection;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }
}
