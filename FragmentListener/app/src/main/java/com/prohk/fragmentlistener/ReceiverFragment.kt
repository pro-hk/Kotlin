package com.prohk.fragmentlistener

import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import com.prohk.fragmentlistener.databinding.FragmentReceiverBinding

class ReceiverFragment : Fragment() {

    lateinit var binding: FragmentReceiverBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReceiverBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFragmentResultListener("request") { key, bundle -> // 수신준비
            bundle.getString("senderKey")?.let {
                binding.textView.text = it
            }
        }
    }
}