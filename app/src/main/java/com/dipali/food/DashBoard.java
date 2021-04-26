package com.dipali.food;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.Toast;

import com.dipali.food.DynamicLoadmore.LoadMore;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DashBoard extends AppCompatActivity {

    //Variables
    private RecyclerView recyclerView;
    private StaticRvAdapter staticRvAdapter;

    List<DynamicRvModel> list = new ArrayList<>();
    DynamicRvAdapter dynamicRvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }

        ArrayList<StaticRvModel> item = new ArrayList<>();
        item.add(new StaticRvModel(R.drawable.pizza,"Pizza"));
        item.add(new StaticRvModel(R.drawable.hamburger,"Burger"));
        item.add(new StaticRvModel(R.drawable.fries,"Fries"));
        item.add(new StaticRvModel(R.drawable.sandwich,"Sandwich"));
        item.add(new StaticRvModel(R.drawable.icecream,"Ice Cream"));

        recyclerView = findViewById(R.id.rv_1);
        staticRvAdapter = new StaticRvAdapter(item);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(staticRvAdapter);

        list.add(new DynamicRvModel("Burger"));
        list.add(new DynamicRvModel("Burger"));
        list.add(new DynamicRvModel("Burger"));
        list.add(new DynamicRvModel("Burger"));
        list.add(new DynamicRvModel("Burger"));
        list.add(new DynamicRvModel("Burger"));
        list.add(new DynamicRvModel("Burger"));
        list.add(new DynamicRvModel("Burger"));
        list.add(new DynamicRvModel("Burger"));
        list.add(new DynamicRvModel("Burger"));
        list.add(new DynamicRvModel("Burger"));
        list.add(new DynamicRvModel("Burger"));
        list.add(new DynamicRvModel("Burger"));
        list.add(new DynamicRvModel("Burger"));
        list.add(new DynamicRvModel("Burger"));
        list.add(new DynamicRvModel("Burger"));
        list.add(new DynamicRvModel("Burger"));
        list.add(new DynamicRvModel("Burger"));
        list.add(new DynamicRvModel("Burger"));

        RecyclerView drv = findViewById(R.id.rv_2);
        drv.setLayoutManager(new LinearLayoutManager(this));
        dynamicRvAdapter = new DynamicRvAdapter(drv,this,list);
        drv.setAdapter(dynamicRvAdapter);

        dynamicRvAdapter.setLoadMore(new LoadMore() {
            @Override
            public void onLoadMore() {
                if(list.size()<=10){
                    list.add(null);
                    dynamicRvAdapter.notifyItemInserted(list.size()-1);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            list.remove(list.size()-1);
                            dynamicRvAdapter.notifyItemRemoved(list.size());

                            int index = list.size();
                            int end = index+10;
                            for(int i = index;i<end;i++){
                                String name = UUID.randomUUID().toString();
                                DynamicRvModel item = new DynamicRvModel(name);
                                list.add(item);
                            }
                            dynamicRvAdapter.notifyDataSetChanged();
                            dynamicRvAdapter.setLoaded();
                        }
                    },4000);
                }
                else
                    Toast.makeText(DashBoard.this,"Data Completed",Toast.LENGTH_SHORT).show();
            }
        });
    }
}