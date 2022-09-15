package com.trendit.api.response;

import com.trendit.api.response.data.BarChartData;
import com.trendit.common.model.response.BaseRes;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BarChartGetRes extends BaseRes {
    List<BarChartData> data;

    public static BarChartGetRes of(int statusCode, String msg, List<BarChartData> barChartDataList) {
        BarChartGetRes barChartGetRes = new BarChartGetRes();
        barChartGetRes.setMessage(msg);
        barChartGetRes.setStatusCode(statusCode);
        barChartGetRes.setData(barChartDataList);

        return barChartGetRes;
    }
}
