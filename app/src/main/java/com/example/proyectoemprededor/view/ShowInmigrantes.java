package com.example.proyectoemprededor.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;

import com.example.proyectoemprededor.R;
import com.example.proyectoemprededor.model.inmigrantes;
import com.example.proyectoemprededor.viewholderadapter.InmigrantesAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class ShowInmigrantes extends AppCompatActivity {

    private RecyclerView recyclerView;
    private InmigrantesAdapter inmigrantesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_inmigrantes);
        recyclerView = findViewById(R.id.recview);
        recyclerView.setLayoutManager(new CustomLinearLayoutManager(this));

        FirebaseRecyclerOptions<inmigrantes> options =
                new FirebaseRecyclerOptions.Builder<inmigrantes>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("inmigrantes"), inmigrantes.class)
                        .build();

        Log.d("Options"," data : "+options);

        inmigrantesAdapter = new InmigrantesAdapter(options);
        recyclerView.setAdapter(inmigrantesAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        inmigrantesAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        inmigrantesAdapter.stopListening();
    }

    public class CustomLinearLayoutManager extends LinearLayoutManager {
        public CustomLinearLayoutManager(Context context) {
            super(context);
        }

        public CustomLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
            super(context, orientation, reverseLayout);
        }

        public CustomLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
            super(context, attrs, defStyleAttr, defStyleRes);
        }

        //Generate constructors

        @Override
        public boolean supportsPredictiveItemAnimations() {
            return false;
        }
    }
}