package com.talentboost.ics.repositories;

import com.talentboost.ics.data.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    boolean existsTagByName(String name);
    Tag getTagByName(String name);
}
