/*
 * SonarQube
 * Copyright (C) 2009-2017 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.db.qualityprofile;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.annotation.CheckForNull;
import org.apache.ibatis.annotations.Param;
import org.sonar.db.KeyLongValue;

public interface QualityProfileMapper {

  void insert(@Param("dto") RulesProfileDto dto, @Param("now") Date now);

  void update(@Param("dto") RulesProfileDto dto, @Param("now") Date now);

  void deleteByKeys(@Param("profileKeys") Collection<String> profileKeys);

  List<RulesProfileDto> selectAll(@Param("organizationUuid") String organizationUuid);

  @CheckForNull
  RulesProfileDto selectDefaultProfile(@Param("organizationUuid") String organizationUuid, @Param("language") String language);

  List<RulesProfileDto> selectDefaultProfiles(
    @Param("organizationUuid") String organizationUuid,
    @Param("languages") Collection<String> languages);

  @CheckForNull
  RulesProfileDto selectByNameAndLanguage(@Param("organizationUuid") String organizationUuid, @Param("name") String name, @Param("language") String language);

  List<RulesProfileDto> selectByNameAndLanguages(
    @Param("organizationUuid") String organizationUuid,
    @Param("name") String name,
    @Param("languages") Collection<String> languages);

  @CheckForNull
  RulesProfileDto selectByKey(String key);

  List<RulesProfileDto> selectByLanguage(@Param("organizationUuid") String organizationUuid, @Param("language") String language);

  List<RulesProfileDto> selectByKeys(@Param("keys") List<String> keys);

  // INHERITANCE

  List<RulesProfileDto> selectChildren(String key);

  // PROJECTS

  List<KeyLongValue> countProjectsByProfileKey(@Param("organizationUuid") String organizationUuid);

  @CheckForNull
  RulesProfileDto selectAssociatedToProjectUuidAndLanguage(
    @Param("organizationUuid") String organizationUuid,
    @Param("projectUuid") String projectUuid,
    @Param("language") String language);

  List<RulesProfileDto> selectAssociatedToProjectUuidAndLanguages(
    @Param("organizationUuid") String organizationUuid,
    @Param("projectUuid") String projectUuid,
    @Param("languages") Collection<String> languages);

  void insertProjectProfileAssociation(
    @Param("projectUuid") String projectUuid,
    @Param("profileUuid") String profileUuid);

  void updateProjectProfileAssociation(
    @Param("projectUuid") String projectUuid,
    @Param("profileUuid") String profileUuid,
    @Param("oldProfileUuid") String oldProfileUuid);

  void deleteProjectProfileAssociation(@Param("projectUuid") String projectUuid, @Param("profileKey") String profileKey);

  void deleteProjectAssociationByProfileKeys(@Param("profileKeys") Collection<String> profileKeys);

  List<ProjectQprofileAssociationDto> selectSelectedProjects(
    @Param("organizationUuid") String organizationUuid,
    @Param("profileKey") String profileKey,
    @Param("nameQuery") String nameQuery);

  List<ProjectQprofileAssociationDto> selectDeselectedProjects(
    @Param("organizationUuid") String organizationUuid,
    @Param("profileKey") String profileKey,
    @Param("nameQuery") String nameQuery);

  List<ProjectQprofileAssociationDto> selectProjectAssociations(
    @Param("organizationUuid") String organizationUuid,
    @Param("profileKey") String profileKey,
    @Param("nameQuery") String nameQuery);

  List<String> selectOutdatedProfiles(@Param("language") String language, @Param("name") String name);

  void rename(@Param("newName") String newName, @Param("updatedAt") Date updatedAt, @Param("profileKeys") Collection<String> profileKeys);
}
