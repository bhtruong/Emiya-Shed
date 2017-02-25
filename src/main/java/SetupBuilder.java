import java.sql.SQLException;
import java.util.List;

/**
 * Created by brian on 2/19/17.
 */
abstract class SetupBuilder {
    protected CardRepository cardRepository;

    SetupBuilder(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    protected void openConnection() throws SQLException {
        cardRepository.openConnection();
    }

    protected void closeConnection() throws SQLException {
        cardRepository.closeConnection();
    }

    abstract Scheme getScheme();
    abstract List<Mastermind> getMastermind();
    abstract List<Villain> getVillains();
    abstract List<Hero> getHeroes();
}