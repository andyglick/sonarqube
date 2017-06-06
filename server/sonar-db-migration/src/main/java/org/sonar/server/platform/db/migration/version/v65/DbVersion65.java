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
package org.sonar.server.platform.db.migration.version.v65;

import org.sonar.server.platform.db.migration.step.MigrationStepRegistry;
import org.sonar.server.platform.db.migration.version.DbVersion;

public class DbVersion65 implements DbVersion {
  @Override
  public void addSteps(MigrationStepRegistry registry) {
    registry
      .add(1700, "Drop table AUTHORS", DropTableAuthors.class)
      .add(1701, "Clean orphans from USER_ROLES", CleanOrphanRowsInUserRoles.class)
      .add(1702, "Clean orphans from GROUP_ROLES", CleanOrphanRowsInGroupRoles.class)
      .add(1703, "Populate EVENTS.COMPONENT_UUID", PopulateEventsComponentUuid.class)
      .add(1704, "Drop index EVENTS_COMPONENT_UUID", DropIndexEventsComponentUuid.class)
      .add(1705, "Make EVENTS.COMPONENT_UUID not nullable", MakeEventsComponentUuidNotNullable.class)
      .add(1706, "Recreate index EVENTS_COMPONENT_UUID", RecreateIndexEventsComponentUuid.class)
      .add(1707, "Add rules_profiles.is_built_in", AddBuiltInFlagToRulesProfiles.class)
      .add(1708, "Set rules_profiles.is_built_in to false", SetRulesProfilesIsBuiltInToFalse.class)
      .add(1709, "Make rules_profiles.is_built_in not null", MakeRulesProfilesIsBuiltInNotNullable.class)
      .add(1710, "Delete unused loaded_templates on quality profiles", DeleteLoadedTemplatesOnQProfiles.class)
      .add(1711, "Create table default_qprofiles", CreateTableDefaultQProfiles.class)
      .add(1712, "Populate table default_qprofiles", PopulateTableDefaultQProfiles.class)
      .add(1713, "Drop rules_profiles.is_default", DropIsDefaultColumnFromRulesProfiles.class)
      .add(1714, "Create table org_qprofiles", CreateTableOrgQProfiles.class)
      .add(1715, "Populate table org_qprofiles", PopulateOrgQProfiles.class)
      .add(1716, "Drop columns organization_uuid and parent_kee from rules_profiles", DropOrgUuidAndParentKeeFromRulesProfiles.class)
      .add(1717, "Mark rules_profiles.is_built_in to true for default organization", SetRulesProfilesIsBuiltToTrueForDefaultOrganization.class)
      .add(1718, "Update OrgQProfiles to point to built-in profilesÒ", UpdateOrgQProfilesToPointToBuiltInProfiles.class);
  }
}
