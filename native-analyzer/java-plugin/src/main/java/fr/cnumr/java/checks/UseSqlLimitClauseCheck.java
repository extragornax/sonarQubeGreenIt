package fr.cnumr.java.checks;

import jdk.nashorn.api.tree.FunctionCallTree;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.java.model.declaration.AnnotationTreeImpl;
import org.sonar.java.model.expression.AssignmentExpressionTreeImpl;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.*;

import java.util.Collections;
import java.util.List;

@Rule(
        key = UseSqlLimitClauseCheck.KEY,
        name = "Developpement",
        description = UseSqlLimitClauseCheck.DESCRIPTION,
        priority = Priority.MINOR,
        tags = {"bug"})
public class UseSqlLimitClauseCheck extends BaseTreeVisitor implements JavaFileScanner {

    public static final String KEY = "S75";
    public static final String DESCRIPTION = "Limiter le nombre de résultats (clause LIMIT)";


    private String currentPackage = null;
    private String currentProject = null;
    private String currentFunctionalArea = null;

    private JavaFileScannerContext context;

    public UseSqlLimitClauseCheck() {
        super();
    }
    @Override
    public void scanFile(JavaFileScannerContext context) {
        this.context = context;
        currentPackage = "";
        currentProject = "";
        currentFunctionalArea = "";


        // The call to the scan method on the root of the tree triggers the visit of the AST by this visitor
        scan(context.getTree());
    }
    @Override
    public void  visitAnnotation(AnnotationTree annotationTree)  {
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

                        context.reportIssue(this, annotationTree, DESCRIPTION);
                        }
                    } else if (arguments.get(0).is(Tree.Kind.ASSIGNMENT)) {
                        LiteralTree literalTree = (LiteralTree) ((AssignmentExpressionTreeImpl) arguments.get(0)).getChildren().get(2);
                        String query = literalTree.value();
                        if (!query.contains("LIMIT")) {
                            context.reportIssue(this, annotationTree, DESCRIPTION);


                        }
                    }

                }
            });
        }
    }

    @Override
    public void visitMethodInvocation(MethodInvocationTree tree) {
        tree.arguments();
    }

}
