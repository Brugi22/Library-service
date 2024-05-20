package com.infobip.pmf.course.library.libraryservice.storage;

import com.infobip.pmf.course.library.libraryservice.Library;
import com.infobip.pmf.course.library.libraryservice.Version;
import jakarta.persistence.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "libraries", schema = "library_service",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"groupId", "artifactId"})
})
public class LibraryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String groupId;

    @Column(nullable = false)
    private String artifactId;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @OneToMany(mappedBy = "library", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VersionEntity> versions;

    public static LibraryEntity from(Library library) {
        var libraryEntity = new LibraryEntity();
        libraryEntity.setId(library.id());
        libraryEntity.setGroupId(library.groupId());
        libraryEntity.setArtifactId(library.artifactId());
        libraryEntity.setName(library.name());
        libraryEntity.setDescription(library.description());
        libraryEntity.setVersions(library.versions().stream().map(Version::toEntity).toList());
        return libraryEntity;
    }

    public Library asLibrary() {
        return new Library(id, groupId, artifactId, name, description, Optional.ofNullable(versions)
                                                                                .orElse(Collections.emptyList())
                                                                                .stream()
                                                                                .map(VersionEntity::asVersion)
                                                                                .toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<VersionEntity> getVersions() {
        return versions;
    }

    public void setVersions(List<VersionEntity> versions) {
        this.versions = versions;
    }
}
