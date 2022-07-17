package com.example.custombroadcastsender

import android.content.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.custombroadcastsender.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private val broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(p0: Context, p1: Intent) {
            if (p0.getString(R.string.custom_broadcast_action) == p1.action) {
                val stringValue = p1.getStringExtra(p0.getString(R.string.string_extra))
                binding.textView.text = stringValue
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            sendBroadCast()
        }
    }

    override fun onStart() {
        super.onStart()
        registerReceiver(
            broadcastReceiver,
            IntentFilter(getString(R.string.custom_broadcast_action)),
        )
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(broadcastReceiver)
    }

    private fun sendBroadCast() {
        val intent = Intent(getString(R.string.custom_broadcast_action))
        intent.putExtra(getString(R.string.string_extra), "Hello From Sender App")
        sendBroadcast(intent,getString(R.string.custom_broadcast_permission_name))
    }
}