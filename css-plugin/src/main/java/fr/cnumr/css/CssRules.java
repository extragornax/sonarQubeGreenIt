package fr.cnumr.css;

import fr.cnumr.css.checks.css.PromoteStandardPolicy;
import org.sonar.plugins.java.api.JavaCheck;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CssRules {

    private CssRules() {
    }

    public static List<Class> getChecks() {
        List<Class> checks = new ArrayList<>();
        checks.addAll(getRuleClasses());
        checks.addAll(getJavaTestChecks());
        return Collections.unmodifiableList(checks);
    }

    public static List<Class> getRuleClasses() {
        //ajouter les checks ici
        return Collections.unmodifiableList(Arrays.asList(
                PromoteStandardPolicy.class
        ));
    }

    public static List<Class> getJavaTestChecks() {
        return Collections.emptyList();
    }
}
