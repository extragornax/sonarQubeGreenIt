<?php

namespace AppBundle\Repository;

/**
 * HardwareRepository
 *
 * This class was generated by the Doctrine ORM. Add your own custom
 * repository methods below.
 */
class UseSqlLimitClause extends \Doctrine\ORM\EntityRepository
{
    /**
     * @param $userId
     * @return mixed
     */
    public function getCompanyForUser($userId)
    {
        $query =  $this->createQueryBuilder('c')
            ->select('c')
            ->andWhere('c.user = :userId AND  c.returnedDate IS NULL')
            ->setParameter('userId', $userId);

        return $query->getQuery()->getResult();
    }

    /**
     * @param $userId
     * @return mixed
     */
    public function getCompanyForUserWithLimit($userId)
    {
        $query =  $this->createQueryBuilder('c')
            ->select('c')
            ->andWhere('c.user = :userId AND  c.returnedDate IS NULL')
            ->setParameter('userId', $userId)
            ->setMaxResults(10);

        return $query->getQuery()->getResult();
    }
}