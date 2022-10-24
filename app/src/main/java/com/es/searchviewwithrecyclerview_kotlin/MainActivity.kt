package com.es.searchviewwithrecyclerview_kotlin

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.WindowManager
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.es.searchviewwithrecyclerview_kotlin.databinding.ActivityMainBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*


class MainActivity : AppCompatActivity(), CountrySelectListener {
    // creating variables for
    // our ui components.
    private lateinit var courseRV: RecyclerView

    // variable for our adapter
    // class and array list
    private lateinit var adapter: CourseAdapter
    private lateinit var courseModelArrayList: ArrayList<Course>
    private lateinit var binding: ActivityMainBinding

    private lateinit var bottomSheetDialog: BottomSheetDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.courseName.setOnClickListener {
            showBottomSheetDialog()

        }


    }


    private fun showBottomSheetDialog() {
        bottomSheetDialog = BottomSheetDialog(this)
        bottomSheetDialog.setContentView(R.layout.bottom_dialog_country)
        bottomSheetDialog.behavior.maxHeight = 1000 // set max height when expanded in PIXEL
        bottomSheetDialog.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        //  bottomSheetDialog.behavior.peekHeight = 400 // set default height when collapsed in PIXEL
        // val copy = bottomSheetDialog.findViewById<LinearLayout>(R.id.copyLinearLayout)
        val recyclerView = bottomSheetDialog.findViewById<RecyclerView>(R.id.courseRecyclerView)


        buildRecyclerView(recyclerView!!)
        val searchView = bottomSheetDialog.findViewById<SearchView>(R.id.searchText)

        searchView!!.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                // inside on query text change method we are
                // calling a method to filter our recycler view.
                filter(newText)
                return false
            }
        })



        bottomSheetDialog.show()


    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // below line is to get our inflater
        val inflater = menuInflater

        // inside inflater we are inflating our menu file.
        inflater.inflate(R.menu.search_menu, menu)

        // below line is to get our menu item.
        val searchItem = menu.findItem(R.id.actionSearch)

        // getting search view of our item.
        val searchView = searchItem.actionView as SearchView

        // below line is to call set on query text listener method.
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                // inside on query text change method we are
                // calling a method to filter our recycler view.
                filter(newText)
                return false
            }
        })
        return true
    }

    private fun filter(text: String) {
        // creating a new array list to filter our data.
        val filteredlist = ArrayList<Course>()

        // running a for loop to compare elements.
        for (item in courseModelArrayList) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.getCourseName().lowercase(Locale.ROOT)
                    .contains(text.lowercase(Locale.getDefault()))
            ) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item)
            }
        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show()
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            adapter.filterList(filteredlist)
        }
    }

    private fun buildRecyclerView(recyclerView: RecyclerView) {

        // below line we are creating a new array list
        courseModelArrayList = ArrayList<Course>()

        // below line is to add data to our array list.
        courseModelArrayList.add(Course("DSA", "DSA Self Paced Course"))
        courseModelArrayList.add(Course("JAVA", "JAVA Self Paced Course"))
        courseModelArrayList.add(Course("C++", "C++ Self Paced Course"))
        courseModelArrayList.add(Course("Python", "Python Self Paced Course"))
        courseModelArrayList.add(Course("Fork CPP", "Fork CPP Self Paced Course"))

        // initializing our adapter class.
        adapter = CourseAdapter(this, courseModelArrayList, this)

        // adding layout manager to our recycler view.
        val manager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        // setting layout manager
        // to our recycler view.
        recyclerView.layoutManager = manager

        // setting adapter to
        // our recycler view.
        recyclerView.adapter = adapter


    }

    override fun selectCountry(course: Course) {

        binding.courseName.text= course.getCourseName()
        bottomSheetDialog.dismiss()
        // bottomSheetDialog.cancel()

    }
}