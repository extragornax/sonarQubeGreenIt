package fr.cnumr.php.checks;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.php.tree.impl.expression.FunctionCallTreeImpl;
import org.sonar.plugins.php.api.tree.Tree;
import org.sonar.plugins.php.api.tree.expression.FunctionCallTree;
import org.sonar.plugins.php.api.tree.expression.MemberAccessTree;
import org.sonar.plugins.php.api.visitors.PHPVisitorCheck;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Rule(
        key = UseSqlLimitClauseCheck.KEY,
        name = "Developpement",
        description = UseSqlLimitClauseCheck.DESCRIPTION,
        priority = Priority.MINOR,
        tags = {"bug"})
public class UseSqlLimitClauseCheck extends PHPVisitorCheck {

    public static final String SQL_LIMIT_SYMFONY = "setMaxResults";
    public static final String SYMFONY_QUERY = "createQueryBuilder";
    public static final List<String> LARAVEL_QUERY = Arrays.asList("table", "select");
    public static final List<String> SQL_LIMIT_LARAVEL = Arrays.asList("take", "limit");


    public static final String KEY = "S75";
    public static final String DESCRIPTION = "Limiter le nombre de résultats (clause LIMIT)";

    private static final Map<String, MultiStepFunctionCallInfo> queryByFileAndLine = new HashMap<>();

    // DOUBLE KEY NEEDED
    // filename : line
    private static class MultiStepFunctionCallInfo {
        private Integer start;
        private Integer end;
        private boolean isQuery;
        private boolean useLimit;

        public Integer getStart() {
            return start;
        }

        public void setStart(Integer start) {
            this.start = start;
        }

        public Integer getEnd() {
            return end;
        }

        public void setEnd(Integer end) {
            this.end = end;
        }

        public boolean isQuery() {
            return isQuery;
        }

        public void setQuery(boolean query) {
            isQuery = query;
        }

        public boolean isUseLimit() {
            return useLimit;
        }

        public void setUseLimit(boolean useLimit) {
            this.useLimit = useLimit;
        }
    }

    @Override
    public void visitFunctionCall(FunctionCallTree tree) {
        Integer start = ((FunctionCallTreeImpl) tree).getFirstToken().line();
        Integer end = ((FunctionCallTreeImpl) tree).getLastToken().line();
        final String filename = context().getPhpFile().toString();
        final String KEY = String.format("%s:%s", filename, start);
        // ajout a  la map pour une nouvelle function
        // On chck si il y a pas d'entre pour le fichier puis pour la ligne du fichier
        if (this.queryByFileAndLine.get(KEY) == null || this.queryByFileAndLine.get(KEY).getEnd() < end) {
            MultiStepFunctionCallInfo multiStepFunctionCallInfo = new MultiStepFunctionCallInfo();
            multiStepFunctionCallInfo.setStart(start);
            multiStepFunctionCallInfo.setEnd(end);
            this.queryByFileAndLine.put(KEY, multiStepFunctionCallInfo);
        }

        // Recall de la function ppour split les differntes appel de functions enchainné
        super.visitFunctionCall(tree);

        if (tree.callee().is(Tree.Kind.OBJECT_MEMBER_ACCESS, Tree.Kind.CLASS_MEMBER_ACCESS)) {
            MemberAccessTree memberAccessTree = (MemberAccessTree) tree.callee();


            if (memberAccessTree.member().toString().equals(SYMFONY_QUERY) || LARAVEL_QUERY.contains(memberAccessTree.member().toString())) {
                this.queryByFileAndLine.get(KEY).setQuery(true);
            }

            if (memberAccessTree.member().toString().equals(SQL_LIMIT_SYMFONY) || SQL_LIMIT_LARAVEL.contains(memberAccessTree.member().toString())) {
                this.queryByFileAndLine.get(KEY).setUseLimit(true);
            }

            if (this.queryByFileAndLine.get(KEY) != null && this.queryByFileAndLine.get(KEY) != null && this.queryByFileAndLine.get(KEY).getEnd() == end) {
                if (this.queryByFileAndLine.get(KEY).isQuery && !this.queryByFileAndLine.get(KEY).useLimit) {
                    context().newIssue(this, memberAccessTree, DESCRIPTION);
                }
            }

        }


    }


}
