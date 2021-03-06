package emiya;

import dagger.Component;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Singleton;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by brian on 5/17/17.
 */
@RestController
public class SetupController {
    @Component(modules = emiya.RandomizerModule.class)
    @Singleton
    interface SetupDirector {
        GameSetupDirector setupDirector();
    }

    private SetupDirector setupDirector;
    private GameSetupDirector gameSetupDirector;

    @RequestMapping(value = "/api/randomSetup", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public SetupDTO generateRandomSetup(@RequestBody List<String> cardSets,
                                        @RequestParam(value="players") int players) throws SQLException
    {
        SetupDetails setupDetails = SetupDetailsFactory.getSetupDetails(players);

        //TODO 9/15/18: Move this into the constructor
        setupDirector = DaggerSetupController_SetupDirector.create();
        gameSetupDirector = setupDirector.setupDirector();

        gameSetupDirector.setSetupDetails(setupDetails);

        return gameSetupDirector.buildSetup(cardSets);
    }
}
