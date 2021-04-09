package fr.cnumr.css.checks;

import fr.cnumr.css.checks.css.PromoteStandardPolicy;
import org.junit.Test;
import org.sonar.css.checks.verifier.CssCheckVerifier;

import java.io.File;

public class PromoteStandardPolicyTest {

    @Test
    public void test() {
        CssCheckVerifier.issuesOnCssFile(new PromoteStandardPolicy(), new File("src/test/files/PromoteStandardPolicy.css"))
                .next().atLine(2).withMessage("Favoriser les polices standard plutôt que votre police 'quadranta'")
                .next().atLine(12).withMessage("Favoriser les polices standard plutôt que votre police quadranta")
                .next().atLine(17).withMessage("Favoriser les polices standard plutôt que votre police \"Times New Roman\"")
                .next().atLine(17).withMessage("Favoriser les polices standard plutôt que votre police Georgia")
                .next().atLine(17).withMessage("Favoriser les polices standard plutôt que votre police Times")



                .noMore();

    }

}
