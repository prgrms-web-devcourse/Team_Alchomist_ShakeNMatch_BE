package com.shake_match.alchomist.users.repository;

import com.shake_match.alchomist.users.Users;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByNickname(String nickname);

    @Query("select u from users u join fetch u.group g left join fetch g.permissions gp join fetch gp.permission where u.username = :username")
    Optional<Users> findByUsername(String username);

    @Query("select u from users u join fetch u.group g left join fetch g.permissions gp join fetch gp.permission where u.provider = :provider and u.providerId = :providerId")
    Optional<Users> findByProviderAndProviderId(String provider, String providerId);

    @Query("select u from users u join fetch u.group g left join fetch g.permissions gp join fetch gp.permission where u.providerId = :id")
    Optional<Users> findByProviderId(String id);
}
