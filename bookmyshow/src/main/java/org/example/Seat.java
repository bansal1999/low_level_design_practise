package org.example;

import org.example.Enums.SeatStatus;
import org.example.Enums.SeatType;

public class Seat {
    private String id;
    private String row;
    private int column;
    private SeatType type;
    private double price;
    private SeatStatus status;

    public Seat(String id, String row, int column, double price, SeatType type) {
        this.id = id;
        this.row = row;
        this.column = column;
        this.price = price;
        this.type = type;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public SeatType getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public SeatStatus getStatus() {
        return status;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setType(SeatType type) {
        this.type = type;
    }

    public void setStatus(SeatStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "id='" + id + '\'' +
                ", row='" + row + '\'' +
                ", column=" + column +
                ", type=" + type +
                ", price=" + price +
                ", status=" + status +
                '}';
    }
}
