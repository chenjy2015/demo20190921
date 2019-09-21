package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ScrollActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Car> cars = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);

        recyclerView = findViewById(R.id.rc);

        cars.add(new Car("吉利-帝豪", 185));
        cars.add(new Car("比亚迪-秦", 190));
        cars.add(new Car("奔驰-E300", 280));
        cars.add(new Car("宝马-530", 2850));
        cars.add(new Car("奥迪-A6", 290));

        cars.add(new Car("吉利-帝豪", 185));
        cars.add(new Car("比亚迪-秦", 190));
        cars.add(new Car("奔驰-E300", 280));
        cars.add(new Car("宝马-530", 2850));
        cars.add(new Car("奥迪-A6", 290));

        cars.add(new Car("吉利-帝豪", 185));
        cars.add(new Car("比亚迪-秦", 190));
        cars.add(new Car("奔驰-E300", 280));
        cars.add(new Car("宝马-530", 2850));
        cars.add(new Car("奥迪-A6", 290));

        recyclerView.setAdapter(new CarAdapter());
        LinearLayoutManager manager = new LinearLayoutManager(this);
//        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL));
//        new LinearSnapHelper().attachToRecyclerView(recyclerView);
    }

    public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder> {

        @NonNull
        @Override
        public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(ScrollActivity.this).inflate(R.layout.layout_car, parent, false);
            Log.d("onCreateViewHolder","viewType");
            return new CarViewHolder(view);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
            holder.carModel.setText("汽车型号: " + cars.get(position).model);
            holder.carSpeed.setText("最高时速: " + cars.get(position).speed);

        }

        @Override
        public int getItemCount() {
            return cars.size();
        }

        public class CarViewHolder extends RecyclerView.ViewHolder {

            TextView carModel;
            TextView carSpeed;

            public CarViewHolder(@NonNull View itemView) {
                super(itemView);
                carModel = itemView.findViewById(R.id.model);
                carSpeed = itemView.findViewById(R.id.speed);
            }
        }
    }

    public class Car {
        public String model;
        public int speed;

        public Car(String model, int speed) {
            this.model = model;
            this.speed = speed;
        }
    }
}
