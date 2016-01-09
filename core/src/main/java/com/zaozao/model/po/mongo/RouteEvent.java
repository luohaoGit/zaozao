package com.zaozao.model.po.mongo;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by luohao on 15/12/19.
 */
@Document(collection = "RouteEvent")
public class RouteEvent extends MongoBase{

    private String from;
    private String to;
    private String fromTel;
    private String toTel;
    private String type;
    private String fromCar;
    private String toCar;
    private String source;
    private String symbol;

    public RouteEvent(String from, String to, String type) {
        this.from = from;
        this.to = to;
        this.type = type;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFromTel() {
        return fromTel;
    }

    public void setFromTel(String fromTel) {
        this.fromTel = fromTel;
    }

    public String getToTel() {
        return toTel;
    }

    public void setToTel(String toTel) {
        this.toTel = toTel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFromCar() {
        return fromCar;
    }

    public void setFromCar(String fromCar) {
        this.fromCar = fromCar;
    }

    public String getToCar() {
        return toCar;
    }

    public void setToCar(String toCar) {
        this.toCar = toCar;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
