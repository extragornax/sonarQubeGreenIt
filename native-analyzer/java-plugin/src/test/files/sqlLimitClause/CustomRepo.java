package files.sqlLimitClause;

public class CustomRepo {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> findUserByEmails(Set<String> emails) {

     /*   CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> user = query.from(User.class);

        Path<String> emailPath = user.get("email");

        List<Predicate> predicates = new ArrayList<>();
        for (String email : emails) {
            predicates.add(cb.like(emailPath, email));
        }
        query.select(user)
                .where(cb.or(predicates.toArray(new Predicate[predicates.size()])));
*/

        query.select(user)
                .where(zgeg);
        return entityManager.createQuery(query)
                .getResultList();
    }

// getMaxResults()
}
