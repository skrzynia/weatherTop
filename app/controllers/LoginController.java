package controllers;

import models.Member;
import play.Logger;
import play.mvc.Controller;

import java.util.List;

public class LoginController extends Controller
{
  public static void index() {
    Logger.info("Rendering Start");
    render ("login.html");
  }

  public static void authenticate(String email, String password)
  {
    Member member = Member.findByMail(email);

    if (member != null && member.isPasswordCorrect(password))
    {
      session.put("logged_in_Memberid",member.id);
      redirect("/dashboard");
    }
    else
    {
      redirect("/login");
    }



  }

  public static void logout()
  {
    session.clear();
    redirect ("/");
  }
}
