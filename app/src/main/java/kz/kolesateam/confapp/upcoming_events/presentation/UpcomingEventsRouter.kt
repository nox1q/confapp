package kz.kolesateam.confapp.upcoming_events.presentation

import android.content.Context
import android.content.Intent
import kz.kolesateam.confapp.all_events.presentation.AllEventsActivity

object UpcomingEventsRouter {
    fun createBranchEventIntent(context: Context, id: Int, title: String): Intent {
        return Intent(context, AllEventsActivity::class.java).apply {
            putExtra(EXTRA_TITLE, title)
            putExtra(EXTRA_BRANCH_ID, id)
        }
    }
}