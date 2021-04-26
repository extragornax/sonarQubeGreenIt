package files.sqlLimitClause;

public interface Native {

    @Query(  // Noncompliant
            value = "SELECT * FROM USERS u WHERE u.status = 1",
            nativeQuery = true)
    Collection<User> findAllActiveUsersNative();

    @Query(
            value = "SELECT * FROM USERS u WHERE u.status = 1 LIMIT 20",
            nativeQuery = true)
    Collection<User> findAllActiveUsersNativeLimit();
}
