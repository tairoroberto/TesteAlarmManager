package com.example.testealarmmanager;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Boolean para verificar se alarme já está ativo
		boolean alarmeAtivo = (PendingIntent.getBroadcast(this, 0,  new Intent("ALARM_DISPARADO"), PendingIntent.FLAG_NO_CREATE) == null);
		
		if (alarmeAtivo) {			
			Log.i("Script","Novo Alarme");
			Intent intent = new Intent("ALARM_DISPARADO");
			PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
			
			//Calendar para pegar o tempo em milissegundos
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(System.currentTimeMillis());
			calendar.add(Calendar.SECOND, 3);//define um delay em segundos
			
			//Instancia um AlarmManager
			AlarmManager alarme = (AlarmManager)getSystemService(ALARM_SERVICE);
			//alarme.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
			
			//trabalahndo com repetição
			alarme.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 5000,pendingIntent);
		}else{
			Log.i("Script","Alarme já ativo");
		}
		
		
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		/*//Cancela o alarme para liberar recursos
		Intent intent = new Intent("ALARM_DISPARADO");
		PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
		AlarmManager alarme = (AlarmManager)getSystemService(ALARM_SERVICE);
		alarme.cancel(pendingIntent);*/
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
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
