package com.justeat.androiduitechtest

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData

class TasksRepository {

    private val activeTasks = mutableListOf<Task>()
    private val completedTasks = mutableListOf<Task>()

    private val activeTasksLiveData = MutableLiveData<List<Task>>()
    private val completedTasksLiveData = MutableLiveData<List<Task>>()

    fun getActiveTasks(): LiveData<List<Task>> {
        activeTasksLiveData.postValue(activeTasks)
        return activeTasksLiveData
    }

    fun getCompletedTasks(): LiveData<List<Task>> {
        completedTasksLiveData.postValue(completedTasks)
        return completedTasksLiveData
    }

    fun addTask(task: Task) {
        activeTasks.add(task)
        activeTasksLiveData.postValue(activeTasks)
    }

    fun removeTask(task: Task) {
        activeTasks.remove(task)
        completedTasks.remove(task)
        activeTasksLiveData.postValue(activeTasks)
        completedTasksLiveData.postValue(completedTasks)
    }

    fun completeTask(task: Task) {
        activeTasks.remove(task)
        completedTasks.add(task)
        activeTasksLiveData.postValue(activeTasks)
        completedTasksLiveData.postValue(completedTasks)
    }
}
