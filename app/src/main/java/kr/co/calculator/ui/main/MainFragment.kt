package kr.co.calculator.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.main_fragment.*
import kr.co.calculator.R

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        var binding: MainFragmentBinding =
//            DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
//        viewModel = ViewModelProviders.of(this@MainFragment).get(MainViewModel::class.java)
//        binding.viewmodel = viewModel

//        return binding.root

        return inflater.inflate(R.layout.main_fragment, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        number0.setOnClickListener(onClickListener)
        number1.setOnClickListener(onClickListener)
        number2.setOnClickListener(onClickListener)
        number3.setOnClickListener(onClickListener)
        number4.setOnClickListener(onClickListener)
        number5.setOnClickListener(onClickListener)
        number6.setOnClickListener(onClickListener)
        number7.setOnClickListener(onClickListener)
        number8.setOnClickListener(onClickListener)
        number9.setOnClickListener(onClickListener)

        cal_plus.setOnClickListener(onClickListener)
        cal_minus.setOnClickListener(onClickListener)
        cal_multi.setOnClickListener(onClickListener)
        cal_division.setOnClickListener(onClickListener)

        all_clean_btn.setOnClickListener {
            viewModel.onAllClear()
            brifing_tv.setText(viewModel.calculatorText)
            calculator.setText("")
        }
    }

    val onClickListener = View.OnClickListener {
        calculator.setText(viewModel.onButton(it).toString())

        brifing_tv.setText(viewModel.calculatorText)
    }

}
