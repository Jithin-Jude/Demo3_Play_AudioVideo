package com.inc.mountzoft.demo3_play_audiovideo;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mp;
    int MY_INTENT_CLICK=302;
    int MY_INTENT_CLICK_FOR_AUDIO=304;
    Uri selectedFileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void chooseVideo(View view){

        Intent intent = new Intent();
        intent.setType("*/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select File"),MY_INTENT_CLICK);
    }

    public void openButtonFunction(View view){

        Intent intent = new Intent();
        intent.setType("*/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select File"),MY_INTENT_CLICK_FOR_AUDIO);
    }

    void playVideo(){
        VideoView videoView =(VideoView)findViewById(R.id.videoView);
        MediaController mediaController= new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(selectedFileUri);
        videoView.requestFocus();

        videoView.start();
    }

    void playAudio(){
        mp =new MediaPlayer();
        Toast.makeText(this, selectedFileUri.getPath(), Toast.LENGTH_SHORT).show();
        try{
            mp.setDataSource(selectedFileUri.getPath());//Write your location here
            mp.prepare();
            Toast.makeText(this, "Audio file Ready to rock & Roll, Press Play", Toast.LENGTH_SHORT).show();

        }catch(Exception e){
            e.printStackTrace();
            Toast.makeText(this, "Error loading file ! Please Choose from File Exploer", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (resultCode == RESULT_OK)
        {
            if (requestCode == MY_INTENT_CLICK)
            {
                if (null == data) return;
                selectedFileUri = data.getData();
                playVideo();
            }
            else if(requestCode == MY_INTENT_CLICK_FOR_AUDIO){
                if (null == data) return;
                selectedFileUri = data.getData();
                playAudio();
            }
        }
    }

    public void playButtonFunction(View view) {
        try {
            mp.start();
        }catch(Exception e){
        e.printStackTrace();
        Toast.makeText(this, "No file selected !", Toast.LENGTH_SHORT).show();
    }
    }

    public void pauseButtonFunction(View view) {
        mp.pause();
    }

    public void stopButtonFunction(View view) {
        mp.stop();
    }
}
