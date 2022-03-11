package com.wufanfirstkotlin.asynctask

import android.os.AsyncTask
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import com.wufanfirstkotlin.BaseActivity
import com.wufanfirstkotlin.R

class AsyncTaskActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async_task)

        var txt = findViewById<TextView>(R.id.textView)
        var button = findViewById<Button>(R.id.button)
        var progressBar = findViewById<ProgressBar>(R.id.progressBar)

        button.setOnClickListener {
            TimerTickLoad(txt,progressBar).execute(1000)
        }
    }

    /**
     *  ① task的实例必须在UI 线程中创建
     *  ② execute方法必须在UI线程中调用
     *  ③ 不要手动的调用doInBackground onPreExecute onProgressUpdate onPostExecute 这几个方法
     *  ④ 该task只能被执行一次，否则多次调用会出现异常
     */
    class TimerTickLoad : AsyncTask<Int, Int, String> {
        var textView: TextView
        var progressBar: ProgressBar


        constructor(textView: TextView, progressBar: ProgressBar) {
            this.progressBar = progressBar
            this.textView = textView
        }

        /**
         * 在onPreExecute（）后执行，该方法运行于后台中，主要负责很耗时的任务，可调用publishProgress()方法来更新任务的进度
         */
        override fun doInBackground(vararg params: Int?): String {
            var dos = DelayOperator()
            var i: Int = 0
            for (i in 10..100 step 10) {
                dos.delay()
                publishProgress(i)
            }
            return (i + params[0]!!.toInt()).toString()
        }

        /**
         * 再执行后台耗时操作之前调用，通常用作于一些初始化操作，比如这里的进度条的显示
         */
        override fun onPreExecute() {
            textView.text = "开始执行异步线程~"
        }

        /**
         * 在publishProgress(i)调用后被UI线程执行此方法，从界面上展示一个进度
         */
        override fun onProgressUpdate(vararg values: Int?) {
            var value = values[0]
            textView.text = "onProgressUpdate~"

            progressBar.progress = value!!
        }

        /**
         * 在doInBackground()方法执行完后执行，这个方法将被UI线程调用，后台的计算结果会通过该方法传递到UI线程，并且在界面显示给用户
         */
        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
        }

        /**
         * 在用户取消线程任务时调用，在主线程中调用onCancelled()时调用
         */
        override fun onCancelled() {
            super.onCancelled()
        }

    }

    class DelayOperator {
        fun delay() {
            Thread.sleep(1000)
        }
    }


}