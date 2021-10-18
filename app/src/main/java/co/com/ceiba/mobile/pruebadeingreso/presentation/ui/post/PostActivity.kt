package co.com.ceiba.mobile.pruebadeingreso.presentation.ui.post

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import co.com.ceiba.mobile.pruebadeingreso.R
import co.com.ceiba.mobile.pruebadeingreso.common.Failure
import co.com.ceiba.mobile.pruebadeingreso.common.ResourceState
import co.com.ceiba.mobile.pruebadeingreso.data.Constants
import co.com.ceiba.mobile.pruebadeingreso.data.Constants.USER_ID
import co.com.ceiba.mobile.pruebadeingreso.databinding.ActivityPostBinding
import co.com.ceiba.mobile.pruebadeingreso.domain.model.PostModel
import co.com.ceiba.mobile.pruebadeingreso.domain.model.UserModel
import co.com.ceiba.mobile.pruebadeingreso.presentation.ui.util.showSnackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostActivity : AppCompatActivity(), OnEmptyListListener {

    private val postViewModel: PostViewModel by viewModels()
    private var viewBinding: ActivityPostBinding? = null
    private var listAdapter: PostListAdapter? = null
    private var userId = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityPostBinding.inflate(layoutInflater)
        setContentView(viewBinding!!.root)
        userId = intent.getIntExtra(USER_ID, 1)
        setupListAdapter()
        userLiveDateObserver()
        postsLiveDateObserver()
        postViewModel.getUserById(userId)
        postViewModel.getPostsById(userId)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewBinding = null
    }

    private fun userLiveDateObserver() = postViewModel.userLiveData.observe(this, Observer {
        it?.let {
            when (it.state) {
                ResourceState.SUCCESS -> {
                    setUser(it.data!!)
                }
                ResourceState.ERROR -> {
                    it.failure?.let { showErrorInSnackBar(it) }
                }
                else -> {
                }
            }
        }
    })

    private fun postsLiveDateObserver() = postViewModel.postsLiveData.observe(this, Observer {
        it?.let {
            when (it.state) {
                ResourceState.LOADING -> {
                    showProgressBar()
                }
                ResourceState.SUCCESS -> {
                    hideProgressBar()
                    updatePosts(it.data!!)
                }
                ResourceState.ERROR -> {
                    hideProgressBar()
                    it.failure?.let { showErrorInSnackBar(it) }
                }
            }
        }
    })

    private fun setUser(user: UserModel) {
        val userView = postViewModel.convertUserModelToUserView(user)
        viewBinding?.name?.text = userView.name
        viewBinding?.phone?.text = userView.phone
        viewBinding?.email?.text = userView.email
    }

    private fun updatePosts(posts: List<PostModel>) {
        listAdapter?.modifyList(postViewModel.convertPostModelsToPostViews(posts))
    }

    private fun showErrorInSnackBar(failure: Failure) {
        val message =
            if (failure is Failure.Error) failure.errorMessage else Constants.UNKNOWN_ERROR
        showSnackbar(viewBinding?.root!!, "Error: $message", this)
    }

    private fun showProgressBar() {
        viewBinding?.postsProgressBar?.visibility = View.VISIBLE
        viewBinding?.notificationText?.visibility = View.VISIBLE
        viewBinding?.notificationText?.text = getString(R.string.generic_message_progress)
    }

    private fun hideProgressBar() {
        viewBinding?.postsProgressBar?.visibility = View.GONE
        viewBinding?.notificationText?.visibility = View.GONE
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

    private fun setupListAdapter() {
        listAdapter = PostListAdapter(this)
        viewBinding?.recyclerViewPostsResults?.adapter = listAdapter
    }
}