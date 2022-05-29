package controllers;

import models.Member;
import models.Reading;
import models.Station;
import play.data.validation.Required;
import play.data.validation.Validation;
import play.mvc.Controller;

import java.util.List;

public class DashboardController extends Controller {

    public void index()
    {
        Member member = SignupController.getLoggedInMember();
        List<Station> stations = member.stations;
        render("dashboard.html", stations);
    }
    public void start()
    {
        Member member = SignupController.getLoggedInMember();
        render("start.html", member);
    }

    public void about()
    {
        render("about.html");
    }

    public void addstation(@Required String name, @Required double latitude, @Required double longitude)
    {

        if(Validation.hasErrors()) {
            params.flash(); // add http parameters to the flash scope
            Validation.keep(); // keep the errors for the next request
            index();
        }
        Station station = new Station(name, latitude,longitude);
        Member member = SignupController.getLoggedInMember();
        member.stations.add(station);
        member.save();

        redirect("/dashboard");
    }

    public void deletestation(Long id)
    {
        Member member = SignupController.getLoggedInMember();
        Station station = Station.findById(id);

        member.stations.remove(station);
        member.save();
        station.delete();
        List<Station> stations =  member.stations;
        render("dashboard.html",stations);
    }
}


