package com.example.note

//import dev.sasikanth.colorsheet.ColorSheet

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.nfc.NfcAdapter.EXTRA_ID
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.new_word_layout.*
import top.defaults.colorpicker.ColorPickerPopup
import top.defaults.colorpicker.ColorPickerPopup.ColorPickerObserver
import java.util.*


class NewWordActivity : AppCompatActivity() {

    private lateinit var editWordView: EditText
    private lateinit var descrp: EditText
    var tec : String = ""
    var colr : String = ""


    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_word_layout)
        editWordView = findViewById(R.id.word_edittext)
        descrp = findViewById(R.id.word_description)

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)


        date.setOnClickListener {
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                // Display Selected date in textbox
                //textView1.setText("" + dayOfMonth + "/ " + month + " /" + year)
                tec = "" + dayOfMonth + "/ " + month + " /" + year
            }, year, month, day)
            dpd.show()
        }
        color.setOnClickListener {
            ColorPickerPopup.Builder(this)
                .initialColor(Color.RED) // Set initial color
                .enableBrightness(true) // Enable brightness slider or not
                .enableAlpha(true) // Enable alpha slider or not
                .okTitle("Choose")
                .cancelTitle("Cancel")
                .showIndicator(true)
                .showValue(true)
                .build()
                .show( object : ColorPickerObserver() {
                    override fun onColorPicked(color: Int) {
                        editWordView.setBackgroundColor(color)
                        colr=color.toString()
//                        println(colr)
//                        println(colr.toString())
                    }

                    fun onColor(color: Int, fromUser: Boolean) {}
                })
         }
        val intent = intent

        if (intent.hasExtra(EXTRA_ID)) {
            title = "Edit Note"
            editWordView .setText(intent.getStringExtra(EXTRA_REPLY))
            descrp.setText(intent.getStringExtra(EXTRA_DESCRIPTION))

        } else {
            title = "Add Note"
        }


        val saveNoteBtn = findViewById<Button>(R.id.button_save_word)
        saveNoteBtn.setOnClickListener {
            checkAndSave()
        }
    }


    fun checkAndSave(){
        val replyIntent = Intent()
        val word = editWordView.text.toString()
        val description :String = descrp.text.toString()

        if (TextUtils.isEmpty(word)) {
            setResult(Activity.RESULT_CANCELED, replyIntent)
        } else {

            replyIntent.putExtra(EXTRA_REPLY, word)
            replyIntent.putExtra(EXTRA_DESCRIPTION,description );
            replyIntent.putExtra(EXTRA_DATE,tec);
            replyIntent.putExtra(EXTRA_COLOR,colr);
            setResult(Activity.RESULT_OK, replyIntent)
        }
        finish()

    }
   // val EXTRA_DESCRIPTION = "com.example.note.EXTRA_DESCRIPTION"

    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
        const val EXTRA_DESCRIPTION = "com.example.note.EXTRA_DESCRIPTION"
        const val EXTRA_DATE = "com.example.note.EXTRA_DATE"
        const val EXTRA_COLOR  = "com.example.note.EXTRA_COLOR"
    }

//    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
//    private fun colorize() {
//        val d = ShapeDrawable(OvalShape())
//        d.setBounds(58, 58, 58, 58)
//        d.paint.style = Paint.Style.FILL
//        d.paint.color = Constant.color
//        // button.setBackground(d)
//    }
}