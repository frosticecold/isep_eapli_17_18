package eapli.ecafeteria.domain.authz;

public class MecanographicNumberValidationContext {
    private MecanographicNumberValidationStrategy strategy;

    public MecanographicNumberValidationContext(MecanographicNumberValidationStrategy strategy, String mec) {
        this.strategy = strategy;
        this.strategy.mecanographicNumber(mec);
    }

    public boolean validateUser() {
        return this.strategy.isValid();
    }
}
