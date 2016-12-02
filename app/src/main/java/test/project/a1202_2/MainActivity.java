package test.project.a1202_2;

import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click1(View v)
    {
        File f1 = getFilesDir();
        Log.d("FILE", f1.toString());
        File f2 = getCacheDir();
        Log.d("FILE", f2.toString());
        File f3 = getExternalFilesDir("");
        Log.d("FILE", f3.toString());
    }

    public void clickWrite(View v)
    {
        FileOutputStream fOut = null;
        try {

            fOut = openFileOutput("mydata.txt", MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);  // 寫入資料
            osw.write("She sell sea shells on the sea shore .");
            osw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clickRead(View v)
    {
        char[] buffer = new char[1];
        FileReader fr = null;
        StringBuilder sb = new StringBuilder();
        File file = new File(getFilesDir() + "/" + "mydata.txt");

        try {
            fr = new FileReader(file);
            while (fr.read(buffer)!= -1) {
                sb.append(new String(buffer));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("READFILE", sb.toString());

    }

    public void clickReadRaw(View v)
    {
        InputStream is = null;
        InputStreamReader reader = null;
        StringBuilder sb = new StringBuilder();
        is = getResources().openRawResource(R.raw.test1);

        char[] buffer = new char[1];
        try {
            reader = new InputStreamReader(is, "UTF-8");
            while (reader.read(buffer) != -1) {
                sb.append(new String(buffer));
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d("RAWREAD", sb.toString());
    }

    public void click5(View v)
    {
        File f3 = getExternalFilesDir("");
        Log.d("FILE", f3.toString());
        String wFile = f3.toString() + File.separator + "myfile2.txt";
        Log.d("FILE", "wFile:" + wFile);
        try {
            FileOutputStream fos = new FileOutputStream(wFile);
            OutputStreamWriter osw = new OutputStreamWriter(fos);  // 寫入資料
            osw.write("She sell sea shells on the sea shore .");
            osw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void click6(View v)
    {
        File f3 = getExternalFilesDir("");
        File f4 = new File(f3.toString() + File.separator + "test6");
        f4.mkdir();
    }
    public void click7(View v)
    {
        int permission = ActivityCompat.checkSelfPermission(this,
                WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            //未取得權限，向使用者要求允許權限
            Log.d("PERM", "沒有權限");

            ActivityCompat.requestPermissions(this,
                    new String[] {WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE},
                    123 // RequestCode
            );
        }
        else
        {
            Log.d("PERM", "有權限");
            File f3 = Environment.getExternalStorageDirectory();
            Log.d("EXT", f3.toString());
            File f4 = new File(f3.toString() + File.separator + "test7");
            f4.mkdir();
        }
    }
}
