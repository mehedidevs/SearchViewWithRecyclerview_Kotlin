package com.es.searchviewwithrecyclerview_kotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CourseAdapter(
    var countrySelectListener: CountrySelectListener,
    private var courseModelArrayList: ArrayList<Course>,
    var context: Context
) :
    RecyclerView.Adapter<CourseAdapter.ViewHolder>() {

    // creating a variable for array list and context.


    // method for filtering our recyclerview items.
    fun filterList(filterList: ArrayList<Course>) {
        // below line is to add our filtered
        // list in our course array list.
        courseModelArrayList = filterList
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // below line is to inflate our layout.
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_course, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // setting data to our views of recycler view.
        val model: Course = courseModelArrayList[position]
        holder.courseNameTV.setText(model.getCourseName())
        holder.courseDescTV.setText(model.getCourseDescription())

        holder.courseNameTV.setOnClickListener {
            countrySelectListener.selectCountry(model)


        }
    }

    override fun getItemCount(): Int {
        // returning the size of array list.
        return courseModelArrayList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // creating variables for our views.
        lateinit var courseNameTV: TextView
        lateinit var courseDescTV: TextView

        init {
            // initializing our views with their ids.
            courseNameTV = itemView.findViewById(R.id.idTVCourseName)
            courseDescTV = itemView.findViewById(R.id.idTVCourseDescription)
        }
    }

    // creating a constructor for our variables.

}
