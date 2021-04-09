package fr.cnumr.css.checks.css;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.css.model.property.standard.FontFamily;
import org.sonar.plugins.css.api.tree.css.PropertyDeclarationTree;
import org.sonar.plugins.css.api.visitors.DoubleDispatchVisitorCheck;
import org.sonar.squidbridge.annotations.ActivatedByDefault;
import org.sonar.squidbridge.annotations.SqaleConstantRemediation;

import java.util.Arrays;
import java.util.List;

@Rule(
        key = "29",
        priority = Priority.INFO,
        name = "Favoriser les polices standard",
        tags = {"eco-conception"})
@SqaleConstantRemediation("5min")
@ActivatedByDefault
public class PromoteStandardPolicy extends DoubleDispatchVisitorCheck {

    @Override
    public void visitPropertyDeclaration(PropertyDeclarationTree tree) {
        if (tree.property().standardProperty() instanceof FontFamily) {
            // FontFamily.GENERIC_FAMILY_NAMES;
            tree.value().valueElements().forEach(element -> {
                Arrays.stream(element.treeValue().split(",")).forEach(policy -> {
                    if (!FontFamily.GENERIC_FAMILY_NAMES.contains(policy)) {
                        addPreciseIssue(tree, "Favoriser les polices standard plutôt que votre police "+policy);
                    }
                });



            });
            // addPreciseIssue(tree, "Remove this usage of the forbidden  property.");
        }
        // super method must be called in order to visit what is under the key node in the syntax tree

        super.visitPropertyDeclaration(tree);
    }

}
