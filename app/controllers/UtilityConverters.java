package controllers;

import models.Reading;

import java.util.HashMap;
import java.util.List;

public class UtilityConverters {

    public static String convertFromCode(int code)
    {
        HashMap<Integer,String> map = new HashMap<>();
        map.put(100,"Clear");
        map.put(200,"Partial Couds");
        map.put(300,"Cloudy");
        map.put(400,"Light Showers");
        map.put(500, "Heavy Showers");
        map.put(600, "Rain");
        map.put(700,"Snow");
        map.put(800,"Thunder");

        return map.get(code);
    }

    public static double convertCelToFahr(double temp)
    {
        return temp * 9/5 + 32;
    }

    public static double[] minMaxTemperature(List<Reading> list)
    {
        double minMax[] = new double[2];
        double min = 999;
        double max = -999;

        for(int i = 0; i <= list.size()-1; i++)
        {
            if(list.get(i).temperature < min)
            {
                min = list.get(i).temperature;
            }
            if (list.get(i).temperature > max)
            {
                max = list.get(i).temperature;
            }
        }
        minMax[0] = min;
        minMax[1] = max;

        return minMax;
    }

    public static String convKmToBea(double windSpeed)
    {
        if(windSpeed == 1)
        {
            return "0 bft";
        }
        if (windSpeed > 1 && windSpeed <  5)
        {
            return "1 bft";
        }
        if (windSpeed >= 6 && windSpeed <=  11)
        {
            return "2 bft";
        }
        if (windSpeed >= 12 && windSpeed <=  19)
        {
            return "3 bft";
        }
        if (windSpeed >= 20 && windSpeed <=  28)
        {
            return "4 bft";
        }
        if (windSpeed >= 29 && windSpeed <=  38)
        {
            return "5 bft";
        }
        if (windSpeed >= 39 && windSpeed <=  49)
        {
            return "6 bft";
        }
        if (windSpeed >= 50 && windSpeed <=  61)
        {
            return "7 bft";
        }
        if (windSpeed >= 62 && windSpeed <=  74)
        {
            return "8 bft";
        }
        if (windSpeed >= 75 && windSpeed <=  88)
        {
            return "9 bft";
        }
        if (windSpeed >= 89 && windSpeed <=  102)
        {
            return "10 bft";
        }
        if (windSpeed > 103)
        {
            return "11 bft";
        }

        return "Wrong data";

    }

    public static String convertKmToBea(double windSpeed)
    {
        if(windSpeed == 1)
        {
            return "Calm";
        }
        if (windSpeed > 1 && windSpeed <  5)
        {
            return "Light Air";
        }
        if (windSpeed >= 6 && windSpeed <=  11)
        {
            return "Light Breeze";
        }
        if (windSpeed >= 12 && windSpeed <=  19)
        {
            return "Gentle Breeze";
        }
        if (windSpeed >= 20 && windSpeed <=  28)
        {
            return "Moderate Breeze";
        }
        if (windSpeed >= 29 && windSpeed <=  38)
        {
            return "Fresh Breeze";
        }
        if (windSpeed >= 39 && windSpeed <=  49)
        {
            return "Strong Breeze";
        }
        if (windSpeed >= 50 && windSpeed <=  61)
        {
            return "Near Gale";
        }
        if (windSpeed >= 62 && windSpeed <=  74)
        {
            return "Gale";
        }
        if (windSpeed >= 75 && windSpeed <=  88)
        {
            return "Severe Gale";
        }
        if (windSpeed >= 89 && windSpeed <=  102)
        {
            return "Strong Storm";
        }
        if (windSpeed > 103 )
        {
            return "Violent Storm";
        }

        return "Wrong data";

    }

    public static double getWindChill(double celsius, double windSpeed)
    {
        return 13.12 + 0.6215 * celsius - 11.37*(Math.pow(windSpeed,0.16) + 0.3965*
                celsius*(Math.pow(windSpeed, 0.16)));
    }

