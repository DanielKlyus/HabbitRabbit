package com.nsu.habbitrabbit.repo;

import com.nsu.habbitrabbit.domain.Members;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface MembersRepository extends JpaRepository<Members, Long> {
    ArrayList<Members> getAllByRoomId(Long id);
}
