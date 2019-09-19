package com.example.asus.geeknews;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.greenrobot.eventbus.EventBus;

import java.util.Calendar;
import java.util.Date;

import util.DateUtil;

public class CalendarActivity extends AppCompatActivity implements View.OnClickListener {

    private MaterialCalendarView mCalendarView;
    /**
     * 确定
     */
    private Button mBtOk;
    private String mTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        initView();
    }

    private void initView() {
        mCalendarView = (MaterialCalendarView) findViewById(R.id.calendarView);
        mBtOk = (Button) findViewById(R.id.bt_ok);
        mBtOk.setOnClickListener(this);

        mCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.MONDAY)   //每周的第一天
        //格里高利历/儒略历 月份是0开始
                .setMinimumDate(CalendarDay.from(2018, 11, 3))//设置最低的可以选中的日期
                .setMaximumDate(CalendarDay.from(DateUtil.getYear(),
                        DateUtil.getMonth(), DateUtil.getDay()))//设置最大的可选的日期
                .setCalendarDisplayMode(CalendarMode.MONTHS)//展示的样式
                .commit();

        mCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget,
                                       @NonNull CalendarDay date, boolean selected) {
                Date date1 = date.getDate();
                mTime = DateUtil.formatDate(date1);

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.bt_ok:
            sendTime();
                break;
        }
    }

    private void sendTime() {
            if (!TextUtils.isEmpty(mTime)){
                EventBus.getDefault().post(mTime+"");
                finish();
            }
    }
}
