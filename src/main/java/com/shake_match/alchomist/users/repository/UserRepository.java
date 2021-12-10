package com.shake_match.alchomist.users.repository;

import com.shake_match.alchomist.users.Users;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByNickname(String nickname);

}
