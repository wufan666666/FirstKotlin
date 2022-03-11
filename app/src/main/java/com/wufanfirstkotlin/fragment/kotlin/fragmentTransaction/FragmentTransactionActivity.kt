package com.wufanfirstkotlin.fragment.kotlin.fragmentTransaction

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.wufanfirstkotlin.R
import com.wufanfirstkotlin.himalaya.utils.L

/**
 * ① 创建一个待处理的fragment
 * ② 通过supportFragmentManager获取fragmentManager
 * ③ 通过fragmentManager获取beginTransaction
 * ④ 通过beginTransaction的replace方法替换fragment
 * ⑤ 提交 commit
 *
 */
class FragmentTransactionActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_transaction)

        var change = findViewById<Button>(R.id.change)
        var cancel = findViewById<Button>(R.id.cancel)

        change.setOnClickListener(this)
        cancel.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.cancel -> {
                var itemFragment = ItemFragment()
                itemFragment.setIFragmentCallback(object :IFragmentCallback{
                    override fun sendMsg(msg: String) {
                        Toast.makeText(applicationContext,msg,Toast.LENGTH_SHORT).show()
                        L.e("ItemFragment",msg)
                    }

                    override fun receiveMsg(): String {
                        return "i am from activity"
                    }

                })
                changeFragment(itemFragment)
            }
            R.id.change -> {
                var bundle = Bundle().apply {
                    putString("key", "value")
                }
                var fragment = TransactionFragment1()
                fragment.arguments = bundle
                changeFragment(fragment)
            }
        }
    }

    private fun changeFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        var beginTransaction = fragmentManager.beginTransaction()
        beginTransaction.replace(R.id.framelayout, fragment)
        beginTransaction.addToBackStack(null)//回退fragment栈
        beginTransaction.commit()

    }
}