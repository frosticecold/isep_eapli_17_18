package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.authz.*;
import eapli.ecafeteria.domain.authz.*;
import eapli.framework.actions.*;
import eapli.framework.util.*;
import java.util.*;

/**
 * eCafeteria Bootstrapping data app
 */
public class ECafeteriaBootstrapper implements Action {

    @Override
    public boolean execute() {
        // declare bootstrap actions
        final Action[] actions = {new MasterUsersBootstrapper(), new BackofficeUsersBootstrapper(), new DishTypesBootstrapper(), new CafeteriaUserBootstrapper(), new AlergenBootstrapper(), new DishBootstrapper(), new MaterialsBootstrapper(), new MenuBootstrapper(), new MealBootstrapper(), new BatchesBootstrapper(), new MealMaterialBootstrapper(), new POSBootstrapper(),  new DeliveryRegistryBootstrapper(), new BookingBootstrapper(), new RatingBootstrapper(), new ExecutionBootstrapper(), new AllergenProfileBootstrapper(), new MenuPlanBootstrapper(), new TransactionBootstrapper() };


        authenticateSuperUser();

        // execute all bootstrapping
        boolean ret = true;
        for (final Action boot : actions) {
            System.out.println("Bootstrapping " + nameOfEntity(boot) + "...");
            ret &= boot.execute();
        }
        return ret;
    }

    private void authenticateSuperUser() {
        // authenticate a super user to be able to register new users, ...
        // in this case we will inject the session but we shouldn't do this
        final Set<RoleType> roles = new HashSet<>();
        roles.add(RoleType.ADMIN);
        roles.add(RoleType.MENU_MANAGER);
        roles.add(RoleType.KITCHEN_MANAGER);
        final UserSession adminSession = new UserSession(
                new SystemUser("poweruser", "poweruserA1", "joe", "doe", "joe@email.org", roles));
        AuthorizationService.setSession(adminSession);
    }

    private String nameOfEntity(final Action boot) {
        final String name = boot.getClass().getSimpleName();
        return Strings.left(name, name.length() - "Bootstrapper".length());
    }
}
