package com.talentboost.ics.repositories;

import com.talentboost.ics.data.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    Image getImageByUrl(String url);
    boolean existsByUrl(String url);
    boolean existsById(Long id);
    boolean existsByChecksum(Long checksum);
}
