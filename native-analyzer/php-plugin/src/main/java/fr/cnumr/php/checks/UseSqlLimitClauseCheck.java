package fr.cnumr.php.checks;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.php.tree.impl.expression.FunctionCallTreeImpl;
import org.sonar.plugins.php.api.tree.expression.FunctionCallTree;
import org.sonar.plugins.php.api.tree.expression.MemberAccessTree;
import org.sonar.plugins.php.api.visitors.PHPVisitorCheck;

import java.util.HashMap;
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


    public static final String KEY = "S69";
    public static final String DESCRIPTION = "Limiter le nombre de résultats (clause LIMIT)";

    private static final Map<String, Map<Integer, CustomMap>> myMap = new HashMap<>();

    private static class CustomMap {
        private Integer end;
        private boolean isQuery;
        private boolean useLimit;

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
        Integer start = ((FunctionCallTreeImpl) tree).getFirstToken().column();
        Integer end = ((FunctionCallTreeImpl) tree).getLastToken().column();
        final String filename = context().getPhpFile().toString();

        // ajout a  la map pour une nouvelle function
        // On chck si il y a pas d'entre pour le fichier puis pour la ligne du fichier
        if (this.myMap.get(filename) == null || this.myMap.get(filename).get(start) == null) {
            CustomMap customMap = new CustomMap();
            customMap.setEnd(end);
            Map<Integer, CustomMap> map = new HashMap<Integer, CustomMap>();
            map.put(start, customMap);
            this.myMap.put(filename, map);
        }

        // Recall de la function ppour split les differntes appel de functions enchainné
        super.visitFunctionCall(tree);

        MemberAccessTree memberAccessTree = (MemberAccessTree) tree.callee();


        if (memberAccessTree.member().toString().equals(SYMFONY_QUERY)) {
            this.myMap.get(filename).get(start).setQuery(true);
        }

        if (memberAccessTree.member().toString().equals(SQL_LIMIT_SYMFONY)) {
            this.myMap.get(filename).get(start).setUseLimit(true);
        }

        if (this.myMap.get(filename).get(start).getEnd() == end) {
            if (this.myMap.get(filename).get(start).isQuery && !this.myMap.get(filename).get(start).useLimit) {
                context().newIssue(this, memberAccessTree, DESCRIPTION);
            }
        }

    }


}
