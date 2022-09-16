package com.example.test_nfc.ui.card

import android.app.PendingIntent
import android.content.Intent
import android.content.IntentFilter
import android.nfc.*
import android.nfc.tech.Ndef
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.test_nfc.databinding.FragmentCardBinding
import java.io.IOException
import java.io.UnsupportedEncodingException

class cardFragment : Fragment() {

    private var _binding: FragmentCardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val cardViewModel =
            ViewModelProvider(this).get(cardViewModel::class.java)
        super.onCreate(savedInstanceState)
        _binding = FragmentCardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}