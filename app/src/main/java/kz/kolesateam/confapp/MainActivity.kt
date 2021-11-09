package kz.kolesateam.confapp

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kz.kolesateam.confapp.di.SHARED_PREFS_DATA_SOURCE
import kz.kolesateam.confapp.upcoming_events.domain.UserDataSource
import kz.kolesateam.confapp.upcoming_events.presentation.UpcomingEventsActivity
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named

class MainActivity : AppCompatActivity() {

    private val userDataSource: UserDataSource by inject(named(SHARED_PREFS_DATA_SOURCE))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val goToUpcomingEventsActivityButton = findViewById<Button>(R.id.hello_activity_button)
        val enteredName: EditText = findViewById(R.id.entered_name)

        enteredName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, cound: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val isInputEmpty: Boolean = s.toString().isBlank()
                goToUpcomingEventsActivityButton.isEnabled = !isInputEmpty
            }
        })

        goToUpcomingEventsActivityButton.setOnClickListener() {
            saveUserName(enteredName.text.toString())
            navigateToUpcomingEventsActivity()
        }
    }

    private fun saveUserName(userName: String) {
        userDataSource.saveUserName(userName)
    }

    private fun navigateToUpcomingEventsActivity() {
        val activityIntent = Intent(this, UpcomingEventsActivity::class.java)
        startActivity(activityIntent)
    }
}