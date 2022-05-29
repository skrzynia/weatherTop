package controllers;

import models.Member;
import play.Logger;
import play.mvc.Controller;

public class SignupController extends Controller
{
  public static void index() {

    render ("signup.html");

  }

  public static void login()
  {
      render("login.html");
  }

  public void register(String firstName, String lastName, String email, String password)
  {
   if(Member.findByMail(email) == null)
   {
       String[] tempArray = {firstName,lastName,password};

       for(int i = 0; i < tempArray.length; i++)
       {

           if (!UtilityValidators.isDataCorrect(tempArray[i]))
           {
               Logger.info("Data must be longer than 0");
               redirect("/signup");
           }
       }
       if (!UtilityValidators.isEmailCorrect(email)) {
           Logger.info("Email must be in the 'abc@acb.com' format");
           redirect("/signup");
       }

       Member member = new Member(firstName,lastName,email,password);
       member.save();
       Logger.info("Member correctly created");
       redirect("/login");

   }
   Logger.info("This email adress is registered already!");
   redirect("/signup");
  }

    public static Member getLoggedInMember()
    {
        Member member = null;
        if (session.contains("logged_in_Memberid")) {
            String memberId = session.get("logged_in_Memberid");
            member = Member.findById(Long.parseLong(memberId));
        } else {
            login();
        }
        return member;
    }
}
