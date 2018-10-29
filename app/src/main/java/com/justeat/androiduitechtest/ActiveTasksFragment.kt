package com.justeat.androiduitechtest

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class ActiveTasksFragment : Fragment() {

    private lateinit var tasksAdapter: TasksListAdapter
    private lateinit var tasksRepository: TasksRepository

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val list = inflater.inflate(R.layout.fragment_active_tasks, container, false) as RecyclerView
        tasksAdapter = TasksListAdapter { task ->
            TaskOptionsDialogFragment().setListener(
                onComplete = {
                    tasksRepository.completeTask(task)
                },
                onDelete = {
                    tasksRepository.removeTask(task)
                }
            )
                .show(fragmentManager, "TaskOptions")
        }

        list.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = tasksAdapter
        }
        return list
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tasksRepository = (requireActivity() as MainActivity).getTasksRepository()
        tasksRepository.getActiveTasks().observe(this, Observer { tasks -> tasks?.let { tasksAdapter.updateWith(it) } })
    }
}
