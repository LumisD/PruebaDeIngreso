package co.com.ceiba.mobile.pruebadeingreso.presentation.ui.main

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import co.com.ceiba.mobile.pruebadeingreso.R
import co.com.ceiba.mobile.pruebadeingreso.common.Failure
import co.com.ceiba.mobile.pruebadeingreso.common.ResourceState
import co.com.ceiba.mobile.pruebadeingreso.data.Constants.UNKNOWN_ERROR
import co.com.ceiba.mobile.pruebadeingreso.data.Constants.USER_ID
import co.com.ceiba.mobile.pruebadeingreso.databinding.ActivityMainBinding
import co.com.ceiba.mobile.pruebadeingreso.domain.model.UserModel
import co.com.ceiba.mobile.pruebadeingreso.presentation.ui.post.PostActivity
import co.com.ceiba.mobile.pruebadeingreso.presentation.ui.util.showSnackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnUserClickListener, OnEmptyListListener {

    private val mainViewModel: MainViewModel by viewModels()
    private var viewBinding: ActivityMainBinding? = null
    private var listAdapter: UserListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding!!.root)
        setupListAdapter()
        filterUsers()
        usersLiveDateObserver()
        mainViewModel.getUsers()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewBinding = null
    }

    private fun usersLiveDateObserver() = mainViewModel.usersLiveData.observe(this, Observer {
        it?.let {
            when (it.state) {
                ResourceState.LOADING -> {
                    showProgressBar()
                }
                ResourceState.SUCCESS -> {
                    hideProgressBar()
                    updateUsers(it.data!!)
                }
                ResourceState.ERROR -> {
                    hideProgressBar()
                    it.failure?.let { showErrorInSnackBar(it) }
                }
            }
        }
    })

    private fun updateUsers(users: List<UserModel>) {
        listAdapter?.modifyList(mainViewModel.convertUserModelsToUserViews(users))
    }

    private fun filterUsers() {
        viewBinding?.editTextSearch?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                listAdapter?.filter(s)
            }
        })
    }

    private fun showErrorInSnackBar(failure: Failure) {
        val message = if (failure is Failure.Error) failure.errorMessage else UNKNOWN_ERROR
        showSnackbar(viewBinding?.root!!, "Error: $message", this, mainViewModel::getUsers)
    }

    private fun showProgressBar() {
        viewBinding?.usersProgressBar?.visibility = View.VISIBLE
        viewBinding?.notificationText?.visibility = View.VISIBLE
        viewBinding?.notificationText?.text = getString(R.string.generic_message_progress)
    }

    private fun hideProgressBar() {
        viewBinding?.usersProgressBar?.visibility = View.GONE
        viewBinding?.notificationText?.visibility = View.GONE
    }

    private fun setupListAdapter() {
        listAdapter = UserListAdapter(this, this)
        viewBinding?.recyclerViewSearchResults?.adapter = listAdapter
    }

    override fun onItemClicked(userId: Int) {
        val i = Intent(this, PostActivity::class.java)
        i.putExtra(USER_ID, userId)
        startActivity(i)
    }

    override fun onEmptyList() {
        viewBinding?.notificationText?.visibility = View.VISIBLE
        viewBinding?.notificationText?.text = getString(R.string.list_is_empty)
    }

    override fun onNotEmptyList() {
        if (viewBinding?.notificationText?.visibility == View.VISIBLE) {
            viewBinding?.notificationText?.visibility = View.GONE
        }
    }
}