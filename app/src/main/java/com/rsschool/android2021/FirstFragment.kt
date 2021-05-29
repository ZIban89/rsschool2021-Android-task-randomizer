package com.rsschool.android2021

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.Toast

class FirstFragment : Fragment() {

    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    private lateinit var minField: EditText
    private lateinit var maxField: EditText
    private lateinit var mainActivity: IMainActivity


    override fun onAttach(cont: Context) {
        super.onAttach(cont)
        if (cont is IMainActivity) {
            mainActivity = cont
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)
        minField = view.findViewById(R.id.min_value)
        maxField = view.findViewById(R.id.max_value)
        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = "Previous result: ${result.toString()}"

        generateButton?.setOnClickListener {
            val min: Long
            val max: Long
            when {
                (minField.text.isEmpty() || maxField.text.isEmpty()) -> makeToast(R.string.empty_field)
                (minField.text.length > 10 || maxField.text.length > 10) -> makeToast(R.string.numb_too_much)
                else -> {
                    min = minField.text.toString().toLong()
                    max = maxField.text.toString().toLong()
                    when {
                        (min > max) -> makeToast(R.string.min_greater_max)
                        (max > Int.MAX_VALUE) -> makeToast(R.string.numb_too_much)
                        else -> mainActivity.onGenerateBtnPressed(min.toInt(), max.toInt())
                    }
                }
            }
        }
    }


    private fun makeToast(message: Int) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
    }

    companion object {

        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putInt(PREVIOUS_RESULT_KEY, previousResult)
            fragment.arguments = args
            return fragment
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }


}