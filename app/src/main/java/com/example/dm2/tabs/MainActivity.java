package com.example.dm2.tabs;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Resources res;
    private TabHost mitab;
    TabHost.TabSpec llamadas,chats,contactos;
    private ListView milista;
    private  Menu myActionBarMenu;
    private ListView lista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        res=getResources();
        mitab = (TabHost)findViewById(R.id.mitab);
        mitab.setup();

        TabHost.TabSpec llamadas=mitab.newTabSpec("Llamadas");
        llamadas.setContent(R.id.llamadas);
        llamadas.setIndicator("Llamadas", ContextCompat.getDrawable(this, android.R.drawable.ic_btn_speak_now));
        mitab.addTab(llamadas);

        TabHost.TabSpec chats=mitab.newTabSpec("Chats");
        chats.setContent(R.id.chats);
        chats.setIndicator("Chats", ContextCompat.getDrawable(this, android.R.drawable.ic_btn_speak_now));
        mitab.addTab(chats);

        TabHost.TabSpec contactos=mitab.newTabSpec("Contactos");
        contactos.setContent(R.id.contactos);
        contactos.setIndicator("Contactos", ContextCompat.getDrawable(this, android.R.drawable.ic_btn_speak_now));
        mitab.addTab(contactos);

        mitab.setCurrentTab(0);



        mitab.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                Log.i("AndroidTabsDemo", "Pulsada pestaña: " + tabId);


                if (tabId.equals("Llamadas")) {
                    myActionBarMenu.findItem(R.id.img1).setIcon(R.drawable.tel);
                    iniciarLista();

                }
                if (tabId == "Chats") {
                    myActionBarMenu.findItem(R.id.img1).setIcon(R.drawable.men);

                }
                if (tabId == "Contactos")
                {
                    myActionBarMenu.findItem(R.id.img1).setIcon(R.drawable.cont);
                    lista = (ListView) findViewById(R.id.lista);





                }



            }
        });

        if (Build.VERSION.SDK_INT >= 11) {
            invalidateOptionsMenu();
        }


    }



    /**
     * Creates action bar items.



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        //myActionBarMenu = menu;

        return true;
       // return false;
    }
    */


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        if (Build.VERSION.SDK_INT >= 11) {
            selectMenu(menu);
        }
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (Build.VERSION.SDK_INT < 11) {
            selectMenu(menu);
        }
        return true;
    }

    private void selectMenu(Menu menu) {
        myActionBarMenu = menu;
        menu.clear();
        MenuInflater inflater = getMenuInflater();


            inflater.inflate(R.menu.menu_main, menu);

    }





    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void iniciarLista()
    {


        MisWebs[] datos =
                new MisWebs[]{
                        new MisWebs("Google", "http://www.google.es", ContextCompat.getDrawable(this, R.drawable.google)),
                        new MisWebs("Bing", "http://www.bing.com", ContextCompat.getDrawable(this, R.drawable.bing)),
                        new MisWebs("yahoo", "http://www.yahoo.es", ContextCompat.getDrawable(this, R.drawable.yahoo)),
                        new MisWebs("Google", "http://www.google.es", ContextCompat.getDrawable(this, R.drawable.google)),
                        new MisWebs("Bing", "http://www.bing.com", ContextCompat.getDrawable(this, R.drawable.bing)),
                        new MisWebs("yahoo", "http://www.yahoo.es", ContextCompat.getDrawable(this, R.drawable.yahoo)),
                        new MisWebs("Google", "http://www.google.es", ContextCompat.getDrawable(this, R.drawable.google)),
                        new MisWebs("Bing", "http://www.bing.com", ContextCompat.getDrawable(this, R.drawable.bing)),
                        new MisWebs("yahoo", "http://www.yahoo.es", ContextCompat.getDrawable(this, R.drawable.yahoo))
                };

        Adaptador adaptador =  new Adaptador(this, datos);

        milista = (ListView)findViewById(R.id.milista);

        milista.setAdapter(adaptador);


        milista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {

                //Alternativa 1:
                // String opcionSeleccionada =
                //((Titular)a.getItemAtPosition(position)).getTitulo();

                //Alternativa 2:
                String opcionSeleccionada = ((TextView) v.findViewById(R.id.url)).getText().toString();
                AbrirNueva (v , opcionSeleccionada);
                // Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(opcionSeleccionada));
                // startActivity(intent);
                //lblEtiqueta.setText("Opción seleccionada: " + opcionSeleccionada);
            }
        });


    }
    public void AbrirNueva (View view, String sel) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(sel));
        startActivity(intent);
    }
}
