package com.shake_match.alchomist.users.repository;

import com.shake_match.alchomist.users.Users;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<Users, java.lang.Long> {

    Optional<Users> findByNickname(String nickname);

}
