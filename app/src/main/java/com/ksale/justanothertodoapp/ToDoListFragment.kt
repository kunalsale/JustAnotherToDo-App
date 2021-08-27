package com.ksale.justanothertodoapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ToDoListFragment : Fragment() {

    lateinit var fabAddToDo: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_to_do_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fabAddToDo = view.findViewById(R.id.fabAddToDo)
        fabAddToDo.setOnClickListener {
            findNavController().navigate(R.id.action_toDoListFragment_to_addToDoFragment)
        }
    }
}