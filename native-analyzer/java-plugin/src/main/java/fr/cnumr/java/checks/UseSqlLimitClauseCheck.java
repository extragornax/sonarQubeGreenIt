package fr.cnumr.java.checks;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.java.model.declaration.AnnotationTreeImpl;
import org.sonar.java.model.expression.AssignmentExpressionTreeImpl;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.AnnotationTree;
import org.sonar.plugins.java.api.tree.Arguments;
import org.sonar.plugins.java.api.tree.LiteralTree;
import org.sonar.plugins.java.api.tree.Tree;

import java.util.Collections;
import java.util.List;

@Rule(
        key = UseSqlLimitClauseCheck.KEY,
        name = "Developpement",
        description = UseSqlLimitClauseCheck.DESCRIPTION,
        priority = Priority.MINOR,
        tags = {"bug"})
public class UseSqlLimitClauseCheck extends IssuableSubscriptionVisitor {

    public static final String KEY = "S75";
    public static final String DESCRIPTION = "Limiter le nombre de résultats (clause LIMIT)";

    @Override
    public List<Tree.Kind> nodesToVisit() {
        return Collections.singletonList(Tree.Kind.ANNOTATION);
    }


    @Override
    public void visitNode(Tree tree) {
        AnnotationTree annotationTree = (AnnotationTree) tree;
        if (annotationTree.annotationType().symbolType().name().equals("Query")) {
            ((AnnotationTreeImpl) annotationTree).children().forEach(child -> {
                child.parent();
                if (child.is(Tree.Kind.ARGUMENTS)) {
                    Arguments arguments = (Arguments) child;
                    arguments.get(0);
                    // ((AssignmentExpressionTreeImpl) arguments.get(0)).getChildren().get(2));
                    // AssignmentExpressionTreeImpl assignmentExpressionTree =
                    if (arguments.get(0).is(Tree.Kind.STRING_LITERAL)) {
                        String query = ((LiteralTree) arguments.get(0)).value();
                        query.length();
                        if (!query.contains("LIMIT")) {
                            reportIssue(tree, DESCRIPTION);

                        }
                    } else if (arguments.get(0).is(Tree.Kind.ASSIGNMENT)) {
                        LiteralTree literalTree = (LiteralTree) ((AssignmentExpressionTreeImpl) arguments.get(0)).getChildren().get(2);
                        String query = literalTree.value();
                        if (!query.contains("LIMIT")) {
                            reportIssue(tree, DESCRIPTION);

                        }
                    }

                }
            });
        }
    }
}
