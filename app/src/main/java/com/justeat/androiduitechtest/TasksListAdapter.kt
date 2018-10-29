package com.justeat.androiduitechtest

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_task.view.*

class TasksListAdapter(private val clickListener: OnTaskClickListener = {}) :
    RecyclerView.Adapter<TasksListAdapter.TaskViewHolder>() {

    private val tasks = mutableListOf<Task>()

    fun updateWith(tasks: List<Task>) {
        this.tasks.clear()
        this.tasks.addAll(tasks)
        notifyDataSetChanged()
    }

    fun add(task: Task) {
        this.tasks.add(task)
        notifyItemInserted(tasks.size)
    }

    fun remove(task: Task) {
        this.tasks.remove(task)
        notifyItemRemoved(tasks.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder =
        TaskViewHolder(parent.inflate(R.layout.item_task), clickListener)

    override fun getItemCount(): Int = tasks.size

    override fun onBindViewHolder(viewHolder: TaskViewHolder, position: Int) = viewHolder.bind(tasks[position])

    class TaskViewHolder(val view: View, val clickListener: OnTaskClickListener) : RecyclerView.ViewHolder(view) {

        fun bind(task: Task) = with(view) {
            item_task_title.text = task.title
            setOnClickListener { clickListener(task) }
        }
    }

    private fun ViewGroup.inflate(layoutRes: Int): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, false)
    }

}

typealias OnTaskClickListener = (Task) -> Unit
