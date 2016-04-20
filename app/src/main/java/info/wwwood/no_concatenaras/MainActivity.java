package info.wwwood.no_concatenaras;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String LIPSUM="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum tellus libero, consectetur in nisi quis, condimentum tristique velit. Suspendisse molestie vel risus sed iaculis. Mauris eget neque eget dolor finibus pellentesque in ornare dolor. Praesent maximus, libero non tempus euismod, justo sem commodo nibh, vitae gravida nunc odio ut nisl. Sed efficitur elit auctor tellus maximus mollis vitae in massa. Fusce in faucibus metus. Nulla vel finibus orci. Fusce quam turpis, ornare commodo venenatis at, rutrum nec risus.";
    private EditText ETIteraciones=null;
    private TextView TVTiempo=null;
    private Button BTConcatenando=null;
    private Button BtStringBuilder=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ETIteraciones=(EditText) findViewById(R.id.ETIteraciones);
        TVTiempo=(TextView) findViewById(R.id.TVTiempo);
        BTConcatenando=(Button) findViewById((R.id.BTConcatenando));
        BtStringBuilder=(Button) findViewById((R.id.BtStringBuilder));

        BTConcatenando.setOnClickListener(this); //IMPLEMENTEM CONTROLADORA DE CLICKS
        BtStringBuilder.setOnClickListener(this); //IMPLEMENTEM CONTROLADORA DE CLICKS

    }

    @Override
    public void onClick(View v) {
        Intent intent=null;

        switch (v.getId()){
            case R.id.BTConcatenando:

                Concatenar();
                break;
            case R.id.BtStringBuilder:
                NoConcatenar();
                break;
        }

    }
    private void Concatenar(){

        /***************CODI SINCRONO ***********************/

        /*long time_start, time_end;
        time_start = System.currentTimeMillis();

        int iteraciones = Integer.parseInt(ETIteraciones.getText().toString());
        String TextConcat="";
        for (int i=0; i<= iteraciones;i++){
            TextConcat+=LIPSUM;
        }
        Log.d("Concatenar: ",String.valueOf(TextConcat));
        time_end = System.currentTimeMillis();
        TVTiempo.setText("Temps emprat "+ ( time_end - time_start ) +" milliseconds");*/

        /**********************************************************************/

        /***************CODI ASINCRONO ***********************/
        int iteraciones = Integer.parseInt(ETIteraciones.getText().toString());
        new Concatenar().execute(iteraciones,LIPSUM,TVTiempo); //LLANÇAR CRIDA ASINCRONA
        /**********************************************************************/

    }
    private void NoConcatenar(){
        long time_start, time_end;
        time_start = System.currentTimeMillis();


        int iteraciones = Integer.parseInt(ETIteraciones.getText().toString());
        StringBuilder TextBuild = new StringBuilder();
        for (int i=0; i<= iteraciones;i++){
            //TextConcat=TextConcat + LIPSUM;
            TextBuild.append(LIPSUM);
        }
        TextBuild.toString();
        Log.d("String Builder: ",String.valueOf(TextBuild));
        time_end = System.currentTimeMillis();
        TVTiempo.setText("Temps emprat "+ ( time_end - time_start ) +" milliseconds");
    }
}
class Concatenar extends AsyncTask<Object, Object, Object> //els 3 objects són per als 3 mètodes implementats a la classe
{
    EditText ETIteraciones=null;
    String LIPSUM="";
    private long tiempo;
    @Override
    protected Object doInBackground(Object... params) { //params és un array de paràmetres de 0 a n
        long time_start, time_end;
        time_start = System.currentTimeMillis();
        /*EditText ETIteraciones=(EditText)params[0];
        String LIPSUM=(String) params[1];*/

        int iteraciones = Integer.parseInt(params[0].toString());
        String LIPSUM=String.valueOf(params[1]);

        TextView TVTiempo=(TextView) params[2];

        String TextConcat="";
        for (int i=0; i<= iteraciones;i++){
            TextConcat+=LIPSUM;
        }
        Log.d("Concatenar: ",String.valueOf(TextConcat));
        time_end = System.currentTimeMillis();

        tiempo=time_end - time_start;
        return TVTiempo;
    }

    @Override
    protected void onProgressUpdate(Object... values) {

    }

    @Override
    protected void onPostExecute(Object result) {
        super.onPostExecute(result);
        TextView TVTiempo=(TextView) result;
        TVTiempo.setText(String.valueOf(tiempo));
    }

}
class NoConcatenar
{

}
