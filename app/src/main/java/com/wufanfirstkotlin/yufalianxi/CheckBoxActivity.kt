package com.wufanfirstkotlin.yufalianxi

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.Toast
import com.wufanfirstkotlin.R
import com.wufanfirstkotlin.Service.VolumeService

class CheckBoxActivity : AppCompatActivity() {

    lateinit var swimming: CheckBox
    lateinit var basketball: CheckBox
    lateinit var football: CheckBox
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_box)

        swimming = findViewById(R.id.box_sw)
        basketball = findViewById(R.id.box_bas)
        football = findViewById(R.id.box_foot)

        /*swimming.setOnClickListener(this)
        basketball.setOnClickListener(this)
        football.setOnClickListener(this)*/
        /*swimming.setOnCheckedChangeListener(this)
        basketball.setOnCheckedChangeListener(this)*/
        /*stopService(Intent(this, VolumeService::class.java))
        startService(Intent(this, VolumeService::class.java))
        startService(Intent(this, VolumeService::class.java))*/

        basketball.setOnCheckedChangeListener { buttonView, isChecked -> if (basketball.isChecked) Toast.makeText(applicationContext, "choice "+basketball.text +" has picked", Toast.LENGTH_SHORT)
            .show() }
        swimming.setOnCheckedChangeListener { buttonView, isChecked -> if (swimming.isChecked) Toast.makeText(applicationContext, "choice "+swimming.text +" has picked", Toast.LENGTH_SHORT)
            .show() }
        football.setOnCheckedChangeListener { buttonView, isChecked -> if (football.isChecked) Toast.makeText(applicationContext, "choice "+football.text +" has picked", Toast.LENGTH_SHORT)
            .show() }
    }

    /*

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {


        when {
            swimming.isChecked -> {
                Toast.makeText(applicationContext, "choice "+swimming.text +" has picked", Toast.LENGTH_SHORT)
                    .show()
            }
            basketball.isChecked -> {
                Toast.makeText(applicationContext, "choice "+basketball.text +"has picked", Toast.LENGTH_SHORT)
                    .show()
            }
            football.isChecked -> {
                Toast.makeText(applicationContext, "choice "+football.text +" has picked", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }*/

    /*override fun onClick(v: View?) {
        when(v?.id){
            R.id.box_sw -> Toast.makeText(applicationContext,"choice swimming",Toast.LENGTH_SHORT).show()
            R.id.box_foot -> Toast.makeText(applicationContext,"choice football",Toast.LENGTH_SHORT).show()
            R.id.box_bas -> Toast.makeText(applicationContext,"choice basketball",Toast.LENGTH_SHORT).show()
        }
    }*/
}