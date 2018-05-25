package eapli.ecafeteria.domain.authz;

public interface MecanographicNumberValidationStrategy {
    boolean isValid();

    void mecanographicNumber(String mecanographicNumber);
}

