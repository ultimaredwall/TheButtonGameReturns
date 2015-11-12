package ashwiniyer.thebuttongamereturns;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class StartScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        //This block of code animates the blinking "tap anywhere to begin message
            TextView myText = (TextView) findViewById(R.id.tapMessageTextView );

            Animation anim = new AlphaAnimation(0.0f, 1.0f);
            anim.setDuration(350); //You can manage the blinking time with this parameter
            anim.setStartOffset(20);
            anim.setRepeatMode(Animation.REVERSE);
            anim.setRepeatCount(Animation.INFINITE);
            myText.startAnimation(anim);

        //Making the relative layout tappable in order to bring up "Enter Name" dialog box
          RelativeLayout rl = (RelativeLayout) findViewById(R.id.relativeLayout);
            rl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(StartScreen.this,GameModeSelect.class);
                    startActivity(intent);
                }
            });
    }



}
