package com.infobip.pmf.course.library.libraryservice.storage;

import com.infobip.pmf.course.library.libraryservice.Version;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListCrudRepository;

public interface VersionEntityRepository extends ListCrudRepository<VersionEntity, Long> {
    Page<VersionEntity> findByLibrary(LibraryEntity library, Pageable pageable);

    boolean existsBySemanticVersion(String semanticVersion);
}
