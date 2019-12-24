package kr.co.calculator.ui.main

import android.view.View
import android.widget.TextView
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import java.util.*
import kotlin.collections.ArrayList

class MainViewModel : ViewModel() {
    var calculatorText: ObservableField<String> = ObservableField("")
    var finalResult: ObservableField<String> = ObservableField("")

    fun onAllClear() {
        calculatorText.set("")
        finalResult.set("")
    }

    fun onButton(view: View) {
        if (view is TextView) {
            calculatorText.set(calculatorText.get() + view.text)
        }

        finalResult.set(calculating(postFixExpression()).toString())
    }

    fun calculating(cals: ArrayList<String>): Int {
        val calStack: Stack<Int> = Stack()
        var finalResult = 0

        for (item in cals) {
            when (item) {
                "+", "-", "*", "/" -> {
                    if (finalResult == 0) {
                        finalResult = calStack.pop()
                    }

                    finalResult = operator(item, finalResult, calStack)
                }

                else -> {
                    item.toIntOrNull()?.let {
                        calStack.push(it)
                    }
                }
            }

        }

        return finalResult
    }

    fun operator(item: String, finalResult: Int, calStack: Stack<Int>): Int {
        var ret = 0

        if (calStack.isEmpty())
            return finalResult

        when (item) {
            "+" -> {
                ret = finalResult + calStack.pop()
            }
            "-" -> {
                ret = finalResult - calStack.pop()
            }
            "*" -> {
                ret = finalResult * calStack.pop()
            }
            "/" -> {
                ret = finalResult / calStack.pop()
            }
        }
        return ret
    }

    fun postFixExpression(): ArrayList<String> {
        val text = calculatorText.get().toString()
        val cal: Stack<Char> = Stack()
        val ret: ArrayList<String> = ArrayList()

        var addText = StringBuffer("")

        for (index in 0..text.length - 1) {
            when (text[index]) {
                '+', '-', '*', '/' -> {
                    cal.push(text[index])
                    ret.add(addText.toString())
                    addText = StringBuffer("")
                }
                in '0'..'9' -> {
                    addText.append(text[index])
                }
            }
        }

        if (addText.length > 0)
            ret.add(addText.toString())

        while (!cal.empty()) {
            ret.add(cal.pop().toString())
        }

        return ret
    }
}
