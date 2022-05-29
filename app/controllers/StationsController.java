package controllers;

import models.Member;
import models.Reading;
import models.Station;
import play.Logger;
import play.data.validation.Min;
import play.data.validation.Range;
import play.data.validation.Required;
import play.data.validation.Validation;
import play.mvc.Controller;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class StationsController extends Controller {



    public void index(Long id)
    {
        Station station = Station.findById(id);
        List<Reading> readings = station.readings;
        String weather;
        double fahrenhite;
        double celsius;
        double[] minMax = new double[2];
        double min;
        double max;
        String beaufort;
        String beaufrotText;
        double windChill;
        String windDirection;
        int pressure;
        int[] minMaxPressure = new int[2];
        int maxPressure;
        int minPressure;
        String tendTemperature;
        String tendWind;
        String tendPressure;

        if (!readings.isEmpty())
        {
            weather = UtilityConverters.convertFromCode(readings.get(readings.size() - 1).code);
            fahrenhite = UtilityConverters.convertCelToFahr(readings.get(readings.size() - 1).temperature);
            celsius = readings.get(readings.size() - 1).temperature;
            minMax = UtilityConverters.minMaxTemperature(readings);
            min = minMax[0];
            max = minMax[1];
            beaufort = UtilityConverters.convKmToBea(readings.get(readings.size() - 1).windSpeed);
            beaufrotText = UtilityConverters.convertKmToBea(readings.get(readings.size() - 1).windSpeed);
            windChill = UtilityConverters.getWindChill(readings.get(readings.size() - 1).temperature,
                    readings.get(readings.size() - 1).windSpeed);
            windChill =  Math.floor(windChill * 100) / 100;
            windDirection = UtilityConverters.getWindDirectionCompass(readings.get(readings.size() - 1).windDirection);
            pressure = readings.get(readings.size() - 1).pressure;
            minMaxPressure = UtilityConverters.minMaxPressure(readings);
            minPressure = minMaxPressure[0];
            maxPressure = minMaxPressure[1];
            tendTemperature = UtilityConverters.tendTemperature(readings);
            tendWind = UtilityConverters.tendWind(readings);
            tendPressure = UtilityConverters.tendPressure(readings);
        }
        else
        {
            weather = "No data";
            fahrenhite = 0.00;
            celsius = 0.00;
            min = 0.00;
            max = 0.00;
            beaufort = "No data";
            beaufrotText = "No data";
            windChill = 0.00;
            windDirection = "No data";
            pressure = 0;
            minPressure = 0;
            maxPressure = 0;
            tendTemperature = "No Data";
            tendWind = "No Data";
            tendPressure = "No Data";
        }



        Logger.info("Station id " + id);
        render("station.html", station,readings,weather,fahrenhite,celsius,min,max,
                beaufort,beaufrotText,windChill, windDirection,pressure,minPressure
                ,maxPressure, tendTemperature, tendWind,tendPressure);
    }

    public void addreading(Long id, @Required  @Min(1) int code, @Required double temperature,
                           @Required double windspeed, @Required int winddirection,@Required @Range(min = 800,max =1200) int pressure)
    {

        if(Validation.hasErrors()) {
            params.flash(); // add http parameters to the flash scope
            Validation.keep(); // keep the errors for the next request
            index(id);
        }
            Reading reading =
                    new Reading(
                            code,temperature,windspeed,winddirection,pressure);

            Station station = Station.findById(id);
            station.readings.add(reading);
            station.save();



            index(id);


    }

    public void deletereading(Long id, Long readingid)
    {
        Station station = Station.findById(id);
        Reading reading = Reading.findById(readingid);


        station.readings.remove(reading);
        station.save();
        reading.delete();
        List<Reading> readings = station.readings;
        render("station.html",station,readings);
    }


}
