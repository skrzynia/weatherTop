package controllers;

import models.Member;
import play.Logger;
import play.mvc.Controller;

public class UserController extends Controller {

    public void index()
    {
        Member member = SignupController.getLoggedInMember();
        member = Member.findById(member.id);

        render("user.html",member);
    }
    public void update(String firstname, String lastname, String password, String email)
    {
        Member member = SignupController.getLoggedInMember();
        member = Member.findById(member.id);
        member.firstName = firstname;
        member.lastName = lastname;
        member.password = password;
        member.email = email;
        member.save();
        Logger.info("User data has been changed! " + member.firstName);
        redirect("/dashboard");


    }
}
