package com.adopcan.adopcan_voluntarios;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.adopcan.adopcan_voluntarios.DTO.CalendarInfo;
import com.adopcan.adopcan_voluntarios.DTO.DogTemp;
import com.adopcan.adopcan_voluntarios.Service.CalendarInfoService;
import com.adopcan.adopcan_voluntarios.Service.DogService;
import com.adopcan.adopcan_voluntarios.Utils.DateUtils;

import java.util.List;

public class CalendarActivity extends AppCompatActivity {

    private CalendarInfoService calendarInfoService;
    private List<CalendarInfo> listCalendar;
    private DateUtils dateUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        dateUtils = new DateUtils();
        initListDog();

    }

    /*busca los perros y los agrega en el listview*/
    private void initListDog(){
        calendarInfoService = new CalendarInfoService();
        listCalendar = calendarInfoService.getCalendarDogsByVoluntaryId(1);
        ListView listView = (ListView)findViewById(R.id.listView_calendar);
        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);
    }

    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return listCalendar.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.calendar_info, null);

            ImageView imageView = (ImageView)view.findViewById(R.id.imageView);
            TextView textName = (TextView)view.findViewById(R.id.textView_name);
            TextView textDescription = (TextView)view.findViewById(R.id.textView_description);

            imageView.setImageResource(R.drawable.makephoto);
            textName.setText("fecha: " + dateUtils.getDate(listCalendar.get(i).getEventDate()) +  dateUtils.getHour(listCalendar.get(i).getEventHour())+ listCalendar.get(i).getDog().getName());
            textDescription.setText(listCalendar.get(i).getDescription());

            return view;
        }
    }

}
