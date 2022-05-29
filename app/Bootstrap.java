import java.util.List;

import org.h2.engine.User;
import play.*;
import play.jobs.*;
import play.test.*;

import models.*;

@OnApplicationStart
public class Bootstrap extends Job
{
    public void doJob()
    {
        Fixtures.delete();
        Fixtures.loadModels("data.yml");



    }
}
