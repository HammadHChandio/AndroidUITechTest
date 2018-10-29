package com.justeat.androiduitechtest

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_add_task.view.*

class MainActivity : AppCompatActivity() {

    private val tasksRepository: TasksRepository = TasksRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        val tasksAdapter = TasksPagerAdapter(supportFragmentManager)

        view_pager.adapter = tasksAdapter
        tabs.setupWithViewPager(view_pager)

        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {
                //no-op
            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
                //no-op
            }

            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> add.show()
                    1 -> add.hide()
                }
            }
        })

        add.setOnClickListener {
            val view = LayoutInflater.from(this).inflate(R.layout.dialog_add_task, null, false)
            AlertDialog.Builder(this)
                .setTitle(R.string.add_task_title)
                .setMessage(R.string.add_task_subtitle)
                .setView(view)
                .setPositiveButton(R.string.add) { _, _ ->
                    val title = view.add_task.text.toString()
                    if (title.isNotBlank()) {
                        tasksRepository.addTask(Task(title))
                    }
                }
                .setNegativeButton(R.string.cancel) { dialog, _ -> dialog.dismiss() }
                .show()
        }
    }

    fun getTasksRepository() = tasksRepository
}
