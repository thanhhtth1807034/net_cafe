package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entity.Member;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import model.ConnectionHelper;
import model.MemberModel;

import javax.swing.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private JFXTextField txtAvatar;

    @FXML
    private TableColumn<Member, String> columnUsername;

    @FXML
    private JFXTextField txtUsername;

    @FXML
    private TableColumn<Member, Long> columnRemainTime;

    @FXML
    private JFXButton btnDeactive;

    @FXML
    private JFXTextField txtFullname;

    @FXML
    private JFXTextField txtStatus;

    @FXML
    private JFXButton btnUpdate;


    @FXML
    private TableView<Member> tableView;

    @FXML
    private TableColumn<Member, ImageView> columnAvatar;

    @FXML
    private JFXTextField txtRemain;

    @FXML
    private JFXTextField txtPwd;



    private MemberModel memberModel = new MemberModel();

    public void create(Member member ) {
        memberModel.updateMember(member);
    }
    @FXML
    void doUpdate(ActionEvent event) {
        Controller memberController = new Controller();
        Member member = new Member(txtUsername.getText(),
                txtPwd.getText(),
                txtFullname.getText(),
                txtAvatar.getText(),
                Integer.parseInt(txtRemain.getText()),
                Integer.parseInt(txtStatus.getText()));

        for (int i = 0; i < tableView.getItems().size(); i++) {
            Member member1 = tableView.getItems().get(i);
            if (member1.getUsername().equals(member.getUsername())) {
                tableView.getItems().remove(i);
            }
        }
        memberController.create(member);
        tableView.getItems().clear();
        tableView.getItems().add(member);
    }

    @FXML
    void doDeactive(ActionEvent event) {

    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.tableView.setRowFactory(new Callback<TableView<Member>, TableRow<Member>>() {
            @Override
            public TableRow<Member> call(TableView<Member> param) {
                TableRow<Member> memberTableRow = new TableRow<>();
                memberTableRow.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (!memberTableRow.isEmpty()) {
                            Member member = memberTableRow.getItem();
                            txtUsername.setText(member.getUsername());
                            txtPwd.setText(member.getPassword());
                            txtFullname.setText(member.getFullName());
                            txtAvatar.setText(member.getAvatar());
                            txtRemain.setText(String.valueOf(member.getRemainTime()));
                            txtStatus.setText(String.valueOf(member.getStatus()));
                        }
                    }
                });
                return memberTableRow;
            }
        });

        columnUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        columnAvatar.setCellValueFactory(new PropertyValueFactory<>("imageAvatar"));
        columnRemainTime.setCellValueFactory(new PropertyValueFactory<>("remainTime"));

        tableView.setItems(members());
    }

    private ObservableList<Member> members() {
        ObservableList<Member> members = FXCollections.observableArrayList();

        try {
            Connection conn = ConnectionHelper.getConnection();
            String query = "SELECT * FROM members";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Member member;
            while (rs.next()) {
                member = new Member(rs.getString("username"),
                        rs.getString("avatar"),
                        rs.getLong("remainTime")
                        );
                members.add(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }
}

