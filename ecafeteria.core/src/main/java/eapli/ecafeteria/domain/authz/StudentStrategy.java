package eapli.ecafeteria.domain.authz;

import eapli.framework.util.Strings;
import java.util.Calendar;

public class StudentStrategy implements MecanographicNumberValidationStrategy {

    private String mec;

    @Override
    public void mecanographicNumber(String mecanographicNumber) {
        this.mec = mecanographicNumber;
        System.out.println("Mec Number: " + this.mec);
    }

    @Override
    public boolean isValid() {
        //Sanity check
        if (Strings.isNullOrEmpty(mec)) {
            return false;
        }

        //If length less than 3 mec number is incomplete
        if (mec.length() < 3 || mec.length() > 6) {
            return false;
        }

        int aux = Integer.parseInt("20" + mec.substring(0, 1));
        int ano = Calendar.getInstance().get(Calendar.YEAR);

        //Checks if year in mec number is not in the future
        return aux <= ano;
    }
}
