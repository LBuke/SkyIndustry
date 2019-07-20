package com.destinymc.achievement;

import com.destinymc.achievement.profile.AchievementData;
import com.destinymc.profile.Profile;
import com.google.common.collect.Lists;

import java.sql.*;
import java.util.List;
import java.util.UUID;

/**
 * @author Luke Bingham
 */
public final class AchievementDAO {

    public static List<AchievementData> getAchievementData(Connection connection, Profile profile) {
        List<AchievementData> dataList = Lists.newArrayList();

        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM `achievement` WHERE `uuid`=UNHEX(?);")) {
            statement.setString(1, profile.getUniqueId().toString().replaceAll("-", ""));

            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    int achievementId = result.getInt("id");
                    int data = result.getInt("data");
                    boolean complete = result.getBoolean("complete");

                    dataList.add(new AchievementData(profile, AchievementManager.getAchievementById(achievementId), data, complete));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dataList;
    }

    public static void setData(Connection connection, UUID uuid, int id, int data) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO `achievement` (`uuid`, `id`, `data`) VALUES (UNHEX(?), ?, ?) ON DUPLICATE KEY UPDATE `data`=?;")) {
            statement.setString(1, uuid.toString().replaceAll("-", ""));
            statement.setInt(2, id);
            statement.setInt(3, data);
            statement.setInt(4, data);

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setComplete(Connection connection, UUID uuid, int id, boolean complete) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO `achievement` (`uuid`, `id`, `complete`, `date_complete`) VALUES (UNHEX(?), ?, ?, ?) ON DUPLICATE KEY UPDATE `complete`=?, `date_complete`=?;")) {
            statement.setString(1, uuid.toString().replaceAll("-", ""));
            statement.setInt(2, id);
            statement.setBoolean(3, complete);
            statement.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            statement.setBoolean(5, complete);
            statement.setTimestamp(6, new Timestamp(System.currentTimeMillis()));

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
