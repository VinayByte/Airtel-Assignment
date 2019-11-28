package com.test.airtel

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.test.airtel.adapter.AddressAdapter
import com.test.airtel.model.AddressListItem
import com.test.airtel.viewmodel.AddressViewModel
import com.test.airtel.viewmodel.AddressViewModelFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import javax.inject.Inject

/**
 * Created by VINAY on 2019-11-18.
 * vinay6kr@gmail.com
 */
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var addressViewModelFactory: AddressViewModelFactory

    lateinit var addressViewModel: AddressViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)
        setSupportActionBar(toolbar)


        addressViewModel = ViewModelProviders.of(this, addressViewModelFactory).get(
            AddressViewModel::class.java)

        progressBar.visibility = View.VISIBLE
        loadData()


        addressViewModel.addressResult().observe(this,
            Observer<List<AddressListItem>> {
                if (it != null) {
                    var addressAdapter = AddressAdapter(this, android.R.layout.simple_list_item_1, it)
                    autoCompleteTextView.setAdapter(addressAdapter)
                    autoCompleteTextView.threshold = 3
                }
            })

        addressViewModel.addressError().observe(this, Observer<String> {
            if (it != null) {
                Toast.makeText(this, resources.getString(R.string.error_message) + it,
                    Toast.LENGTH_SHORT).show()
            }
        })

        addressViewModel.addressLoader().observe(this, Observer<Boolean> {
            if (it == false) progressBar.visibility = View.GONE
        })


//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }



    fun loadData() {
        addressViewModel.loadAddress()
    }
    override fun onDestroy() {
        addressViewModel.disposeElements()
        super.onDestroy()
    }
}
