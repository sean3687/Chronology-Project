package com.tassiecomp.mychronology.ui.fragments

import android.app.AlertDialog
import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.tassiecomp.mychronology.App
import com.tassiecomp.mychronology.R
import com.tassiecomp.mychronology.models.HomeGrade
import com.tassiecomp.mychronology.ui.MainViewModel
import dev.sasikanth.colorsheet.ColorSheet
import dev.sasikanth.colorsheet.utils.ColorSheetUtils
import kotlinx.android.synthetic.main.create_subject_dialog.*
import kotlinx.android.synthetic.main.fragment_update.view.*


class UpdateFragment : Fragment(R.layout.fragment_update) {


    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mainViewModel: MainViewModel
    private var selectedColor: Int = ColorSheet.NO_COLOR


    companion object {
        private const val COLOR_SELECTED = "selectedColor"

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)




        view.apply {
            Update_cardTitle.setText(args.currentSubject.title)
            Update_description.setText(args.currentSubject.description)
            Update_min.setText(args.currentSubject.min.toString())
            Update_max.setText(args.currentSubject.max.toString())
            Update_Weighing.setText(args.currentSubject.weigthing)
            colorPicker_button.setText(args.currentSubject.color)


            topAppBar.setNavigationOnClickListener {
                findNavController().navigate(R.id.action_updateFragment_to_homeFragment)
            }
            topAppBar.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.save -> {
                        updateDataToDatabase()
                    }
                    R.id.delete -> {
                        deleteData()
                    }

                }
                true
            }
            colorPicker_button.setOnClickListener {
                setupColorSheet()
            }

        }

        return view
    }


    private fun updateDataToDatabase() {
        var title = Update_cardTitle.text.toString()
        var description = Update_description.text.toString()
        var weighing = Update_Weighing.text.toString()
        var min = Update_min.text
        var max = Update_max.text
        var color = colorPicker_button.text.toString()
        Log.d("TAGGG", "updated title$title")

        if (inputCheck(title, description, weighing, max!!, min!!)) {
            if (color == "Pick Color") {
                color = "#c2c2c2"
                Log.d("TAGGG", color)
            }

            val subject = HomeGrade(
                title,
                description,
                weighing,
                Integer.parseInt(max.toString()),
                Integer.parseInt(min.toString()),
                color
            )
            mainViewModel.updateSubject(subject)
            Toast.makeText(App.instance, "Saved", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_homeFragment)
        } else {
            Toast.makeText(App.instance, "Please fill out all fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun deleteData() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yse") { _, _ ->
            mainViewModel.deleteSubject(args.currentSubject)
            findNavController().navigate(R.id.action_updateFragment_to_homeFragment)
            Toast.makeText(
                requireContext(),
                "Suscessfully removed: ${args.currentSubject.title}",
                Toast.LENGTH_SHORT
            ).show()
        }
        builder.setNegativeButton("No") { _, _ ->
        }

        builder.setTitle("Delete ${args.currentSubject.title}?")
        builder.setMessage("Are you sure to delete ${args.currentSubject.title}")
        builder.create().show()
    }

    private fun setupColorSheet() {
        val colors = resources.getIntArray(R.array.colors)
        ColorSheet().cornerRadius(8)
            .colorPicker(

                colors = colors,
                selectedColor = selectedColor,
                listener = { color ->
                    selectedColor = color
                    setColor(selectedColor)
                })
            .show(childFragmentManager)

    }

    private fun setColor(@ColorInt color: Int) {
        displayColor.backgroundTintList = ColorStateList.valueOf(color)
        colorPicker_button.text = ColorSheetUtils.colorToHex(color)
    }

    private fun inputCheck(
        title: String,
        description: String,
        weighing: String,
        max: Editable,
        min: Editable,
    ): Boolean {
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(description) && weighing.isEmpty() && max.isEmpty() && min.isEmpty())
    }

}
