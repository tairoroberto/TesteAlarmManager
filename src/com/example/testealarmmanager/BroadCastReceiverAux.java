package com.example.testealarmmanager;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class BroadCastReceiverAux extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// log da notificação
		Log.i("Script", "-> Alarme");
		gerarNotificacao(context,new Intent(context, MainActivity.class),"Nova mensagem","Titulo","Descrição da mensagem");

	}
	
	public void gerarNotificacao(Context context, Intent intent, CharSequence ticker, CharSequence title,CharSequence descricao) {
		//Instacia de notification
		NotificationManager manager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
		
		//Pega a intent Pendente que foi enviada
		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
		
		//Constroi builder para criar incone, texto e etc.
		NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
		
		//seta o TickerText, texto que aparece e some
		builder.setTicker(ticker);
		builder.setContentTitle(title);
		builder.setContentText(descricao);
		builder.setSmallIcon(R.drawable.ic_launcher); //icone pequeno
		builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.drawable.ic_launcher)); //icone grande
		
		//Adiciona a intent Pendente para ser enviar		
		builder.setContentIntent(pendingIntent);
		
		//coloca o builder na notificação em si
		Notification notification = builder.build();
		
		//coloca a vibração
		notification.vibrate = new long[]{150,300,150,600};
		
		//Flag para cancelar notificação automaticamente de pois de acabar
		notification.flags = Notification.FLAG_AUTO_CANCEL;
		manager.notify(R.drawable.ic_launcher, notification);
		
		//adiciona o toque
		try {
			Uri som = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
			Ringtone toque = RingtoneManager.getRingtone(context, som);
			toque.play();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
