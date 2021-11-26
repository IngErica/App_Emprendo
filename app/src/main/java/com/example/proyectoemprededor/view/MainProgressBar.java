package com.example.proyectoemprededor.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.baoyachi.stepview.VerticalStepView;
import com.example.proyectoemprededor.R;

import java.util.ArrayList;
import java.util.List;


public class MainProgressBar extends AppCompatActivity {

    VerticalStepView verticalStepView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_progress_bar);

        verticalStepView = findViewById(R.id.verticalStepView);

        List<String> source = new ArrayList<>();
        source.add("Unidad 1");
        source.add("Unidad 2");
        source.add("Unidad 3");
        source.add("Unidad 4");
        source.add("Unidad 5");
        source.add("Unidad 6");
        source.add("Unidad 7");

        //verticalStepView.setStepsViewIndicatorComplectingPosition()

    }
}