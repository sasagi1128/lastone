package edu.byuh.cis.cs300.slidefall2024;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;

public class SplashActivity extends Activity {

    private ImageView iv;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        iv = new ImageView(this);
        iv.setImageResource(R.drawable.splash);
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        setContentView(iv);
    }

    @Override
    public boolean onTouchEvent(MotionEvent m) {
        if (m.getAction() == MotionEvent.ACTION_UP) {
            float w = iv.getWidth();
            float h = iv.getHeight();
            RectF onePlayer = new RectF(76/600f*w,600/1024f*h,528/600f*w,712/1024f*h);
            RectF twoPlayerOneDevice = new RectF(75/600f*w,733/1024f*h,528/600f*w,846/1024f*h);
            RectF helpButton = new RectF(16/600f*w, 936/1024f*h, 96/600f*w, 1009/1024f*h);
            float x = m.getX();
            float y = m.getY();
            if (onePlayer.contains(x,y)) {
                launch(GameMode.ONE_PLAYER.toString());
            }
            else if (twoPlayerOneDevice.contains(x, y)) {
                launch(GameMode.TWO_PLAYER.toString());
            }
            else if (helpButton.contains(x, y)) {
                AlertDialog.Builder ab = new AlertDialog.Builder(this);
                ab.setTitle(R.string.set_title);
                ab.setNeutralButton(R.string.set_neutral_button, null);
                ab.setMessage(R.string.set_message);
                ab.show();
            }else {
                //TODO open the "settings"
                Intent japeth = new Intent(this, Prefs.class);
                startActivity(japeth);}
        }
        return true;
    }

    private void launch(String mode) {
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("GAME_MODE", mode);
        startActivity(i);
        finish();
    }


}