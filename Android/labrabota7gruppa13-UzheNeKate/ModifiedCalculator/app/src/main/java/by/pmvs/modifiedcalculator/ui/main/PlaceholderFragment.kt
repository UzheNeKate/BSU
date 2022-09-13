package by.pmvs.modifiedcalculator.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import by.pmvs.modifiedcalculator.EntertainmentActivity
import by.pmvs.modifiedcalculator.R
import by.pmvs.modifiedcalculator.databinding.FragmentMainBinding
import kotlin.math.pow

/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment : Fragment() {
    var currentEditText: Int = 0;

    private var intent: Intent? = null

    private lateinit var inputs: List<EditText>
    private lateinit var inputsLabels: List<TextView>

    private lateinit var pageViewModel: PageViewModel
    private var _binding: FragmentMainBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProvider(this).get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
        intent = Intent(activity, EntertainmentActivity::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val root = binding.root
        inputsLabels = listOf(
            binding.tvA,
            binding.tvB,
            binding.tvC,
            binding.tvD,
            binding.tvX
        )
        inputs = listOf(
            binding.etInputA,
            binding.etInputB,
            binding.etInputC,
            binding.etInputD,
            binding.etInputX,
        )
        val index = pageViewModel.getIndex()
        val tvOperation = binding.tvOperation
        var numericButtons: List<Button> = listOf(
            binding.btNumber0,
            binding.btNumber1,
            binding.btNumber2,
            binding.btNumber3,
            binding.btNumber4,
            binding.btNumber5,
            binding.btNumber6,
            binding.btNumber7,
            binding.btNumber8,
            binding.btNumber9,
            binding.btSep,
        )
        for (bt in numericButtons) {
            bt.setOnClickListener {
                System.out.println("$currentEditText $inputs")
                inputs[currentEditText].text.append(bt.text)
            }
        }
        for ((i, et) in inputs.withIndex()) {
            et.showSoftInputOnFocus = false
            et.setOnFocusChangeListener { _, _ ->
                currentEditText = i
            }
        }
        binding.btEquals.setOnClickListener {
            val values = ArrayList<Double>()
            for (et in inputs) {
                val str = et.text.toString()
                val v = if (str != "") et.text.toString().toDouble() else 0.0
                values.add(v)
            }
            checkEaster(values)
            var res = 0.00
            val x = values[values.size - 1]
            for ((i, v) in values.withIndex()) {
                if (i > index + 1) {
                    break;
                }
                val power = index + 1 - i
                res += v * x.pow(power)
            }
            tvOperation.text = "y(x)=$res";
        }

        binding.btMinus.setOnClickListener {
            if (inputs[currentEditText].text.isEmpty()) {
                inputs[currentEditText].text.append("-")
            }
        }
        chooseShowing(index + 1)
        binding.btClear.setOnClickListener { clearInputs() }
        binding.btDelete.setOnClickListener {
            inputs[currentEditText].setText("")
        }

        return root
    }

    private fun checkEaster(values: java.util.ArrayList<Double>) {
        var easter = true
        for (x in values) {
            if (x != 0.00) {
                easter = false
                break
            }
        }
        if (easter) {
            startActivity(intent)
        }
    }

    private fun chooseShowing(start: Int) {
        //clearInputs()
        for (i in 0..start) {
            inputs[i].isVisible = true
            inputsLabels[i].isVisible = true
        }
        for (i in start until inputsLabels.size - 1) {
            inputs[i].isVisible = false
            inputsLabels[i].isVisible = false
        }
    }

    private fun clearInputs() {
        for (et in inputs) {
            et.setText("")
        }
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}