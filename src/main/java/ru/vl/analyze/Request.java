package ru.vl.analyze;

import ru.vl.analyze.util.Util;

import java.text.ParseException;
import java.util.Date;
import java.util.Objects;

public class Request {
    float miliseconds;
    int code;
    Date date;

    public Request(float miliseconds, int code, Date date) {
        this.miliseconds = miliseconds;
        this.code = code;
        this.date = date;
    }

    public float getMiliseconds() {
        return miliseconds;
    }

    public int getCode() {
        return code;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Request{" +
                "miliseconds=" + miliseconds +
                ", code=" + code +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return Float.compare(request.getMiliseconds(), getMiliseconds()) == 0 && getCode() == request.getCode()
                && getDate().equals(request.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMiliseconds(), getCode(), getDate());
    }

    public boolean isBadRequest(int milisec) {
        return ((code >= 500 && code < 600)
                || ((float) miliseconds > milisec));
    }

    public static Request parseRequest(String request) {
        String[] reqs = request.split(" ");

        return new Request(Float.parseFloat(reqs[10]),
                Integer.parseInt(reqs[8]),
                Util.toDate(reqs[3]));
    }
}
