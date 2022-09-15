package com.trendit.api.response;

import com.trendit.common.model.response.BaseRes;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LineChartGetRes extends BaseRes {
    List<Integer> data;

    public static LineChartGetRes of(int statusCode, String msg, List<Integer> data) {
        LineChartGetRes lineChartGetRes = new LineChartGetRes();
        lineChartGetRes.setMessage(msg);
        lineChartGetRes.setStatusCode(statusCode);
        lineChartGetRes.setData(data);

        return lineChartGetRes;
    }
}
