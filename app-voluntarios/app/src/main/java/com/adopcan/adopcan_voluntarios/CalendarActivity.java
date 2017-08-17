package com.adopcan.adopcan_voluntarios;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.adopcan.adopcan_voluntarios.DTO.DogTemp;
import com.adopcan.adopcan_voluntarios.Service.DogService;

import java.util.List;

public class CalendarActivity extends AppCompatActivity {

    private DogService dogService;
    private List<DogTemp> listDog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        dogService = new DogService();
        listDog = dogService.getCalendarDogsByVoluntaryId(1);
        ListView listView = (ListView)findViewById(R.id.listView_calendar);
        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);

    }

    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return listDog.size();
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
            textName.setText(listDog.get(i).getName());
            textDescription.setText(listDog.get(i).getName());

            return view;
        }
    }

}
