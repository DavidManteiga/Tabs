package com.example.dm2.tabs;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private Resources res;
    private TabHost mitab;
    TabHost.TabSpec llamadas, chats, contactos;
    private ListView milista;
    private Menu myActionBarMenu;
    private ListView listacontactos;

    private Contacto[] datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        res = getResources();
        mitab = (TabHost) findViewById(R.id.mitab);
        mitab.setup();

        TabHost.TabSpec llamadas = mitab.newTabSpec("Llamadas");
        llamadas.setContent(R.id.llamadas);
        llamadas.setIndicator("Llamadas", ContextCompat.getDrawable(this, android.R.drawable.ic_btn_speak_now));
        mitab.addTab(llamadas);

        TabHost.TabSpec chats = mitab.newTabSpec("Chats");
        chats.setContent(R.id.chats);
        chats.setIndicator("Chats", ContextCompat.getDrawable(this, android.R.drawable.ic_btn_speak_now));
        mitab.addTab(chats);

        TabHost.TabSpec contactos = mitab.newTabSpec("Contactos");
        contactos.setContent(R.id.contactos);
        contactos.setIndicator("Contactos", ContextCompat.getDrawable(this, android.R.drawable.ic_btn_speak_now));
        mitab.addTab(contactos);

        mitab.setCurrentTab(0);

        cargarContactos();

        mitab.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                Log.i("AndroidTabsDemo", "Pulsada pestaña.........................................: " + tabId);


                if (tabId.equals("Llamadas")) {
                    myActionBarMenu.findItem(R.id.img1).setIcon(R.drawable.tel);
                    iniciarLista();

                }
                if (tabId.equals("Chats")) {
                    myActionBarMenu.findItem(R.id.img1).setIcon(R.drawable.men);

                }
                if (tabId.equals("Contactos")) {
                    myActionBarMenu.findItem(R.id.img1).setIcon(R.drawable.cont);

                    listacontactos();


                }


            }
        });

        if (Build.VERSION.SDK_INT >= 11) {
            invalidateOptionsMenu();
        }


    }


    /**
     * Creates action bar items.



     @Override public boolean onCreateOptionsMenu(Menu menu) {
     // Inflate the menu; this adds items to the action bar if it is present.
     getMenuInflater().inflate(R.menu.menu_main, menu);
     //myActionBarMenu = menu;

     return true;
     // return false;
     }
     */


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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

    public void iniciarLista() {


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

        Adaptador adaptador = new Adaptador(this, datos);

        milista = (ListView) findViewById(R.id.milista);

        milista.setAdapter(adaptador);


        milista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {

                //Alternativa 1:
                // String opcionSeleccionada =
                //((Titular)a.getItemAtPosition(position)).getTitulo();

                //Alternativa 2:
                String opcionSeleccionada = ((TextView) v.findViewById(R.id.url)).getText().toString();

                AbrirNueva(v, opcionSeleccionada);
                // Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(opcionSeleccionada));
                // startActivity(intent);
                //lblEtiqueta.setText("Opción seleccionada: " + opcionSeleccionada);
            }
        });


    }

    public void cargarContactos() {

        String nombre;
        String telefono = "";
        Uri simUri= Uri.parse("content://icc/adn");


       Cursor cursorSim=this.getContentResolver().query(simUri, null, null, null, null);
        datos = new Contacto[cursorSim.getCount()];
        int i = 0;
        // Log.i("PhoneContact", "total: "+cursorSim.getCount());

        while (cursorSim.moveToNext()) {
            nombre = cursorSim.getString(cursorSim.getColumnIndex("name"));


            telefono = cursorSim.getString(cursorSim.getColumnIndex("number"));
            telefono.replaceAll("\\D", "");
            telefono.replaceAll("&", "");
            nombre = nombre.replace("|", "");

            // Log.i("PhoneContact", "name: "+nombre+" phone: "+telefono);
            Contacto contac = new Contacto(telefono, nombre);
            datos[i] = contac;
            i++;

        }
        cursorSim.close();


    }

    public void listacontactos() {
        listacontactos = null;

        Adaptador2 adaptador2 = new Adaptador2(this, datos);

        listacontactos = (ListView) findViewById(R.id.listacontactos);

        listacontactos.setAdapter(adaptador2);


        listacontactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {

                //Alternativa 1:
                // String opcionSeleccionada =
                //((Titular)a.getItemAtPosition(position)).getTitulo();

                //Alternativa 2:
                String opcionSeleccionada = ((TextView) v.findViewById(R.id.telefono)).getText().toString();
                System.out.print("PhoneContact" + opcionSeleccionada);
                //Log.i("AndroidTabsDemo", "PhoneContact:.............................................. " + opcionSeleccionada);
                call(opcionSeleccionada);
                // Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(opcionSeleccionada));
                // startActivity(intent);
                //lblEtiqueta.setText("Opción seleccionada: " + opcionSeleccionada);
            }
        });


    } 


    private void call(String tel) {
        try {
            Intent callIntent;
            Log.i("AndroidTabsDemo", "PhoneContact:.............................................. " + tel);

            if (tel.length() > 9) {
                tel = tel.substring(3, tel.length());
            }
            Log.i("AndroidTabsDemo", "PhoneContact:.............................................. " + tel);
            callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + Uri.encode(tel)));
            //callIntent.setPackage("com.android.phone");

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    public void requestPermissions(@NonNull String[] permissions, int requestCode)
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for Activity#requestPermissions for more details.
                    return;
                }
            }
            startActivity(callIntent);
        } catch (ActivityNotFoundException activityException) {
            Log.e("dialing-example", "Call failed", activityException);
        }
    }

    public void AbrirNueva (View view, String sel) {
        //System.out.print("seleccionado" + sel);
        Log.i("AndroidTabsDemo", "PhoneContact:............................................... " + sel);
        Toast.makeText(getApplicationContext(),
                "PhoneContact: " + sel, Toast.LENGTH_SHORT);
        //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(sel));
        //startActivity(intent);
    }
}
