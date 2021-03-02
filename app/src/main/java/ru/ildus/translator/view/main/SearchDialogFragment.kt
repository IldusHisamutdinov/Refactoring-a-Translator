package ru.ildus.translator.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputEditText
import ru.ildus.translator.databinding.SearchDialogFragmentBinding

class SearchDialogFragment : BottomSheetDialogFragment() {
    private lateinit var searchEditText: TextInputEditText
    private lateinit var searchButton: TextView
    private var onSearchClickListener: OnSearchClickListener? = null
    private var binding: SearchDialogFragmentBinding? = null

    private val onSearchButtonClickListener =
        View.OnClickListener {
            onSearchClickListener?.onClick(searchEditText.text.toString())
            dismiss()
        }

    internal fun setOnSearchClickListener(listener: OnSearchClickListener) {
        onSearchClickListener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = SearchDialogFragmentBinding.inflate(inflater).let {
        binding = it
        it.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchEditText = binding!!.searchEditText
        searchButton = binding!!.searchButtonTextview
        searchButton.setOnClickListener(onSearchButtonClickListener)
    }

    override fun onDestroyView() {
        onSearchClickListener = null
        super.onDestroyView()
        binding = null
    }

    interface OnSearchClickListener {
        fun onClick(searchWord: String)
    }

    companion object {
        fun newInstance(): SearchDialogFragment {
            return SearchDialogFragment()
        }
    }
}
