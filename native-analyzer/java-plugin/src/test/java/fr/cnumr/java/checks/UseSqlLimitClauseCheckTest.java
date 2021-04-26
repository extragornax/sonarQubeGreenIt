package fr.cnumr.java.checks;

import org.junit.jupiter.api.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

public class UseSqlLimitClauseCheckTest {
    @Test
    void testJpql() {
        JavaCheckVerifier.newVerifier()
                .onFile("src/test/files/sqlLimitClause/Jpql.java")
                .withCheck(new UseSqlLimitClauseCheck())
                .verifyIssues();
    }

}
