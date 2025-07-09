package de.fh_dortmund.swt2.backend.repository;

import de.fh_dortmund.swt2.backend.model.Chat;
import de.fh_dortmund.swt2.backend.model.AppUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    @Query("SELECT c FROM Chat c WHERE (c.participantA = :user1 AND c.participantB = :user2) OR (c.participantA = :user2 AND c.participantB = :user1)")
    Optional<Chat> findByParticipants(@Param("user1") AppUser user1, @Param("user2") AppUser user2);

    @Query("SELECT c FROM Chat c WHERE c.participantA = :user OR c.participantB = :user")
    List<Chat> findAllByParticipant(@Param("user") AppUser user);
}
