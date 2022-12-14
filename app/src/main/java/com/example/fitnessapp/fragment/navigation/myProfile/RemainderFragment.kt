package com.example.fitnessapp.fragment.navigation.myProfile

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.fitnessapp.*
import com.example.fitnessapp.databinding.FragmentRemainderBinding
import kotlinx.android.synthetic.main.fragment_remainder.*
import java.util.*


class RemainderFragment : Fragment() {
    lateinit var binding: FragmentRemainderBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRemainderBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createNotificationChannel()
        binding.submitButton.setOnClickListener { scheduleNotification() }
    }

    private fun scheduleNotification() {
        val intent = Intent(activity?.applicationContext, Notification::class.java)
        val title = titleET.text.toString()
        val message = messageET.text.toString()
        intent.putExtra(titleExtra, title)
        intent.putExtra(messageExtra, message)

        val pendingIntent = PendingIntent.getBroadcast(
            activity?.applicationContext,
            notificationID,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val alarmManager = activity?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val time = getTime()

        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            time,
            pendingIntent
        )
        showAlert(time, title, message)
    }

    private fun showAlert(time: Long, title: String, message: String) {
        val date = Date(time)
        val dateFormat =
            android.text.format.DateFormat.getLongDateFormat(activity?.applicationContext)
        val timeFormat = android.text.format.DateFormat.getTimeFormat(activity?.applicationContext)

        AlertDialog.Builder(context)
            .setTitle(getString(R.string.notification_scheduled))
            .setMessage(
                getString(R.string.titlee) + title +
                        "\n" + getString(R.string.mess) + message +
                        "\n" + getString(R.string.at) + dateFormat.format(date) + " " + timeFormat.format(
                    date
                )
            )
            .setPositiveButton(getString(R.string.set_remaind)) { _, _ -> }
            .setNegativeButton(getString(R.string.cancel)) { di, _ ->
                di.cancel()
            }
            .show()
    }

    private fun getTime(): Long {
        val minute = timePicker.minute
        val hour = timePicker.hour
        val day = datePicker.dayOfMonth
        val month = datePicker.month
        val year = datePicker.year
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day, hour, minute)
        return calendar.timeInMillis
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        val name = "Notif Channel"
        val desc = "A Description of the Channel"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelID, name, importance)
        channel.description = desc
        val notificationManager =
            activity?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}