package com.example.proyectoemprededor.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectoemprededor.R;
import com.example.proyectoemprededor.model.inmigrantes;
import com.example.proyectoemprededor.viewholderadapter.inmigrantesContenidoAdapter;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import me.saket.bettermovementmethod.BetterLinkMovementMethod;

public class ShowDetail extends AppCompatActivity {

    private inmigrantes inmigrantes;
    private TextView titulocontenido, materialurl, actividadurl, documentourl;
    private RecyclerView recyclerViewContenido;
    private inmigrantesContenidoAdapter inmigrantesContenidoAdapter;
    private YouTubePlayerView youTubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);
        youTubePlayerView = findViewById(R.id.youtube_player_view);
        recyclerViewContenido =  findViewById(R.id.recviewcontenido);
        titulocontenido = findViewById(R.id.titleTextcontenido);
        materialurl = findViewById(R.id.materialurl);
        actividadurl =  findViewById(R.id.actividaurl);
        documentourl =  findViewById(R.id.documentourl);
        documentourl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(inmigrantes.getDocumentourl()));
                startActivity(intent);
            }
        });

        initData();
        initVideo();
        initRecyclerView();

        BetterLinkMovementMethod.linkify(Linkify.WEB_URLS, actividadurl)
                .setOnLinkClickListener(new BetterLinkMovementMethod.OnLinkClickListener() {
                    @Override
                    public boolean onClick(TextView textView, String url) {
                        Toast.makeText(ShowDetail.this, "Website:"+ url, Toast.LENGTH_SHORT).show();
                        return false;
                    }
                });

        BetterLinkMovementMethod.linkify(Linkify.WEB_URLS, materialurl)
                .setOnLinkClickListener(new BetterLinkMovementMethod.OnLinkClickListener() {
                    @Override
                    public boolean onClick(TextView textView, String url) {
                        Toast.makeText(ShowDetail.this, "Website:"+ url, Toast.LENGTH_SHORT).show();
                        return false;
                    }
                });

    }
    private void initData() {
        Intent inputText = getIntent();
        inmigrantes = (inmigrantes) inputText.getSerializableExtra("inmigrantes");
        titulocontenido.setText(inmigrantes.getTitulo());

        materialurl.setText(inmigrantes.getMaterialurl());
        actividadurl.setText(inmigrantes.getActividaurl());
        documentourl.setText(inmigrantes.getDocumentourl());
    }

    private void initRecyclerView() {
        inmigrantesContenidoAdapter = new inmigrantesContenidoAdapter(inmigrantes.getContenido());
        recyclerViewContenido.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewContenido.setAdapter(inmigrantesContenidoAdapter);
    }

    private void initVideo() {
        getLifecycle().addObserver(youTubePlayerView);

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = inmigrantes.getVideo();
                youTubePlayer.loadVideo(videoId, 0);
            }
        });
    }
}