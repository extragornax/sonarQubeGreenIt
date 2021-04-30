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

    @Test
    void testNative() {
        JavaCheckVerifier.newVerifier()
                .onFile("src/test/files/sqlLimitClause/Native.java")
                .withCheck(new UseSqlLimitClauseCheck())
                .verifyIssues();
    }

    @Test
    void testCustomRepo() {
        JavaCheckVerifier.newVerifier()
                .onFile("src/test/files/sqlLimitClause/CustomRepo.java")
                .withCheck(new UseSqlLimitClauseCheck())
                .verifyIssues();
    }

}
