package com.example.testrecyclerview;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.PointTarget;
import com.github.amlcurran.showcaseview.targets.Target;
import com.github.amlcurran.showcaseview.targets.ViewTarget;

public class MainActivity extends ActionBarActivity {

	int id = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity__principal);

		RecyclerView lista = (RecyclerView) findViewById(R.id.lista);
		lista.setHasFixedSize(true);

		LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
		lista.setLayoutManager(mLayoutManager);

		final Adaptador adaptador = new Adaptador(this);
		lista.setAdapter(adaptador);

		final Button btnAgregar = (Button) findViewById(R.id.btnMas);
		btnAgregar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Elemento e = new Elemento(id, "Elemento " + id);
				adaptador.agregarItem(0, e);
				id++;
			}
		});

		Button btnQuitar = (Button) findViewById(R.id.btnMenos);
		btnQuitar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				adaptador.borrarSeleccionados();
			}
		});

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		// noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
