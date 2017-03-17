package bean;

import java.sql.Date;

/**
 * Created by LeeKane on 17/3/16.
 */
public class Plan {

    private int hostelId;
    private String startData;
    private String overData;
    private int roomNum;
    private double price;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHostelId() {
        return hostelId;
    }

    public void setHostelId(int hostelId) {
        this.hostelId = hostelId;
    }

    public String getStartData() {
        return startData;
    }

    public void setStartData(String startData) {
        this.startData = startData;
    }

    public String getOverData() {
        return overData;
    }

    public void setOverData(String overData) {
        this.overData = overData;
    }

    public int getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(int rommNum) {
        this.roomNum = rommNum;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
