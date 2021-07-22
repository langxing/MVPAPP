package com.example.application.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.application.*
import com.example.application.base.AbsBaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AbsBaseActivity<Contract.MainView, MainPresenterImpl>(), View.OnClickListener, Contract.MainView {
    private lateinit var mHeadView: HeadView
    private lateinit var mResultView: View
    private lateinit var mCbWuWenTi: CheckBox
    private lateinit var mCbYouSheCha: CheckBox
    private lateinit var mCbYanZhongSheCha: CheckBox
    private lateinit var mEditRemark: EditText

    private var resultCheckBox: CheckBox? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mHeadView = findViewById(R.id.headview)
        mHeadView.tvName.text = "印刷"
        mHeadView.tvNorm.text = "图案和字迹应整洁、清晰、不易脱落，色泽均匀一致"
        mHeadView.tvClaim.text = "目测"
        mResultView = LayoutInflater.from(this).inflate(R.layout.view_result_print, null)
        mEditRemark = mResultView.findViewById(R.id.editRemark)
        mCbWuWenTi = mResultView.findViewById(R.id.cbProblem)
        mCbYouSheCha = mResultView.findViewById(R.id.cbYSC)
        mCbYanZhongSheCha = mResultView.findViewById(R.id.cbSCYZ)
        mHeadView.setResultView(mResultView)
        mCbWuWenTi.setOnClickListener(this)
        mCbYouSheCha.setOnClickListener(this)
        mCbYanZhongSheCha.setOnClickListener(this)
        mHeadView.setOnClickListener(object: HeadView.OnClickListener {
            override fun onSaveClick() {
                log()
            }

            override fun onResetClick() {
                resetResult()
                log()
            }

            override fun onNotApplicableClick(isChecked: Boolean) {
                checkResult()
                log()
            }

        })
        tvNext.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
        getPresenter().loadData()
    }

    private fun getFactory(): ViewModelProvider.AndroidViewModelFactory {
        return ViewModelProvider.AndroidViewModelFactory.getInstance(application)
    }

    private fun checkResult(result: CheckBox? = null) {
        mCbWuWenTi.isChecked = false
        mCbYouSheCha.isChecked = false
        mCbYanZhongSheCha.isChecked = false
        result?.isChecked = true
        resultCheckBox = result
    }

    private fun resetResult() {
        mCbWuWenTi.isChecked = false
        mCbYouSheCha.isChecked = false
        mCbYanZhongSheCha.isChecked = false
        mEditRemark.text.clear()
    }

    private fun log() {
        Log.e("result", "result=${resultCheckBox?.text};remark=${mEditRemark.text}")
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.cbProblem -> checkResult(mCbWuWenTi)
            R.id.cbYSC -> checkResult(mCbYouSheCha)
            R.id.cbSCYZ -> checkResult(mCbYanZhongSheCha)
        }
    }

    override fun onComplete() {

    }

    override fun onSuccess(data: List<Int>) {
        data.forEach { item->
            Log.e("main", "----$item---")
        }
    }

    override fun onError(code: Int, msg: String) {

    }

    override fun showLoading() {
        Toast.makeText(this, "显示loading", Toast.LENGTH_SHORT).show()
    }

    override fun hideLoading() {
        Toast.makeText(this, "隐藏loading", Toast.LENGTH_SHORT).show()
    }

    override fun getPresenter(): MainPresenterImpl = MainPresenterImpl(this)

}