    public static String getWindDirectionCompass(double windDirection)
    {
        if (windDirection >= 348.75 || windDirection <= 11.25)
            return "N";
        else if (windDirection > 11.25 && windDirection <= 33.75)
            return "NNE";
        else if (windDirection > 33.75 && windDirection <= 56.25)
            return "NE";
        else if (windDirection > 56.25 && windDirection <= 78.75)
            return "ENE";
        else if (windDirection > 78.75 && windDirection <= 101.25)
            return "E";
        else if (windDirection > 101.25 && windDirection <= 123.75)
            return "ESE";
        else if (windDirection > 123.75 && windDirection <= 146.25)
            return "SE";
        else if (windDirection > 146.25 && windDirection <= 168.75)
            return "SSE";
        else if (windDirection > 168.75 && windDirection <= 191.25)
            return "S";
        else if (windDirection > 191.25 && windDirection <= 213.75)
            return "SSW";
        else if (windDirection > 213.75 && windDirection <= 236.25)
            return "SW";
        else if (windDirection > 236.25 && windDirection <= 258.75)
            return "WSW";
        else if (windDirection > 258.75 && windDirection <= 281.25)
            return "W";
        else if (windDirection > 281.25 && windDirection <= 303.75)
            return "WNW";
        else if (windDirection > 303.75 && windDirection <= 326.25)
            return "NW";
        else if (windDirection > 326.25 && windDirection <= 348.74)
            return "NNW";

        return "Wrong Data";

    }

    public static int[] minMaxPressure(List<Reading> list)
    {
        int minMax[] = new int[2];
        int min = 99999;
        int max = -99999;

        for(int i = 0; i <= list.size()-1; i++)
        {
            if(list.get(i).pressure < min)
            {
                min = list.get(i).pressure;
            }
            if (list.get(i).pressure > max)
            {
                max = list.get(i).pressure;
            }
        }
        minMax[0] = min;
        minMax[1] = max;

        return minMax;
    }

    public static String tendTemperature(List<Reading> list)
    {
        boolean tempArr[] = new boolean[2];
        double temp = list.get(0).temperature;
        if (list.size() >= 3)
        {
            for(int i = 1; i < list.size() - 1; i++)
            {
                if (list.get(i).temperature > temp)
                {
                    tempArr[i-1] = true;
                    temp = list.get(i).temperature;
                }
                else if (list.get(i).temperature < temp)
                {
                    tempArr[i-1] = false;
                    temp = list.get(i).temperature;
                }
            }
            System.out.println(tempArr[0] + " " + tempArr[1]);

            if (tempArr[0] && tempArr[1])
            {
                return "big blue arrow up icon";
            }else if(!tempArr[0] && !tempArr[1])
            {
                return "big blue arrow down icon";
            }
            else
            {
                return "big blue arrows alternate horizontal icon";

            }
            }

        return tempArr[0] + " " + tempArr[1];
    }


    public static String tendWind(List<Reading> list)
    {
        boolean tempArr[] = new boolean[2];
        double temp = list.get(0).windSpeed;
        if (list.size() >= 3)
        {
            for(int i = 1; i < list.size() - 1; i++)
            {
                if (list.get(i).windSpeed > temp)
                {
                    tempArr[i-1] = true;
                    temp = list.get(i).windSpeed;
                }
                else if (list.get(i).windSpeed < temp)
                {
                    tempArr[i-1] = false;
                    temp = list.get(i).windSpeed;
                }
            }

            if (tempArr[0] && tempArr[1])
            {
                return "big green arrow up icon";
            }else if(!tempArr[0] && !tempArr[1])
            {
                return "big green arrow down icon";
            }
            else
            {
                return "big green arrows alternate horizontal icon";

            }
        }

        return "WrongData";
    }

    public static String tendPressure(List<Reading> list)
    {
        boolean tempArr[] = new boolean[2];
        double temp = list.get(0).pressure;
        if (list.size() >= 3)
        {
            for(int i = 1; i < list.size() - 1; i++)
            {
                if (list.get(i).pressure > temp)
                {
                    tempArr[i-1] = true;
                    temp = list.get(i).pressure;
                }
                else if (list.get(i).pressure < temp)
                {
                    tempArr[i-1] = false;
                    temp = list.get(i).pressure;
                }
            }

            if (tempArr[0] && tempArr[1])
            {
                return "big orange arrow up icon";
            }else if(!tempArr[0] && !tempArr[1])
            {
                return "big orange arrow down icon";
            }
            else
            {
                return "big orange arrows alternate horizontal icon";

            }
        }

        return "WrongData";
    }

}







