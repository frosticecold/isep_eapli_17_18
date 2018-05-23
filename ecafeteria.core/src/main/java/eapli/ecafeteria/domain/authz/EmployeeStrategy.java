package eapli.ecafeteria.domain.authz;

import eapli.framework.util.Strings;

public class EmployeeStrategy implements MecanographicNumberValidationStrategy {

    private String mec;

    @Override
    public void mecanographicNumber(String mecanographicNumber) {
        this.mec = mecanographicNumber;
    }

    @Override
    public boolean isValid() {
        //Sanity check
        if (Strings.isNullOrEmpty(mec)) {
            return false;
        }

        //If length less than 3 mec number is incomplete
        return mec.length() == 4;
    }
}
