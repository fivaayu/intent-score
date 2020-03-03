package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MatchActivity extends AppCompatActivity {
    private TextView homeText;
    private TextView awayText;
    private ImageView homeGambar;
    private ImageView awayGambar;
    private Integer skorHome;
    private TextView Homescore;
    private TextView Awayscore;
    private Integer skorAway;

    private static final String HASIL_KEY = "hasil";

    //1.Menampilkan detail match sesuai data dari main activity
    //2.Tombol add score menambahkan satu angka dari angka 0, setiap kali di tekan
    //3.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang ke ResultActivity, jika seri di kirim text "Draw"
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        //TODO
        homeText = findViewById(R.id.txt_home);
        awayText = findViewById(R.id.txt_away);
        homeGambar=findViewById(R.id.home_logo);
        awayGambar=findViewById(R.id.away_logo);
        Homescore = findViewById(R.id.score_home);
        Awayscore = findViewById(R.id.score_away);

        skorAway = 0;
        skorHome = 0;
        Homescore.setText("0");
        Awayscore.setText("0");


        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            // TODO: display value here
            Bundle extra = getIntent().getExtras();
            Bitmap bmp = extra.getParcelable("imageHome");
            Bitmap bmp2 = extra.getParcelable("imageAway");

            homeGambar.setImageBitmap(bmp);
            awayGambar.setImageBitmap(bmp2);

            homeText.setText(extras.getString("home"));
            awayText.setText(extras.getString("away"));
        }
    }

    public void handleAddHomeScore(View view) {
        skorHome++;
        Homescore.setText(String.valueOf(skorHome));
    }

    public void handleAddAwayScore(View view) {
        skorAway++;
        Awayscore.setText(String.valueOf(skorAway));
    }

    public void handleHasil(View view) {
        String hasil;

        if (skorHome > skorAway) {
            hasil = homeText.getText().toString();
        }else if (skorAway > skorHome) {
                hasil = awayText.getText().toString();
        } else {
            hasil = "Draw";
        }

        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(HASIL_KEY, hasil);
        startActivity(intent);
    }
}