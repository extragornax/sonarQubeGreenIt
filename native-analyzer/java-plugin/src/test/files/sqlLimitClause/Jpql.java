package files.sqlLimitClause;

import java.util.Collection;

public interface Jpql {
    @Query("SELECT u FROM User u WHERE u.status = 1") // Noncompliant
    Collection<User> findAllActiveUsers();

    @Query("SELECT u FROM User u WHERE u.status = 1 LIMIT 100:")
    Collection<User> findAllActiveUsersLimit();
}
