package com.test.airtel.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.annotation.LayoutRes
import com.test.airtel.model.AddressListItem

/**
 * Created by VINAY on 2019-11-18.
 * vinay6kr@gmail.com
 */
 class AddressAdapter(context: Context, @LayoutRes private val layoutResource: Int, private val listItem: List<AddressListItem>):
    ArrayAdapter<AddressListItem>(context, layoutResource, listItem),
    Filterable {
    private var addresslist: List<AddressListItem> = listItem

    override fun getCount(): Int {
        return addresslist.size
    }

    override fun getItem(p0: Int): AddressListItem? {
        return addresslist.get(p0)
    }

     override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
         val view: TextView = convertView as TextView? ?: LayoutInflater.from(context).inflate(layoutResource, parent, false) as TextView
        view.text = "${addresslist[position].addressString} ${addresslist[position].city} (${addresslist[position].pinCode})"
        return view
     }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun publishResults(charSequence: CharSequence?, filterResults: Filter.FilterResults) {
                addresslist = filterResults.values as List<AddressListItem>
                notifyDataSetChanged()
            }

            override fun performFiltering(charSequence: CharSequence?): Filter.FilterResults {
                val queryString = charSequence?.toString()?.toLowerCase()

                val filterResults = Filter.FilterResults()
                filterResults.values = if (queryString==null || queryString.isEmpty())
                    listItem
                else
                    listItem.filter {
                        it.addressString!!.toLowerCase().contains(queryString) ||
                                it.city!!.toLowerCase().contains(queryString) ||
                                it.pinCode!!.toLowerCase().contains(queryString)
                    }
                return filterResults
            }
        }
    }
}