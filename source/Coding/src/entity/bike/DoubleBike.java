package entity.bike;

import controller.OutputController;
import entity.costcalculator.DepositStrategy;
import entity.costcalculator.Strategy1;
import utils.Configs;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DoubleBike extends Bike{
    private int numOfPedal;
    public DoubleBike(ResultSet res) throws SQLException {
        super(res);
        this.costScale = 1.5F;
        this.numOfPedal = res.getInt("numOfPedal");
    }


    @Override
    public int calculateDepositCost() {
        depositCostCalculator = new DepositStrategy();
        return super.calculateDepositCost();
    }

    @Override
    public long calculateRentCost(String startTime, String endTime) {
        rentCostCalculator = new Strategy1();
        return super.calculateRentCost(startTime, endTime);
    }

    @Override
    public String getInfo() {
        return "Id: " + OutputController.Convert(Integer.toString(getId())) + "\n" +
                "Num of Seats: " + getNumOfSeat() + "\n" +
                "Type: " + getType() + "\n" +
                "Value: " + getValueOfBike() + " VNĐ" + "\n" +
                "Status: " + getBikeStatus();
    }

    @Override
    public String getRentInfo() {
        return "Id: " + OutputController.Convert(Integer.toString(getId())) + "\n" +
                "Num of Seats: " + getNumOfSeat() + "\n" +
                "Type: " + getType() + "\n" +
                "Status: " + getBikeStatus() + "\n" +
                "Value: " + getValueOfBike() + " VNĐ" + "\n" +
                "Deposit: " + calculateDepositCost() + " VNĐ" + "\n" +
                "Start time: " + Configs.rentTransaction.getRentTime();
    }
}
