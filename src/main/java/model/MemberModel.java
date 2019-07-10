package model;

import entity.Member;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MemberModel {
    public boolean updateMember(Member member) {
        try {
            String sqlCommand = "insert into members (username, password, fullName, avatar, remainTime, status) values (?,?,?,?,?,?)";
            PreparedStatement preparedStatement = ConnectionHelper.getConnection().prepareStatement(sqlCommand);
            preparedStatement.setString(1, member.getUsername());
            preparedStatement.setString(2, member.getPassword());
            preparedStatement.setString(3, member.getFullName());
            preparedStatement.setString(4, member.getAvatar());
            preparedStatement.setLong(5, member.getRemainTime());
            preparedStatement.setInt(6, member.getStatus());
            preparedStatement.execute();
            JOptionPane.showMessageDialog(null, "Member Updated !");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
