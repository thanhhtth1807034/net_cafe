package entity;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Date;

public class Member {
    private String username;
    private String password;
    private String fullName;
    private String avatar;
    private ImageView imageAvatar;
    private long remainTime;
    private int status;

    public Member() {
    }

    public Member(String username, String avatar, long remainTime) {
        this.username = username;
        this.avatar = avatar;
        this.imageAvatar = new ImageView( new Image(this.avatar, true));
        this.imageAvatar.setFitWidth(50);
        this.imageAvatar.setFitHeight(50);
        this.remainTime = remainTime;
    }

    public Member(String username, String password, String fullName, String avatar, long remainTime, int status) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.avatar = avatar;
        this.imageAvatar = new ImageView( new Image(this.avatar, true));
        this.imageAvatar.setFitWidth(50);
        this.imageAvatar.setFitHeight(50);
        this.remainTime = remainTime;
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public ImageView getImageAvatar() {
        return imageAvatar;
    }

    public void setImageAvatar(ImageView imageAvatar) {
        this.imageAvatar = imageAvatar;
    }

    public long getRemainTime() {
        return remainTime;
    }

    public void setRemainTime(long remainTime) {
        this.remainTime = remainTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
