
package com.nsu.habbitrabbit.repo;

import com.nsu.habbitrabbit.domain.Members;
import com.nsu.habbitrabbit.domain.MembersId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface MembersRepository extends JpaRepository<Members, MembersId> {
    ArrayList<Members> getAllByRoomId(Long id);

    ArrayList<Members> getAllByPlayerId(Long id);
}