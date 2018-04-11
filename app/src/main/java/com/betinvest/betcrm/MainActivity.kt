package com.betinvest.betcrm

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.TextView
import android.widget.Toast
import android.view.Menu;
import android.view.MenuItem;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;import javax.net.ssl.HttpsURLConnection;

class MainActivity : AppCompatActivity() {

    private var count: TextView? = null
    private val USER_AGENT = "Mozilla/5.0"
    private var url: String? = "http://de2crm02d.dev.favorit:1909/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Example of a call to a native method
        sample_text.text = stringFromJNI()
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.add -> {
                count = findViewById(R.id.add) as TextView
                Toast.makeText(this, count.toString(), Toast.LENGTH_LONG).show()
                //count.toString()
                return true
            }
            R.id.reset -> {
                count = findViewById(R.id.reset) as TextView
                Toast.makeText(this, count.toString(), Toast.LENGTH_LONG).show()
                //count.toString()
                return true
            }
            R.id.about -> {
                Toast.makeText(this, R.string.app_name, Toast.LENGTH_LONG).show()
                return true
            }
            R.id.exit -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun GetVersion(url: String): String {
        val obj = URL(url + "version")
        val con = obj.openConnection() as HttpURLConnection

        // optional default is GET
        con.requestMethod = "GET"

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT)

        val responseCode = con.responseCode
        System.out.println("\nSending 'GET' request to URL : $url")
        println("Response Code : $responseCode")

        var BufferedReader ins = new BufferedReader(
                new InputStreamReader(con.getInputStream())
        var inputLine: String
        val response = StringBuffer()

        while ((inputLine = `in`.readLine()) != null) {
            response.append(inputLine)
        }
        `in`.close()
        return response.toString()
    }

}

